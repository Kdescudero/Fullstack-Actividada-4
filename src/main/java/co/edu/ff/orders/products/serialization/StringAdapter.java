package co.edu.ff.orders.products.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.function.Function;

public class StringAdapter<T extends StringSerializable> implements GsonAdapter<T> {
    private final Function<String, T> factory;

    public StringAdapter(Function<String, T> factory) {
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        String value = src.valueOf();
        return new JsonPrimitive(value);
    }
}
