/*
 * Copyright (c) [2019-2020] [NorthLan](lan6995@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lan.iti.common.gateway.annotation;

import org.lan.iti.common.gateway.annotation.enums.DynamicType;
import org.lan.iti.common.gateway.configuration.DynamicRouteConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启动态路由
 *
 * @author NorthLan
 * @date 2020-03-09
 * @url https://noahlan.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Import(DynamicRouteConfigurationSelector.class)
public @interface EnableITIDynamicRoute {
    /**
     * 动态路由类型
     */
    DynamicType value() default DynamicType.NACOS_CONFIG;
}