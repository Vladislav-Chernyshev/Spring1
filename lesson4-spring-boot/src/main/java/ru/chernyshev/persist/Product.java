package ru.chernyshev.persist;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class Product {

    private Long id;
    @NotBlank(message = "Введите наименование товара")
    private String title;

    @Min(message = "Сумма долна быть не менее 15", value = 15)
    private Double cost;

    @DecimalMin(message = "Срок должен быть не менее 1", value = "1")
    @DecimalMax(message = "Максимальный срок 60", value = "60")
    private Integer term;

    public Product(String title, Double cost, Integer term) {
        this.title = title;
        this.cost = cost;
        this.term = term;
    }

}
