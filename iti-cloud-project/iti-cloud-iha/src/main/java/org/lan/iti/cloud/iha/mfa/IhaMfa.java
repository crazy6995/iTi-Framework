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

package org.lan.iti.cloud.iha.mfa;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.warrenstrange.googleauth.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author NorthLan
 * @date 2021-07-12
 * @url https://noahlan.com
 */
@Slf4j
public class IhaMfa {
    @Getter
    private final GoogleAuthenticator authenticator;
    private final IhaMfaConfig mfaConfig;

    public IhaMfa(ICredentialRepository credentialRepository, IhaMfaConfig config) {
        this.mfaConfig = config;
        this.authenticator = new GoogleAuthenticator(new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setCodeDigits(mfaConfig.getDigits())
                .setTimeStepSizeInMillis(mfaConfig.getPeriod())
                .setHmacHashFunction(HmacHashFunction.valueOf(mfaConfig.getAlgorithm().name()))
                .build());
        authenticator.setCredentialRepository(credentialRepository);
    }

    public IhaMfa(ICredentialRepository credentialRepository) {
        this(credentialRepository, new IhaMfaConfig());
    }

    public String getSecretKey(String username) {
        return authenticator.getCredentialRepository().getSecretKey(username);
    }

    /**
     * Returns the URL of totp
     *
     * @param username The user name
     * @param issuer   The issuer name. This parameter cannot contain the colon (:) character.
     * @return {@code String}
     */
    public String getTotpUrl(String username, String issuer) {
        final GoogleAuthenticatorKey key = authenticator.createCredentials(username);
        return GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(issuer, username, key);
    }

    /**
     * Returns the URL of a Google Chart API call to generate a QR barcode to be loaded into the Google Authenticator application.
     * <p>
     * The user scans this bar code with the application on their smart phones or enters the secret manually.
     *
     * @param username The user name
     * @param issuer   The issuer name. This parameter cannot contain the colon (:) character.
     * @return {@code String}
     */
    public String getOtpQrCodeUrl(String username, String issuer) {
        final GoogleAuthenticatorKey key = authenticator.createCredentials(username);
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(issuer, username, key);
    }

    /**
     * One time password verification by user name
     *
     * @param username The user name
     * @param otpCode  The totp code
     * @return {@code bool}
     */
    public boolean verifyByUsername(String username, int otpCode) {
        return authenticator.authorizeUser(username, otpCode);
    }

    /**
     * One time password verification by OTP secret key
     *
     * @param secret  The totp secret Key.
     * @param otpCode The totp code
     * @return {@code bool}
     */
    public boolean verifyBySecret(String secret, int otpCode) {
        return authenticator.authorize(secret, otpCode);
    }

    /**
     * Create OTP QR code and write it to browser through {@code HttpServletResponse}
     *
     * @param username The user name
     * @param issuer   The issuer name. This parameter cannot contain the colon (:) character.
     * @param response HttpServletResponse
     */
    public void createOtpQrcode(String username, String issuer, HttpServletResponse response) {
        try {
            QrCodeUtil.generate(getTotpUrl(username, issuer),
                    mfaConfig.getQrcodeWidth(), mfaConfig.getQrcodeHeight(),
                    mfaConfig.getQrcodeImgType(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create and return the QR code file of OTP
     *
     * @param username The user name
     * @param issuer   The issuer name. This parameter cannot contain the colon (:) character.
     * @return File
     */
    public File getOtpQrcodeFile(String username, String issuer) {
        String tempFilePath = mfaConfig.getQrcodeTempPath();
        String tempFileFullPath = tempFilePath.concat(username)
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".")
                .concat(mfaConfig.getQrcodeImgType());
        log.debug("Create QR code: {}", tempFileFullPath);
        File file = FileUtil.newFile(tempFileFullPath);
        if (FileUtil.exist(file)) {
            FileUtil.del(file);
        } else {
            FileUtil.mkParentDirs(file);
        }
        return QrCodeUtil.generate(getTotpUrl(username, issuer),
                mfaConfig.getQrcodeWidth(), mfaConfig.getQrcodeHeight(), file);
    }

    /**
     * Create and return the base64 string of OTP QR code
     *
     * @param username   The user name
     * @param issuer     The issuer name. This parameter cannot contain the colon (:) character.
     * @param deleteFile Delete temporary QR code file
     * @return String
     */
    public String getOtpQrcodeFileBase64(String username, String issuer, boolean deleteFile) {
        File imgFile = this.getOtpQrcodeFile(username, issuer);
        Image image = ImgUtil.read(imgFile);
        String base64Str = ImgUtil.toBase64DataUri(image, mfaConfig.getQrcodeImgType());
        if (deleteFile) {
            FileUtil.del(imgFile);
        }
        return base64Str;
    }
}
