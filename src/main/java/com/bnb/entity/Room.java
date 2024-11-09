package com.bnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

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