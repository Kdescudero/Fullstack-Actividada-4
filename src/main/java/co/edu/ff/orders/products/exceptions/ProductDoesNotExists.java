package co.edu.ff.orders.products.exceptions;

import co.edu.ff.orders.products.domain.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor="of")
public class ProductDoesNotExists  extends ProductException{
    Long id;

    public ProductDoesNotExists(Long id) {
        super(String.format("El Producto %s no existe", id));
        this.id = id;
    }
}
