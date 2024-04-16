package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    @Column(columnDefinition = "varchar(30)")
    private String dropOffStation;

    @Column(columnDefinition = "varchar(30)")
    private String pickUpStation;

    @Column(columnDefinition = "boolean default= false")
    private Boolean haveChargingStation;

    @Column(columnDefinition = "varchar(30)")
    private Integer capacity;

    @Pattern(regexp = "^(open|under maintenance | temporarily closed)")
    @Column(columnDefinition = "varchar(60)")
    private String status;

    @ManyToMany(mappedBy = "stations")
    private Set<Cars>cars;

    @ManyToMany(mappedBy = "stations")
    private Set<Bicycle>bicycles;

    @ManyToMany(mappedBy = "stations")
    private Set<Scooter>scooters;

    @ManyToOne
    @JoinColumn(name = "renId", referencedColumnName = "rentId")
    @JsonIgnore
    private Rent rent;
}
