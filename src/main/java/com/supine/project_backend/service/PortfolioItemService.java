package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.PortfolioItemRepository;
import com.supine.project_backend.repository.PortfolioRepository;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PortfolioItemService {

    @Autowired
    PortfolioItemRepository portfolioItemRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    UserRepository userRepository;

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

    private Portfolio getPortfolioFromUserId(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null) {
            return null;
        }

        Portfolio existingPortfolio = portfolioRepository.findById(existingUser.getVendor().getId()).orElse(null);
        return existingPortfolio;
    }

    public PortfolioItemDTO updatePortfolioItem(Long user_id, Long item_id, PortfolioItemDTO newItemDTO) {
        Portfolio existingPortfolio = getPortfolioFromUserId(user_id);
        if (existingPortfolio == null) {
            System.out.println("hi there 2");
            return null;
        }
        PortfolioItem existingPortfolioItem = portfolioItemRepository.findById(item_id).orElse(null);
        if (existingPortfolioItem == null || !existingPortfolio.getItems().contains(existingPortfolioItem)) {
  
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
