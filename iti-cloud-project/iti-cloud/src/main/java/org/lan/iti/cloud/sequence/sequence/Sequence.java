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

package org.lan.iti.cloud.sequence.sequence;

import org.lan.iti.cloud.sequence.exception.SequenceException;

/**
 * 序列号生成接口
 *
 * @author NorthLan
 * @date 2020-05-06
 * @url https://noahlan.com
 */
public interface Sequence {
    /**
     * 生成下一个序列号
     *
     * @return 序列号
     * @throws SequenceException 生成异常
     */
    long next() throws SequenceException;

    /**
     * 生成下一个序列号（带格式）
     *
     * @return 带格式的序列号
     * @throws SequenceException 生成异常
     */
    String nextStr() throws SequenceException;

    /**
     * 生成下一个序列号（带格式 String.format）
     *
     * @param format 格式
     * @return 带格式的序列号
     * @throws SequenceException 生成异常
     */
    String nextStr(String format) throws SequenceException;
}
