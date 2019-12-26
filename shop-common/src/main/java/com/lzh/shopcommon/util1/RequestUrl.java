package com.lzh.shopcommon.util1;

public class RequestUrl {


    //询价模块-B2B
    public static final String[] B2B_ASKPRICE = new String[]{
            "/listLittleVAskPriceOrder.action",//sales/askPriceOrder/listLittleVAskPriceOrder.action
            "/littleVqueryAskPriceOrder.action",//sales/askPriceOrder/littleVqueryAskPriceOrder.action
            "/bigVqueryAskPriceOrder.action"//sales/askPriceOrder/bigVqueryAskPriceOrder.action
    };

    //线上销售模块-B2B
    public static final String[] B2B_SALES_ORDER_ONLINE = new String[]{
            "/listConfirmGgcSalesOrder.action",//mggc/ggcSalesOrder/listConfirmGgcSalesOrder.action
            "/queryGgcSalesOrderHeader.action",//ggc/ggcSalesOrder/queryGgcSalesOrderHeader.action
            "/listConfirmGGCSalesReturn.action",//sales/ggcReturn/GGC/listConfirmGGCSalesReturn.action
            "/queryGGCSalesReturnHeader.action"//sales/ggcReturn/GGC/query/queryGGCSalesReturnHeader.action
    };

    //线下销售模块-B2B
    public static final String[] B2B_SALES_ORDER_OFFLIN = new String[]{
            "/directSalesList.action",///ggc/ggcSalesOrder/directSalesList.action
            "/directReturnList.action",//ggc/ggcSalesOrder/directReturnList.action
    };

    //采购模块-B2B
    public static final String[] B2B_PURCHASE_ORDER = new String[]{
            "/innerPurchaseOrder.action",///InnerPurchaseController/innerPurchaseOrder.action
            "/innerPurchaseReturnOrder.action",//InnerPurchaseController/innerPurchaseReturnOrder.action
            "/queryM2VSalesOrderHeader.action",///m2v/m2vSalesOrder/queryM2VSalesOrderHeader.action
            "/queryM2vReturnOrderHeader.action",//m2v/m2vReturnOrder/queryM2vReturnOrderHeader.action
            "/chainPurchaseOrderHeader.action",//purchase/order/chain/RDC/search/chainPurchaseOrderHeader.action
            "/queryV2bReturnOrderHeader.action",//purchase/return/chain/RDC/query/queryV2bReturnOrderHeader.action
            "/queryM2VSalesOrderHeaderForDv.action",//m2v/m2vSalesOrder/queryM2VSalesOrderHeaderForDv.action
            "/queryM2vReturnOrderHeaderForDv.action"//m2v/m2vReturnOrder/queryM2vReturnOrderHeaderForDv.action
    };


    //销售-MOBILE
    public static final String[] M2V_MOBILE_SALES_ORDER = new String[]{
            "/listOrder.mpi",///mAutozi/order/listOrder.mpi
    };

    //询价-MOBILE
    public static final String[] M2V_MOBILE_ASKPRICE = new String[]{
            "/listAskOrder.mpi"//mAutozi/b2rAskPriceOrder/listAskOrder.mpi
    };


    //询价-B2C
    public static final String[] B2C_ASKPRICE = new String[]{
            "/askPriceOrderList.do"//askPriceOrder/askPriceOrderList.do
    };

    //我的订单-B2C
    public static final String[] B2C_SALES_ORDER = new String[]{
            "/orderCenter.do",//orderCenter/orderCenter.do
            "/waitPayOrder.do",//orderCenter/waitPayOrder.do
            "/waitPayOrderOnline.do",//orderCenter/waitPayOrderOnline.do
            "/waitApplyLoan.do"//orderCenter/waitApplyLoan.do
    };


}
