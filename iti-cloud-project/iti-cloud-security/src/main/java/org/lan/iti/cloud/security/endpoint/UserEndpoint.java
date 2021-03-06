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

package org.lan.iti.cloud.security.endpoint;

import lombok.RequiredArgsConstructor;
import org.lan.iti.common.core.api.ApiResult;
import org.lan.iti.iha.security.IhaSecurity;
import org.lan.iti.iha.security.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关 内置端点
 *
 * @author NorthLan
 * @date 2021/9/28
 * @url https://blog.noahlan.com
 */
@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserEndpoint {

    @GetMapping
    public ApiResult<UserDetails> fetchUser() {
        return ApiResult.ok(IhaSecurity.getUser());
    }

    @GetMapping("/check")
    public ApiResult<Boolean> check() {
        return ApiResult.ok(IhaSecurity.isAuthenticated());
    }
}
