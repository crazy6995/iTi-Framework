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

package org.lan.iti.cloud.constants;

/**
 * 框架AOP-Order常量
 * <p>
 * 数值越低越靠前
 * </p>
 *
 * @author NorthLan
 * @date 2020-02-20
 * @url https://noahlan.com
 */
public interface AopConstants {
    /**
     * GlobalExceptionHandler
     */
    int GLOBAL_EXCEPTION_HANDLER = 100;

    /**
     * ArgumentsExceptionHandler
     */
    int ARGUMENTS_EXCEPTION_HANDLER = GLOBAL_EXCEPTION_HANDLER - 1;

    /**
     * SQLExceptionHandler
     */
    int SQL_EXCEPTION_HANDLER = GLOBAL_EXCEPTION_HANDLER - 2;

    /**
     * SpringJdbcExceptionHandler
     */
    int SPRING_JDBC_EXCEPTION_HANDLER = GLOBAL_EXCEPTION_HANDLER - 3;

    /**
     * 业务异常处理器Order
     */
    int BIZ_EXCEPTION_HANDLER = GLOBAL_EXCEPTION_HANDLER - 4;

    /**
     * Axon 异常处理器 Order
     */
    int AXON_EXCEPTION_HANDLER = GLOBAL_EXCEPTION_HANDLER - 5;

    /**
     * 返回值包装顺序
     */
    int API_RESULT_WRAPPER_ADVICE = GLOBAL_EXCEPTION_HANDLER + 100;

    /**
     * 动态过滤顺序
     */
    int DYNAMIC_FILTER_ADVICE = GLOBAL_EXCEPTION_HANDLER + 101;

}
