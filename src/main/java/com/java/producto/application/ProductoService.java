package com.java.producto.application;

import java.util.List;
import java.util.Optional;

import com.java.producto.domain.model.Producto;
import com.java.producto.infrastucture.ProductoRepository;


public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void save(Producto Producto) {
        productoRepository.save(Producto);
    }

    public void update(Producto producto) {
        productoRepository.update(producto);
    }

    public void delete(int id_producto) {
        productoRepository.delete(id_producto);
    }

    public Optional<Producto> findById(int id_producto) {
        return productoRepository.findById(id_producto);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

}
