package hu.cj.reservation.services;

import hu.cj.reservation.config.TokenRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeyCloakService {

//    @Value("${clien.id}")
//    private String clientID;
//
//    @Value("${post.url}")
//    private String postUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenRequest tokenRequest;

    //    @Value("${post.url}")
//    private String url;
    @Autowired
    private Environment env;

    public String getToken(String username, String password) {
        HttpEntity<MultiValueMap<String, String>> request =
                new TokenRequest.Builder("reservation-login", "password")
                        .add("username", username)
                        .add("password", password)
                        .add("scope", "openid")
                        .build();
        ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("post.url"), request, String.class);
        return response.getBody();
    }
}
