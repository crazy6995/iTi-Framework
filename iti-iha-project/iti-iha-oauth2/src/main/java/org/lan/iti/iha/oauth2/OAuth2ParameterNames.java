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

package org.lan.iti.iha.oauth2;

/**
 * Standard and custom (non-standard) parameter names defined in the OAuth Parameters
 * Registry and used by the authorization endpoint, token endpoint and token revocation
 * endpoint.
 *
 * @author NorthLan
 * @date 2021-07-05
 * @url https://noahlan.com
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc6749#section-11.2">11.2 OAuth Parameters Registry</a>
 */
public interface OAuth2ParameterNames {
    /**
     * user consent
     */
    String AUTOAPPROVE = "autoapprove";

    /**
     * {@code grant_type} - used in Access Token Request.
     */
    String GRANT_TYPE = "grant_type";

    /**
     * {@code response_type} - used in Authorization Request.
     */
    String RESPONSE_TYPE = "response_type";

    /**
     * {@code client_id} - used in Authorization Request and Access Token Request.
     */
    String CLIENT_ID = "client_id";

    /**
     * {@code client_secret} - used in Access Token Request.
     */
    String CLIENT_SECRET = "client_secret";

    /**
     * {@code client_assertion_type} - used in Access Token Request.
     *
     * @since 5.5
     */
    String CLIENT_ASSERTION_TYPE = "client_assertion_type";

    /**
     * {@code client_assertion} - used in Access Token Request.
     *
     * @since 5.5
     */
    String CLIENT_ASSERTION = "client_assertion";

    /**
     * {@code assertion} - used in Access Token Request.
     *
     * @since 5.5
     */
    String ASSERTION = "assertion";

    /**
     * {@code redirect_uri} - used in Authorization Request and Access Token Request.
     */
    String REDIRECT_URI = "redirect_uri";

    /**
     * {@code scope} - used in Authorization Request, Authorization Response, Access Token
     * Request and Access Token Response.
     */
    String SCOPE = "scope";

    /**
     * {@code state} - used in Authorization Request and Authorization Response.
     */
    String STATE = "state";

    /**
     * {@code code} - used in Authorization Response and Access Token Request.
     */
    String CODE = "code";

    /**
     * {@code access_token} - used in Authorization Response and Access Token Response.
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * {@code token_type} - used in Authorization Response and Access Token Response.
     */
    String TOKEN_TYPE = "token_type";

    /**
     * {@code expires_in} - used in Authorization Response and Access Token Response.
     */
    String EXPIRES_IN = "expires_in";

    /**
     * Addon
     * <p>
     * {@code refresh_token_expires_in} - used in Authorization Response and Access Token Response.
     */
    String REFRESH_TOKEN_EXPIRES_IN = "refresh_token_expires_in";

    /**
     * Addon
     * <p>
     * {@code id_token_expires_in} - used in Authorization Response and Access Token Response.
     */
    String ID_TOKEN_EXPIRES_IN = "id_token_expires_in";

    /**
     * {@code refresh_token} - used in Access Token Request and Access Token Response.
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * {@code username} - used in Access Token Request.
     */
    String USERNAME = "username";

    /**
     * {@code password} - used in Access Token Request.
     */
    String PASSWORD = "password";

    /**
     * {@code error} - used in Authorization Response and Access Token Response.
     */
    String ERROR = "error";

    /**
     * {@code error_description} - used in Authorization Response and Access Token
     * Response.
     */
    String ERROR_DESCRIPTION = "error_description";

    /**
     * {@code error_uri} - used in Authorization Response and Access Token Response.
     */
    String ERROR_URI = "error_uri";

    /**
     * Non-standard parameter (used internally).
     */
    String REGISTRATION_ID = "registration_id";

    /**
     * {@code token} - used in Token Revocation Request.
     *
     * @since 5.5
     */
    String TOKEN = "token";

    /**
     * {@code token_type_hint} - used in Token Revocation Request.
     *
     * @since 5.5
     */
    String TOKEN_TYPE_HINT = "token_type_hint";

    /**
     * {@code id_token} - used in the Access Token Response. (OIDC)
     */
    String ID_TOKEN = "id_token";

    /**
     * token header name
     */
    String AUTHORIZATION_HEADER_NAME = "Authorization";

    /**
     * Token Type
     */
    String TOKEN_TYPE_BEARER = "Bearer";
}
