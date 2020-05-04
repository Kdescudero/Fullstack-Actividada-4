package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.products.exceptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationFailure implements ProductOperation {
    ProductException exception;

    @Override
    public Product value() {
        return null;
    }

    @Override
    public String errorMessage() {
        String message = String.format("Ocurrio un error: %s", exception.getMessage());
        return message;
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
