package com.lzh.shopcommon;


public interface MobileConstant {
    public interface PageSize{
        int _page_size = 10;
    }

    public int SOLR_QUERY_ALL = 0;
    public int SOLR_QUERY_BRAND = 1;
    public int SOLR_QUERY_CATEGORY = 2;
    public int SOLR_QUERY_ATTRIBUTE = 3;
    public int SOLR_QUERY_GOODS = 4;
    public int SOLR_QUERY_WEARING_CATEGORY = 5;
    /**
     * 错误信息(通用)
     *
     * @author Administrator
     * for example 0000
     */
    public interface Error{
        String _ok = "0000";
        String _ok_msg = "OK";

        String _error = "0001";
        String _error_msg = "数据加载失败，请稍后重试！";

        String _error_access = "0002";
        String _error_access_msg = "请求数据失败，请稍后重试！";

        String _tokenIsExpire = "0008";
        String _tokenIsExpire_msg = "登录过期，请重新登录";

        String _errorToken = "0009";
        String _errorToken_msg = "令牌无效，请重新登录";

        String _notEnoughPoints = "0025";
        String _notEnoughPoints_msg = "当前积分不足";

        String _multiCommit = "0027";
        String _multiCommit_msg = "请不要重复提交！";

        String _userNuEnabled = "0008";
        String _userNuEnabled_msg = "您还不是本站会员，不能登录！";

        String _deliverySuccess = "0028";
        String _deliverySuccess_msg = "交货成功";

        String _receiveSuccess = "0030";
        String _receiveSuccess_msg = "收货成功";

        String _receiveFail = "0031";
        String _receiveFail_msg = "收货失败";

        String _systemBusy = "0029";
        String _systemBusy_msg = "系统繁忙，请稍后再试！";

        String _paying_error = "0032";
        String _paying_error_msg = "当前订单正在支付中，请稍后再进行此操作！";

        String _isNotIMUser_error = "0033";
        String _isNotIMUser_error_msg = "此用户还不是IM（环信用户）会员！";

        String _mobilePhoneIsEmpty = "0041";
        String _mobilePhoneIsEmpty_msg = "手机号码必须输入";

        String _mobilePhoneIsError = "0042";
        String _mobilePhoneIsError_msg = "手机号码格式不正确";

        String _mobilePhoneCodeIsEmpty = "0043";
        String _mobilePhoneCodeIsEmpty_msg = "手机验证码不能为空";

        String _mobilePhoneCodeIsError = "0044";
        String _mobilePhoneCodeIsError_msg = "手机验证码错误或已失效";

        String _mobilePhoneExist = "0045";
        String _mobilePhoneExist_msg = "手机号码已经存在";

        String _mobilePhoneNotExist = "0047";
        String _mobilePhoneNotExist_msg = "手机号码不存在";


        String _mobilePhoneMore = "0048";
        String _mobilePhoneMore_msg = "手机号码重复请用密码登录";

        String _goodsExpired="0050";
        String _goodsExpired_msg="该商品已失效";

        //		String _mobilePhoneCodeSuccess = "0047";
//		String _mobilePhoneCodeSuccess_msg = "短信发送成功";
//
//		String _editPhoneSuccess = "0048";
//		String _editPhongSuccess_msg = "手机号修改成功";
//
//		String _invoiceSuccess = "0051";
//		String _invoiceSuccess_msg = "发票信息上传成功";
        String _tokenIsNull = "0004";
        String _tokenIsNull_msg = "请前往登录！";

        String _notLoginFreeSelectTimes = "0052";
        String _notLoginFreeSelectTimes_msg = "未登录查询次数已用尽，请前往登录！";

        String _requestNotAccessible = "0060";
        String _requestNotAccessible_msg = "该用户没有访问权限！";
        String _requestNoOrderIds = "0061";
        String _requestNoOrderIds_msg = "该选择退款审批的订单！";
        String _requestInvoiceImage = "0070";
        String _requestInvoiceImage_msg = "发票必须是图片！";
        String _requestInvoiceNr = "0071";
        String _requestInvoiceNr_msg = "发票号码为空！";


