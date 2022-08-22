package ru.chernyshev;

public class Cart {

    private Long id;
    private String title;
    private double cost;

    public Cart(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }
}
