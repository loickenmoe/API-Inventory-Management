package com.example.inventory.service;

import com.example.inventory.dto.ProductDto;
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

    public Product createProduct(ProductDto productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, ProductDto productDTO) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setId(productDTO.getId());
                    product.setName(productDTO.getName());
                    product.setPrice(productDTO.getPrice());
                    product.setStockQuantity(productDTO.getStockQuantity());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findByStockQuantityLessThan(5);
    }
}

