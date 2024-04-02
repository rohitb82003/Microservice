package org.example.microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author")
    private Author author;
    private int year;
    private double price;
}
