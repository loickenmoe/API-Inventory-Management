package com.example.inventory.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;

    @NotBlank(message = "Le nom du produit ne peut pas être vide")
    private String name;

    @Min(value = 1, message = "Le prix doit être supérieur à 0")
    private double price;

    @Min(value = 1, message = "La quantité en stock doit être supérieure à 0")
    private int stockQuantity;

    // Getters et Setters
    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

}
