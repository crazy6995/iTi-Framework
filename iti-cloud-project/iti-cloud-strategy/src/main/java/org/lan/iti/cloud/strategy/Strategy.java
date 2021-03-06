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

import lombok.experimental.UtilityClass;
import org.lan.iti.common.extension.ExtensionLoader;

/**
 * 策略快速访问
 *
 * @author NorthLan
 * @date 2021-05-18
 * @url https://noahlan.com
 */
@UtilityClass
public class Strategy {

    public <T extends IStrategy<P>, P> T find(Class<T> clazz, P identify) {
        return ExtensionLoader.getLoader(clazz).getFirst(identify);
    }
}
