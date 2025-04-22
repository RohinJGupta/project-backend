package com.supine.project_backend.service;

import com.supine.project_backend.dto.FilterItemDTO;
import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilterService {

    private final FilterRepository filterRepository;

    @Autowired
    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    public List<PortfolioItem> getFilteredItems(FilterItemDTO filter) {
        return filterRepository.findFilteredItems(
            filter.getSearch(),
            filter.getCategory(),
            filter.getStartDate(),
            filter.getEndDate(),
            filter.getMinCost(),
            filter.getMaxCost(),
            filter.getMinDuration(),
            filter.getMaxDuration()
        );
    }

    public Map<String, Object> getFilteredItemsStatistics(FilterItemDTO filter) {
        return filterRepository.getFilteredItemsStatistics(
            filter.getSearch(),
            filter.getCategory(),
            filter.getStartDate(),
            filter.getEndDate(),
            filter.getMinCost(),
            filter.getMaxCost(),
            filter.getMinDuration(),
            filter.getMaxDuration()
        );
    }
    
    public List<Map<String, Object>> getCategoryDistribution(FilterItemDTO filter) {
        return filterRepository.getCategoryDistribution(
            filter.getSearch(),
            filter.getCategory(),
            filter.getStartDate(),
            filter.getEndDate(),
            filter.getMinCost(),
            filter.getMaxCost(),
            filter.getMinDuration(),
            filter.getMaxDuration()
        );
    }
} 