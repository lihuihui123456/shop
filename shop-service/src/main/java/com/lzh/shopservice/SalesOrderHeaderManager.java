package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.SalesOrderHeaderDao;
import com.lzh.shopentity.SalesOrderHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单头管理
 */
@Service
public class SalesOrderHeaderManager {
    @Autowired
    private SalesOrderHeaderDao salesOrderHeaderDao;
    public SalesOrderHeader getSalesOrderHeader(Long salesOrderHeaderId) {
        return salesOrderHeaderDao.get(salesOrderHeaderId);
    }

    public void insert(SalesOrderHeader salesOrderHeader) {
        salesOrderHeaderDao.insert(salesOrderHeader);
    }

    public Page<SalesOrderHeader> listSalesOrderHeader(Page<SalesOrderHeader> page, Map<String, Object> filters) {
        return salesOrderHeaderDao.findPage(page,"", filters);
    }
    public void update(SalesOrderHeader salesOrderHeader) {
        salesOrderHeaderDao.update(salesOrderHeader);
    }
}
