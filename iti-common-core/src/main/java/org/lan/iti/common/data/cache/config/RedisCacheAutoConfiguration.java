/*
 *
 *  * Copyright (c) [2019-2020] [NorthLan](lan6995@gmail.com)
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

package org.lan.iti.common.data.cache.config;

import org.lan.iti.common.data.cache.ITIRedisCacheManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展 redis-cache
 * <p>
 * 支持注解形式的ttl key: xx#ttl -> testKey:10   (s)
 * </p>
 *
 * @author NorthLan
 * @date 2020-04-11
 * @url https://noahlan.com
 */
@Configuration
@ConditionalOnClass(RedisConnectionFactory.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConditionalOnBean(RedisConnectionFactory.class)
@ConditionalOnMissingBean(CacheManager.class)
@EnableConfigurationProperties(CacheProperties.class)
public class RedisCacheAutoConfiguration {
    private final CacheProperties cacheProperties;
    private final CacheManagerCustomizers cacheManagerCustomizers;

    public RedisCacheAutoConfiguration(CacheProperties cacheProperties,
                                       CacheManagerCustomizers cacheManagerCustomizers) {
        this.cacheProperties = cacheProperties;
        this.cacheManagerCustomizers = cacheManagerCustomizers;
    }

    @Bean
    public RedisCacheManager cacheManager(ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                                          RedisConnectionFactory redisConnectionFactory,
                                          ResourceLoader resourceLoader) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        RedisCacheConfiguration cacheConfiguration = this.determineConfiguration(redisCacheConfiguration, resourceLoader.getClassLoader());
        List<String> cacheNames = this.cacheProperties.getCacheNames();
        Map<String, RedisCacheConfiguration> initialCaches = new LinkedHashMap<>();
        if (!cacheNames.isEmpty()) {
            Map<String, RedisCacheConfiguration> cacheConfigMap = new LinkedHashMap<>(cacheNames.size());
            cacheNames.forEach(it -> cacheConfigMap.put(it, cacheConfiguration));
            initialCaches.putAll(cacheConfigMap);
        }
        ITIRedisCacheManager cacheManager = new ITIRedisCacheManager(
                redisCacheWriter,
                cacheConfiguration,
                initialCaches, true);
        cacheManager.setTransactionAware(false);
        return this.cacheManagerCustomizers.customize(cacheManager);
    }

    private RedisCacheConfiguration determineConfiguration(ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                                                           ClassLoader classLoader) {
        return redisCacheConfiguration.getIfAvailable(() -> {
            CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
            config = config.serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                            new JdkSerializationRedisSerializer(classLoader)));
            if (redisProperties.getTimeToLive() != null) {
                config = config.entryTtl(redisProperties.getTimeToLive());
            }
            if (redisProperties.getKeyPrefix() != null) {
                config = config.prefixKeysWith(redisProperties.getKeyPrefix());
            }
            if (!redisProperties.isCacheNullValues()) {
                config = config.disableCachingNullValues();
            }
            if (!redisProperties.isUseKeyPrefix()) {
                config = config.disableKeyPrefix();
            }
            return config;
        });
    }
}
