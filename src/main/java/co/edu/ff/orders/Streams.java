package co.edu.ff.orders;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<Pizza> pizzaList = Arrays.asList(
                new Pizza("Básica", Size.SMALL, 600),
                new Pizza("Familiar", Size.LARGE, 1800),
                new Pizza("Vegetariana", Size.LARGE, 860),
                new Pizza("Solo queso", Size.MEDIUM, 1000),
                new Pizza("Hawaiana", Size.SMALL, 1200),
                new Pizza("Extra carnes", Size.LARGE, 2100),
                new Pizza("Pollo", Size.SMALL, 900),
                new Pizza("Pollo + tocineta", Size.MEDIUM, 1500),
                new Pizza("Pollo + Jamon", Size.MEDIUM, 1300)
        );

        /*
         * 1. Obtener todas las pizzas de tamaño "MEDIUM"
         */
        List<Pizza> pizza1 = pizzaList.stream()
                .filter(data -> data.size.equals(Size.MEDIUM))
                .collect(Collectors.toList());
        System.out.println("pizza1 = " + pizza1);

        /*
         * 2. Obtener todas las pizzas que las calorias esten entre 700 y 1500
         */
        List<Pizza> pizza2 = pizzaList.stream()
                .filter(data -> data.calories>700 && data.calories<1500)
                .collect(Collectors.toList());
        System.out.println("pizza2 = " + pizza2);

        /*
         * 3. Obtener las 3 pizzas con más calorias
         */
        List<Pizza> pizza3 = pizzaList.stream()
                .sorted( (i1, i2) -> i2.calories.compareTo(i1.calories) )
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("pizza3 = " + pizza3);

        /*
         * 4. Obtener las 2 pizzas con menos calorias
         */
        List<Pizza> pizza4 = pizzaList.stream()
                .sorted(Comparator.comparing(i -> i.calories))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("pizza4 = " + pizza4);

        /*
         * 5. Del numeral 2 obtener las 2 pizzas con mas calorias
         */
        List<Pizza> pizza5 = pizza2.stream()
                .sorted((i1, i2) -> i2.calories.compareTo(i1.calories))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("pizza5 = " + pizza5);

        /*
         * 6. Agrupar las pizzas por tamaño
         */
        Map<Size, List<Pizza>> collect = pizzaList.stream().collect(
                Collectors.groupingBy(Pizza::getSize));
                System.out.println("pizza6 = " + "\n" +
                        "LARGE  = " + collect.get(Size.LARGE)  + "\n" +
                        "MEDIUM = " + collect.get(Size.MEDIUM) + "\n" +
                        "SMALL  = " + collect.get(Size.SMALL)  + "\n"
                );

        /*
         * 7. Agrupar las pizzas por los siguientes grupos:
         * de 0 a 1000 calorias
         * de 1001 a 2000 calorias
         * de 2001 a 3000 calorias
         */

        Function<Pizza, Calories> pizzaByCalories = pizza -> Calories.getTypeCalories(pizza.getCalories());
        Map<Calories, List<Pizza>> pizzasByCaloriesList =  pizzaList.stream()
                .collect(Collectors.groupingBy(pizzaByCalories));

        System.out.println("pizzas7 : " + "\n" +
                "LOW       = " + pizzasByCaloriesList.get(Calories.LOW) + "\n" +
                "MODERATE  = " + pizzasByCaloriesList.get(Calories.MODERATE) + "\n" +
                "HIGH      = " + pizzasByCaloriesList.get(Calories.HIGH));
    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    public enum Calories {
        LOW,
        MODERATE,
        HIGH,
        UNKNOWN;

        public static Calories getTypeCalories (Integer caloriesQuantity) {
            if(caloriesQuantity >= 0 && caloriesQuantity <= 1000){
                return LOW;
            }else if(caloriesQuantity >= 1001 && caloriesQuantity <= 2000){
                return MODERATE;
            }else if(caloriesQuantity >= 2001 && caloriesQuantity <= 3000){
                return HIGH;
            }else{
                return UNKNOWN;
            }
        }
    }

    public static class Pizza {
        private final String name;
        private final Size size;
        private final Integer calories;

        public Pizza(String name, Size size, Integer calories) {
            this.name = name;
            this.size = size;
            this.calories = calories;
        }

        public Size getSize() {
            return size;
        }

        public String getName() {
            return name;
        }

        public Integer getCalories() {
            return calories;
        }

        @Override
        public String toString() {
            return String.format("Pizza{%s, %s, %s}", name, size, calories);
        }
    }
}
