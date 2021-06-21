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

package org.lan.iti.cloud.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author NorthLan
 * @date 2021-05-19
 * @url https://noahlan.com
 */
@Slf4j
public class StrategyApplicationListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private final AtomicBoolean once = new AtomicBoolean();
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (!once.compareAndSet(false, true)) {
            log.warn("register applicationContext more than once, ignored!");
            return;
        }

        RegistryFactory.init();
        RegistryFactory.register(entry -> applicationContext.getBeansWithAnnotation(entry.getAnnotation()).values());

        StrategyApplicationListener.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (event.getApplicationContext().equals(applicationContext)) {
            // TODO remove ignored
        }
    }
}