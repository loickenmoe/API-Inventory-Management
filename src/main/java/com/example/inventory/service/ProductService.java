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

    //  Créer un produit
    public Product createProduct(ProductDto productDTO) {
        if (productDTO.getPrice() <= 0) {
            throw new InvalidInputException("Le prix doit être supérieur à 0");
        }
        if (productDTO.getStockQuantity() <= 0) {
            throw new InvalidInputException("La quantité en stock doit être supérieure à 0");
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return productRepository.save(product);
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
    public Product updateProduct(Long id, ProductDto productDTO) {

        if (productDTO.getPrice() <= 0) {
            throw new InvalidInputException("Le prix doit être supérieur à 0");
        }
        if (productDTO.getStockQuantity() <= 0) {
            throw new InvalidInputException("La quantité en stock doit être supérieure à 0");
        }

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDTO.getName());
                    product.setPrice(productDTO.getPrice());
                    product.setStockQuantity(productDTO.getStockQuantity());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Produit avec ID " + id + " non trouvé"));
    }

    //  Supprimer un produit (avec vérification de l'existence)
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit avec ID " + id + " non trouvé");
        }
        productRepository.deleteById(id);
    }

    //  Récupérer les produits avec un stock bas
    public List<Product> getLowStockProducts() {
        return productRepository.findByStockQuantityLessThan(5);
    }

}
