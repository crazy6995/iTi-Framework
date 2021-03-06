package org.lan.iti.sdk.pay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.lan.iti.sdk.pay.model.response.RefundResponse;

/**
 * @author I'm
 * @date 2021/6/24
 * description 退款模型
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RefundNotifyModel extends NotifyModel implements INotifyModel {

    /**
     * 退款通知
     */
    public RefundResponse refundResponse;

}
