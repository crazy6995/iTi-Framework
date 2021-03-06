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

package org.lan.iti.cloud.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * Rest请求响应失败处理,不将400错误视为异常
 *
 * @author NorthLan
 * @date 2020-07-30
 * @url https://noahlan.com
 */
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
            super.handleError(response);
        }
    }
}
