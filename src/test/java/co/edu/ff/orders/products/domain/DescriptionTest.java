package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingSupplier;

import org.junit.jupiter.api.function.Executable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    @DisplayName("No devería crear descripciones para casos inválidos")
    void isShouldNotDescription(){
        String des1 = "";
        String des2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> Description.of(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> Description.of(des1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Description.of(des2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear description validas")
    Stream<DynamicTest> isShouldDescription(){
        return Stream.of("Esta es una descripción")
            .map(item -> {
                String testName = String.format("Deberia ser valido para la descripcion: %s", item);
                Executable executable = () -> {
                    ThrowingSupplier<Description> descriptionThrowingSupplier = () -> Description.of(item);
                    assertAll(
                            () -> assertDoesNotThrow(descriptionThrowingSupplier),
                            () -> assertNotNull(descriptionThrowingSupplier.get())
                    );
                };
                return DynamicTest.dynamicTest(testName, executable);
        });
    }
}