        String _error_paid_amount = "0101";
        String _error_paid_amount_msg = "收款总金额必须大于零！";

        String _error_paid_amount_ne = "0102";
        String _error_paid_amount_ne_msg = "输入的金额不能大于应收金额！";

        String _error_remark = "0103";
        String _error_remark_msg = "备注信息不能超过255个字符！";

        String _error_pay_type = "0104";
        String _error_pay_type_msg = "请填写收款方式！";

        String _error_bank_name = "0105";
        String _error_bank_name_msg = "请填写账户名称！";

        String _error_bank_name_length = "0106";
        String _error_bank_name_length_msg = "账户名称不能超过128个字符！";

        String _error_acc_nature = "0107";
        String _error_acc_nature_msg = "请选择账户类型！";

        String _error_pay_amount = "0108";
        String _error_pay_amount_msg = "付款金额必须大于零！";

        String _error_paid_type = "0109";
        String _error_paid_type_msg = "请选择付款方式！";

        String _error_wechat_version = "0110";
        String _error_wechat_version_msg = "当前版本不支持微信支付，请更新最新版本！";
    }

    /**
     * Description 登录模块返回的 Code 和  Messages
     * @author liwensuo
     * for example 1000
     */
    public interface LoginError{

        String _registerNameIsMust = "1000" ;
        String _registerNameIsMust_msg = "请输入汽修厂名称" ;
        String _registerNameLengthIs100 = "1001" ;
        String _registerNameLengthIs100_msg = "企业名称最大长度为100" ;

        String _registerAreaIdIsMust = "1002" ;
        String _registerAreaIdIsMust_msg = "请输入区域" ;

        String  _registerConnectorIsMust = "1003" ;
        String  _registerConnectorIsMust_msg = "请输入联系人" ;
        String  _registerConnectorLengthIs15 = "1004" ;
        String  _registerConnectorLengthIs15_msg = "联系人长度为2-10个汉字" ;

        String  _registerPhoneIsMust = "1005" ;
        String  _registerPhoneIsMust_msg = "请输入电话" ;
        String  _registerPhoneFormateErr = "1006" ;
        String  _registerPhoneFormateErr_msg = "联系电话格式不正确" ;

        String _registerAddressIsMust = "1007" ;
        String _registerAddressIsMust_msg = "请输入通讯地址" ;
        String _registerAddressLengthIs100 = "1008" ;
        String _registerAddressLengthIs100_msg = "通讯地址最大长度为 100" ;

        String _registerEmailIsMust = "1009" ; ;
        String _registerEmailIsMust_msg = "请输入Email" ;
        String _registerEmailFormateErr = "1010" ;
        String _registerEmailFormateErr_msg = "Email 格式不正确" ;
        String _registerEmailIsExist = "1011" ;
        String _registerEmailIsExist_msg = "该Email 已经存在" ;
        String _registerEmailIsNotExist = "1012" ;
        String _registerEmailIsNotExist_msg = "该Email 可以使用" ;
        String _findPWDEmailIsInvalid = "1013" ; ;
        String _findPWDEmailIsInvalid_msg = "该Email无效" ;
        String _findPWDSendEmailIsFail = "1014" ;
        String _findPWDSendEmailIsFail_msg = "找回密码时 , 邮件发送失败" ;
        String _findPWDSendEmailIsSuccess = "1015" ;
        String _findPWDSendEmailIsSuccess_msg = "请登录邮箱 ,进行密码重置" ;

        String _registerLoginNameIsMust = "1016" ;
        String _registerLoginNameIsMust_msg = "请输入登录用户名" ;
        String _registerLoginNameLengthErr = "1017" ;
        String _registerLoginNameLengthErr_msg = "用户名长度为 5 到 20位" ;
        String _registerLoginNameIsExist = "1018" ;
        String _registerLoginNameIsExist_msg = "该用户名已经存在" ;
        String _registerLoginNameIsNotExist = "1019" ;
        String _registerLoginNameIsNotExit_msg = "该用户名可以使用" ;

