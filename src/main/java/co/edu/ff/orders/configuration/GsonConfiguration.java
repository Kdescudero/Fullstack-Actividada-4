package co.edu.ff.orders.configuration;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.exceptions.ProductException;
import co.edu.ff.orders.products.serialization.BigDecimalValueAdapter;
import co.edu.ff.orders.products.serialization.IntegerValueAdapter;
import co.edu.ff.orders.products.serialization.StringAdapter;
import co.edu.ff.orders.user.domain.Password;
import co.edu.ff.orders.user.domain.Username;
import co.edu.ff.orders.user.exceptions.UserException;
import co.edu.ff.orders.user.serialization.StringValueAdapter;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.function.Function;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson(){
        Function<String, Password> creator = new Function<String, Password>() {
            @Override
            public Password apply(String s) {
                return Password.of(s);
            }
        };

        return new GsonBuilder()
                .registerTypeAdapter(Username.class, new StringValueAdapter<>(Username::of))
                .registerTypeAdapter(Password.class, new StringValueAdapter<Password>(creator))
                .registerTypeAdapter(UserException.class, new JsonSerializer<UserException>() {
                    @Override
                    public JsonElement serialize(UserException src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        String message = src.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                .registerTypeAdapter(Name.class, new StringAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringAdapter<>(Description::of))
                .registerTypeAdapter(BasePrice.class, new BigDecimalValueAdapter<>(BasePrice::of))
                .registerTypeAdapter(TaxRate.class, new BigDecimalValueAdapter<>(TaxRate::of))
                .registerTypeAdapter(InventoryQuantity.class, new IntegerValueAdapter<>(InventoryQuantity::of))
                .registerTypeAdapter(ProductException.class, new JsonSerializer<ProductException>() {
                    @Override
                    public JsonElement serialize(ProductException e, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jsonObject = new JsonObject();
                        String message = e.getMessage();
                        JsonPrimitive errprValue = new JsonPrimitive(message);
                        jsonObject.add("error", errprValue);
                        return jsonObject;
                    }
                })
                .create();
    }
}
