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

package org.lan.iti.cloud.iha.server.exception;

import org.lan.iti.cloud.iha.server.model.enums.ErrorResponse;

/**
 * @author NorthLan
 * @date 2021-07-06
 * @url https://noahlan.com
 */
public class InvalidJwksException extends IhaServerException {
    private static final long serialVersionUID = -2865821140925306743L;

    public InvalidJwksException(String message) {
        super(message);
    }

    public InvalidJwksException(String error, String errorDescription) {
        super(error, errorDescription);
    }

    public InvalidJwksException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
