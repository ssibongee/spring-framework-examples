package com.example.api.common;

import com.example.api.common.properties.KakaoApiProperties;
import com.example.api.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
@RequiredArgsConstructor
public class KakaoRestApiHelper {

    private final KakaoApiProperties properties;

    public String getRequestUrl(SearchRequest request) throws UnsupportedEncodingException {
        return properties.getApiServerHost()
                + properties.getSearchPlaceKeywordPath()
                + "?query=" + URLEncoder.encode(request.getQuery(), "UTF-8")
                + "&page=" + request.getPage()
                + "&size" + request.getSize();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + properties.getKey());
        headers.add("Accept", APPLICATION_JSON_VALUE);
        headers.add("Content-Type", APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        return headers;
    }

}
