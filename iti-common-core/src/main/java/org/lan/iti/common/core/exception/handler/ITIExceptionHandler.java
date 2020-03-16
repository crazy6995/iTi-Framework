/*
 * Copyright (c) [2019-2020] [NorthLan](lan6995@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lan.iti.common.core.exception.handler;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.lan.iti.common.model.response.ApiResult;
import org.lan.iti.common.core.exception.ServiceException;
import org.lan.iti.common.core.exception.enums.ITICoreExceptionEnum;
import org.lan.iti.common.core.constants.OrderConstants;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 框架异常拦截统一处理
 *
 * @author NorthLan
 * @date 2020-02-22
 * @url https://noahlan.com
 */
@Slf4j
@RestControllerAdvice
@Order(OrderConstants.ITI_EXCEPTION_HANDLER_ORDER)
public class ITIExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> handleServiceException(ServiceException e) {
        log.error("服务具体异常：", e);
        return ApiResult.error(e.getCode(), e.getMessage(), e.getBody());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> handleBodyValidException(MethodArgumentNotValidException e) {
        val fieldErrors = e.getBindingResult().getFieldErrors();
        val msg = fieldErrors.get(0).getDefaultMessage();
        log.error("参数绑定异常,ex = {}", msg);
        return ApiResult.error(ITICoreExceptionEnum.METHOD_ARGUMENT_NOT_VALID.getCode(), msg);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> handleException(RuntimeException e) {
        log.error("运行时异常，未知错误：", e);
        return ApiResult.error(ITICoreExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
