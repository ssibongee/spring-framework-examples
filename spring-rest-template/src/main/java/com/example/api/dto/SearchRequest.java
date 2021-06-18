package com.example.api.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class SearchRequest {

    private String query;

    @Min(1)
    @Max(45)
    private int page = 1;

    @Min(1)
    @Max(15)
    private int size = 1;

    @Builder
    public SearchRequest(String query, @Min(1) @Max(45) int page, @Min(1) @Max(15) int size) {
        this.query = query;
        this.page = page;
        this.size = size;
    }
}
