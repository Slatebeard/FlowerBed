package se.slatbeard.flowerbed;

import jakarta.persistence.*;

@Entity
@Table(name = "Bouquets")
public class Bouquet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantitySold;

    private double price;

    // Constructors
    public Bouquet() {}

    public Bouquet(int quantitySold, double price) {
        this.quantitySold = quantitySold;
        this.price = price;
    }

    // Getters and setters
    public Long getId() { return id; }
    public int getQuantitySold() { return quantitySold; }
    public double getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setQuantitySold(int quantitySold) { this.quantitySold = quantitySold; }
    public void setPrice(double price) { this.price = price; }
}
