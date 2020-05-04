package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class Product {
    Long ID;
    Name name;
    Description description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    InventoryQuantity inventoryQuantity;

    public Product(Long ID, Name name, Description description, BasePrice basePrice, TaxRate taxRate, ProductStatus productStatus, InventoryQuantity inventoryQuantity) {
        Preconditions.checkNotNull(ID);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(description);
        Preconditions.checkNotNull(basePrice);
        Preconditions.checkNotNull(taxRate);
        Preconditions.checkNotNull(productStatus);
        Preconditions.checkNotNull(inventoryQuantity);

        this.ID                 = ID;
        this.name               = name;
        this.description        = description;
        this.basePrice          = basePrice;
        this.taxRate            = taxRate;
        this.productStatus      = productStatus;
        this.inventoryQuantity  = inventoryQuantity;
    }
}
