package com.example.inventory.service;

import com.example.inventory.dto.ProductDto;
import com.example.inventory.exception.InvalidInputException;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final int LOW_STOCK_THRESHOLD = 5;

    //  Créer un produit
    public Product createProduct(ProductDto productDto) {
        if (productDto.getPrice() <= 0) {
            throw new InvalidInputException("Le prix doit être supérieur à 0");
        }
        if (productDto.getStockQuantity() <= 0) {
            throw new InvalidInputException("La quantité en stock doit être supérieure à 0");
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());

        Product savedProduct = productRepository.save(product);
        checkLowStockAlert(savedProduct); // Vérification du stock après création
        return savedProduct;
    }

    //  Récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //  Récupérer un produit par son ID (avec exception si inexistant)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit avec ID " + id + " non trouvé"));
    }

    //  Mettre à jour un produit
    public Product updateProduct(Long id, ProductDto productDto) {

        if (productDto.getPrice() <= 0) {
            throw new InvalidInputException("Le prix doit être supérieur à 0");
        }
        if (productDto.getStockQuantity() <= 0) {
            throw new InvalidInputException("La quantité en stock doit être supérieure à 0");
        }

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.getName());
                    product.setPrice(productDto.getPrice());
                    product.setStockQuantity(productDto.getStockQuantity());

                    Product updatedProduct = productRepository.save(product);
                    checkLowStockAlert(updatedProduct); // Vérification du stock après mise à jour
                    return updatedProduct;
                }).orElseThrow(() -> new ResourceNotFoundException("Produit avec ID " + id + " non trouvé"));
    }

    //  Supprimer un produit (avec vérification de l'existence)
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit avec ID " + id + " non trouvé");
        }
        productRepository.deleteById(id);
    }

    // Méthode privée pour vérifier le stock bas
    private void checkLowStockAlert(Product product) {
        if (product.getStockQuantity() < LOW_STOCK_THRESHOLD) {
            String alertMessage = String.format(
                    "Alerte stock bas! Produit: %s (ID: %d) - Quantité: %d",
                    product.getName(),
                    product.getId(),
                    product.getStockQuantity()
            );
            System.out.println(alertMessage);
        }
    }

    //  Récupérer les produits avec un stock bas
    public List<Product> getLowStockProducts() {
        return productRepository.findByStockQuantityLessThan(5);
    }

}
