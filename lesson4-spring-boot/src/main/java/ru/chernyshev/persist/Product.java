package ru.chernyshev.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private Integer term;

    public Product(String title, Double cost, Integer term) {
        this.title = title;
        this.cost = cost;
        this.term = term;
    }

}
