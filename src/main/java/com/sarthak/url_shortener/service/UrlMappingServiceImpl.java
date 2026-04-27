package com.sarthak.url_shortener.service;

import com.sarthak.url_shortener.dto.UrlResponse;
import com.sarthak.url_shortener.dto.UrlStatsResponse;
import com.sarthak.url_shortener.exception.ResourceNotFoundException;
import com.sarthak.url_shortener.model.UrlMapping;
import com.sarthak.url_shortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlMappingServiceImpl implements UrlMappingService{

    @Autowired
    public UrlMappingRepository urlMappingRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    @Override
    public UrlResponse createShortUrl(String longUrl) {
//        longUrl = longUrl.replace("\"", "");
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        UrlMapping urlMapping = new UrlMapping();

        urlMapping.setCreatedAt(LocalDateTime.now());
        urlMapping.setShortCode(shortCode);
        urlMapping.setLongUrl(longUrl);
        UrlMapping saved = urlMappingRepository.save(urlMapping);

        String shortUrl = baseUrl + "/" + shortCode;
        return new UrlResponse(shortUrl);
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        Optional<UrlMapping> existingUrlMapping = urlMappingRepository.findByShortCode(shortCode);

        if(existingUrlMapping.isEmpty()) {
            throw new ResourceNotFoundException("short URL not found.");
        }
        UrlMapping urlMapping = existingUrlMapping.get();

        urlMapping.setClickCount(urlMapping.getClickCount() + 1);
        urlMappingRepository.save(urlMapping);

        return urlMapping.getLongUrl();
    }

    @Override
    public UrlStatsResponse getStats(String shortCode){
        Optional<UrlMapping> urlMapping = urlMappingRepository.findByShortCode(shortCode);

        if(urlMapping.isEmpty()) {
            throw new ResourceNotFoundException("short URL not found.");
        }
        String shortUrl = baseUrl + "/" + shortCode;
        UrlMapping existing = urlMapping.get();
        return new UrlStatsResponse(shortUrl,existing.getLongUrl(),existing.getClickCount());
    }
}
