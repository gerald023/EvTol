package com.eVTOL.controller;

import com.eVTOL.dto.ProductDTO;
import com.eVTOL.model.Product;
import com.eVTOL.service.implimentation.ProductImplimentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductImplimentation productImplimentation;

    public ProductController(ProductImplimentation productImplimentation) {
        this.productImplimentation = productImplimentation;
    }
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
      String addedProduct =  productImplimentation.addProduct(productDTO);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED );
    }
    @GetMapping
    public List<Product> getAllProducts(Product product){
        return productImplimentation.getAllProducts();
    }
    @DeleteMapping("/{id}")
    public  String deleteByID(@PathVariable Long id){
        productImplimentation.deleteByID(id);
        return "product with the id of " + id + " has been deleted";
    }
}
