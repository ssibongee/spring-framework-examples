package com.example.api.service;

import com.example.api.common.KakaoRestApiHelper;
import com.example.api.dto.SearchRequest;
import com.example.api.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoSearchService implements SearchService {

    private final KakaoRestApiHelper kakaoRestApiHelper;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    @Override
    public ResponseEntity<SearchResponse> searchPlaceByKeyword(SearchRequest request) throws UnsupportedEncodingException {
        URI uri = URI.create(kakaoRestApiHelper.getRequestUrl(request));

        return restTemplate.exchange(new RequestEntity<>(httpHeaders, HttpMethod.GET, uri), SearchResponse.class);
    }
}
