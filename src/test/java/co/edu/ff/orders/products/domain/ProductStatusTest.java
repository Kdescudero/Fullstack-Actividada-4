package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {
    @Test
    @DisplayName("No devería crear estado del producto para casos inválidos")
    void isShouldNotProductStatus(){
        String p1 = "";
        String p2 = "Value";
        String p3 = "value";
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> ProductStatus.valueOf(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> ProductStatus.valueOf(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> ProductStatus.valueOf(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> ProductStatus.valueOf(p3))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear estado del producto validas")
    Stream<DynamicTest> isShouldProductStatus(){
        return Stream.of("PUBLICADO", "BORRADO")
            .map(item -> {
                String testName = String.format("Deberia ser valido para la estado del producto: %s", item);
                Executable executable = () -> {
                    ThrowingSupplier<ProductStatus> productStatusThrowingSupplier = () -> ProductStatus.valueOf(item);
                    assertAll(
                            () -> assertDoesNotThrow(productStatusThrowingSupplier),
                            () -> assertNotNull(productStatusThrowingSupplier.get())
                    );
                };
                return DynamicTest.dynamicTest(testName, executable);
            });
    }
}