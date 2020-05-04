package co.edu.ff.orders.products.domain;

public interface ProductOperation {
    Product value();
    String errorMessage();
    Boolean isValid();
}
