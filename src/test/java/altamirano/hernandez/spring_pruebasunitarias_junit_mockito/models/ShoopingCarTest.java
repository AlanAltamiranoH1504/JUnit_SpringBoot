package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShoopingCarTest {
    // * VARIABLE GENERAL DE PRUEBA
    ShoopingCar shoopingCar;

    // * INICIALIZACION DE VARIABLE ANTES DE CADA PRUEBA
    @BeforeEach
    void setUp() {
        shoopingCar = new ShoopingCar();
    }

    @Nested
    @DisplayName("Pruebas con carrito vacio")
    class ShoppinCarEmpty {
        @Test
        @DisplayName("Validacion de carrito vacio correctamente")
        void showCartEmpty() {
            assertTrue(shoopingCar.getProductos().isEmpty());
        }

        @Test
        @DisplayName("Validacion de total de carrito igual a 0")
        void testTotalAmount() {
            assertNull(shoopingCar.getAmount(), "El monto del carrito no es nullo");
        }
    }

    @Nested
    @DisplayName("Pruebas de carrito con productos")
    class ShoppingCarWithProducts {
        // * AGREGADO DE PRODUCTOS AL CARRITO
        @BeforeEach
        void setUp() {
            shoopingCar.addProduct(new Producto("Audifonos Sony", "Audifonos de Diademe", BigDecimal.valueOf(7000)));
            shoopingCar.addProduct(new Producto("Escritorio", "Escritorio de altura ajustable", new BigDecimal(12000)));
        }

        @Test
        @DisplayName("Test de monto total de carrito")
        void totalAmount() {
            BigDecimal total = shoopingCar.getProductos()
                    .stream()
                    .reduce(
                            BigDecimal.ZERO,
                            (acum, product) -> acum.add(product.getPrecio()),
                            BigDecimal::add
                    );
            assertEquals(new BigDecimal(19000), total);
        }

        @Test
        @DisplayName("Test de reduccion de monto total de carrito")
        void reduceTotalAmount() {
            shoopingCar.getProductos().remove(1);
            BigDecimal totalAmount = shoopingCar.getProductos()
                    .stream()
                    .reduce(BigDecimal.ZERO,
                            (acum, product) -> acum.add(product.getPrecio()),
                            BigDecimal::add
                    );
            assertEquals(new BigDecimal(7000), totalAmount);
        }
    }
}