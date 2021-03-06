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

package org.lan.iti.iha.security.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.lan.iti.iha.security.jwt.JwtConfig;

/**
 * @author NorthLan
 * @date 2021/8/13
 * @url https://blog.noahlan.com
 */
@Data
@Accessors(chain = true)
public class SecurityConfig {
    /**
     * Generate/verify the global configuration of jwt token.
     * If the caller needs to configure a set of jwt config for each client,
     * you can specify jwt config when obtaining the token.
     */
    private JwtConfig jwtConfig = new JwtConfig();
}
