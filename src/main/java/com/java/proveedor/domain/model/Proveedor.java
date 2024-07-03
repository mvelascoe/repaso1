package com.java.proveedor.domain.model;

public class Proveedor {
    private int id_proveedor;
    private String nombre_proveedor;
    private int id_producto;
    
    public Proveedor(String crearNombre, int idProducto) {
    }

    public Proveedor(int id_proveedor, String nombre_proveedor, int id_producto) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
       
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    

}
