package com.eVTOL.service;

import com.eVTOL.dto.ProductDTO;
import com.eVTOL.model.Product;
import com.eVTOL.model.Vehicle;

import java.util.List;

public interface ProductService {
    public String addProduct(ProductDTO productDTO);
    public List<Product> getAllProducts();
    public String deleteByID(Long id);
    public String updateProduct(Long id, Product product);
}
