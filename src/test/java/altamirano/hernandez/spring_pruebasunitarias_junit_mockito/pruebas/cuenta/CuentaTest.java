package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas.cuenta;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Banco;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Cuenta;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.IBancoRepository;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.repositories.ICuentaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class CuentaTest {

    @Autowired
    private IBancoRepository iBancoRepository;
    @Autowired
    private ICuentaRepository iCuentaRepository;

    @AfterAll()
    static void testCompletados() {
        System.out.println("Testing completados");
    }

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
    @DisplayName("Test de almacanaje de nuevo banco")
    @Transactional
    void testSaveBanco() {
        Banco banco = new Banco("Banco de prueba");
        iBancoRepository.save(banco);

        Optional<Banco> bancoBusqueda = iBancoRepository.findByNombre("Banco de prueba");
        assertTrue(bancoBusqueda.isPresent(), "El banco no fue insertado en la base de datos");
    }

    @Test
    @DisplayName("Test de busqueda de banco por su nombre")
    @Transactional
    void testFindBancoByNombre() {
        Optional<Banco> bancoCitiBanamex = iBancoRepository.findByNombre("CitiBanamex");
        assertTrue(bancoCitiBanamex.isPresent(), "El banco no existe dentro de la base de datos");
    }

    @Test
    @DisplayName("Test de almacenamiento de nueva cuenta")
    @Transactional
    void testSaveCuenta() {
        Banco bancoPrueba = new Banco("Banco de prueba");
        iBancoRepository.save(bancoPrueba);

        Cuenta cuentaPrueba = new Cuenta("Prueba", "Apellidos de Prueba", 50000, bancoPrueba);
        iCuentaRepository.save(cuentaPrueba);

        Optional<Cuenta> cuentaBusqueda = iCuentaRepository.findByNombreAndApellidos("Prueba", "Apellidos de Prueba");
        assertTrue(cuentaBusqueda.isPresent(), "La cuenta no fue registrada en la base de datos");
    }

    @Test
    @DisplayName("Test de Transferencia de Dinero entre Cuentas")
    @Transactional
    void testTrasferenciaDineroEntreCuentas() {
        //Creacion de bancos
        Banco bancoPrueba1 = new Banco("Banco de prueba 1");
        Banco bancoPrueba2 = new Banco("Banco de prueba 2");
        iBancoRepository.save(bancoPrueba1);
        iBancoRepository.save(bancoPrueba2);

        //Creacion de cuentas
        Cuenta cuenta1 = new Cuenta("Alan", "Altamirano Hernandez", 40000, bancoPrueba1);
        Cuenta cuenta2 = new Cuenta("Vanessa Adriana", "Rivera Garcia", 35000, bancoPrueba2);
        iCuentaRepository.save(cuenta1);
        iCuentaRepository.save(cuenta2);

        //Proceso de transferencia
        bancoPrueba1.transferir(cuenta1, cuenta2, 5000);
        iCuentaRepository.save(cuenta1);
        iCuentaRepository.save(cuenta2);

        assertEquals(40000, cuenta2.getSaldo(), "El saldo de la cuenta2 no es igual a 40000");
        assertEquals(35000, cuenta1.getSaldo(), "El saldo de la cuenta1 no es igual a 35000");

        assertAll(
                () -> assertNotNull(iBancoRepository.findAll(), "No hay bancos en la base de datos"),
                () -> assertNotNull(iCuentaRepository.findAll(), "No hay cuentas registradas en la base de datos"),
                () -> {
                    assertEquals(40000, cuenta2.getSaldo(), "El saldo de la cuenta2 no es igual a 40000");
                    assertEquals(35000, cuenta1.getSaldo(), "El saldo de la cuenta1 no es igual a 35000");
                }
        );
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows(){

    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testSoloMac() {

    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testNoWindows() {

    }
}
