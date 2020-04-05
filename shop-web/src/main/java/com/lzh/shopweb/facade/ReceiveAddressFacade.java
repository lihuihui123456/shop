package com.lzh.shopweb.facade;

import com.lzh.shopentity.ReceiveAddress;
import com.lzh.shopservice.ReceiveAddressManager;
import com.lzh.shopweb.vo.ReceiveAddressView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReceiveAddressFacade {
    @Autowired
    private ReceiveAddressManager receiveAddressManager;

    public ReceiveAddress convertToEntity(ReceiveAddressView receiveAddressView) {
        ReceiveAddress receiveAddress = new ReceiveAddress();
        BeanUtils.copyProperties(receiveAddressView, receiveAddress);
        return receiveAddress;
    }

    public ReceiveAddressView convertToView(ReceiveAddress receiveAddress) {
        ReceiveAddressView receiveAddressView = new ReceiveAddressView();
        BeanUtils.copyProperties(receiveAddress, receiveAddressView);
        if (receiveAddress.isDefaultAddress()) {
            receiveAddressView.setDefaultAddressStr("默认");
        }
        return receiveAddressView;
    }

    public List<ReceiveAddressView> convertToView(List<ReceiveAddress> receiveAddressList) {
        List<ReceiveAddressView> receiveAddressViewList = new ArrayList<ReceiveAddressView>();
        for (ReceiveAddress receiveAddress : receiveAddressList) {
            receiveAddressViewList.add(convertToView(receiveAddress));
        }
        return receiveAddressViewList;
    }

    public List<ReceiveAddressView> listReceiveAddress(Map<String, Object> filters) {
        return convertToView(receiveAddressManager.listReceiveAddress(filters));
    }
    public Map<String,String> findListForMapByPartyId(Map<String, Object> filters) {
        List<ReceiveAddress> addresses = receiveAddressManager.findListForMapByPartyId(filters);
        Map<String,String> map = new HashMap<String,String>();
        for (ReceiveAddress receiveAddress:addresses){
            map.put(receiveAddress.getId()+"",receiveAddress.getAddress()+"");
        }
        return map;
    }

    /**
     * 获取一个收货地址
     *
     * @param receiveAddressId
     * @return
     */
    public ReceiveAddressView getReceiveAddress(long receiveAddressId) {
        return convertToView(receiveAddressManager.getReceiveAddress(receiveAddressId));
    }

    /**
     * 删除收货地址
     *
     * @param receiveAddressId
     * @return
     */
    public boolean deleteReceiveAddress(long receiveAddressId) {
        return receiveAddressManager.deleteReceiveAddress(receiveAddressId)>0;
    }


    public boolean  setDefaultAddress(long receiveAddressId){
        return receiveAddressManager.setDefaultAddress(receiveAddressId);
    }
}
