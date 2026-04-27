package com.sarthak.url_shortener.controller;

import com.sarthak.url_shortener.dto.UrlRequest;
import com.sarthak.url_shortener.dto.UrlResponse;
import com.sarthak.url_shortener.dto.UrlStatsResponse;
import com.sarthak.url_shortener.service.UrlMappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest urlRequest){
        UrlResponse response = urlMappingService.createShortUrl(urlRequest.getUrl());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectOriginalUrl(@PathVariable String shortCode){
        String originalUrl = urlMappingService.getOriginalUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/api/stats/{shortCode}")
    public ResponseEntity<UrlStatsResponse> getUrlStats(@PathVariable String shortCode){
        UrlStatsResponse response = urlMappingService.getStats(shortCode);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
