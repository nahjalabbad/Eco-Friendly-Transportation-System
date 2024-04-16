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
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @NotEmpty(message = "car type must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String carType;

    @NotNull(message = "car model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer carModel;

    @NotNull(message = "fuel Percentage must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer fuelPercentage;

    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;

    @NotNull(message = "number of seats must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer numSeats;

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
    private Set<Station>stations;

    @ManyToMany
    @JsonIgnore
    private Set<Rent> rents;

}
