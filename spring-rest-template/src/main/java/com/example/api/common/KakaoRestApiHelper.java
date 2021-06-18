package com.example.api.common;

import com.example.api.dto.SearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
public class KakaoRestApiHelper {

    @Value("${kakao.restapi.key}")
    private String key;

    // https://developers.kakao.com/docs/latest/ko/local/dev-guide
    private static final String API_SERVER_HOST = "http://dapi.kakao.com";
    private static final String SEARCH_PLACE_KEYWORD_PATH = "/v2/local/search/keyword.json";

    public String getRequestUrl(SearchRequest request) throws UnsupportedEncodingException {
        return API_SERVER_HOST
                + SEARCH_PLACE_KEYWORD_PATH
                + "?query=" + URLEncoder.encode(request.getQuery(), "UTF-8")
                + "&page=" + request.getPage()
                + "&size" + request.getSize();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + key);
        headers.add("Accept", APPLICATION_JSON_VALUE);
        headers.add("Content-Type", APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        return headers;
    }

}
