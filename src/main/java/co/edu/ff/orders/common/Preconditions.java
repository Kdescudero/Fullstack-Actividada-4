package co.edu.ff.orders.common;

import java.math.BigDecimal;
import java.util.Objects;

public class Preconditions {

    public static void checkNotNull(Object reference) {
        if(Objects.isNull(reference)){
            throw new NullPointerException();
        }
    }

    public static void checkArgument(boolean argument) {
        if(!argument) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgumentBigDecimalMax(BigDecimal argument){
        if(argument.doubleValue() >= 1) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgumentBigDecimalMin(BigDecimal argument){
        if(argument.doubleValue() >= 0) {
            throw new IllegalArgumentException();
        }
    }
}
