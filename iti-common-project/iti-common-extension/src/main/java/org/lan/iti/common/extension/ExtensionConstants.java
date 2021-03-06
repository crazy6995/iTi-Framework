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

package org.lan.iti.common.extension;

/**
 * @author NorthLan
 * @date 2021-07-09
 * @url https://noahlan.com
 */
public interface ExtensionConstants {
    String PREFIX_REFLECT = "reflect#";
    String PREFIX_TYPE_CLASS = "typeClass#";

    String KEY_CLASS_LOADER = PREFIX_REFLECT + "classLoader";
    String KEY_PACKAGE_NAMES = PREFIX_REFLECT + "packageNames";
    String KEY_INTERFACE_CLASS = PREFIX_REFLECT + "interfaceClass";
    String KEY_CHECK_INTERFACE_CLASS = PREFIX_REFLECT + "checkInterfaceClass";

    // typeClass
    String KEY_TYPE = PREFIX_TYPE_CLASS + "type";
    String KEY_CLASSES = PREFIX_TYPE_CLASS + "classes";
}
