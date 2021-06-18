package com.example.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceMeta {

    private int total_count;
    private int pageable_count;
    private boolean is_end;
    private RegionInfo same_name;
}
