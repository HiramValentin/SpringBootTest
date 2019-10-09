package com.ang.test.offer.service;

import com.ang.test.offer.domain.Product;
import com.ang.test.offer.dto.ProductDTO;
import com.ang.test.offer.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class created by Hiram
 */
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {

        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty())
            return new ArrayList<>();

        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
