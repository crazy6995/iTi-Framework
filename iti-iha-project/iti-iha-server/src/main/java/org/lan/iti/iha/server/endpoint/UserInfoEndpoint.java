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

package org.lan.iti.iha.server.endpoint;

import org.lan.iti.common.core.util.StringUtil;
import org.lan.iti.iha.oauth2.OAuth2Constants;
import org.lan.iti.iha.oauth2.enums.ErrorEnum;
import org.lan.iti.iha.oauth2.exception.InvalidTokenException;
import org.lan.iti.iha.security.IhaSecurity;
import org.lan.iti.iha.security.exception.authorization.UnauthorizedException;
import org.lan.iti.iha.security.userdetails.UserDetails;
import org.lan.iti.iha.security.userdetails.UserDetailsHelper;
import org.lan.iti.iha.server.model.AuthorizationToken;
import org.lan.iti.iha.server.model.enums.ScopeClaimsMapping;
import org.lan.iti.iha.server.util.AuthorizationTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author NorthLan
 * @date 2021-07-06
 * @url https://noahlan.com
 */
public class UserInfoEndpoint extends AbstractEndpoint {
    /**
     * Get the currently logged-in user information through the access token
     *
     * @param request current HTTP request
     * @return IhaResponse
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#UserInfo" target="_blank">5.3.  UserInfo Endpoint</a>
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#UserInfoResponse" target="_blank">5.3.2.  Successful UserInfo Response</a>
     * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims" target="_blank">5.4.  Requesting Claims using Scope Values</a>
     */
    public Map<String, Object> getCurrentUserInfo(HttpServletRequest request) {
        String accessTokenStr = AuthorizationTokenUtil.getAccessToken(request);
        AuthorizationToken authorizationToken = AuthorizationTokenUtil.getByAccessToken(accessTokenStr);

        if (null == authorizationToken) {
            throw new InvalidTokenException();
        }
        UserDetails userDetails = IhaSecurity.getContext().getUserDetailsService().loadById(authorizationToken.getUserId());
        if (null == userDetails) {
            throw new UnauthorizedException(ErrorEnum.ACCESS_DENIED);
        }
        String scope = authorizationToken.getScope();
        List<String> scopes = StringUtil.split(scope, OAuth2Constants.SCOPE_SEPARATOR);

        Map<String, Object> userInfoMap = UserDetailsHelper.toMap(userDetails);

        // This scope value requests access to the End-User's default profile Claims,
        // which are: name, family_name, given_name, middle_name, nickname, preferred_username, profile, picture, website, gender, birthdate, zoneinfo, locale, and updated_at.
        if (!scopes.contains("profile")) {
            removeClaims(userInfoMap, ScopeClaimsMapping.PROFILE);
        }
        // This scope value requests access to the email and email_verified Claims.
        if (!scopes.contains("email")) {
            removeClaims(userInfoMap, ScopeClaimsMapping.EMAIL);
        }
        // This scope value requests access to the phone_number and phone_number_verified Claims.
        if (!scopes.contains("phone")) {
            removeClaims(userInfoMap, ScopeClaimsMapping.PHONE);
        }
        // This scope value requests access to the address Claim.
        if (!scopes.contains("address")) {
            removeClaims(userInfoMap, ScopeClaimsMapping.ADDRESS);
        }
        // This scope value requests access to the roles Claim.
        if (!scopes.contains("roles")) {
            removeClaims(userInfoMap, ScopeClaimsMapping.ROLES);
        }
        return userInfoMap;
    }

    private void removeClaims(Map<String, Object> userInfoMap, ScopeClaimsMapping claimsMapping) {
        for (String claim : claimsMapping.getClaims()) {
            userInfoMap.remove(claim);
        }
    }
}
