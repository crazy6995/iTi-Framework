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

package org.lan.iti.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全配置
 *
 * @author NorthLan
 * @date 2020-02-27
 * @url https://noahlan.com
 */
@Data
@ConfigurationProperties("iti.security")
public class SecurityProperties {
    /**
     * 自动注册登录
     */
    private boolean autoRegistration = true;

    /**
     * 基于 oAuth2 客户端信息
     */
    private OAuth2ClientDetailsProperties client = new OAuth2ClientDetailsProperties();
}
