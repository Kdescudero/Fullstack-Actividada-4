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

class TaxRateTest {

    @Test
    @DisplayName("No devería crear tasa de impuesto para casos inválidos")
    void isShouldNotTaxRate(){
        BigDecimal big1 = new BigDecimal(2);
        BigDecimal big2 = new BigDecimal(-1);
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> TaxRate.of(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> TaxRate.of(big1)),
                () -> assertThrows(IllegalArgumentException.class, () -> TaxRate.of(big2))
        );
    }

   @TestFactory
    @DisplayName("Deberia crear tasa de impuesto validas")
    Stream<DynamicTest> isShouldTaxRate(){
        return Stream.of(new BigDecimal(0.1), new BigDecimal(1) )
            .map(item -> {
                String testName = String.format("Deberia ser valido para el tasa de impuesto: %s", item);
                Executable executable = () -> {
                    ThrowingSupplier<TaxRate> taxRateThrowingSupplier = () -> TaxRate.of(item);
                    assertAll(
                            () -> assertDoesNotThrow(taxRateThrowingSupplier),
                            () -> assertNotNull(taxRateThrowingSupplier.get())
                    );
                };
                return DynamicTest.dynamicTest(testName, executable);
            });
    }
}