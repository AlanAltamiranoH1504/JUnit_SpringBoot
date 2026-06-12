package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    // * OBJETOS GENERALES DE LAS PRUEBAS
    private Cuenta originAccount;
    private Cuenta targetAccount;
    private Banco bank;

    // * INICIALIZACION DE OBJETOS GENERALES EN CADA PRUEBA
    @BeforeEach
    void setUp() {
        bank = new Banco("Santander");
        originAccount = new Cuenta("Alan", "Altamirano Hernández", 15000, bank);
        targetAccount = new Cuenta("Itzel", "Altamirano Hernandez", 10000, bank);
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando conexion a la base de datos");
    }

    @Test
    @DisplayName("Validacion de nombre de cuenta")
    void test_nombre_cuenta() {
//        originAccount = new Cuenta("Alan", "Altamirano Hernandez", 500000);
        assertEquals("Alan", originAccount.getNombre());
    }

    @Test
    @DisplayName("Validación de montos de cuenta")
    void test_account_amount() {
//        Cuenta cuenta = new Cuenta("Alan", "Altamirano Hernandez", 50000);
        assertEquals(15000, originAccount.getSaldo());
        assertFalse(originAccount.getSaldo() < 1000);
        assertTrue(originAccount.getSaldo() > 100);
    }

    @Test
    @Disabled
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
//        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        originAccount.retiro(1000);
        assertEquals(14000, originAccount.getSaldo());
    }

    @Test
    @DisplayName("Validación de lanzamiento de excepcion DineroInsuficienteException")
    void test_throw_exception() {
//        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        assertThrows(DineroInsuficienteException.class, () -> {
            originAccount.retiro(16000);
        });
    }

    @Test
    @DisplayName("Validación de lanzamiento de excepcion MontoNegativoException")
    void test_throw_MontoNegativoException() {
//        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        assertThrows(MontoNegativoException.class, () -> {
            originAccount.retiro(0);
        });
    }

    @Test
    @DisplayName("Validación de deposito correcto de dinero en cuenta")
    void test_deposit() {
//        Cuenta account = new Cuenta("Alan", "Altamirano Hernandez", 5000);
        originAccount.deposito(500);
        assertEquals(15500, originAccount.getSaldo());
    }

    @Test
    @DisplayName("Validación de transferencia de dinero entre cuentas")
    void test_transaction() {
//        Cuenta originAccount = new Cuenta("Alan", "Altamirano Hernández", 15000);
//        Cuenta targetAccount = new Cuenta("Itzel", "Altamirano Hernandez", 10000);
//        Banco bank = new Banco("Santander");

        bank.transferir(originAccount, targetAccount, 1000);
        assertEquals(14000, originAccount.getSaldo());
        assertEquals(11000, targetAccount.getSaldo());
    }

    @Test
    @DisplayName("Validacion de relacion entre Banco y Cuentas")
    void test_relations_models() {
//        Banco bank = new Banco("Santander");
//        Cuenta originAccount = new Cuenta("Alan", "Altamirano Hernández", 15000, bank);
//        Cuenta targetAccount = new Cuenta("Itzel", "Altamirano Hernandez", 10000, bank);
        bank.addCuenta(originAccount);
        bank.addCuenta(targetAccount);

        assertFalse(bank.getCuentas().isEmpty(), "La lista de cuentas del banco esta vacia");
        assertEquals(2, bank.getCuentas().size(), "El numero de cuentas no es correcto");
        assertEquals("Santander", originAccount.getBanco().getNombre(), "El banco de la cuenta no es santander");
        assertEquals("Alan", bank.getCuentas() // * Evalua que haya una cuenta que tenga el nombre de Alan
                .stream()
                .filter(account -> account.getNombre().equals("Alan"))
                .findFirst()
                .get()
                .getNombre(), "No existe ninguna propiedad de Alan");
        assertTrue(bank.getCuentas().stream() // * Evaluar que exista al menos una cuenta con el nombre Alan
                .anyMatch(cuenta -> cuenta.getNombre().equals("Alan")), "No existe ninguna cuenta propidad de Alan");
    }

    @Test
    @DisplayName("Validación de funcionamiento assert all")
    void test_assert_all() {
//        Banco bank = new Banco("Santander");
//        Cuenta originAccount = new Cuenta("Alan", "Altamirano Hernández", 15000, bank);
//        Cuenta targetAccount = new Cuenta("Itzel", "Altamirano Hernández", 10000, bank);
        bank.addCuenta(originAccount);
        bank.addCuenta(targetAccount);

        assertAll("Validacion de relaciones entre cuentas y banco",
                () -> assertFalse(bank.getCuentas().isEmpty(), "La lista de cuentas del banco esta vacia"),
                () -> assertEquals(2, bank.getCuentas().size(), "El numero de cuentas no es correcto"),
                () -> assertEquals("Santander", originAccount.getBanco().getNombre(), "El banco de la cuenta no es santander"),
                () -> assertEquals("Alan", bank.getCuentas() // * Evalua que haya una cuenta que tenga el nombre de Alan
                        .stream()
                        .filter(account -> account.getNombre().equals("Alan"))
                        .findFirst()
                        .get()
                        .getNombre(), "No existe ninguna propiedad de Alan"),
                () -> assertTrue(bank.getCuentas().stream() // * Evaluar que exista al menos una cuenta con el nombre Alan
                        .anyMatch(cuenta -> cuenta.getNombre().equals("Alan")), "No existe ninguna cuenta propidad de Alan")
        );
    }

    // * CONDITIONAL TEST (OS, ENVs, ETC)
    @Test
    @DisplayName("Test Aplicable solo a SO Windows")
    @EnabledOnOs(OS.WINDOWS)
    void onlyWindows() {
    }

    @Test
    @DisplayName("Test aplicable a solo SO Linux")
    @EnabledOnOs(OS.LINUX)
    void onlyLinux() {
    }

    @Test
    @DisplayName("Test aplicable a solo SO MAC")
    @EnabledOnOs(OS.MAC)
    void onlyMac() {
    }

    @Test
    @DisplayName("Test aplicable solo en Java 21")
    @EnabledOnJre(JRE.JAVA_21)
    void onlyJava21() {
    }

    @Test
    @DisplayName("Test aplicable solo en Java 8")
    @EnabledOnJre(JRE.JAVA_8)
    void onlyJava8() {
    }

    @Test
    @DisplayName("Test de visualizacion de propiedades del sistema")
    void showSystemProperties() {
        Properties systemProperties = System.getProperties();
        systemProperties.forEach((key, value) -> System.out.println(key + ": "  + value));
    }

    @Test
    @DisplayName("Test de arquitectura de 64 bits")
    @EnabledIfSystemProperty(named = "sun.arch.data.model", matches = "64")
    void onnlyArchDataModel64() {
    }

    @Test
    @DisplayName("Test de muestra de variables de ambiente")
    void showEnviromentVariables() {
        System.getenv().forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @Test
    @DisplayName("Test de validacion de usuario Alan")
    @EnabledIfEnvironmentVariable(named = "USERDOMAIN_ROAMINGPROFILE", matches = "ALAN_AH")
    void onlyUserDomainAlan() {
    }

    // * LIMPIEZA DE OBJETOS GENERALES EN CADA PRUEBA
    @AfterEach
    void tearDown() {
        originAccount = null;
        targetAccount = null;
        bank = null;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Cerrando conexion a la base de datos");
    }
}