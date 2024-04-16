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
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bicycleId;

    @NotNull(message = "model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer model;

    @NotEmpty(message = "feature must be not empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String features;

    @NotNull(message = "number Of Wheels must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer numOfWheels;

    @NotNull(message = "Pin number must be not empty")
    @Column(columnDefinition = "int unique")
    @Pattern(regexp = "\\d{4}", message = "PIN must be exactly 4 digits")
    private Integer pinNumber;

    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;

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
