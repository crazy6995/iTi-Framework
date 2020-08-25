/*
 *
 *  * Copyright (c) [2019-2020] [NorthLan](lan6995@gmail.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.lan.iti.common.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.lan.iti.common.core.constants.ITIConstants;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 数据校验 工具类
 *
 * @author NorthLan
 * @date 2020-04-18
 * @url https://noahlan.com
 */
@UtilityClass
@Slf4j
public class ValidationUtils {

    /**
     * hibernate注解验证
     */
    private Validator javaxValidator;
    private org.springframework.validation.Validator springValidator;
    private MessageSource validationMessageSource;

    /**
     * 获取javax.Validator
     *
     * @return 优先获取Bean, 若无法获取, 则取默认属性的Validator
     */
    public Validator getJavaxValidator() {
        if (javaxValidator == null) {
            javaxValidator = SpringContextHolder.getBeanOfNull(Validator.class);
            if (javaxValidator == null) {
                javaxValidator = Validation.byDefaultProvider().configure()
                        .messageInterpolator(new LocaleContextMessageInterpolator(getMessageInterpolator(validationMessageSource)))
                        .buildValidatorFactory()
                        .getValidator();
            }
        }
        return javaxValidator;
    }

    /**
     * 获取spring的validator
     *
     * @return 通过SpringValidatorAdapter的adapter适配
     * @see SpringValidatorAdapter
     */
    public org.springframework.validation.Validator getSpringValidator() {
        Validator javaxValidator = getJavaxValidator();
        if (javaxValidator == null) {
            return null;
        }
        if (springValidator == null) {
            springValidator = new SpringValidatorAdapter(javaxValidator);
        }
        return springValidator;
    }

    /**
     * 获取框架默认的消息解析器
     *
     * @return 框架修订的消息解析器
     */
    public MessageInterpolator getMessageInterpolator(MessageSource messageSource) {
        if (messageSource != null) {
            // 仅第一次调用赋值
            if (validationMessageSource == null) {
                validationMessageSource = messageSource;
            }
            return new ResourceBundleMessageInterpolator(
                    new MessageSourceResourceBundleLocator(messageSource));
        } else {
            return new ResourceBundleMessageInterpolator(
                    new PlatformResourceBundleLocator(ITIConstants.DEFAULT_VALIDATOR_MESSAGES));
        }
    }

    /**
     * 获取仅用于消息验证的 messageSource
     *
     * @return bean-name: ITIConstants.VALIDATION_MESSAGE_SOURCE_BEAN_NAME
     */
    public MessageSource getValidationMessageSource() {
        return validationMessageSource;
    }

    /**
     * 对bean进行参数验证
     *
     * @param target 目标对象
     * @param groups 验证组
     */
    public void validate(Object target, Object... groups) throws BindException {
        DataBinder dataBinder = new DataBinder(target);
        dataBinder.addValidators(getSpringValidator());
        dataBinder.validate(groups);

        // 当存在参数验证错误时,抛出BindException
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
    }

    /**
     * 验证拒绝
     *
     * @param target  目标对象
     * @param field   待拒绝字段
     * @param message 消息或消息代码
     */
    public BindException rejectValueException(Object target, String field, String message) {
        BindException bindException = new BindException(target, target.getClass().getSimpleName());
        String i18nMessage = message;
        try {
            i18nMessage = getValidationMessageSource().getMessage(message, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            // do nothing...
        }
        bindException.rejectValue(field, message, i18nMessage);
        return bindException;
    }

    /***
     * 字符串数组是否不为空
     */
    public boolean isEmpty(String[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断是否为数字（允许小数点）
     *
     * @return true Or false
     */
    public boolean isNumber(String str) {
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        return str.matches(regex);
    }

    /**
     * 判断是否为正确的邮件格式
     *
     * @param str
     * @return boolean
     */
    public boolean isEmail(String str) {
        if (StrUtil.isEmpty(str)) {
            return false;
        }
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * 是否boolean值范围
     */
    private final Set<String> TRUE_SET = CollUtil.newHashSet("true", "y", "yes", "1", "是");
    private final Set<String> FALSE_SET = CollUtil.newHashSet("false", "n", "no", "0", "否");

    /***
     * 是否为boolean类型
     */
    public boolean isValidBoolean(String value) {
        if (value == null) {
            return false;
        }
        value = value.trim().toLowerCase();
        return TRUE_SET.contains(value) || FALSE_SET.contains(value);
    }

    /***
     * 转换为boolean类型, 并判定是否为true
     */
    public boolean isTrue(String value) {
        if (value == null) {
            return false;
        }
        value = value.trim().toLowerCase();
        return TRUE_SET.contains(value);
    }
}
