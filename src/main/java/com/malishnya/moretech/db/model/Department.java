package com.malishnya.moretech.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEPARTMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String salePointName;

    private String status;

    private String address;

    private String rko;

    private String officeType;

    private String salePointFormat;

    private String suoAvailability;

    private String hasRamp;

    private Double latitude;

    private Double longitude;

    private String metroStation;

    private Long distance;

    private Boolean kep;

    private Boolean myBranch;
}
