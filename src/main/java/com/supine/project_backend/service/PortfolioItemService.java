package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.PortfolioItemRepository;
import com.supine.project_backend.repository.UserRepository;


import jakarta.transaction.Transactional;

@Service
public class PortfolioItemService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioItemRepository portfolioItemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    private PortfolioItem savePortfolioItem(PortfolioItem portfolioItem) {
      return portfolioItemRepository.save(portfolioItem);
    }

    public PortfolioItemDTO getPortfolioItem(Long item_id) {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(item_id).orElse(null);
        return modelMapper.map(portfolioItem, PortfolioItemDTO.class);
    }

    public PortfolioItemDTO updatePortfolioItem(Long item_id, PortfolioItemDTO newItemDTO) {
        PortfolioItem existingPortfolioItem = portfolioItemRepository.findById(item_id).orElse(null);
        if (existingPortfolioItem == null) {
            return null;
        }
        modelMapper.map(newItemDTO, existingPortfolioItem);
        return modelMapper.map(portfolioItemRepository.save(existingPortfolioItem), PortfolioItemDTO.class);
    }

    @Transactional
    public boolean deletePortfolioItem(Long item_id) {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(item_id).orElse(null);
        if (portfolioItem != null) {
            portfolioItemRepository.delete(portfolioItem);
            return true;
        }
        return false;
    }
}
