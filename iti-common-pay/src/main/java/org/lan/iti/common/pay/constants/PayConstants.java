package org.lan.iti.common.pay.constants;

/**
 * @author I'm
 * @since 2020/10/10
 * description
 */
public interface PayConstants {
    /**
     * bizCode createCharge 创建订单
     */
    String BIZ_CODE_CREATE_CHARGE = "createCharge";
    /**
     * bizCode query 订单查询
     */
    String BIZ_CODE_QUERY = "query";
    /**
     * bizCode refund 退款
     */
    String BIZ_CODE_REFUND = "refund";
    /**
     * bizCode refundQuery 退款查询
     */
    String BIZ_CODE_REFUND_QUERY = "refundQuery";
    /**
     * bizCode fund 转账
     */
    String BIZ_CODE_FUND = "fund";
    /**
     * bizCode fundQuery 转账查询
     */
    String BIZ_CODE_FUND_QUERY = "fundQuery";

    /**
     * privateKey 私钥
     */
    String PRIVATE_KEY = "privateKey";
    /**
     * privateKey 接口网关
     */
    String GATEWAY_HOST = "gatewayHost";
    /**
     * 签名
     */
    String SIGN = "sign";

    /**
     * outOrderNo 商户订单号
     */
    String OUT_ORDER_NO = "outOrderNo";
    /**
     * outRefundNo 商户退款凭据号
     */
    String OUT_REFUND_NO = "outRefundNo";
    /**
     * outFundNo 商户转账订单号
     */
    String OUT_FUND_NO = "outFundNo";

    /**
     * 订单标题
     */
    String SUBJECT = "subject";
    /**
     * 订单金额
     */
    String AMOUNT = "amount";
    /**
     * 退款金额
     */
    String REFUND_AMOUNT = "refundAmount";
    /**
     * 转账金额
     */
    String FUND_AMOUNT = "fundAmount";
    /**
     * 转账到账用户类型
     */
    String ACCOUNT_ID = "accountId";
    /**
     * 转账到账用户标识
     */
    String ACCOUNT_TYPE = "accountType";
    /**
     * 收款方姓名
     */
    String ACCOUNT_NAME = "accountName";
    /**
     * 转账渠道
     */
    String FUND_CHANNEL = "fundChannel";

    /**
     * 参数错误
     */
    String PARAM_ERROR = "参数错误";

}
