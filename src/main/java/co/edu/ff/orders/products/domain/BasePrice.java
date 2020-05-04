package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.products.serialization.BigDecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice implements BigDecimalSerializable {

    BigDecimal value;

    public BasePrice(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO)> 0);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
