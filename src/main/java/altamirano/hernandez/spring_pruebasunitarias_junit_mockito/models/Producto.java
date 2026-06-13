package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El maximo de caracteres para el nombre son 100")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 3, max = 100, message = "La descripcion debe tener un maximo de 100 caracteres")
    private String descripcion;

    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @ManyToMany(mappedBy = "productos")
    List<ShoopingCar> shoopingCars =  new ArrayList<>();

    //Constructores
    public Producto(){

    }
    public Producto(String nombre, String descripcion, BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Get y Set
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(precio, producto.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio);
    }
}
