package com.bnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_of_room", nullable = false)
    private String type_of_room;

    @Column(name = "availability", nullable = false)
    private Integer availability;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "date")
    private LocalDate date;

}