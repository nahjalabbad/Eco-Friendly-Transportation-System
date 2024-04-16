package com.example.capstone3.Model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scooterId;
    @NotNull(message = "model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer model;
    @NotEmpty(message = "Features must be not empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String features;
    @NotNull(message = "max speed must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer maxSpeed;
    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;
    @NotNull(message = "charge Percentage must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer chargePercentage;
    @NotNull(message = "Pin number must be not empty")
    @Column(columnDefinition = "int unique")
    @Pattern(regexp = "\\d{4}", message = "PIN must be exactly 4 digits")
    private Integer pinNumber;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    @JsonIgnore
    private Company company;



    @ManyToMany
    @JsonIgnore
    private Set<Station> stations;


    @ManyToMany
    @JsonIgnore
    private Set<Rent> rents;
}
