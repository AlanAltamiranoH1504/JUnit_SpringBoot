package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import java.util.Objects;

public class Cuenta {
    private int id;
    private String nombre;
    private String apellidos;
    private double saldo;

    public Cuenta() {
    }
    public Cuenta(String nombre, String apellidos, double saldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }
    public Cuenta(int id, String nombre, String apellidos, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = saldo;
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

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return id == cuenta.id && Double.compare(saldo, cuenta.saldo) == 0 && Objects.equals(nombre, cuenta.nombre) && Objects.equals(apellidos, cuenta.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, saldo);
    }
}
