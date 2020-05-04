package co.edu.ff.orders.products.services;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.exceptions.ProductDoesNotExists;
import co.edu.ff.orders.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductOperation createProduct(ProductOperationRequest productOperationRequest){
        return productRepository.insertOne(productOperationRequest);
    }

    public ProductOperation findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public ProductOperation deleteById(Long id){
        return productRepository.deleteOne(id);
    }

    public ProductOperation updateById(Long id, ProductOperationRequest productOperationRequest){
        return productRepository.updateOne(id, productOperationRequest);
    }
}
