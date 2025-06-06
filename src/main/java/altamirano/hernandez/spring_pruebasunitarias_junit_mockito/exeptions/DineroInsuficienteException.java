package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
