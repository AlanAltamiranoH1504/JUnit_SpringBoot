package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "tbl_shooping_cart")
public class ShoopingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shopping_cart;
    private String nameOwnerCar;
    private BigDecimal amount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_rel_car_produc",
            joinColumns = @JoinColumn(name = "id_shopping_cart"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Producto> productos = new ArrayList<>();

    public ShoopingCar() {
    }

    public ShoopingCar(Long id_shopping_cart, String nameOwnerCar, BigDecimal amount, List<Producto> productos) {
        this.id_shopping_cart = id_shopping_cart;
        this.nameOwnerCar = nameOwnerCar;
        this.amount = amount;
        this.productos = productos;
    }

    public Long getId_shopping_cart() {
        return id_shopping_cart;
    }

    public void setId_shopping_cart(Long id_shopping_cart) {
        this.id_shopping_cart = id_shopping_cart;
    }

    public String getNameOwnerCar() {
        return nameOwnerCar;
    }

    public void setNameOwnerCar(String nameOwnerCar) {
        this.nameOwnerCar = nameOwnerCar;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProduct(Producto producto) {
        productos.add(producto);
    }


}
