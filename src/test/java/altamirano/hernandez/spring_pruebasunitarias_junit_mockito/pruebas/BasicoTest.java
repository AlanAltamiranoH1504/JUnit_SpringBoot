package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.pruebas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicoTest {

    @Test
    public void testPrimero() {
        System.out.println("Primera prueba en Spring Boot Test");
    }
}
