package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.user.serialization.StringSerializable;
import org.apache.commons.lang3.StringUtils;

public class Name implements StringSerializable {
    String value;

    private Name(String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() <= 100);
        this.value = value;
    }

    @Override
    public String valueOf() {  return value;  }
}
