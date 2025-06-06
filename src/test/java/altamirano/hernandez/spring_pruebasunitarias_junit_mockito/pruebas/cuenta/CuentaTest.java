package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas.cuenta;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Banco;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.IBancoRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.ICuentaRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
public class CuentaTest {

    @Autowired
    private IBancoRepository iBancoRepository;
    @Autowired
    private ICuentaRepository iCuentaRepository;

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

    @Test
    @Rollback(value = true)
    void testTransferenciaDineroCuentas() {
        //Creacion de bancos
        Banco banco = new Banco("CitiBanamex");
        Banco banco2 = new Banco("BBVA");
        iBancoRepository.save(banco);
        iBancoRepository.save(banco2);
        assertNotNull(iBancoRepository.findAll(), "No hay bancos registrados en la base de datos");

        //Creacion de cuentas con bancos asociados
        Cuenta cuentaOrigen = new Cuenta("Alan", "Altamirano Hernandez", 40000, banco);
        Cuenta cuentaDestino = new Cuenta("Itzel", "Altamirano Hernandez", 35000, banco2);

        //Aseguramos que las cuentas no sean nullas y los saldos mayores a 0
        assertNotNull(cuentaOrigen, "El objeto cuentaOrigen es null");
        assertNotNull(cuentaDestino, "El objeto cuentaDestino es null");
        assertNotEquals(0, cuentaOrigen.getSaldo(), "El saldo de la cuentaOrigen es igual a 0");
        assertNotEquals(0, cuentaDestino.getSaldo(), "El saldo de la cuentaDestino es igual a 0");

        iCuentaRepository.save(cuentaOrigen);
        iCuentaRepository.save(cuentaDestino);
        assertNotNull(iCuentaRepository.findAll(), "No hay cuentas registradas en la base de datos");

        //Creacion de banco y proceso de transferencia
        Banco bancoBanamex = iBancoRepository.findByNombre("CitiBanamex");
        assertNotEquals(null, bancoBanamex.getNombre(), "El nombre del banco es null");
        banco.transferir(cuentaOrigen, cuentaDestino, 5000);

        //Comprobacion de transferencia
        assertEquals(35000, cuentaOrigen.getSaldo(), "El saldo actualizado de la cuenta origen no es igual a 35000");
        assertEquals(40000, cuentaDestino.getSaldo(), "El saldo actualizado de la cuenta destino no es igual a 40000");

        iCuentaRepository.save(cuentaOrigen);
        iCuentaRepository.save(cuentaDestino);
    }
}
