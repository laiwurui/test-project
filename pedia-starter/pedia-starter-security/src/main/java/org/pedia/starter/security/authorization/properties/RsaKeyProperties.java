package org.pedia.starter.security.authorization.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ConfigurationProperties(prefix = "spring.security.key-pair")
public class RsaKeyProperties {

    private String publicKey;

    private String privateKey;

    private Resource publicKeyLocation;

    private Resource privateKeyLocation;

    public RsaKeyProperties() {
    }

    public RsaKeyProperties(String publicKey, String privateKey, Resource publicKeyLocation, Resource privateKeyLocation) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.publicKeyLocation = publicKeyLocation;
        this.privateKeyLocation = privateKeyLocation;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Resource getPublicKeyLocation() {
        return publicKeyLocation;
    }

    public void setPublicKeyLocation(Resource publicKeyLocation) {
        this.publicKeyLocation = publicKeyLocation;
    }

    public Resource getPrivateKeyLocation() {
        return privateKeyLocation;
    }

    public void setPrivateKeyLocation(Resource privateKeyLocation) {
        this.privateKeyLocation = privateKeyLocation;
    }

    public String readPublicKey() throws IOException {
        String key = "spring.security.key-pair.public-key-location";
        Assert.notNull(this.publicKeyLocation, "PublicKeyLocation must not be null");
        if (!this.publicKeyLocation.exists()) {
            throw new InvalidConfigurationPropertyValueException(key, this.publicKeyLocation,
                    "Public key location does not exist");
        }
        try (InputStream inputStream = this.publicKeyLocation.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
    }

    public String readPrivateKey() throws IOException {
        String key = "spring.security.key-pair.private-key-location";
        Assert.notNull(this.privateKeyLocation, "PublicKeyLocation must not be null");
        if (!this.privateKeyLocation.exists()) {
            throw new InvalidConfigurationPropertyValueException(key, this.privateKeyLocation,
                    "Public key location does not exist");
        }
        try (InputStream inputStream = this.privateKeyLocation.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
    }

    @Override
    public String toString() {
        return "RsaKeyProperties{" +
                "publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
