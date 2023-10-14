package com.malishnya.moretech.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ATM {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String address;

    private Double latitude;

    private Double longitude;

    private boolean allDay;

}