        String _registerLoginPwdIsMust = "1020" ;
        String _registerLoginPwdIsMust_msg = "请输入登录密码" ;
        String _registerLoginPwdLengthErr = "1021" ;
        String _registerLoginPwdLengthErr_msg = "密码长度为 8 到 20位" ;


        String _registerLoginPwdRepeatIsMust = "1022" ;
        String _registerLoginPwdRepeatIsMust_msg = "请输入确认密码" ;
        String _registerLoginPwdIsDiff = "1023" ;
        String _registerLoginPwdIsDiff_msg = "两次密码不一致" ;

        String _logUserNameOrPwdIsErr = "1024" ;
        String _logUserNameOrPwdIsErr_msg = "用户名或密码错误" ;

        String _CarSeriesIdIsMust="1025" ;
        String _CarSeriesIdIsMust_msg="请输入车系ID" ;

        String _logUserNameUnRight = "1027" ;
        String _logUserNameUnRight_msg = "用户名不存在" ;

        String _logPwdIsErr = "1028" ;
        String _logPwdIsErr_msg = "密码不正确" ;

        String _invitationCodeIsErr = "1029" ;
        String _invitationCodeIsErr_msg = "邀请码不正确" ;

        String  _registerMobilePhoneIsMust = "1030" ;
        String  _registerMobilePhoneIsMust_msg = "请输入手机号码" ;

        String  _mobilePhoneIsNotOnly = "1031" ;
        String  _mobilePhoneIsNotOnly_msg = "当前手机号不支持登录，请使用用户名登录！" ;

        String  _mobilePhoneIsExist = "1032" ;
        String  _mobilePhoneIsExist_msg = "当前手机号已注册！";

        String _userNotHasMobilePhone = "1033";
        String _userNotHasMobilePhone_msg = "用户缺少手机号信息，请联系客服!";

        String _MobilePhoneIsNotBind = "1034";
        String _MobilePhoneIsNotBind_msg = "请使用绑定手机号获取验证码";

        String _passwordModifiedMuccessfully = "1035";
        String _passwordModifiedMuccessfully_msg = "密码修改成功，请牢记新密码!";

        String _verificationCodeExpires = "1036";
        String _verificationCodeExpires_msg = "验证码已过期，请重新获取验证码";

        String _invalidRequest = "1037";
        String _invalidRequest_msg = "无效的请求!";

        String _noAuthority = "1038";
        String _noAuthority_msg = "暂无登录权限!";

        String _registerBusinessLicenseIsExist = "1034" ;
        String _registerBusinessLicenseIsExist_msg = "请填写正确的营业执照号！";

    }

    public interface Area{

        String rootAreaId = "0" ;
    }

    /**
     * Description 订单模块返回的 Code 和  Messages
     * @author wangjinpeng
     * for example 2000
     */
    public interface OrderError{
        String _returnReasonMinLengthErr = "2000" ;
        String _returnReasonMinLengthErr_msg = "退货原因至少三个字" ;
        String _returnReasonMaxLengthErr = "2001" ;
        String _returnReasonMaxLengthErr_msg = "退货原因最多二百个字" ;

        String _returnNumIsZeroErr = "2002" ;
        String _returnNumIsZeroErr_msg = "退货数量不能为0" ;
        String _returnNumOverLimitErr = "2003" ;
        String _returnNumOverLimitErr_msg = "退货数量不能大于订单可退货量" ;

        String _cancelOrderFailErr = "2004";
        String _cancelOrderFailErr_msg = "撤销子订单失败：";

        String _cancelOrderHeaderFailErr = "2005";
        String _cancelOrderHeaderFailErr_msg = "撤销订单头失败：";

