package com.hnevkop.shortener.backend.repository;

import com.hnevkop.shortener.backend.dto.URL;
import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, String> {
}