package org.lan.iti.common.pay.constants;

/**
 * @author I'm
 * @since 2021/3/27
 * description
 */
public interface PayConstants {

    /**
     * 签名
     */
    String SIGN_SIGNATURE = "signature";

    /**
     * 参数错误
     */
    String PARAM_ERROR = "参数错误";

    /**
     * 渠道编码
     */
    String CHANNEL = "channel";

    /**
     * 最小金额
     */
    String MIN_AMOUNT = "0.01";

    /**
     * 最小金额(支付宝)
     */
    String ALIPAY_MIN_FUND_AMOUNT = "0.1";

    /**
     * 最小金额(微信)
     */
    String WX_MIN_FUND_AMOUNT = "0.3";

    /**
     * 金额比较结果为大于
     */
    int BIG_DECIMAL_GREATER_THAN = 1;

    /**
     * 金额比较结果为等于
     */
    int BIG_DECIMAL_EQUALS = 0;

    /**
     * 金额比较结果为小于
     */
    int BIG_DECIMAL_LESS_THAN = -1;

    /**
     * Http header Content-Type
     */
    String CONTENT_TYPE = "Content-Type";

    /**
     * HttpEntity code
     */
    String HTTP_ENTITY_CODE = "code";

    /**
     * HttpEntity message
     */
    String HTTP_ENTITY_MESSAGE = "message";

    /**
     * HttpEntity data
     */
    String HTTP_ENTITY_DATA = "data";

    /**
     * 签名信息 随机串
     */
    String SIGN_NONCE_STR = "nonceStr";

    /**
     * 签名信息 时间戳
     */
    String SIGN_TIMESTAMP = "timestamp";


    /**
     * 签名信息 私钥
     */
    String SIGN_PRIMARY_KEY = "primaryKey";

    /**
     * 符号 =
     */
    String SYMBOL_EQUAL = "=";

    /**
     * 换行符号 \n
     */
    String SYMBOL_WRAP = "\n";

    /**
     * 符号 ,
     */
    String SYMBOL_COMMA = ",";

    /**
     * 符号 "
     */
    String SYMBOL_QUOTES = "\"";

    /**
     * 符号 "
     */
    String SYMBOL_QUESTION = "?";

    /**
     * http 超时时间
     */
    int HTTP_TIMEOUT = 5000;

    /**
     * http header Authorization
     */
    String HTTP_HEADER_AUTHORIZATION = "Authorization";

    /**
     * one pay schema code
     */
    String ONE_PAY_SCHEMA_CODE = "schema";

    /**
     * one pay schema code
     */
    String ONE_PAY_SCHEMA_VALUE = "ONEPAY-SHA256-RSA2048";

}
