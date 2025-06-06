package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "bancos")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    //Un banco para muchas cuentas
    @OneToMany(mappedBy = "banco")
    private List<Cuenta> cuentas = new ArrayList<>();

    public Banco() {

    }
    public Banco(String nombre) {
        this.nombre = nombre;
    }
    public Banco(String nombre, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.cuentas = cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    //Metodos de la clase
    public void transferir(Cuenta origen, Cuenta destino, double montoTransferencia) {
        origen.setSaldo(origen.getSaldo() - montoTransferencia);
        destino.setSaldo(destino.getSaldo() + montoTransferencia);
    }
}
