package org.pedia.starter.security.jwe.jwt;

import com.alibaba.fastjson.JSONObject;
import org.pedia.starter.security.authorization.token.PediaOAuth2TokenCustomizer;
import org.pedia.starter.security.jwe.jwt.properties.JWTProperties;
import org.pedia.starter.security.util.EncryptUtil;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.util.ReflectionUtils;

import javax.crypto.Cipher;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.Map;

public class JWTEncryptCustomizer extends PediaOAuth2TokenCustomizer {

    private final JWTProperties jwtProperties;

    public JWTEncryptCustomizer(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void customize(JwtEncodingContext context) {
        super.customize(context);
        JwtClaimsSet jwtClaimsSet = context.getClaims().build();
        try {
            Map<String, Object> claims = jwtClaimsSet.getClaims();
            String jsonString = JSONObject.toJSONString(claims);
            // 执行加密操作
            Key desKeySpec = EncryptUtil.getDESKeySpec(jwtProperties.getKey());
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, desKeySpec);
            byte[] result = cipher.doFinal(jsonString.getBytes());

            Field field = ReflectionUtils.findField(JwtClaimsSet.Builder.class, "claims", Map.class);
            field.setAccessible(true);
            Map m = (Map)field.get(context.getClaims());
            m.clear();
            m.put("content", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
