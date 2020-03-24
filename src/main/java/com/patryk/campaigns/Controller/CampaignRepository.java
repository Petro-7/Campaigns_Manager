package com.patryk.campaigns.Controller;

import com.patryk.campaigns.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
