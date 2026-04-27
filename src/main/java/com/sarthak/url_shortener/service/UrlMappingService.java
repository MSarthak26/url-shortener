package com.sarthak.url_shortener.service;


import com.sarthak.url_shortener.dto.UrlResponse;
import com.sarthak.url_shortener.dto.UrlStatsResponse;

public interface UrlMappingService {
    public UrlResponse createShortUrl(String longUrl);
    public String getOriginalUrl(String shortCode);
    public UrlStatsResponse getStats(String shortCode);
}
