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

import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.lan.iti.common.core.constants.CommonConstants;

/**
 * 租户 持有器
 *
 * @author NorthLan
 * @date 2020-02-24
 * @url https://noahlan.com
 */
public final class TenantContextHolder {
    private static final TransmittableThreadLocal<String> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * 设置租户ID
     *
     * @param tenantId 租户ID
     */
    public static void setTenantId(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取租户ID
     */
    public static String getTenantId() {
        String result = THREAD_LOCAL_TENANT.get();
        if (StrUtil.isBlank(result)) {
            setTenantId(CommonConstants.DEFAULT_TENANT_ID);
        }
        return THREAD_LOCAL_TENANT.get();
    }

    /**
     * 当前请求链中是否存在租户信息
     */
    public static boolean hasTenant() {
        return THREAD_LOCAL_TENANT.get() != null;
    }

    /**
     * 清理
     */
    public static void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
