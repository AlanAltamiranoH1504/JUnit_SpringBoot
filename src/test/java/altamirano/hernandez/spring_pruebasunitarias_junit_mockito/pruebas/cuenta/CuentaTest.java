package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas.cuenta;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CuentaTest {

    @Test
    void nombreCuentaTest(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("Alan");
        cuenta.setApellidos("Altamirano Hernandez");
        String nombreEsperado = "Alan";

        assertEquals(nombreEsperado, cuenta.getNombre());
        assertTrue(cuenta.getNombre().equals("Alan"));
    }

    @Test
    void saldoCuentaTest(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("Alan");
        cuenta.setApellidos("Altamirano Hernandez");
        cuenta.setSaldo(35000);

        assertTrue(cuenta.getSaldo() > 0, "El saldo no es mayor que 0");
        assertNotEquals(0, cuenta.getSaldo(), "El saldo es mayor que 0");
    }
}
