//package com.bnb.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class OpenAiService {
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
//
//    public String getChatGptResponse(String prompt) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        // Construct the body
//        Map<String, Object> body = new HashMap<>();
//        body.put("model", "gpt-3.5-turbo");
//        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        // Send the POST request
//        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
//
//        return response.getBody();
//    }
//}
