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

package org.lan.iti.cloud.iha.core;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * 基本用户
 *
 * @author NorthLan
 * @date 2021-07-05
 * @url https://noahlan.com
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class IhaUser implements Serializable {
    private static final long serialVersionUID = -8066382503410442934L;

    /**
     * The id of the user in the business system
     */
    private String userId;

    /**
     * User name in the business system
     */
    private String username;

    /**
     * User mobile in business system
     */
    private String mobile;

    /**
     * User email in business system
     */
    private String email;

    /**
     * User password in business system
     */
    private String password;

    /**
     * Additional information about users in the developer's business system returned when obtaining user data.
     * Please do not save private data, such as secret keys, token.
     */
    private Map<String, Object> additionalInformation;

    /**
     * token
     */
    private String token;

    /**
     * remember me
     */
    private boolean rememberMe;
}
