package com.supine.project_backend.controller;

import com.supine.project_backend.dto.FilterItemDTO;
import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.service.FilterService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/filter")
public class FilterController {

    private final FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping("/items")
    public ResponseEntity<List<PortfolioItem>> getFilteredItems(@Valid @RequestBody FilterItemDTO filter) {
        System.out.println(filter.toString());
        List<PortfolioItem> items = filterService.getFilteredItems(filter);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/items/statistics")
    public ResponseEntity<Map<String, Object>> getFilteredItemsStatistics(@Valid @RequestBody FilterItemDTO filter) {
        Map<String, Object> statistics = filterService.getFilteredItemsStatistics(filter);
        return ResponseEntity.ok(statistics);
    }
    
    @PostMapping("/items/category-distribution")
    public ResponseEntity<List<Map<String, Object>>> getCategoryDistribution(@Valid @RequestBody FilterItemDTO filter) {
        List<Map<String, Object>> categoryDistribution = filterService.getCategoryDistribution(filter);
        return ResponseEntity.ok(categoryDistribution);
    }
} 