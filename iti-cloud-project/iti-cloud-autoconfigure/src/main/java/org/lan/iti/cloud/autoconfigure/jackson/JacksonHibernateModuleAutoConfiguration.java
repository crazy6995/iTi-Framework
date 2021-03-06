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

package org.lan.iti.cloud.autoconfigure.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.hibernate.Hibernate;
import org.lan.iti.common.core.util.ReflectUtil;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate5 Jackson模块自动装配
 * 1. 懒加载序列化问题
 * 2. 无限递归
 *
 * @author NorthLan
 * @date 2020-12-03
 * @url https://noahlan.com
 */
@Configuration
@ConditionalOnClass({ObjectMapper.class, Hibernate.class, Transient.class, Hibernate5Module.class})
@AutoConfigureAfter(ITIJacksonAutoConfiguration.class)
public class JacksonHibernateModuleAutoConfiguration {

    @SuppressWarnings("unchecked")
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer hibernateJacksonModuleCustomizer() {
        return builder -> {
            // 由于builder.modules会覆盖原设置,通过反射获取builder原先modules值
            Object originalModulesObj = ReflectUtil.getFieldValue(builder, "modules");
            List<Module> modules = new ArrayList<>();
            if (originalModulesObj instanceof List) {
                modules.addAll((List<Module>) originalModulesObj);
            }
            // hibernate5
            modules.add(new Hibernate5Module());
            builder.modules(modules);
        };
    }
}
