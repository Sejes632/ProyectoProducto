package Controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Repositorio.ProductRepository;
import model.Producto;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Producto> getProductByName(@PathVariable String Nombre){
        Optional<Producto> producto = productRepository.findByName(Nombre);
        return producto.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("path")
    public Producto createProducto(@RequestBody Producto producto){
        return productRepository.save(producto);
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<Producto> updateProduct(@PathVariable String Nombre, @RequestBody Producto updatedProduct) {
        Optional<Producto> existingProducto = productRepository.findByName(Nombre);
        if (existingProducto.isPresent()) {
            Producto producto = existingProducto.get();
            producto.setNombre(updatedProduct.getNombre());
            producto.setValor(updatedProduct.getValor());
            producto.setCantidad(updatedProduct.getCantidad());
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String Nombre) {
        Optional<Producto> producto = productRepository.findByName(Nombre);
        if (producto.isPresent()) {
            productRepository.delete(producto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
