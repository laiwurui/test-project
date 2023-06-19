package org.pedia.starter.security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class EncryptUtil {

    private static final Logger log = LoggerFactory.getLogger(EncryptUtil.class);

    public static Key getDESKeySpec(String secretKey) {
        try {
            byte[] bytes = parseTo8ByteArray(secretKey);
            DESKeySpec desKeySpec = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            return factory.generateSecret(desKeySpec);
        } catch (Exception e) {
            log.error("获取DES KEY失败！", e);
            throw new RuntimeException(e);
        }
    }

    private static byte[] parseTo8ByteArray(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        int times = (bytes.length + 8) / 8;
        byte[] b = new byte[8];
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < b.length; j++) {
                int index = i * 8 + j;
                if(index < bytes.length) {
                    b[j] = (byte) (b[j] ^ bytes[index]);
                }
            }
        }
        return b;
    }
}
