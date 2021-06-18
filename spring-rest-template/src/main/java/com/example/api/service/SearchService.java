package com.example.api.service;

import com.example.api.dto.SearchRequest;
import com.example.api.dto.SearchResponse;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface SearchService {

    public ResponseEntity<SearchResponse> searchPlaceByKeyword(SearchRequest request) throws UnsupportedEncodingException;

}
