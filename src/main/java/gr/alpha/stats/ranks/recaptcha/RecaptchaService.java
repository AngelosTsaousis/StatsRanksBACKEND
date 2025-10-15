package gr.alpha.stats.ranks.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class RecaptchaService {

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verify(String token) {
        if (token == null || token.isEmpty()) return false;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("secret", recaptchaSecret);
        body.add("response", token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        Map<String, Object> response = restTemplate.postForObject(VERIFY_URL, request, Map.class);
        if (response == null || !(Boolean) response.get("success")) return false;
        // reCAPTCHA v3 gives a score (0.0 - 1.0); higher = more human
        Double score = (Double) response.get("score");
        return score != null && score >= 0.5;
    }
}
