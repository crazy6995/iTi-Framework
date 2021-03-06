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

package org.lan.iti.cloud.sequence.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Snowflake 属性
 *
 * @author NorthLan
 * @date 2020-05-06
 * @url https://noahlan.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "iti.sequence.snowflake")
public class SequenceSnowflakeProperties extends BaseProperties {
    /**
     * 工作机器ID，必须由外部设定，最大值 2^workerIdBitLength-1
     */
    private long workerId = 0;

    /**
     * 基础时间
     * 不能超过当前系统时间
     */
    private long baseTime = System.currentTimeMillis();

    /**
     * 是否使用优化后的系统时钟
     */
    private boolean useSystemClock = true;
}
