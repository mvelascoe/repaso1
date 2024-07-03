package com.java.producto.infrastucture;

import java.util.List;
import java.util.Optional;

import com.java.producto.domain.model.Producto;

public interface ProductoRepository {
    
    void save (Producto Producto);
    void update (Producto producto);
    void delete (int id_producto);
    Optional<Producto> findById(int id_producto);
    List<Producto>findAll();
    
}
