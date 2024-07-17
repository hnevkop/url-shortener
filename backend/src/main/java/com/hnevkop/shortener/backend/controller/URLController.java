package com.hnevkop.shortener.backend.controller;

import com.hnevkop.shortener.backend.service.URLService;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/urls")
@CrossOrigin(origins = "http://localhost:3000")
public class URLController {
    @Autowired
    private URLService urlService;
    @Autowired
    private GenericResponseService responseBuilder;

    @PostMapping
    public String shorten(@RequestBody UrlRequest urlRequest) {
        String responseUrl= urlService.createShortURL(urlRequest.url());
        log.info("Shortening URL: {} to {}", urlRequest.url(), responseUrl);
        return responseUrl;
    }

    @GetMapping("/{shortenedUrl}")
    public String resolve(@PathVariable("shortenedUrl") String shortenedUrl) {
        var originalUrl = urlService.getOriginalURL(shortenedUrl);
        log.info("resolving original URL: {} to {} ", originalUrl, shortenedUrl);
        return originalUrl;
    }
}