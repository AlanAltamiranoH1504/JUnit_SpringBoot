package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas.cuenta;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CuentaTest {

    @Test
    void nombreCuentaTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("Alan");
        cuenta.setApellidos("Altamirano Hernandez");
        String nombreEsperado = "Alan";

        assertEquals(nombreEsperado, cuenta.getNombre());
        assertTrue(cuenta.getNombre().equals("Alan"));
    }

    @Test
    void saldoCuentaTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("Alan");
        cuenta.setApellidos("Altamirano Hernandez");
        cuenta.setSaldo(35000);

        assertTrue(cuenta.getSaldo() > 0, "El saldo no es mayor que 0");
        assertNotEquals(0, cuenta.getSaldo(), "El saldo es igual a 0");
        assertEquals(35000, cuenta.getSaldo(), "El saldo no es igual a 35000");
    }

    @Test
    void referenciaCuentaTest() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 35000);
        Cuenta cuenta2 = new Cuenta("Alan", "Altamirano Hernandez", 35000);

        assertEquals(cuenta, cuenta2, "Las cuentas son diferentes");
    }

    @Test
    void retiroCuentaTest() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 35000);
        cuenta.retiro(15000);

        assertNotNull(cuenta.getSaldo(), "El saldo no debe ser null");
        assertEquals(20000, cuenta.getSaldo(), "El saldo de la cuenta no es igual a 20000");
    }

    @Test
    void depositorCuentaTest() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 35000);
        cuenta.deposito(5000);

        assertNotNull(cuenta, "El objeto cuenta es null");
        assertNotNull(cuenta.getSaldo(), "El saldo de la cuenta es null");
        assertNotEquals(35000, cuenta.getSaldo(), "El saldo de la cuenta es igual a 35000");
        assertEquals(40000, cuenta.getSaldo(), "El saldo de la cuenta no es igual a 40000");
    }

    @Test
    void testDineroInsuficienteExepcion() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 40000);
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.retiro(50000);
        }, "No se lanza la excepcion esperada");
        String actual = exception.getMessage();
        String esperado = "Dinero insuficiente";
        assertEquals(esperado, actual, "El mensaje de la exepcion no es igual a: " + esperado);
    }

    @Test
    void testMontoNegativoEnRetiro() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 35000);
        Exception exception = assertThrows(MontoNegativoException.class, () -> {
            cuenta.retiro(-10000);
        }, "No se la lanza la excepcion esperada");
        String actual = exception.getMessage();
        String esperado = "El monto del retiro no puede ser negativo";
        assertEquals(esperado, actual, "El mensaje de la expecion no es igual a: " + esperado);
    }
}
