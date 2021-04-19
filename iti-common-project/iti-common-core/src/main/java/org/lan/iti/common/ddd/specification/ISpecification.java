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

package org.lan.iti.common.ddd.specification;

import javax.validation.constraints.NotNull;

/**
 * 显式的业务规则，一种业务约束条件
 * <p>
 * <p>通过{@link ISpecification}，可以把业务规则显性化，而不是散落在各处，便于复用.</p>
 * <p>同时，由于{@link ISpecification}的统一定义，也可以进行编排，统一处理.</p>
 * <p>{@link ISpecification}，is part of UL(Ubiquitous Language).</p>
 *
 * @author NorthLan
 * @date 2021-02-22
 * @url https://noahlan.com
 */
public interface ISpecification<T> {

    /**
     * Check whether a candidate business object satisfies the specification: the business rule.
     *
     * @param candidate The candidate business object
     * @return true if the business rule satisfied
     */
    default boolean satisfiedBy(@NotNull T candidate) {
        return satisfiedBy(candidate, getNotification());
    }

    /**
     * Check whether a candidate business object satisfies the specification: the business rule.
     *
     * @param candidate    The candidate business object
     * @param notification Collect reasons why specification not satisfied. If null, will not collect unsatisfaction reasons.
     * @return true if the business rule satisfied
     */
    boolean satisfiedBy(@NotNull T candidate, Notification notification);


    Notification getNotification();
}
