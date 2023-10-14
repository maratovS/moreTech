package com.malishnya.moretech.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEPARTMENT")
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

    private String network;

    private String salePointCode;

    private Long distance;

    private Boolean kep;

    private Boolean myBranch;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DEPARTMENT_OPEN_HOURS",
            joinColumns = { @JoinColumn(name = "open_hours_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private List<OpenHours> openHours;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DEPARTMENT_OPEN_HOURS_INDIVIDUAL",
            joinColumns = { @JoinColumn(name = "open_hours_individual_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private List<OpenHoursIndividual> openHoursIndividual;
}
