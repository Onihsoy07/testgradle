package com.example.testgradle.service.impl;

import com.example.testgradle.data.dto.NaverUriDto;
import com.example.testgradle.data.dto.ShortUrlDto;
import com.example.testgradle.data.entity.ShortUrlEntity;
import com.example.testgradle.data.repository.ShortUrlRepository;
import com.example.testgradle.mapping.ShorturlMapping;
import com.example.testgradle.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
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

        LOGGER.info("[getShortUrl] request data : {}", originalUrl);
        Optional<ShortUrlEntity> res = shortUrlRepository.findByOrgUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        LOGGER.info("[getShortUrl] getEntity : {}", res);

        if(res.isEmpty()) {
            LOGGER.info("[getShortUrl] No Entity in Datebase.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();

        } else {
            orgUrl = res.get().getOrgUrl();
            shortUrl = res.get().getUrl();
        }

        ShortUrlDto shortUrlDto = new ShortUrlDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Responese DTO : {}", shortUrlDto.toString());
        return shortUrlDto;
    }

    @Override
    public ShortUrlDto updateShortUrl(String clientId, String clientSecret, String originalUrl) {
        return null;
    }

    @Override
    public ShortUrlDto deleteByUrl(String url) {

        ShortUrlEntity shortUrlEntity;
        if(url.contains("me2.do")) { shortUrlEntity = deleteByShortUrl(url); }
        else { shortUrlEntity = deleteByOriginalUrl(url); }

        shortUrlRepository.delete(shortUrlEntity);
        LOGGER.info("[deleteByUrl] Entity is deleted");

        return ShorturlMapping.convertToDto(shortUrlEntity);
    }

    private ShortUrlEntity deleteByOriginalUrl(String url) {
        Optional<ShortUrlEntity> res = shortUrlRepository.findByOrgUrl(url);
        if(res.isEmpty()) {
            LOGGER.info("[deleteByUrl] No Database in Entity");
            throw new IllegalArgumentException(String.format("%s의 url을 찾을 수 없습니다.", url));
        }
        return res.get();
    }

    private ShortUrlEntity deleteByShortUrl(String url) {
        Optional<ShortUrlEntity> res = shortUrlRepository.findByUrl(url);
        if(res.isEmpty()) {
            LOGGER.info("[deleteByUrl] No Database in Entity");
            throw new IllegalArgumentException(String.format("%s의 url을 찾을 수 없습니다.", url));
        }
        return res.get();
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
