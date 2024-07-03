package com.java.proveedor.infrastructure;

import java.util.List;
import java.util.Optional;

import com.java.proveedor.domain.model.Proveedor;

public interface ProveedorRepository {

    void save(Proveedor proveedor);
    void update(Proveedor proveedor);
    void delete(int id_proveedor);
    Optional<Proveedor>findById(int id_proveedor);
    List<Proveedor>findAll();
}
