package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.PortfolioDTO;
import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.PortfolioRepository;
import com.supine.project_backend.repository.ProfileRepository;

import jakarta.transaction.Transactional;

import com.supine.project_backend.model.PortfolioItem;

import java.util.Arrays;
import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    private Portfolio savePortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    private Portfolio getPortfolioFromProfileId(Long profile_id) {
        Profile existingProfile = profileRepository.findById(profile_id).orElse(null);

        if(existingProfile == null) {
            return null;
        }

        Portfolio existingPortfolio = portfolioRepository.findById(existingProfile.getVendor().getId()).orElse(null);
        return existingPortfolio;
    }

    public PortfolioDTO getPortfolio(Long profile_id) {
        Profile existingProfile = profileRepository.findById(profile_id).orElse(null);
        if (existingProfile == null) {
            return null;
        }
        Portfolio existingPortfolio = existingProfile.getVendor().getPortfolio();
        return modelMapper.map(existingPortfolio, PortfolioDTO.class);
    }


  
    public List<PortfolioItemDTO> getAllItems(Long profile_id) {
        Portfolio p = getPortfolioFromProfileId(profile_id);
        if(p == null) {
            return null;
        }
        return Arrays.asList(modelMapper.map(p.getItems(), PortfolioItemDTO[].class));
    }
  

    public PortfolioDTO updatePortfolio(Long profile_id, PortfolioDTO newPortfolioDTO) {
        Portfolio existingPortfolio = getPortfolioFromProfileId(profile_id);
        if (existingPortfolio != null) {
            modelMapper.map(newPortfolioDTO, existingPortfolio);
            return modelMapper.map(portfolioRepository.save(existingPortfolio), PortfolioDTO.class);
        }
        return null;
    }

    public PortfolioDTO addPortfolioItem(Long profile_id, PortfolioItemDTO item) {

        Portfolio p = getPortfolioFromProfileId(profile_id);

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
