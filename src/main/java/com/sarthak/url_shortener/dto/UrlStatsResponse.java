package com.sarthak.url_shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlStatsResponse {
    private String shortUrl;
    private String originalUrl;
    private Long clickCount;
}
