package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingSupplier;

import org.junit.jupiter.api.function.Executable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InventoryQuantityTest {

    @Test
    @DisplayName("No devería crear cantidad en inventario para casos inválidos")
    void isShouldNotIQT() {
        Integer iqt = 0;
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> InventoryQuantity.of(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> InventoryQuantity.of(iqt))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear cantidad en inventario validas")
    Stream<DynamicTest> isShouldIQT() {
        return Stream.of(1,2,3)
        .map(item -> {
            String testName = String.format("Deberia ser valido para la cantidad en inventari %s", item);
            Executable executable = () -> {
                ThrowingSupplier<InventoryQuantity> inventoryQuantityThrowingSupplier = () -> InventoryQuantity.of(item);
                assertAll(
                        () -> assertDoesNotThrow(inventoryQuantityThrowingSupplier),
                        () -> assertNotNull(inventoryQuantityThrowingSupplier.get())
                );
            };
            return DynamicTest.dynamicTest(testName, executable);
        });
    }
}