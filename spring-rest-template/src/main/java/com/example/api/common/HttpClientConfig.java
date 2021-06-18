package com.example.api.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



@Configuration
public class HttpClientConfig {



    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50)
                .setMaxConnPerRoute(10)
                .build();

        factory.setHttpClient(httpClient);
        return new RestTemplate(factory);
    }


}
