package com.example.api.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchResponse {

    private PlaceMeta meta;
    private List<Place> documents = new ArrayList<>();
}
