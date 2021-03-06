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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.lan.iti.common.core.constants.CommonConstants;

/**
 * 租户 Feign 的 租户ID 传递
 *
 * @author NorthLan
 * @date 2021-03-10
 * @url https://noahlan.com
 */
@Slf4j
public class TenantFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        if (TenantContextHolder.hasTenant()) {
            template.header(CommonConstants.TENANT_ID_HEADER_NAME, TenantContextHolder.getTenantId());
            log.debug("请求链中存在租户信息，Feign增强");
        }
    }
}
