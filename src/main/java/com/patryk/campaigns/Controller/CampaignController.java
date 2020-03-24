package com.patryk.campaigns.Controller;

import com.patryk.campaigns.Model.Campaign;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class CampaignController {
    private CampaignRepository repository;

    public CampaignController(CampaignRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/campaigns")
    List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

    @GetMapping("/campaigns/budget")
    double getBudget() {
        return Campaign.getBudget();
    }

    @PostMapping("/campaigns")
    Campaign newCampaign(@RequestBody Campaign newCampaign) {
        if (Campaign.getBudget() - newCampaign.getFund() >= 0) {
            Campaign.setBudget(Campaign.getBudget() - newCampaign.getFund());
            return repository.save(newCampaign);
        } else {
            return null;
        }
    }

}
