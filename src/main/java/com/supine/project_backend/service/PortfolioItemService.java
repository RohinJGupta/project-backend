package com.supine.project_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.repository.PortfolioItemRepository;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PortfolioItemService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioItemRepository portfolioItemRepository;

    @Transactional
    public PortfolioItem savePortfolioItem(PortfolioItem portfolioItem) {
      return portfolioItemRepository.save(portfolioItem);
    }

    public PortfolioItem getPortfolioItem(Long item_id) {
        return portfolioItemRepository.findById(item_id).orElse(null);
    }

    public PortfolioItem updatePortfolioItem(Long item_id, PortfolioItem newItem) {
        PortfolioItem existingPortfolioItem = portfolioItemRepository.findById(item_id).orElse(null);
        if (existingPortfolioItem == null) {
            return null;
        }   

        existingPortfolioItem.setTitle(newItem.getTitle());
        existingPortfolioItem.setDescription(newItem.getDescription());
        existingPortfolioItem.setProjectCost(newItem.getProjectCost());
        existingPortfolioItem.setDuration(newItem.getDuration());
        existingPortfolioItem.setCategory(newItem.getCategory());
        existingPortfolioItem.setIsFeatured(newItem.isFeatured());
        existingPortfolioItem.setProjectDate(newItem.getProjectDate());
        return portfolioItemRepository.save(existingPortfolioItem);
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
