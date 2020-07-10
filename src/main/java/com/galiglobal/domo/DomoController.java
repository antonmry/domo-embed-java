package com.galiglobal.domo;

import com.fasterxml.jackson.databind.JsonNode;
import com.galiglobal.domo.dto.Authorization;
import com.galiglobal.domo.dto.DomoRequest;
import com.nimbusds.oauth2.sdk.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
public class DomoController {

    private static Logger logger = LoggerFactory.getLogger(DomoController.class);

    private static final String URL = "https://api.domo.com/v1/cards/embed/auth";

    @Value("${domo.client-id}")
    private String clientId;

    @Value("${domo.client-secret}")
    private String clientSecret;

    @Value("${domo.token-uri}")
    private String tokenUri;

    @Autowired
    WebClient client;

    @GetMapping("domo/{embedId}")
    public Mono<String> obtainSecuredResource(@PathVariable String embedId) {

        Authorization authorization = new Authorization();
        authorization.setToken(embedId);
        authorization.setPermissions(Arrays.asList("READ", "FILTER", "EXPORT"));
        DomoRequest domoRequest = new DomoRequest();
        domoRequest.setSessionLength(1440);
        domoRequest.setAuthorizations(Arrays.asList(authorization));

        Mono<String> resource = client.post()
            .uri(tokenUri)
            .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes()))
            .body(BodyInserters.fromFormData(OAuth2ParameterNames.GRANT_TYPE, GrantType.CLIENT_CREDENTIALS.getValue()))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .flatMap(tokenResponse -> {
                String accessTokenValue = tokenResponse.get("access_token")
                    .textValue();
                logger.info("Retrieved the following access token: {}", accessTokenValue);
                return client.post()
                    .uri(URL)
                    .headers(h -> h.setBearerAuth(accessTokenValue))
                    .headers(h -> h.setContentType(MediaType.APPLICATION_JSON))
                    .body(BodyInserters.fromPublisher(Mono.just(domoRequest), DomoRequest.class))
                    .retrieve()
                    .bodyToMono(String.class);
            });
        return resource.map(res -> "Retrieved the resource using a manual approach: " + res);
    }

}
