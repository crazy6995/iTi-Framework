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
//package org.lan.iti.cloud.autoconfigure.ddd;
//
//import org.lan.iti.cloud.ddd.runtime.registry.DDDBootstrap;
//import org.lan.iti.cloud.ddd.runtime.registry.RegistryFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * DDD 自动装配
// *
// * @author NorthLan
// * @date 2021-03-11
// * @url https://noahlan.com
// */
//@Configuration
//@ConditionalOnClass(value = {DDDBootstrap.class, RegistryFactory.class},
//        name = {"org.lan.iti.cloud.ddd.runtime.registry.DDDBootstrap"})
//public class DDDAutoConfiguration {
//
//    @Bean
//    public DDDBootstrap dddBootstrap() {
//        return new DDDBootstrap();
//    }
//
//    @Bean
//    public RegistryFactory registryFactory() {
//        return new RegistryFactory();
//    }
//}
