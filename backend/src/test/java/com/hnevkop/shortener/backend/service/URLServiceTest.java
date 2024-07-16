package com.hnevkop.shortener.backend.service;

import com.hnevkop.shortener.backend.dto.URL;
import com.hnevkop.shortener.backend.repository.URLRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class URLServiceTest {

    @Autowired
    private URLService urlService;

    @MockBean
    private URLRepository urlRepository;

    @Test
    void testCreateShortURL() {
        Mockito.when(urlRepository.findById(any(String.class))).thenReturn(Optional.empty());
        Mockito.when(urlRepository.save(any(URL.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String shortUrl = urlService.createShortURL("http://example.com");
        assertThat(shortUrl).isNotBlank();
    }

    @Test
    void testGetOriginalURL() {
        String expectedOriginalUrl = "http://example.com";
        String shortUrl = "exampleShortUrl";

        URL mockURL = new URL();
        mockURL.setOriginalUrl(expectedOriginalUrl);
        mockURL.setShortUrl(shortUrl);

        Mockito.when(urlRepository.findById(eq(shortUrl))).thenReturn(Optional.of(mockURL));

        String originalUrl = urlService.getOriginalURL(shortUrl);
        assertThat(originalUrl).isEqualTo(expectedOriginalUrl);
    }
}
