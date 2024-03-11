package com.rwa.user.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Pattern;

@Slf4j
public class UserUtil {

    private static Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

    public static boolean matchPassword(final String rawInputPassword, final String encryptedReferencePassword) {
        if (!BCRYPT_PATTERN.matcher(encryptedReferencePassword).matches()) {
            log.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return BCrypt.checkpw(rawInputPassword, encryptedReferencePassword);
    }
}