        String _cancelRemarkMinLengthErr = "2006" ;
        String _cancelRemarkMinLengthErr_msg = "取消订单原因至少三个字" ;
        String _cancelRemarkMaxLengthErr = "2007" ;
        String _cancelRemarkMaxLengthErr_msg = "取消订单原因最多二百个字" ;
    }

    /**
     * Description 会员中心返回的 Code 和  Messages
     * @author gaoyong
     * for example 3000
     */
    public interface MemberCenterError{

        String _connectorIsMust = "3000" ;
        String _connectorIsMust_msg = "请输入联系人" ;
        String _emailIsMust = "3001" ;
        String _emailIsMust_msg = "请输入邮箱";

        String _phoneIsMust = "3002" ;
        String _phoneIsMust_msg = "请输入手机号码" ;
        String _mobilePhoneIsError = "3003";
        String _mobilePhoneIsError_msg = "手机号码格式不正确";

        String _oldPdMust = "3004";
        String _oldPdMust_msg = "旧密码必须输入";
        String _newPdMust = "3005";
        String _newPdMust_msg = "新密码必须输入";
        String _dupNewIsDiff = "3006";
        String _dupNewIsDiff_msg = "两次密码输入不一致";
        String _oldPdError = "3007";
        String _oldPdError_msg = "旧密码错误";

        String _addressIsMust = "3008" ;
        String _addressIsMust_msg = "请输入详细地址" ;
        String _IdIsNotNull = "3009";
        String _IdIsNotNull_msg = "主键不能为空";
        String _defaultAddressIsNotNull = "3010";
        String _defaultAddressIsNotNull_msg = "默认收货地址不能为空";

        String _emailIsError = "3011";
        String _emailIsError_msg = "邮箱格式不正确";

        String _areaIdIsMust = "3012";
        String _areaIdIsMust_msg = "区域id不能为空";

        String _oldPdEqualsNewPd ="3013";
        String _oldPdEqualsNewPd_msg = "新旧密码不能一致";

        String _txImgMust = "0018";
        String _txImgMust_msg = "头像必须是图片";

        String _txImgIsToBig = "0019";
        String _txImgIsToBig_msg = "头像大小不能超过1M";

        String _addressIsNonexistent="3013";
        String _addressIsNonexistent_msg="收货地址已经被删除";

        String _addressIsDefault="3014";
        String _addressIsDefault_msg="默认收货地址无法删除";

        String _connectorLength = "3030" ;
        String _connectorLength_msg = "联系人长度为2-10位" ;

        String _integral_error= "3031";
        String _integral_error_msg= "积分兑换失败";

        String _integral_success= "3032";
        String _integral_success_msg= "积分兑换成功";

        String _taxNumberIsMust = "3033" ;
        String _taxNumberIsMust_msg = "请输税务登记号" ;

        String _taxNumberLength = "3034" ;
        String _taxNumberLength_msg = "税务登记号长度为15-20位" ;

        String _taxIdentificationNumberIsMust = "3033" ;
        String _taxIdentificationNumberIsMust_msg = "请输纳税人识别号" ;

        String _taxIdentificationNumberLength = "3034" ;
        String _taxIdentificationNumberLength_msg = "纳税人识别号长度为15-20位" ;

        String _contacterLength = "3035" ;
        String _contacterLength_msg = "结算联系人长度为2-10位" ;
    }

    /**
     * Description 购物流程返回的 Code 和  Messages
     * @author gaoyong
     * for example 4000
     */
    public interface ShoppingCartError{
        String _promotionError = "4000";
        String _promotionError_msg = "商品参加的促销无效。";

        String _promotionNumError = "4001";
        String _promotionNumError_msg = "商品促销数量不足。";

        String _shoppingcartNumToplimitError = "4002";
        String _shoppingcartNumToplimitError_msg = "单个商品每次限购9999个"; // 上限数量9999

        String _shoppingcartRdcError = "4003";
        String _shoppingcartRdcError_msg = "含本地区不销售的商品,请先删除";

