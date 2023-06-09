package org.pedia.system;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OAuth2AuthorizationServerApplicationITests {

    private static final String CLIENT_ID = "messaging-client";

    private static final String CLIENT_SECRET = "secret";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void performTokenRequestWhenValidClientCredentialsThenOk() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/token")
                        .param("grant_type", "client_credentials")
                        .param("scope", "message:read")
                        .with(basicAuth(CLIENT_ID, CLIENT_SECRET)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.expires_in").isNumber())
                .andExpect(jsonPath("$.scope").value("message:read"))
                .andExpect(jsonPath("$.token_type").value("Bearer"));
        // @formatter:on
    }

    @Test
    void performTokenRequestWhenMissingScopeThenOk() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/token")
                        .param("grant_type", "client_credentials")
                        .param("scope", "message:read message:write")
                        .with(basicAuth(CLIENT_ID, CLIENT_SECRET)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.expires_in").isNumber())
                .andExpect(jsonPath("$.scope").value("message:read message:write"))
                .andExpect(jsonPath("$.token_type").value("Bearer"));
        // @formatter:on
    }

    @Test
    void performTokenRequestWhenInvalidClientCredentialsThenUnauthorized() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/token")
                        .param("grant_type", "client_credentials")
                        .param("scope", "message:read")
                        .with(basicAuth("bad", "password")))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("invalid_client"));
        // @formatter:on
    }

    @Test
    void performTokenRequestWhenMissingGrantTypeThenUnauthorized() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/token")
                        .with(basicAuth("bad", "password")))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("invalid_client"));
        // @formatter:on
    }

    @Test
    void performTokenRequestWhenGrantTypeNotRegisteredThenBadRequest() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/token")
                        .param("grant_type", "client_credentials")
                        .with(basicAuth("login-client", "secret")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("unauthorized_client"));
        // @formatter:on
    }

    @Test
    void performIntrospectionRequestWhenValidTokenThenOk() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/introspect")
                        .param("token", getAccessToken())
                        .with(basicAuth(CLIENT_ID, CLIENT_SECRET)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value("true"))
                .andExpect(jsonPath("$.aud[0]").value(CLIENT_ID))
                .andExpect(jsonPath("$.client_id").value(CLIENT_ID))
                .andExpect(jsonPath("$.exp").isNumber())
                .andExpect(jsonPath("$.iat").isNumber())
                .andExpect(jsonPath("$.iss").value("http://localhost:9000"))
                .andExpect(jsonPath("$.nbf").isNumber())
                .andExpect(jsonPath("$.scope").value("message:read"))
                .andExpect(jsonPath("$.sub").value(CLIENT_ID))
                .andExpect(jsonPath("$.token_type").value("Bearer"));
        // @formatter:on
    }

    @Test
    void performIntrospectionRequestWhenInvalidCredentialsThenUnauthorized() throws Exception {
        // @formatter:off
        this.mockMvc.perform(post("/oauth2/introspect")
                        .param("token", getAccessToken())
                        .with(basicAuth("bad", "password")))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("invalid_client"));
        // @formatter:on
    }

    private String getAccessToken() throws Exception {
        // @formatter:off
        MvcResult mvcResult = this.mockMvc.perform(post("/oauth2/token")
                        .param("grant_type", "client_credentials")
                        .param("scope", "message:read")
                        .with(basicAuth(CLIENT_ID, CLIENT_SECRET)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists())
                .andReturn();
        // @formatter:on

        String tokenResponseJson = mvcResult.getResponse().getContentAsString();
        Map<String, Object> tokenResponse = this.objectMapper.readValue(tokenResponseJson, new TypeReference<Map<String, Object>>() {
        });

        return tokenResponse.get("access_token").toString();
    }

    private static BasicAuthenticationRequestPostProcessor basicAuth(String username, String password) {
        return new BasicAuthenticationRequestPostProcessor(username, password);
    }

    private static final class BasicAuthenticationRequestPostProcessor implements RequestPostProcessor {

        private final String username;

        private final String password;

        private BasicAuthenticationRequestPostProcessor(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(this.username, this.password);
            request.addHeader("Authorization", headers.getFirst("Authorization"));
            return request;
        }

    }

}
