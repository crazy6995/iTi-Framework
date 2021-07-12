/*
 * Copyright (c) 2020-2040, 北京符节科技有限公司 (support@fujieid.com & https://www.fujieid.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lan.iti.cloud.iha.mfa;

/**
 * The cryptographic hash function used to calculate the HMAC (Hash-based Message Authentication Code).
 * <p>
 * This implementation uses the SHA1 hash function by default.
 *
 * @author NorthLan
 * @date 2021-07-12
 * @url https://noahlan.com
 */
public enum IhaMfaAlgorithm {
    /**
     * SHA1
     */
    HmacSHA1,
    /**
     * SHA256
     */
    HmacSHA256,
    /**
     * SHA512
     */
    HmacSHA512
}
