package co.edu.ff.orders.products.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.function.Function;

public class IntegerValueAdapter <IN extends IntegerSerializable> implements GsonAdapter<IN>{
    private final Function<Integer, IN> factory;

    public IntegerValueAdapter(Function<Integer, IN> factory) {
        this.factory = factory;
    }

    @Override
    public IN deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Integer value = json.getAsInt();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(IN src, Type typeOfSrc, JsonSerializationContext context) {
        Integer value = src.valueOf();
        return new JsonPrimitive(value);
    }
}
