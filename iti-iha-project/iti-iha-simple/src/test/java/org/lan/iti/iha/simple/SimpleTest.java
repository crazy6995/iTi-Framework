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

package org.lan.iti.iha.simple;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lan.iti.iha.security.IhaSecurity;
import org.lan.iti.iha.security.authentication.Authentication;
import org.lan.iti.iha.security.context.IhaSecurityContext;
import org.lan.iti.iha.security.context.SecurityContextHolder;
import org.lan.iti.iha.security.pipeline.AuthenticationFailureHandler;
import org.lan.iti.iha.security.pipeline.AuthenticationSuccessHandler;
import org.lan.iti.iha.security.pipeline.PreAuthenticationHandler;
import org.lan.iti.iha.security.userdetails.UserDetails;
import org.lan.iti.iha.security.userdetails.UserDetailsService;
import org.lan.iti.iha.simple.security.SimpleRequestParameter;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author NorthLan
 * @date 2021/8/9
 * @url https://blog.noahlan.com
 */
public class SimpleTest {

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        UserDetailsService service = Mockito.mock(UserDetailsService.class);
        Mockito.when(service.loadByType("test", "username", null))
                .thenReturn(new UserDetails()
                        .setPrincipal("test")
                        .setCredentials("test")
                        .setAuthorities(new ArrayList<>()));

        IhaSecurityContext context = new IhaSecurityContext();
        context.getPipelineManager().registerPipeline(Arrays.asList(
                (PreAuthenticationHandler) (parameter) -> System.out.println("preAuthentication"),
                (AuthenticationSuccessHandler) (parameter, authentication) -> System.out.println("AuthenticationSuccessHandler$1"),
                (AuthenticationSuccessHandler) (parameter, authentication) -> System.out.println("AuthenticationSuccessHandler$2"),
                (AuthenticationFailureHandler) (parameter, exception) -> System.out.println("AuthenticationFailureHandler")
        ));
        context.setUserDetailsService(service);

        IhaSecurity.init(context);
    }

    @Test
    public void testLogin() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Map<String, String[]> map = new TreeMap<>();
        map.put("principal", new String[]{"test"});
        map.put("credentials", new String[]{"test"});
        map.put("type", new String[]{"username"});
        map.put("rememberMe", new String[]{"true"});

        Mockito.when(request.getParameterMap()).thenReturn(map);

        SimpleRequestParameter parameter = new SimpleRequestParameter(request, response);

        Authentication authentication = IhaSecurity.getContext().getSecurityManager().authenticate(parameter);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
    }
}
