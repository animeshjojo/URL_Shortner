package com.example.url_shortner.Controller;

import com.example.url_shortner.Services.PublicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    private PublicService publicService;
    public RedirectController(PublicService service) {
        this.publicService = service;
    }
    @GetMapping("/{shortUrl:[a-zA-Z0-9]{6,8}}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl) {
        return publicService.redirect(shortUrl);
    }
}
