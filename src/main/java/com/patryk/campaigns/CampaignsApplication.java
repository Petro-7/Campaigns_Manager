package com.patryk.campaigns;

import com.patryk.campaigns.Model.Campaign;
import com.patryk.campaigns.Controller.CampaignRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CampaignsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampaignsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CampaignRepository repository){
        return args -> {
            Campaign campaign1 = new Campaign();
            campaign1.setName("Colgate");
            campaign1.setKeyword("Health");
            campaign1.setBid(10);
            campaign1.setFund(10000);
            campaign1.setStatus(false);
            campaign1.setTown("Kraków");
            campaign1.setRadius(44);
            Campaign.setBudget(Campaign.getBudget()-campaign1.getFund());
            repository.save(campaign1);

            Campaign campaign2 = new Campaign();
            campaign2.setName("Adidas");
            campaign2.setKeyword("Clothes");
            campaign2.setBid(44);
            campaign2.setFund(1222);
            campaign2.setStatus(true);
            campaign2.setTown("Gdańsk");
            campaign2.setRadius(100);
            Campaign.setBudget(Campaign.getBudget()-campaign2.getFund());
            repository.save(campaign2);

            repository.findAll().forEach(c->System.out.println(c.getId() + ": " + c.getName()));
        };
    }
}
