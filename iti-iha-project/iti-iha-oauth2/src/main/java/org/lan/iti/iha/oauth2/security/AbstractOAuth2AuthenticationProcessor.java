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

import org.lan.iti.iha.security.processor.AuthenticationProcessor;
import org.lan.iti.iha.security.processor.ProcessorType;

/**
 * @author NorthLan
 * @date 2021/8/9
 * @url https://blog.noahlan.com
 */
public abstract class AbstractOAuth2AuthenticationProcessor implements AuthenticationProcessor {
    @Override
    public boolean matches(String params) {
        return ProcessorType.OAUTH2.matches(params);
    }
}
