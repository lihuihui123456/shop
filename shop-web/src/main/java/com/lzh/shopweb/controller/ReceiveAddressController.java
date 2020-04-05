package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopweb.facade.ReceiveAddressFacade;
import com.lzh.shopweb.facade.UserFacade;
import com.lzh.shopweb.vo.ReceiveAddressView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址管理
 */
@Controller
@RequestMapping("/receiveAddress")
public class ReceiveAddressController extends BaseController{

    @Autowired
    private ReceiveAddressFacade receiveAddressFacade;
    @Autowired
    private UserFacade userFacade;

    /**
     * 收货地址列表
     * @param uiModel
     * @return
     */
    @RequestMapping("/listReceiveAddress.action")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void listReceiveAddress(Model uiModel,HttpServletResponse response)  throws Exception{
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("userId", getCurrentUser().getId());
        List<ReceiveAddressView> receiveAddressViewList = receiveAddressFacade.listReceiveAddress(filters);
        JSONObject data = new JSONObject();
        data.put("list",receiveAddressViewList);
        this.response(response, data.toJSONString());
    }

    /**
     * 进入新增、修改页面
     * @param receiveAddressId
     * @param uiModel
     * @return
     */
    @RequestMapping("/settingReceiveAddress.action")
    public void settingReceiveAddress(Long receiveAddressId, Model uiModel,HttpServletResponse response) throws Exception{
        ReceiveAddressView receiveAddressView;
        if (receiveAddressId != null) {
            receiveAddressView = receiveAddressFacade.getReceiveAddress(receiveAddressId);
        } else{
            receiveAddressView=new ReceiveAddressView();
        }
        JSONObject data = new JSONObject();
        data.put("data",receiveAddressView);
        this.response(response, JSONArray.toJSONString(data));
    }

    /**
     * 保存新增、修改
     * @param address
     * @param tel
     * @param consignee
     * @param id
     * @param uiModel
     * @return
     */
    @RequestMapping("/saveReceiveAddress.action")
    public  @ResponseBody
    void saveReceiveAddress(HttpServletResponse response,String address, String tel, String consignee, Long id, boolean defaultAddress, Model uiModel){
        try{
        ReceiveAddressView receiveAddressView;
        if(id!=null){
            receiveAddressView=receiveAddressFacade.getReceiveAddress(id);
        }else{
            receiveAddressView=new ReceiveAddressView();
        }
        receiveAddressView.setTel(tel);
        receiveAddressView.setAddress(address);
        receiveAddressView.setConsignee(consignee);
        receiveAddressView.setPartyId(getCurrentUser().getId());
        receiveAddressView.setDefaultAddress(defaultAddress);
        // receiveAddressFacade. saveOrUpdateReceiveAddress(receiveAddressView);
         this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
         }catch (Exception e) {
        e.printStackTrace();
        this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
       }
    }

    /**
     * 删除
     * @param id
     * @param uiModel
     * @return
     */
    @RequestMapping("/deleteReceiveAddress.action")
    public @ResponseBody void deleteReceiveAddress(HttpServletResponse response,Long id,Model uiModel){
        try{
            receiveAddressFacade.deleteReceiveAddress(id);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
          }catch (Exception e) {
        e.printStackTrace();
        this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
    }
    }

    @RequestMapping("/setDefaultAddress.action")
    public @ResponseBody void setDefaultAddress(HttpServletResponse response,Long id,Model uiMode){
        try{
                 receiveAddressFacade. setDefaultAddress(id);
            }catch (Exception e) {
             e.printStackTrace();
              this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
            }
    }
}
