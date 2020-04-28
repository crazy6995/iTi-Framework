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

package org.lan.iti.common.data.tenant;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.lan.iti.common.core.constants.ITIConstants;

/**
 * 多租户增强
 * <p>
 * 内部feign访问传递租户信息
 * </p>
 *
 * @author NorthLan
 * @date 2020-02-24
 * @url https://noahlan.com
 */
@Slf4j
public class ITIFeignTenantInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (!TenantContextHolder.hasTenant()) {
            log.warn("请求链中不存在租户信息，无法增强");
            return;
        }
        requestTemplate.header(ITIConstants.TENANT_ID_HEADER_NAME, TenantContextHolder.getTenantId());
    }
}
