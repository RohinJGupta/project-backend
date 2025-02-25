package com.supine.project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.PortfolioRepository;
import com.supine.project_backend.repository.UserRepository;
import com.supine.project_backend.model.PortfolioItem;
import java.util.List;


@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    private Portfolio getPortfolioFromUserId(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null) {
            return null;
        }

        Portfolio existingPortfolio = portfolioRepository.findById(existingUser.getServiceProvider().getId()).orElse(null);
        return existingPortfolio;
    }

    public Portfolio getPortfolio(Long user_id) {
        return getPortfolioFromUserId(user_id);
    }

    public List<PortfolioItem> getAllItems(Long user_id) {
        Portfolio p = getPortfolioFromUserId(user_id);
        return p.getItems();
    }

    public Portfolio updatePortfolio(Long user_id, Portfolio newPortfolio) {
        Portfolio existingPortfolio = getPortfolioFromUserId(user_id);
        if (existingPortfolio != null) {
            existingPortfolio.setTitle(newPortfolio.getTitle());
            existingPortfolio.setOverview(newPortfolio.getOverview());
            return portfolioRepository.save(existingPortfolio);
        }

        return null;

    }

    //move to portfolioitem
    // public Portfolio addPortfolioItem(Long user_id, PortfolioItem item) {
    //     User existingUser = userRepository.findById(user_id).orElse(null);
    //     Portfolio p = getPortfolioFromUserId(user_id);

    //     if(existingUser == null || p == null) {
    //         return null;
    //     }

    //     p.getItems().add(item);
    //     return portfolioRepository.save(p);
    // }

}
