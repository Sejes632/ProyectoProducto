package Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import model.Producto;


@Repository

public class ProductRepository {
    private final List<Producto> productos = new ArrayList<>();

    public ProductRepository(){
        productos.add(new Producto(1L,"Laptop",1200.000,10));
        productos.add(new Producto(2L,"Mouse",100.000,50));
        productos.add(new Producto(3L,"Teclado",70.000,30));
    }

    public List<Producto> findAll(){
        return productos;
    }

    public Optional<Producto> findByName(String Nombre) {
        return productos.stream().filter(product -> product.getNombre().equalsIgnoreCase(Nombre)).findFirst();
    }

    public Producto save(Producto producto) {
        productos.add(producto);
        return producto;
    }

    public void delete(Producto producto) {
        productos.remove(producto);
    }
}
