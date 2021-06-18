package com.example.api.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegionInfo {

    private List<String> region = new ArrayList<>();
    private String keyword;
    private String selected_region;

}
