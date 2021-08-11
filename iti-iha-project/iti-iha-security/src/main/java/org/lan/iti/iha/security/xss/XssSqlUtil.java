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

package org.lan.iti.iha.security.xss;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * @author NorthLan
 * @date 2021/7/28
 * @url https://blog.noahlan.com
 */
@UtilityClass
public class XssSqlUtil {
    private static final String STR_SCRIPT1 = "<script>(.*?)</script>";
    private static final String STR_SCRIPT2 = "</script>";
    private static final String STR_SCRIPT3 = "<script(.*?)>";
    private static final String STR_EVAL = "eval\\((.*?)\\)";
    private static final String STR_EXP = "expression\\((.*?)\\)";
    private static final String STR_JS = "javascript:";
    private static final String STR_VB = "vbscript:";
    private static final String STR_ON = "onload(.*?)=";
    private static final String SQL = "('.+--)|(%7C)";

    private static final Pattern SCRIPT1_PATTERN = Pattern.compile(STR_SCRIPT1, Pattern.CASE_INSENSITIVE);
    private static final Pattern SCRIPT2_PATTERN = Pattern.compile(STR_SCRIPT2, Pattern.CASE_INSENSITIVE);
    private static final Pattern SCRIPT3_PATTERN = Pattern.compile(STR_SCRIPT3,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern STR_EVAL_PATTERN = Pattern.compile(STR_EVAL,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern STR_EXP_PATTERN = Pattern.compile(STR_EXP,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern STR_JS_PATTERN = Pattern.compile(STR_JS, Pattern.CASE_INSENSITIVE);
    private static final Pattern STR_VB_PATTERN = Pattern.compile(STR_VB, Pattern.CASE_INSENSITIVE);
    private static final Pattern STR_ON_PATTERN = Pattern.compile(STR_ON,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SQL_PATTERN = Pattern.compile(SQL);


    /**
     * filter Web xss content
     *
     * @param value content
     * @return java.lang.String content
     */
    public static String stripXss(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.

            rlt = value.replaceAll("", "");

            // Avoid anything between script tags
            rlt = SCRIPT1_PATTERN.matcher(rlt).replaceAll("");

            // Remove any lonesome </script> tag
            rlt = SCRIPT2_PATTERN.matcher(rlt).replaceAll("");

            // Remove any lonesome <script ...> tag
            rlt = SCRIPT3_PATTERN.matcher(rlt).replaceAll("");

            // Avoid eval(...) expressions
            rlt = STR_EVAL_PATTERN.matcher(rlt).replaceAll("");

            // Avoid expression(...) expressions
            rlt = STR_EXP_PATTERN.matcher(rlt).replaceAll("");

            // Avoid javascript:... expressions
            rlt = STR_JS_PATTERN.matcher(rlt).replaceAll("");

            // Avoid vbscript:... expressions
            rlt = STR_VB_PATTERN.matcher(rlt).replaceAll("");

            // Avoid onload= expressions
            rlt = STR_ON_PATTERN.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /**
     * filter sql inject content
     *
     * @param value content
     * @return java.lang.String content
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : SQL_PATTERN.matcher(value).replaceAll("");
    }

    /**
     * filter sql inject and xss content
     *
     * @param value content
     * @return java.lang.String content
     */
    public static String stripSqlXss(String value) {
        return stripXss(stripSqlInjection(value));
    }
}
