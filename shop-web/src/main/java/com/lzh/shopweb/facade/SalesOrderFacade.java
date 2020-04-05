package com.lzh.shopweb.facade;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.SalesOrder;
import com.lzh.shopentity.SalesOrderHeader;
import com.lzh.shopservice.SalesOrderHeaderManager;
import com.lzh.shopservice.SalesOrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SalesOrderFacade {
    @Autowired
    private SalesOrderManager salesOrderManager;
    @Autowired
    private SalesOrderHeaderManager salesOrderHeaderManager;

    public SalesOrder getSalesOrder(Long salesOrderId) {
        return salesOrderManager.getSalesOrder(salesOrderId);
    }

    public void insert(SalesOrder salesOrder) {
        salesOrderManager.insert(salesOrder);
    }

    public Page<SalesOrder> listSalesOrder(Page<SalesOrder> page, Map<String, Object> filters) {
        return salesOrderManager.listSalesOrder(page, filters);
    }
    public List<SalesOrder> listSalesOrder(Map<String, Object> filters) {
        return salesOrderManager.listSalesOrder(filters);
    }
    public void update(SalesOrder salesOrder) {
        salesOrderManager.update(salesOrder);
    }



    public SalesOrderHeader getSalesOrderHeader(Long salesOrderHeaderId) {
        return salesOrderHeaderManager.getSalesOrderHeader(salesOrderHeaderId);
    }

    public void insert(SalesOrderHeader salesOrderHeader) {
        salesOrderHeaderManager.insert(salesOrderHeader);
    }

    public Page<SalesOrderHeader> listSalesOrderHeader(Page<SalesOrderHeader> page, Map<String, Object> filters) {
        return salesOrderHeaderManager.listSalesOrderHeader(page, filters);
    }
    public void update(SalesOrderHeader salesOrderHeader) {
        salesOrderHeaderManager.update(salesOrderHeader);
    }

    /**
     * 根据购物车信息生成订单
     * @param cartOrderIds  购物车清单
     * @param recevieAddressId 收货人信息
     * @param messege  备注信息
     */
    public void createSalesOrder(List<Long> cartOrderIds,Long recevieAddressId,String messege,Double coupon){
        salesOrderManager.createSalesOrder(cartOrderIds,recevieAddressId,messege,coupon);
    }
    public  void cancelSalesOrder(Long salesOrderHeaderId){
        salesOrderManager.cancelSalesOrder(salesOrderHeaderId);
    }
    public  void deleteSalesOrder(Long salesOrderHeaderId){
        salesOrderManager.cancelSalesOrder(salesOrderHeaderId);
    }
}
