package com.fastcampus.boardserver.util;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;

@Log4j2
public class SHA256Util {

  public static final String ENCRYPTION_KEY = "SHA-256";

  public static String encryptSHA256(String str) {
    String SHA = null;

    MessageDigest md;

    try {
      md = MessageDigest.getInstance(ENCRYPTION_KEY);
      md.update(str.getBytes());
      byte[] digest = md.digest();
      StringBuffer sb = new StringBuffer();
      for (byte byteDatum : digest) {
        sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
      }
      SHA = sb.toString();

    } catch (Exception e) {
      log.error("encryptySHA256 error : {}", e.getMessage());
      SHA = null;
    }

    return SHA;
  }

}