        String _shopingcartError = "4004";
        String _shopingcartError_msg = "购物车有无法交易的商品,请先删除";

        String _shopingcartNoGoodsError = "4005";
        String _shopingcartNoGoodsError_msg = "无货";

        String _shoppingcartMaxNum = "4006";
        String _shoppingcartMaxNumError_msg = "添加商品失败，已超出购物车最大容量！"; // 上限数量60
    }

    /**
     * 用户操作处理的时候产生的异常
     * @author Lee
     * for example 5000
     */
    public interface MemberHandleError{
        String _memberHandleError = "5000";
        String _memberHandleError_msg = "操作失败,请稍候重试";

        String _memberNonFavoritesError = "5001";
        String _memberNonFavoritesError_msg = "您还没有选择需要收藏的商品";

        String _memberHadFavoritesError = "5002";
        String _memberHadFavoritesError_msg = "客官  已收藏过了哟！^_^";

        String _noContainerOrCreatePartyIdNotYou = "5003";
        String _noContainerOrCreatePartyIdNotYou_msg = "当前设备不可用";

    }


    /**
     * 个人消息 code message
     * @author Administrator
     *
     */
    public interface personalMessageError {

        String  _registerConnectorIsMust = "10001" ;
        String  _registerConnectorIsMust_msg = "请输入联系人" ;
        String  _registerConnectorLengthIs15 = "10002" ;
        String  _registerConnectorLengthIs15_msg = "联系人长度为2-10位" ;

        String _phoneIsMust = "10003" ;
        String _phoneIsMust_msg = "请输入手机号码" ;
        String _mobilePhoneIsError = "10004";
        String _mobilePhoneIsError_msg = "手机号码格式不正确";

        String _registerEmailIsMust = "10005" ; ;
        String _registerEmailIsMust_msg = "请输入Email" ;
        String _registerEmailFormateErr = "10006" ;
        String _registerEmailFormateErr_msg = "Email 格式不正确" ;

        String _registerLoginPwdIsMust = "10007" ;
        String _registerLoginPwdIsMust_msg = "请输入新密码" ;
        String _registerLoginPwdLengthErr = "10008" ;
        String _registerLoginPwdLengthErr_msg = "密码长度为 8 到 20位" ;


        String _registerLoginPwdRepeatIsMust = "10009" ;
        String _registerLoginPwdRepeatIsMust_msg = "请输入确认密码" ;
        String _registerLoginPwdIsDiff = "10010" ;
        String _registerLoginPwdIsDiff_msg = "两次密码不一致" ;

        String _logPwdIsErr = "10011" ;
        String _logPwdIsErr_msg = "原密码不正确" ;
        String _logPwdIsErrIdentical = "10012" ;
        String _logPwdIsErr_msg_Identical = "原密码与新密码相同" ;

        String _logPwdIsErrIdentical_reg = "10013" ;
        String _logPwdIsErr_msg_Identical_reg = "新密码错误,必须包含字母和数字，字母区分大小写" ;

    }

    /**
     * @Title:销售出入库异常
     * @Description:
     * @Author: an.gong
     * @Date: 2018/9/18 16:46
     * @Param：* @param null
     * @return:
     */
    public interface MemberStockError{
        String _ok = "0000";
        String _ok_msg = "OK";

        String _error = "0001";
        String _error_msg = "数据加载失败，请稍后重试！";

        String _memberStockError = "0002";
        String _memberStockError_msg = "客官  订单状态已发生变化！^_^";

        String _memberNonFavoritesError = "0003";
        String _memberNonFavoritesError_msg = "库存更新失败,暂时无法发货！";
    }

    /**
     * @author wei.wang
     * @date 2019-03-20 22:36:53
     * @desc 品牌商APP销售收款-收款登记托盘操作类型
     */
    interface operationType {
        String operation_type_add = "add";
        String operation_type_delete = "delete";
    }
}
