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

package org.lan.iti.iha.server.provider.authorization;

import org.lan.iti.iha.oauth2.OAuth2ParameterNames;
import org.lan.iti.iha.security.clientdetails.ClientDetails;
import org.lan.iti.iha.security.userdetails.UserDetails;
import org.lan.iti.iha.server.model.enums.ResponseType;
import org.lan.iti.iha.server.security.IhaServerRequestParam;
import org.lan.iti.iha.server.util.AuthorizationTokenUtil;

import java.util.Map;

/**
 * When the value of {@code response_type} is {@code id_token}, an {@code id_token} is returned from the authorization endpoint.
 * This mode does not require the use of token endpoints.
 *
 * @author NorthLan
 * @date 2021/8/2
 * @url https://blog.noahlan.com
 */
public class IdTokenAuthorizationProvider extends AbstractAuthorizationProvider {
    @Override
    public boolean matches(String params) {
        return ResponseType.ID_TOKEN.getType().equalsIgnoreCase(params);
    }

    @Override
    protected void process(Map<String, Object> result, IhaServerRequestParam param, String responseType, ClientDetails clientDetails, UserDetails userDetails, String issuer) {
        String idToken = AuthorizationTokenUtil.createIdToken(clientDetails, userDetails, param.getNonce(), param.getScope(), issuer);
        result.put(OAuth2ParameterNames.ID_TOKEN, idToken);
    }
}
