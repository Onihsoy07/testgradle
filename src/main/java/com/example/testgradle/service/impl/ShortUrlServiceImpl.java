package com.example.testgradle.service.impl;

import com.example.testgradle.data.dto.NaverUriDto;
import com.example.testgradle.data.dto.ShortUrlDto;
import com.example.testgradle.data.entity.ShortUrlEntity;
import com.example.testgradle.data.repository.ShortUrlRepository;
import com.example.testgradle.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    ShortUrlRepository shortUrlRepository;

    ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    private Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

    @Override
    public ShortUrlDto generateShortUrl(String clientId, String clientSecret, String originalUrl) {
        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);
        shortUrlRepository.save(shortUrlEntity);

        ShortUrlDto shortUrlDto = new ShortUrlDto(orgUrl, shortUrl);
        return shortUrlDto;
    }

    @Override
    public ShortUrlDto getShortUrl(String clientId, String clientSecret, String originalUrl) {
        return null;
    }

    @Override
    public ShortUrlDto updateShortUrl(String clientId, String clientSecret, String originalUrl) {
        return null;
    }

    @Override
    public ShortUrlDto deleteByShortUrl(String shortlUrl) {
        return null;
    }

    @Override
    public ShortUrlDto deleteByOriginalUrl(String originalUrl) {
        return null;
    }

    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret, String originalUrl) {
        LOGGER.info("[requestShortUrl] client ID : {}, client Secret : {}, original URL : {}", clientId, clientSecret, originalUrl);

        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/util/shorturl")
            .queryParam("url", originalUrl)
            .encode()
            .build()
            .toUri();

        LOGGER.info("[requestShortUrl] set HTTP request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDto> responseEntity = null;
        try{ responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, NaverUriDto.class); }
        catch (Exception e) { e.printStackTrace(); }

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }


}
