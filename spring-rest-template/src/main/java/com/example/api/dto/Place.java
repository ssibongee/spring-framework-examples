package com.example.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Place {

    private String id;
    private String place_name;
    private String category_name;
    private String category_group_name;

    private String phone;
    private String address_name;
    private String road_address_name;

    private String x;
    private String y;

    private String place_url;
    private String distance;
}
