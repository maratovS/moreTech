package com.malishnya.moretech.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OPEN_HOURS_INDIVIDUAL")
public class OpenHoursIndividual {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String days;
    private String hours;

    @ManyToMany(mappedBy = "openHoursIndividual", fetch = FetchType.EAGER)
    private List<Department> departments;
}
