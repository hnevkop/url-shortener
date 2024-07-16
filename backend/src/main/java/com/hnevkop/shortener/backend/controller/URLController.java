package com.hnevkop.shortener.backend.controller;

import com.hnevkop.shortener.backend.service.URLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/urls")
@CrossOrigin(origins = "http://localhost:3000")
public class URLController {
    @Autowired
    private URLService urlService;

    @PostMapping
    public String shorten(@RequestBody UrlRequest urlRequest) {
        log.info("Shortening URL: {}", urlRequest.url());
        return urlService.createShortURL(urlRequest.url());
    }

    @GetMapping
    public String resolve(@RequestParam("url") String shortenedUrl) {
        var originalUrl = urlService.getOriginalURL(shortenedUrl);
        log.info("Original URL: {}", originalUrl);
        return originalUrl;
    }
}