package co.edu.ff.orders.products.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.function.Function;

public class BigDecimalValueAdapter<IN extends BigDecimalSerializable> implements GsonAdapter<IN> {
    private final Function<BigDecimal, IN> factory;

    public BigDecimalValueAdapter(Function<BigDecimal, IN> factory) {
        this.factory = factory;
    }


    @Override
    public IN deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BigDecimal value = json.getAsBigDecimal();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(IN src, Type typeOfSrc, JsonSerializationContext context) {
        BigDecimal value = src.valueOf();
        return new JsonPrimitive(value);
    }
}
