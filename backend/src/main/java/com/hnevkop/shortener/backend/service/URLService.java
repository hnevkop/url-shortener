package com.hnevkop.shortener.backend.service;

import com.hnevkop.shortener.backend.dto.URL;
import com.hnevkop.shortener.backend.repository.URLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createShortURL(String originalUrl) {
        return generateAndSaveUrl(originalUrl);
    }

    public String getOriginalURL(String shortUrl) {
        URL url = urlRepository.findById(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("Short URL not found"));
        return url.getOriginalUrl();
    }

    private String generateAndSaveUrl(String originalUrl) {
        while (true) {
            String shortUrl = generateShortUrl();
            if (!urlExists(shortUrl)) {
                URL url = createUrl(originalUrl, shortUrl);
                try {
                    urlRepository.save(url);
                    return shortUrl;
                } catch (Exception e) {
                    log.error("Could not save URL to database: {}", e.getMessage());
                }
            }
        }
    }

    private boolean urlExists(String shortUrl) {
        return urlRepository.findById(shortUrl).isPresent();
    }

    private URL createUrl(String originalUrl, String shortUrl) {
        URL url = new URL();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        return url;
    }

    /**
     * The base 32 system means there are 32 possible characters (2^5, since 32 is a power of 2).
     * Since 10 characters are created, this results in 32^10, or 1,099,511,627,776 (about 1.1 trillion) possible combinations.
     *
     * @return a randomly generated short URL string
     */
    private String generateShortUrl() {
        return new java.math.BigInteger(48, new SecureRandom()).toString(32);
    }
}