package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.user.serialization.StringSerializable;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class BasePrice implements StringSerializable {

    BigDecimal value;

    public BasePrice(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.length() <= 280);
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
