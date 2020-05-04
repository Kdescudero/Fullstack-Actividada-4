package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.user.serialization.StringSerializable;
import org.apache.commons.lang3.StringUtils;

public class Description implements StringSerializable {
    String value;

    private Description(String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() <= 280);
        this.value = value;
    }

    @Override
    public String valueOf() {  return value; }
}
