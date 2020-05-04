package co.edu.ff.orders.products.repositories;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.exceptions.ProductDoesNotExists;
import co.edu.ff.orders.products.exceptions.ProductException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;

public class SqlProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public final RowMapper<Product> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("id");
        Name name = Name.of(resultSet.getString("name"));
        Description description = Description.of(resultSet.getString("description"));
        BasePrice basePrice = BasePrice.of(resultSet.getBigDecimal("baseprice"));
        TaxRate taxRate = TaxRate.of(resultSet.getBigDecimal("taxRate"));
        ProductStatus productStatus = ProductStatus.valueOf(resultSet.getString("productstatus"));
        InventoryQuantity inventoryQuantity = InventoryQuantity.of(resultSet.getInt("inventoryquantity"));
        return Product.of(id, name, description, basePrice, taxRate, productStatus, inventoryQuantity);
    };

    @Override
    public ProductOperation insertOne(ProductOperationRequest operationRequest) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name",              operationRequest.getName().getValue());
        parameters.put("description",       operationRequest.getDescription().getValue());
        parameters.put("basePrice",         operationRequest.getBasePrice().getValue());
        parameters.put("taxRate",           operationRequest.getTaxRate().getValue());
        parameters.put("productStatus",     operationRequest.getProductStatus());
        parameters.put("inventoryQuantity", operationRequest.getInventoryQuantity().getValue());
        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        Long id = number.longValue();
        Product product = Product.of(
                id,
                operationRequest.getName(),
                operationRequest.getDescription(),
                operationRequest.getBasePrice(),
                operationRequest.getTaxRate(),
                operationRequest.getProductStatus(),
                operationRequest.getInventoryQuantity()
                );
        return ProductOperationSuccess.of(product);
    }

    @Override
    public ProductOperation findById(Long id) {
        String SQL = "SELECT * FROM PRODUCTS WHERE id = ?";
        Object[] objects = {id};
        try{
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        }catch (EmptyResultDataAccessException e){
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }

    @Override
    public List<Product> findAll() {
        String SQL = "SELECT * FROM PRODUCTS";
        return jdbcTemplate.query(SQL, rowMapper);
    }

    @Override
    public ProductOperation deleteOne(Long id) {
        String SQL = "DELETE FROM PRODUCTS WHERE id = ?";
        Object[] objects = {id};
        Product product = findById(id).value();
        Integer delete = jdbcTemplate.update(SQL, objects);
        if(delete == 1){
            return ProductOperationSuccess.of(product);
        }else{
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }

    @Override
    public ProductOperation updateOne(Long id, ProductOperationRequest operationRequest) {
        String SQL = "UPDATE PRODUCTS SET name = ?, description = ?, baseprice = ?, taxrate = ?, productstatus = ?, inventoryquantity = ?  WHERE id = ?";
        Object[] objects = {
                operationRequest.getName().getValue(),
                operationRequest.getDescription().getValue(),
                operationRequest.getBasePrice().getValue(),
                operationRequest.getTaxRate().getValue(),
                operationRequest.getProductStatus().toString(),
                operationRequest.getInventoryQuantity().getValue(),
                id,
        };
        Integer update = jdbcTemplate.update(SQL, objects);
        if(update == 1){
            Product product = Product.of(
                    id,
                    operationRequest.getName(),
                    operationRequest.getDescription(),
                    operationRequest.getBasePrice(),
                    operationRequest.getTaxRate(),
                    operationRequest.getProductStatus(),
                    operationRequest.getInventoryQuantity()
            );
            return ProductOperationSuccess.of(product);
        } else {
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }
}
