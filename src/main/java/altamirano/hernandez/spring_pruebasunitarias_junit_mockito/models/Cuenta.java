package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.DineroInsuficienteException;
import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.exeptions.MontoNegativoException;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    private double saldo;

    //Muchas cuentas para un banco
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    public Cuenta() {
    }

    public Cuenta(String nombre, String apellidos, double saldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = saldo;
        this.banco = banco;
    }

    public Cuenta(String nombre, String apellidos, double saldo, Banco banco) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = saldo;
        this.banco = banco;
    }

    public Cuenta(int id, String nombre, String apellidos, double saldo, Banco banco) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = saldo;
        this.banco = banco;
    }

    //G y S
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void retiro(double monto) {
        if (this.getSaldo() < monto){
            throw new DineroInsuficienteException("Dinero insuficiente");
        }
        if (monto <= 0) {
            throw new MontoNegativoException("El monto del retiro no puede ser negativo");
        }
        this.setSaldo(this.getSaldo() - monto);
    }

    public void deposito(double monto) {
        this.setSaldo(this.getSaldo() + monto);
    }

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return id == cuenta.id && Double.compare(saldo, cuenta.saldo) == 0 && Objects.equals(nombre, cuenta.nombre) && Objects.equals(apellidos, cuenta.apellidos) && Objects.equals(banco, cuenta.banco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, saldo, banco);
    }
}
