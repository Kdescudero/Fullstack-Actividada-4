package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    @DisplayName("No devería crear nombre para casos inválidos")
    void isShouldNotName(){
        String name1 = "";
        String name2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> Name.of(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> Name.of(name1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Name.of(name2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear nombre validas")
    Stream<DynamicTest> isShouldName(){
        return Stream.of("Esta es una nombre")
            .map(item -> {
                String testName = String.format("Deberia ser valido para la nombre: %s", item);
                Executable executable = () -> {
                    ThrowingSupplier<Name> nameThrowingSupplier = () -> Name.of(item);
                    assertAll(
                            () -> assertDoesNotThrow(nameThrowingSupplier),
                            () -> assertNotNull(nameThrowingSupplier.get())
                    );
                };
                return DynamicTest.dynamicTest(testName, executable);
            });
    }
}