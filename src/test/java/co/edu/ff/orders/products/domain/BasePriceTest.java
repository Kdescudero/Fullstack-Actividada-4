package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasePriceTest {

    @Test
    @DisplayName("No devería crear precio base para casos inválidos")
    void isShouldNotBasePrice(){
        BigDecimal big = new BigDecimal(0);
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> BasePrice.of(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> BasePrice.of(big))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear precio base validas")
    Stream<DynamicTest> isShouldBasePrice(){
        return Stream.of(new BigDecimal(0.1))
        .map(item -> {
            String testName = String.format("Deberia ser valido para el precio base: %s", item);
            Executable executable = () -> {
                ThrowingSupplier<BasePrice> basePriceThrowingSupplier = () -> BasePrice.of(item);
                assertAll(
                        () -> assertDoesNotThrow(basePriceThrowingSupplier),
                        () -> assertNotNull(basePriceThrowingSupplier.get())
                );
            };
            return DynamicTest.dynamicTest(testName, executable);
        });
    }
}