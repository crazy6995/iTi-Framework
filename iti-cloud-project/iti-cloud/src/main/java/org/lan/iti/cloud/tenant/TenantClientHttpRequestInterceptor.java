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

package org.lan.iti.cloud.tenant;

import org.lan.iti.common.core.constants.CommonConstants;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * 传递 RestTemplate 的租户ID
 *
 * @author NorthLan
 * @date 2021-03-10
 * @url https://noahlan.com
 */
public class TenantClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull HttpRequest request,
                                        @NonNull byte[] body,
                                        @NonNull ClientHttpRequestExecution execution) throws IOException {
        if (TenantContextHolder.hasTenant()) {
            request.getHeaders().set(CommonConstants.TENANT_ID_HEADER_NAME, TenantContextHolder.getTenantId());
        }
        return execution.execute(request, body);
    }
}
