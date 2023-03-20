package com.example.testgradle.controller;

import com.example.testgradle.data.dto.ShortUrlDto;
import com.example.testgradle.service.ShortUrlService;
import com.example.testgradle.service.impl.ShortUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short-url")
public class ShortUrlController {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);

    @Value("${test.toy.short.url.id}")
    private String CLIENT_ID;

    @Value("${test.toy.short.url.id}")
    private String ClIENT_SECRET;

    ShortUrlServiceImpl shortUrlService;

    @Autowired
    ShortUrlController(ShortUrlServiceImpl shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public ShortUrlDto generateShortUrl(String originalUrl) {
//        LOGGER.info();
        return shortUrlService.generateShortUrl(CLIENT_ID, ClIENT_SECRET, originalUrl);
    }



}