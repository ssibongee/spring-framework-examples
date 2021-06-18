package com.example.api.controller;

import com.example.api.dto.SearchRequest;
import com.example.api.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class KakaoSearchApiController {

    private final SearchService searchService;

    @GetMapping("/searches")
    public ResponseEntity search(SearchRequest request) throws UnsupportedEncodingException {
        return searchService.searchPlaceByKeyword(request);
    }
}
