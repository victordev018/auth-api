package com.victordev.auth_api.service;

import com.victordev.auth_api.domain.product.Product;
import com.victordev.auth_api.domain.product.ProductRequestDTO;
import com.victordev.auth_api.domain.product.ProductResponseDTO;
import com.victordev.auth_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map( p -> new ProductResponseDTO(p.getId(), p.getName(), p.getPrice())).toList();
    }

    public void save(ProductRequestDTO requestDTO) {
        Product product = new Product(null, requestDTO.name(), requestDTO.price());
        productRepository.save(product);
    }
}
