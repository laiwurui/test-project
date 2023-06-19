package org.pedia.starter.security.jwe.jwt;


import com.alibaba.fastjson.JSONObject;
import org.pedia.starter.security.jwe.jwt.properties.JWTProperties;
import org.pedia.starter.security.util.EncryptUtil;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import javax.crypto.Cipher;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.Key;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EncryptJwtDecoderProxy implements InvocationHandler  {

    private final JWTProperties jwtProperties;

    private final JwtDecoder jwtDecoder;

    public EncryptJwtDecoderProxy(JWTProperties jwtProperties, JwtDecoder jwtDecoder) {
        this.jwtProperties = jwtProperties;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(jwtDecoder, args);
        if(method.getName().equals("decode")) {
            Jwt jwt = (Jwt) result;
            Map<String, Object> claims = jwt.getClaims();

            Key desKeySpec = EncryptUtil.getDESKeySpec(jwtProperties.getKey());
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, desKeySpec);
            List<Long> bs = (List)claims.get("content");
            byte[] bytes = new byte[bs.size()];
            for (int i = 0; i < bs.size(); i++) {
                bytes[i] = bs.get(i).byteValue();
            }
            String decryptSource = new String(cipher.doFinal(bytes));

            Jwt.Builder builder = Jwt.withTokenValue(jwt.getTokenValue());
            Set<Map.Entry<String, Object>> entries = JSONObject.parseObject(decryptSource).entrySet();
            for (Map.Entry<String, Object> entry : entries) {

                if (JwtClaimNames.IAT.equals(entry.getKey())
                        || JwtClaimNames.EXP.equals(entry.getKey())
                        || JwtClaimNames.NBF.equals(entry.getKey()) ) {
                    Instant parse = Instant.parse(entry.getValue().toString());
                    builder.claim(entry.getKey(), parse);
                } else {
                    builder.claim(entry.getKey(), entry.getValue());
                }
            }
            Map<String, Object> headers = jwt.getHeaders();
            Set<Map.Entry<String, Object>> headerEntries = headers.entrySet();
            for (Map.Entry<String, Object> headerEntry : headerEntries) {
                builder.header(headerEntry.getKey(), headerEntry.getValue());
            }
            result = builder.build();
        }

        return result;
    }
}
