package altamirano.hernandez.spring_pruebasunitarias_junit_mockito.services.interfaces;

import altamirano.hernandez.spring_pruebasunitarias_junit_mockito.models.Producto;

import java.util.List;

public interface IProductoService {
    public abstract List<Producto> findAllProductos();
    public abstract Producto findProductoById(int id);
    public abstract Producto findProductoByNombre(String nombre);
    public abstract Producto saveProducto(Producto producto);
    public abstract boolean deleteProductoById(int id);
}
