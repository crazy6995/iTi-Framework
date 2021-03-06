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

package org.lan.iti.cloud.autoconfigure.feign.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author NorthLan
 * @date 2020-04-24
 * @url https://noahlan.com
 */
@Data
@ConfigurationProperties(prefix = ITIFeignProperties.PREFIX)
public class ITIFeignProperties {
    public static final String PREFIX = "iti.feign";

    /**
     * 是否开启默认 框架fallback
     */
    private boolean fallback = false;

    /**
     * 客户端列表
     */
    private List<Client> clients;
}
