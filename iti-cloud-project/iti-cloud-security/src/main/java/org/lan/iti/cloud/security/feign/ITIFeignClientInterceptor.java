///*
// *
// *  * Copyright (c) [2019-2021] [NorthLan](lan6995@gmail.com)
// *  *
// *  * Licensed under the Apache License, Version 2.0 (the "License");
// *  * you may not use this file except in compliance with the License.
// *  * You may obtain a copy of the License at
// *  *
// *  *     http://www.apache.org/licenses/LICENSE-2.0
// *  *
// *  * Unless required by applicable law or agreed to in writing, software
// *  * distributed under the License is distributed on an "AS IS" BASIS,
// *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  * See the License for the specific language governing permissions and
// *  * limitations under the License.
// *
// */
//
//package org.lan.iti.cloud.security.feign;
//
//import cn.hutool.core.collection.CollUtil;
//import feign.RequestTemplate;
//import lombok.extern.slf4j.Slf4j;
//import org.lan.iti.common.core.constants.SecurityConstants;
//import org.springframework.beans.factory.BeanCreationException;
//import org.springframework.cloud.commons.security.AccessTokenContextRelay;
//import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//
//import java.util.Collection;
//
///**
// * OAuth2FeignRequestInterceptor增强,当存在accessToken时允许访问
// * 不再进入 默认拦截器 抛出异常
// *
// * @author NorthLan
// * @date 2020-02-25
// * @url https://noahlan.com
// */
//@Slf4j
//public class ITIFeignClientInterceptor extends RequestInterceptor {
//    private final OAuth2ClientContext oAuth2ClientContext;
//    private final AccessTokenContextRelay accessTokenContextRelay;
//
//    /**
//     * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
//     * within Authorization header
//     *
//     * @param oAuth2ClientContext provided context
//     * @param resource            type of resource to be accessed
//     */
//    public ITIFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext
//            , OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
//        super(oAuth2ClientContext, resource);
//        this.oAuth2ClientContext = oAuth2ClientContext;
//        this.accessTokenContextRelay = accessTokenContextRelay;
//    }
//
//
//    /**
//     * Create a template with the header of provided name and extracted extract
//     * 1. 如果使用 非web 请求，header 区别
//     * 2. 根据authentication 还原请求token
//     */
//    @Override
//    public void apply(RequestTemplate template) {
//        Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
//        if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
//            return;
//        }
//
//        try {
//            accessTokenContextRelay.copyToken();
//        } catch (BeanCreationException e) {
//            log.warn("创建动态代理类异常,不执行token复制,允许程序继续执行 具体错误: {}", e.getMessage());
//        }
//        if (oAuth2ClientContext != null) {
//            OAuth2AccessToken token = null;
//            try {
//                token = oAuth2ClientContext.getAccessToken();
//            } catch (BeanCreationException e) {
//                log.warn("创建动态代理类异常,不执行token复制,允许程序继续执行 具体错误: {}", e.getMessage());
//            }
//            if (token != null) {
//                super.apply(template);
//            }
//        }
//    }
//}
