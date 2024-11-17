package com.victordev.auth_api.controller;

import com.victordev.auth_api.domain.product.ProductRequestDTO;
import com.victordev.auth_api.domain.product.ProductResponseDTO;
import com.victordev.auth_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> response =  productService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductRequestDTO requestDTO) {
        productService.save(requestDTO);
        return ResponseEntity.status(201).build();
    }
}
