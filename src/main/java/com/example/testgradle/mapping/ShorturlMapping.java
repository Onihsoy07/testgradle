package com.example.testgradle.mapping;

import com.example.testgradle.data.dto.ShortUrlDto;
import com.example.testgradle.data.entity.ShortUrlEntity;

public class ShorturlMapping {

    public static ShortUrlDto convertToDto(ShortUrlEntity shortUrlEntity) {
        ShortUrlDto shortUrlDto = new ShortUrlDto().builder()
            .shortUrl(shortUrlEntity.getUrl())
            .orgUrl(shortUrlEntity.getOrgUrl())
            .build();
        return shortUrlDto;
    }

}
