package com.example.testgradle.service;

import com.example.testgradle.data.dto.ShortUrlDto;

public interface ShortUrlService {

    ShortUrlDto getShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlDto generateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlDto updateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlDto deleteByUrl(String shortlUrl);


}
