package com.hnevkop.shortener.backend.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class URL {
    @Id
    private String shortUrl;
    private String originalUrl;
}