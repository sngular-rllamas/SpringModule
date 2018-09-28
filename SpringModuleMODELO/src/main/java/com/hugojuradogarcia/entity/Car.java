package com.hugojuradogarcia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car", schema = "spring_data")
public class Car {

    @Id
    @Column(name = "id_car", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "color")
    private String color;

    @Column(name = "model_car")
    private String model;
}
