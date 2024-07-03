package com.java.proveedor.application;

import java.util.List;
import java.util.Optional;

import com.java.producto.domain.model.Producto;
import com.java.producto.infrastucture.ProductoRepository;
import com.java.proveedor.domain.model.Proveedor;
import com.java.proveedor.infrastructure.ProveedorRepository;

public class ProveedorService {

private final ProveedorRepository proveedorRepository;
private final ProductoRepository productoRepository;


public ProveedorService(ProveedorRepository proveedorRepository, ProductoRepository productoRepository) {
    this.proveedorRepository = proveedorRepository;
    this.productoRepository = productoRepository;
}

public void save(Proveedor proveedor) {
    proveedorRepository.save(proveedor);
}

public void update(Proveedor proveedor) {
    proveedorRepository.update(proveedor);
}

public void delete(int id_proveedor) {
    proveedorRepository.delete(id_proveedor);
}

public Optional<Proveedor> findById(int id_proveedor) {
    return proveedorRepository.findById(id_proveedor);
}

public List<Proveedor> findAll() {
    return proveedorRepository.findAll();
}

//metodos repository


    public void saveproducto(Producto Producto) {
        productoRepository.save(Producto);
    }

    public void updateproducto(Producto producto) {
        productoRepository.update(producto);
    }

    public void deleteproducto(int id_producto) {
        productoRepository.delete(id_producto);
    }

    public Optional<Producto> findByIdproducto(int id_producto) {
        return productoRepository.findById(id_producto);
    }

    public List<Producto> findAllproducto() {
        return productoRepository.findAll();
    }

}
