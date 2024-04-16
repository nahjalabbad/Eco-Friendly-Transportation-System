package com.example.capstone3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentId;

    private String dropOffLocation;

    private String pickUpLocation;

    @NotEmpty(message = "transportType should not be empty")
    @Pattern(regexp = "^(Car|Bike|Scooter)$",  message = "transportType must be either Car, Bike, or Scooter")
    @Column(columnDefinition = "varchar(20)")
    private String transportType;

    @NotEmpty(message = "rentStatus should not be empty")
    @Pattern(regexp = "^(Rented|Not Rented|Not Available)$",  message = "rentStatus must be either Rented, Not Rented, or Not Available")
    @Column(columnDefinition = "varchar(20)")
    private String rentStatus;

    @NotEmpty(message = "returnStatus should not be empty")
    @Pattern(regexp = "^(Returned|Not Returned)$",  message = "returnStatus must be either Returned or Not Returned")
    @Column(columnDefinition = "varchar(50)")
    private String returnStatus;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime startDate;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime endDate;

    @Column(columnDefinition = "varchar(30)")
    private String duration;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "rent")
    @PrimaryKeyJoinColumn
    private RentalHistory rentalHistory;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    @ManyToMany(mappedBy = "rents")
    private Set<Cars> cars;

    @ManyToMany(mappedBy = "rents")
    private Set<Bicycle> bicycles;

    @ManyToMany(mappedBy = "rents")
    private Set<Scooter> scooters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rent")
    private Set<Station>stations;



}

