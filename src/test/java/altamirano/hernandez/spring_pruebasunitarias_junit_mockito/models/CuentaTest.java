package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void test_nombre_cuenta() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 500000);
        assertEquals("Alan", cuenta.getNombre());
    }

    @Test
    void test_account_amount() {
        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 50000);
        assertEquals(50000, cuenta.getSaldo());
        assertFalse(cuenta.getSaldo() < 1000);
        assertTrue(cuenta.getSaldo() > 100);
    }

    @Test
    void test_account_reference() {
        Cuenta accountOne = new Cuenta("Alan", "Altamirano Hernandez", 50000);
        Cuenta accountTwo = new Cuenta("Alan", "Altamirano Hernández", 50000.01);

        // ! FALLA PORQUE NO SON MISMAS INSTANCIAS EN MEMORIA
        // assertEquals(accountOne, accountTwo);

        assertNotEquals(accountOne, accountTwo);
        assertEquals(accountOne.getNombre(), accountTwo.getNombre());
        assertNotEquals(accountOne.getApellidos(), accountTwo.getApellidos());
        assertTrue(accountTwo.getSaldo() > accountOne.getSaldo());
    }

    @Test
    @DisplayName("Validacion de disminución correcta de saldo de cuenta")
    void test_retreat() {
        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        account.retiro(1000);
        assertEquals(4000, account.getSaldo());
    }

    @Test
    @DisplayName("Validación de lanzamiento de excepcion DineroInsuficienteException")
    void test_throw_exception() {
        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        assertThrows(DineroInsuficienteException.class, () -> {
            account.retiro(6000);
        });
    }

    @Test
    @DisplayName("Validación de lanzamiento de excepcion MontoNegativoException")
    void test_throw_MontoNegativoException() {
        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        assertThrows(MontoNegativoException.class, () -> {
            account.retiro(0);
        });
    }

    @Test
    @DisplayName("Validación de deposito correcto de dinero en cuenta")
    void test_deposit() {
        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        account.deposito(500);
        assertEquals(5500, account.getSaldo());
    }
}