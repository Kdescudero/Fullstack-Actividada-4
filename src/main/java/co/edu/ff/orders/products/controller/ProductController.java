package co.edu.ff.orders.products.controller;

import co.edu.ff.orders.products.domain.Product;
import co.edu.ff.orders.products.domain.ProductOperation;
import co.edu.ff.orders.products.domain.ProductOperationRequest;
import co.edu.ff.orders.products.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices services;

    @PostMapping
    public ProductOperation create(@RequestBody ProductOperationRequest productOperationRequest){
        return services.createProduct(productOperationRequest);
    }

    @GetMapping
    public List<Product> findAll(){
        return services.findAll();
    }

    @GetMapping("/{id}")
    public ProductOperation getProductId(@PathVariable Long id){
        return services.findById(id);
    }

    @DeleteMapping("/{id}")
    public ProductOperation deleteProduct(@PathVariable Long id){
        return services.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductOperation updateProduct(@PathVariable Long id, @RequestBody ProductOperationRequest productOperationRequest){
        return services.updateById(id, productOperationRequest);
    }

}
