package co.edu.ff.orders.products.serialization;

import co.edu.ff.orders.products.domain.InventoryQuantity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerValueAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
            .registerTypeAdapter(InventoryQuantity.class, new IntegerValueAdapter<>(InventoryQuantity::of))
            .create();
    }

    @Test
    void deserialize() {
        InventoryQuantity inventoryQuantity = InventoryQuantity.of(1);

        String serializeActual1 = gson.toJson(inventoryQuantity);

        InventoryQuantity deserializeActual1 = gson.fromJson(serializeActual1, InventoryQuantity.class);

        assertEquals(deserializeActual1.valueOf(), inventoryQuantity.valueOf());
    }

    @Test
    void serialize() {
        InventoryQuantity inventoryQuantity = InventoryQuantity.of(1);

        String actual1 = gson.toJson(inventoryQuantity);

        String expected1 = String.format("%s", inventoryQuantity.getValue());
        assertEquals(actual1, expected1);
    }
}