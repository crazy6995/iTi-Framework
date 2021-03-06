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

package org.lan.iti.cloud.resscanner.feign;

import feign.Headers;
import org.lan.iti.common.core.constants.SecurityConstants;
import org.lan.iti.cloud.resscanner.model.ResourceDefinition;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 系统资源服务接口
 *
 * @author NorthLan
 * @date 2020-03-05
 * @url https://noahlan.com
 */
public interface RemoteResourceService {

    /**
     * 报告业务服务的资源到指定服务
     *
     * @param serviceName 服务代码(通常为spring.application.name)
     * @param resources   某服务所有资源
     */
    @PutMapping(value = "/resources/report")
    @Headers(SecurityConstants.HEADER_FROM_IN)
    void reportResources(@RequestParam("serviceName") String serviceName,
                         @RequestBody List<ResourceDefinition> resources);
}
