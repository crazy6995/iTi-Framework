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

package org.lan.iti.iha.security.userdetails;

import org.lan.iti.common.extension.IExtension;
import org.lan.iti.common.extension.annotation.Extension;
import org.lan.iti.iha.security.exception.authentication.UnknownAccountException;
import org.lan.iti.iha.security.mgt.RequestParameter;

import java.util.Map;

/**
 * Core interface which loads user-specific data.
 *
 * @author NorthLan
 * @date 2021/7/29
 * @url https://blog.noahlan.com
 */
@Extension
public interface UserDetailsService extends IExtension<Object> {

    /**
     * Locates the user based on the principal and type
     *
     * @param principal identify
     * @param type      login type
     * @return UserDetails if exists
     */
    default UserDetails loadByType(Object principal, String type, String clientId) throws UnknownAccountException {
        // TODO 异常类型
        throw new RuntimeException("Not Impl");
    }

    /**
     * Locates the user based on the request parameters
     *
     * @param parameter request parameter
     * @return UserDetails if exists
     */
    default UserDetails loadByParameter(RequestParameter parameter, String clientId) throws UnknownAccountException {
        // TODO 异常类型
        throw new RuntimeException("Not Impl");
    }

    /**
     * Locates the user based on the userId
     *
     * @param userId UserId
     * @return UserDetails if exists
     */
    default UserDetails loadById(String userId) throws UnknownAccountException {
        // TODO 异常类型
        throw new RuntimeException("Not Impl");
    }

    /**
     * Save the oauth login user information to the database and return UserDetails
     * It is suitable for the iti-iha-oauth2 module
     * <p>
     * token 实际类型为 AccessToken (依赖关系，此处Object)
     * <p>
     * 兼容标准协议
     * 1. 标准协议
     * 从 userInfo 中取出身份端的唯一用户id
     * 通过 getByPlatformAndUid 查询绑定情况，获取本系统用户信息（未绑定的在此进行绑定）
     * 构建 UserDetails，将多余的用户信息放置于 additionalInformation 中即可
     * 2. 统一身份平台
     * 直接使用 userInfo 转换为 UserDetails 即可
     *
     * @param type     类型 oauth2 / oidc / wx / weibo / qq
     * @param token    token信息
     * @param userInfo 用户信息
     * @return 用户信息
     */
    default UserDetails fromToken(String type, Object token, Map<String, Object> userInfo) throws UnknownAccountException {
        // TODO 异常类型
        throw new RuntimeException("Not Impl");
    }
}
