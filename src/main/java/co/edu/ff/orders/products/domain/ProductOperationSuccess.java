package co.edu.ff.orders.products.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationSuccess implements ProductOperation{
    Product value;

    @Override
    public Product value() { return value; }

    @Override
    public String errorMessage() { return "El Producto se registro exitosamente"; }

    @Override
    public Boolean isValid() { return true; }
}
