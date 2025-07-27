package com.carservice.cardemo.model;

<<<<<<< Updated upstream
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car")
=======
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> Stashed changes

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="model")
    private String model;
    @Column(name="year")
    private LocalDate year;
}
