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

package org.lan.iti.iha.oauth2.pkce;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encryption method of pkce challenge code
 *
 * @author NorthLan
 * @date 2021-07-05
 * @url https://noahlan.com
 * @see <a href="https://tools.ietf.org/html/rfc7636" target="_blank">https://tools.ietf.org/html/rfc7636</a>
 */
public enum CodeChallengeMethod {
    /**
     * code_challenge = code_verifier
     */
    PLAIN,
    /**
     * code_challenge = BASE64URL-ENCODE(SHA256(ASCII(code_verifier)))
     */
    S256;

    public static List<String> getAllMethods() {
        return Arrays.stream(CodeChallengeMethod.values())
                .map(it -> it.name().toUpperCase())
                .collect(Collectors.toList());
    }
}
