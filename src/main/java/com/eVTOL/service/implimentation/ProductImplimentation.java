package com.eVTOL.service.implimentation;

import com.eVTOL.dto.ProductDTO;
import com.eVTOL.model.Product;
import com.eVTOL.repository.ProductRepository;
import com.eVTOL.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductImplimentation implements ProductService {
    private final ProductRepository productRepository;

    public ProductImplimentation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setWeight(productDTO.getWeight());
        productRepository.save(product);
        return productDTO.getName() + " has been added to your products";
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public String deleteByID(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "product with the id of " + id + " has been deleted";
    }

    @Override
    public String updateProduct(Long id, Product product) {
        Product data = productRepository.findById(product.getId()).orElse(null);
        if (data != null){
            data.setName(product.getName());
            data.setWeight(product.getWeight());
            productRepository.save(data);
        }
        return "product has been updated";
    }
}
