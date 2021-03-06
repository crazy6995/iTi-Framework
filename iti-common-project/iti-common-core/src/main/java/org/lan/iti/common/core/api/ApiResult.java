/*
 *
 *  * Copyright (c) [2019-2021] [NorthLan](lan6995@gmail.com)
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

package org.lan.iti.common.core.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lan.iti.common.core.support.IEnum;
import org.lan.iti.common.core.support.Mapped;
import org.lan.iti.common.core.util.StringUtil;

/**
 * 统一的API返回结果类
 *
 * @author NorthLan
 * @date 2020-02-20
 * @url https://noahlan.com
 */
@SuppressWarnings("UnusedReturnValue")
@ApiModel(description = "API返回结果类")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResult<T> extends Mapped<ApiResult<T>> {
    private static final long serialVersionUID = -6067032280959288931L;

    /**
     * 消息码
     * <p>自定义消息码需 >= 600</p>
     */
    public final static String KEY_CODE = "code";

    /**
     * 本次请求描述
     */
    public final static String KEY_MESSAGE = "message";

    /**
     * 数据集
     * <p>可单可一</p>
     */
    public final static String KEY_DATA = "data";

    /////////////////////// 分页参数

    /**
     * 元素总量
     */
    public final static String KEY_TOTAL_ELEMENTS = "totalElements";

    /**
     * 当前页码
     */
    public final static String KEY_PAGE = "page";

    /**
     * 每页元素个数
     */
    public final static String KEY_SIZE = "size";

    /**
     * 总页数
     */
    public final static String KEY_TOTAL_PAGES = "totalPages";

    // region Static-method

    /**
     * 复制ApiResult
     * <pre>
     *     data不进行复制，通常由用户自行转换
     * </pre>
     *
     * @param other 源
     * @param <T>   目标类型
     */
    public static <T> ApiResult<T> from(ApiResult<?> other) {
        ApiResult<T> result = new ApiResult<>();
        result.putMap(other);
        return result;
    }

    /**
     * 融合多个ApiResult，后值覆盖先值
     *
     * @param values 待融合值
     * @param <T>    返回数据集类型
     */
    public static <T> ApiResult<T> merge(ApiResult<?>... values) {
        ApiResult<T> result = new ApiResult<>();
        for (ApiResult<?> value : values) {
            result.putMap(value);
        }
        return result;
    }

    /**
     * 创建空消息体
     */
    public static <T> ApiResult<T> empty() {
        return new ApiResult<>();
    }

    /**
     * 成功结果
     * <pre>
     *     数据与message均使用默认
     * </pre>
     */
    public static <T> ApiResult<T> ok() {
        return ok(null, null, null);
    }

    /**
     * 成功结果,无message
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> ok(T data) {
        return ok(null, null, data);
    }

    /**
     * 成功结果
     *
     * @param data 数据域
     */
    public static <T> ApiResult<T> ok(T data, String message) {
        return ok(null, message, data);
    }

    /**
     * 成功结果
     *
     * @param data 数据域
     */
    public static <T> ApiResult<T> ok(String code, String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        if (StrUtil.isEmpty(message)) {
            message = DefaultEnum.SUCCESS.getMessage();
        }
        if (code == null) {
            code = DefaultEnum.SUCCESS.getCode();
        }
        return result
                .message(message)
                .code(code)
                .data(data);
    }

    /**
     * 失败结果
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return ApiResult实例
     */
    public static <T> ApiResult<T> error(String message) {
        return error(null, message, null);
    }

    /**
     * 失败结果
     *
     * @param code    错误码
     * @param message 错误消息
     * @return ApiResult实例
     */
    public static <T> ApiResult<T> error(String code, String message) {
        return error(code, message, null);
    }

    /**
     * 失败结果
     *
     * @param code    错误码
     * @param message 错误信息 必须重写 ToString
     * @param data    结果集，数据
     * @return ApiResult实例
     */
    public static <T> ApiResult<T> error(String code, String message, T data) {
        ApiResult<T> result = new ApiResult<>();

        if (StrUtil.isBlank(message)) {
            message = DefaultEnum.FAIL.getMessage();
        }
        if (code == null) {
            code = DefaultEnum.FAIL.getCode();
        }

        return result
                .code(code)
                .message(message)
                .data(data);
    }

    // region Methods
    public ApiResult<T> code(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code cannot be null");
        }
        this.put(KEY_CODE, code);
        return this;
    }

    public ApiResult<T> message(String message) {
        this.put(KEY_MESSAGE, message);
        return this;
    }

    public ApiResult<T> data(T data) {
        this.put(KEY_DATA, data);
        return this;
    }

    public ApiResult<T> totalElements(long totalElements) {
        this.put(KEY_TOTAL_ELEMENTS, totalElements);
        return this;
    }

    public ApiResult<T> size(Integer size) {
        this.put(KEY_SIZE, size);
        return this;
    }

    public ApiResult<T> page(Integer page) {
        this.put(KEY_PAGE, page);
        return this;
    }

    public ApiResult<T> totalPages(long totalPages) {
        this.put(KEY_TOTAL_PAGES, totalPages);
        return this;
    }
    // endregion

    /**
     * 消息码
     */
    public String getCode() {
        return getByKey(KEY_CODE, DefaultEnum.SUCCESS.code);
    }

    @JsonIgnore
    public int getCodeInt() {
        String code = this.getCode();
        try {
            return Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return Integer.parseInt(DefaultEnum.SUCCESS.code);
        }
    }

    /**
     * 本次请求描述
     */
    public String getMessage() {
        return getByKey(KEY_MESSAGE, DefaultEnum.SUCCESS.message);
    }

    /**
     * 获取数据集
     */
    public T getData() {
        return getByKey(KEY_DATA);
    }

    /**
     * 元素数量
     */
    public Long getTotalElements() {
        return getByKey(KEY_TOTAL_ELEMENTS);
    }

    /**
     * 页码
     */
    public Integer getPage() {
        return getByKey(KEY_PAGE);
    }

    /**
     * 每页条目数
     */
    public Integer getSize() {
        return getByKey(KEY_SIZE);
    }

    /**
     * 总页数
     */
    public Long getTotalPage() {
        return getByKey(KEY_TOTAL_PAGES);
    }

    // region 判断条件
    public boolean isOk() {
        return StringUtil.equals(getCode(), DefaultEnum.SUCCESS.getCode());
    }
    // endregion

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum DefaultEnum implements IEnum<String> {
        SUCCESS(String.valueOf(HttpStatus.HTTP_OK), "成功"),
        FAIL(String.valueOf(HttpStatus.HTTP_INTERNAL_ERROR), "失败"),
        ;
        private final String code;
        private final String message;
    }
}
