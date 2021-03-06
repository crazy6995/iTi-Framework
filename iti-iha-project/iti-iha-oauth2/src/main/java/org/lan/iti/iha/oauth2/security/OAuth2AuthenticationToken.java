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

package org.lan.iti.iha.oauth2.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.lan.iti.common.core.util.StringUtil;
import org.lan.iti.iha.oauth2.token.AccessToken;
import org.lan.iti.iha.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

/**
 * OAuth2 身份认证 Token
 *
 * @author NorthLan
 * @date 2021/8/3
 * @url https://blog.noahlan.com
 */
@Getter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
public class OAuth2AuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1159293864004832614L;

    protected AccessToken accessToken;
    protected Object principal;
    protected String redirectUri;

    @Setter
    protected boolean needRevoke;
    protected boolean isRevoked;

    public OAuth2AuthenticationToken() {
        this(false);
    }

    public OAuth2AuthenticationToken(AccessToken accessToken, Object principal, Collection<String> authorities) {
        super(authorities);
        this.principal = principal;
        this.accessToken = accessToken;
        setAuthenticated(true);
    }

    public OAuth2AuthenticationToken(boolean isRevoked) {
        super(null);
        this.isRevoked = isRevoked;
    }

    public OAuth2AuthenticationToken(String redirectUri) {
        super(null);
        this.redirectUri = redirectUri;
    }

    public boolean isRedirect() {
        return StringUtil.isNotEmpty(this.redirectUri);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
