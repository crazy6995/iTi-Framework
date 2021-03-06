package org.lan.iti.sdk.pay.payment;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.lan.iti.common.core.exception.BusinessException;
import org.lan.iti.common.pay.constants.PayConstants;
import org.lan.iti.common.pay.constants.PayFieldKeyConstants;
import org.lan.iti.common.pay.reason.CommonExceptionReason;
import org.lan.iti.common.pay.util.PayCommonUtil;
import org.lan.iti.sdk.pay.model.DefaultResponse;
import org.lan.iti.sdk.pay.model.IRequest;
import org.lan.iti.sdk.pay.model.IResponse;
import org.lan.iti.sdk.pay.net.HttpResult;
import org.lan.iti.sdk.pay.util.HttpUtil;

import java.util.List;
import java.util.Map;

/**
 * @author I'm
 * @since 2021/3/27
 * description 抽象业务执行器
 */
public abstract class AbstractPayment<T extends IRequest> {
    protected final T request;

    public AbstractPayment(T request) {
        this.request = request;
    }

    /**
     * @param model 数据模型
     */
    protected Map<String, Object> toMap(IRequest model) {
        return BeanUtil.beanToMap(model, false, true);
    }

    /**
     * @param params 待扩展的数据
     */
    protected void enhance(Map<String, Object> params) {
        // 构造签名
        String gateWayHost = Convert.toStr(params.get(PayFieldKeyConstants.GATEWAY_HOST));
        if (!PayCommonUtil.httpUrlPatternCorrect(gateWayHost)) {
            throw BusinessException.withReason(CommonExceptionReason.SYSTEM.INVALID_GATEWAY_HOST);
        }
        params.remove(PayFieldKeyConstants.PRIVATE_KEY);
    }

    /**
     * 默认处理逻辑
     */
    public <R extends IResponse> DefaultResponse<R> execute(String method, Class<R> clazz) {
        // 默认逻辑
        // request -> map
        Map<String, Object> params = toMap(request);
        String privateKey = Convert.toStr(params.get(PayFieldKeyConstants.PRIVATE_KEY));
        String appId = Convert.toStr(params.get(PayFieldKeyConstants.APP_ID));
        enhance(params);
        String body = JSON.toJSONString(PayCommonUtil.sortMapByKey(params));
        // http -> request
        DefaultResponse<R> defaultResponse;
        HttpResult httpResult = HttpUtil.request(method, Convert.toStr(params.get(PayFieldKeyConstants.GATEWAY_HOST)), appId, privateKey, body);
        int httpCode = httpResult.getCode();
        String httpData = httpResult.getData();
        JSONObject jsonObject = JSON.parseObject(httpData);
        String code = Convert.toStr(jsonObject.get(PayConstants.HTTP_ENTITY_CODE));
        String message = Convert.toStr(jsonObject.get(PayConstants.HTTP_ENTITY_MESSAGE));
        Object data = jsonObject.get(PayConstants.HTTP_ENTITY_DATA);
        if (httpCode != 200) {
            defaultResponse = new DefaultResponse<>(httpCode, message,data);
            return defaultResponse;
        }
        if (data instanceof JSONObject) {
            R response = ((JSONObject) data).toJavaObject(clazz);
            defaultResponse = new DefaultResponse<>(code, message, response);
        } else if (data instanceof JSONArray) {
            List<R> rList = ((JSONArray) data).toJavaList(clazz);
            defaultResponse = new DefaultResponse<>(code, message, rList);
        } else {
            defaultResponse = new DefaultResponse<>(code, message);
        }
        return defaultResponse;
    }

}
