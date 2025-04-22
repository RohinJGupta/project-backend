package com.supine.project_backend.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data @ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FilterItemDTO {
    private String search;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double minCost;
    private Double maxCost;
    private Integer minDuration;
    private Integer maxDuration;
} 