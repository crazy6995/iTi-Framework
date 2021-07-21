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

package org.lan.iti.iha.social;

import cn.hutool.core.util.*;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import org.lan.iti.iha.core.exception.IhaSocialException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Processing JustAuth Request
 *
 * <p>1. {@link JustAuthRequestContext#register(String, AuthRequest)}, manual registration and automatic registration</p>
 * <p>2. {@link JustAuthRequestContext#loadRequest(String[], String[])}, automatic registration</p>
 * <p>3. {@link JustAuthRequestContext#getRequest(String, SocialConfig, AuthConfig, AuthStateCache)}, obtaining the actual AuthRequest</p>
 * <p>4. {@link JustAuthRequestContext#clearContext()}, Clear the request context</p>
 *
 * @author NorthLan
 * @date 2021-07-15
 * @url https://noahlan.com
 */
@Slf4j
public class JustAuthRequestContext {
    /**
     * Save registered AuthRequest implementation classes
     */
    private static final Map<String, Class<?>> AUTH_REQUEST_HOLDER = new HashMap<>(8);

    /**
     * Extract the rules for third-party platform names, only for implementation classes named according to
     * {@code Auth(xxxx)Request} format
     */
    private static final String REGEX = "Auth([\\w]+)Request";

    /**
     * Default scanned AuthRequest implementation class package
     */
    private static final String DEFAULT_SCAN_PACKAGES = "me.zhyd.oauth.request";

    /**
     * Default classes that do not need to be registered
     */
    private static final String[] DEFAULT_EXCLUSION_CLASS_NAMES = {"AuthDefaultRequest",
            "AbstractAuthWeChatEnterpriseRequest", "AuthRequest"};

    /**
     * When the value is {@code true}, the {@link JustAuthRequestContext#loadRequest(String[], String[])}
     * does not repeat execution
     */
    private static boolean isLoaded = false;

    /**
     * Register AuthRequest manually
     *
     * @param source      Name of the third party authorized platform
     * @param authRequest Custom implemented third-party authorized platform
     */
    public static void register(String source, AuthRequest authRequest) {
        if (StrUtil.isBlank(source) || ObjectUtil.isNull(authRequest)) {
            return;
        }
        AUTH_REQUEST_HOLDER.put(source.toUpperCase(), authRequest.getClass());
        log.debug("The AuthRequest implementation class [{}] has been registered, and the platform is named [{}]...", authRequest.getClass().getName(), source);
    }

    /**
     * Loads the default and custom implementation classes for all {@code AuthRequest} interfaces.
     * <p></p>
     * Note:
     * <p>1. the implementation class must be named in {@code Auth(xxxx)Request} format before it can be loaded normally,
     * such as {@code AuthGiteeRequest}, otherwise it will be automatically ignored.</p>
     * <p>2. if the custom implementation class is not named according to the standard format, you can register manually
     * using the {@link JustAuthRequestContext#register(String, AuthRequest)} method.</p>
     * <p>3. the final saved mapping relationship is {@code {xxxx.toUpperCase (), AuthRequest}}. `xxxx` is a string
     * extracted from parentheses in {@code Auth(xxxx)Request}</p>
     *
     * @param scanPackages        Package of {@code AuthRequest} implementation classes
     * @param exclusionClassNames Exclude classes that do not need to be registered, such as: {@code AuthDefaultRequest}、
     *                            {@code AbstractAuthWeChatEnterpriseRequest}、{@code AuthRequest}
     */
    private static void loadRequest(String[] scanPackages, String[] exclusionClassNames) {
        if (isLoaded) {
            return;
        }
        scanPackage(DEFAULT_SCAN_PACKAGES, DEFAULT_EXCLUSION_CLASS_NAMES);

        if (ArrayUtil.isNotEmpty(scanPackages)) {
            for (String scanPackage : scanPackages) {
                scanPackage(scanPackage, exclusionClassNames);
            }
        }
        isLoaded = true;
        log.debug("AuthRequest scan completed, a total of {} class files were scanned...", AUTH_REQUEST_HOLDER.size());
    }

    /**
     * Automatically scan and register the classes under the {@code scanPackage}.
     *
     * @param scanPackage         Package of {@code AuthRequest} implementation classes
     * @param exclusionClassNames Exclude classes that do not need to be registered, such as: {@code AuthDefaultRequest}、
     *                            {@code AbstractAuthWeChatEnterpriseRequest}、{@code AuthRequest}
     */
    private static void scanPackage(String scanPackage, String[] exclusionClassNames) {
        log.debug("Start scanning package path {}...", scanPackage);
        Set<Class<?>> classes = ClassUtil.scanPackage(scanPackage);
        Map<String, Class<?>> item = new HashMap<>(16);
        for (Class<?> clazz : classes) {
            String className = ClassUtil.getClassName(clazz, true);
            if (Arrays.asList(exclusionClassNames).contains(className)) {
                continue;
            }
            String source = ReUtil.get(REGEX, className, 1);
            if (StrUtil.isBlank(source)) {
                continue;
            }
            source = getRealSource(source);
            log.debug("The file {} is scanned successfully under the package path {}, and the platform name is {}....",
                    className, scanPackage, source);

            item.put(source.toUpperCase(), clazz);
        }
        AUTH_REQUEST_HOLDER.putAll(item);
        log.debug("A total of {} class files are scanned under the package path {}...", item.size(), scanPackage);
    }

    private static String getRealSource(String source) {
        switch (source) {
            case "WeChatOpen":
                source = "WECHAT_OPEN";
                break;
            case "WeChatMp":
                source = "WECHAT_MP";
                break;
            case "StackOverflow":
                source = "STACK_OVERFLOW";
                break;
            case "WeChatEnterpriseQrcode":
                source = "WECHAT_ENTERPRISE";
                break;
            case "WeChatEnterpriseWeb":
                source = "WECHAT_ENTERPRISE_WEB";
                break;
            default:
                break;
        }
        return source;
    }

    public static void clearContext() {
        AUTH_REQUEST_HOLDER.clear();
    }

    /**
     * Get the {@code AuthRequest} implementation class
     *
     * @param source       Name of the third party authorized platform
     * @param socialConfig Social config
     * @param authConfig   The configuration class of justauth
     * @param stateCache   Custom state cache
     * @return AuthRequest
     */
    public static AuthRequest getRequest(String source, SocialConfig socialConfig, AuthConfig authConfig, AuthStateCache stateCache) throws IhaSocialException {
        if (StrUtil.isBlank(source)) {
            throw new IhaSocialException("Social#Missing social source");
        }

        loadRequest(socialConfig.getScanPackages(), socialConfig.getExclusionClassNames());

        Class<?> clazz = AUTH_REQUEST_HOLDER.get(source.toUpperCase());
        if (ObjectUtil.isNull(clazz)) {
            throw new IhaSocialException("Social#Current source is not supported. ".concat(source));
        }
        if (ObjectUtil.isNull(authConfig)) {
            throw new IhaSocialException("Social#Missing AuthConfig.");
        }
        if (ObjectUtil.isNull(stateCache)) {
            return (AuthRequest) ReflectUtil.newInstance(clazz, authConfig);
        }
        return (AuthRequest) ReflectUtil.newInstance(clazz, authConfig, stateCache);
    }
}
