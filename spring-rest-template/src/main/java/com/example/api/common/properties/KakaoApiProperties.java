package com.example.api.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kakao.restapi")
public class KakaoApiProperties {

    private String key;
    private String apiServerHost;
    private String searchPlaceKeywordPath;
}
