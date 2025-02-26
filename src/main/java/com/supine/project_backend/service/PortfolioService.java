package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.NewPortfolioItemDTO;
import com.supine.project_backend.dto.PortfolioDTO;
import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.PortfolioRepository;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.supine.project_backend.model.PortfolioItem;

import java.util.Arrays;
import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    private Portfolio savePortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    private Portfolio getPortfolioFromUserId(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null) {
            return null;
        }

        Portfolio existingPortfolio = portfolioRepository.findById(existingUser.getServiceProvider().getId()).orElse(null);
        return existingPortfolio;
    }

    public PortfolioDTO getPortfolio(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        Portfolio existingPortfolio = existingUser.getServiceProvider().getPortfolio();
        return modelMapper.map(existingPortfolio, PortfolioDTO.class);
    }


  
    public List<PortfolioItemDTO> getAllItems(Long user_id) {
        Portfolio p = getPortfolioFromUserId(user_id);
        if(p == null) {
            return null;
        }
        return Arrays.asList(modelMapper.map(p.getItems(), PortfolioItemDTO[].class));
    }
  

    public PortfolioDTO updatePortfolio(Long user_id, PortfolioDTO newPortfolioDTO) {
        Portfolio existingPortfolio = getPortfolioFromUserId(user_id);
        if (existingPortfolio != null) {
            modelMapper.map(newPortfolioDTO, existingPortfolio);
            return modelMapper.map(portfolioRepository.save(existingPortfolio), PortfolioDTO.class);
        }
        return null;
    }

    public PortfolioDTO addPortfolioItem(Long user_id, NewPortfolioItemDTO item) {

        Portfolio p = getPortfolioFromUserId(user_id);

        if(p == null) {
            return null;
        }


        PortfolioItem newItem = new PortfolioItem();
        modelMapper.map(item, newItem); 
        

        newItem.setPortfolio(p);
        p.getItems().add(newItem);
        return modelMapper.map(portfolioRepository.save(p), PortfolioDTO.class);
    }
}
