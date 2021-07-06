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

package org.lan.iti.cloud.iha.server.endpoint;

import org.lan.iti.cloud.iha.core.util.RequestUtil;
import org.lan.iti.cloud.iha.server.model.enums.ErrorResponse;
import org.lan.iti.cloud.iha.server.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author NorthLan
 * @date 2021-07-06
 * @url https://noahlan.com
 */
public class ErrorEndpoint extends AbstractEndpoint {
    /**
     * Generate the html of the error authorization page
     *
     * @param error            error type
     * @param errorDescription error description
     * @return error page html
     */
    public String createErrorPageHtml(String error, String errorDescription) {
        return generateErrorPageHtml(error, errorDescription);
    }

    /**
     * Obtain exception information from the request url and display the exception page
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @throws IOException IOException
     */
    public void showErrorPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.getByError(RequestUtil.getParam("error", request));
        String errorPageHtml = createErrorPageHtml(errorResponse.getError(), errorResponse.getErrorDescription());
        response.setContentType("text/html;charset=UTF-8");
        response.setContentLength(errorPageHtml.getBytes(StandardCharsets.UTF_8).length);
        response.getWriter().write(errorPageHtml);
    }

    /**
     * Display customized exception content
     *
     * @param error            error type
     * @param errorDescription error description
     * @param response         current HTTP response
     * @throws IOException IOException
     */
    public void showErrorPage(String error, String errorDescription, HttpServletResponse response) throws IOException {
        String errorPageHtml = createErrorPageHtml(error, errorDescription);
        response.setContentType("text/html;charset=UTF-8");
        response.setContentLength(errorPageHtml.getBytes(StandardCharsets.UTF_8).length);
        response.getWriter().write(errorPageHtml);
    }


    private String generateErrorPageHtml(String error, String errorDescription) {
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "  <head>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                + "    <meta name=\"description\" content=\"\">\n"
                + "    <meta name=\"author\" content=\"\">\n"
                + "    <title>Oops!, something went wrong</title>\n"
                + "    <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">\n"
                + "  </head>\n"
                + "  <body>\n"
                + "     <div class=\"container text-center\" style=\"margin-top: 10%;\">\n"
                + "        <p><h1>Oops!, something went wrong</h1></p>\n";
        if (StringUtil.isNotEmpty(error)) {
            html += "<p>" + error + "</p>";
        }
        html += "        <p>\n" + errorDescription + "        </p>\n"
                + "        <p>Feel free to contact us.</p>\n"
                + "        <p>Please try again.</p>\n" +
                "</div>\n" +
                "</body></html>";
        return html;
    }
}
