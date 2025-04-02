package com.example.inventory.controller;

import com.example.inventory.dto.ProductDto;
import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "API pour gérer l'inventaire des produits")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    @Operation(summary = "Ajouter un produit")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @GetMapping("/all")
    @Operation(summary = "Lister tous les produits")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Mettre à jour un produit")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer un produit")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Obtenir les produits en stock bas (qtéEnStock < 5)")
    public ResponseEntity<List<Product>> getLowStockProducts() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }
}

