package com.patryk.campaigns.Model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Campaign {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
    private @NonNull String keyword;
    private @NonNull double bid;
    private @NonNull double fund;
    private @NonNull boolean status;
    private @NonNull String town;
    private @NonNull double radius;

    @Getter @Setter private static double budget = 100000;
}
