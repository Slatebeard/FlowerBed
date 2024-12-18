package se.slatbeard.flowerbed;

import jakarta.persistence.*;

@Entity
@Table(name = "Flowers")


public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int quantity;

    // Constructors
    public Flower() {
    }

    public Flower(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
