//package com.bnb.controller;
//
//import com.bnb.service.OpenAiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/chat")
//public class ChatController {
//
//    @Autowired
//    private OpenAiService openAiService;
//
//    @PostMapping("/gpt")
//    public ResponseEntity<String> getChatResponse(@RequestBody Map<String, String> request) {
//        String prompt = request.get("prompt");
//        String response = openAiService.getChatGptResponse(prompt);
//        return ResponseEntity.ok(response);
//    }
//}
//
