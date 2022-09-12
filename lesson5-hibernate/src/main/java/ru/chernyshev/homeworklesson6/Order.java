package ru.chernyshev.homeworklesson6;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private LocalDate date;


}
