package com.java;

import com.java.producto.adapter.in.ProductoConsoleAdapter;
import com.java.producto.adapter.out.ProductoMySQLRepository;
import com.java.producto.application.ProductoService;
import com.java.proveedor.adapter.in.ProveedorConsoleAdapter;
import com.java.proveedor.adapter.out.ProveedorMySQLRepository;
import com.java.proveedor.application.ProveedorService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Filtro Java");


        //Conexion con la base de datos
        String url = "jdbc:mysql://localhost:3306/prueba2";
        String user = "root";
        String password = "MVE11feb94";

        ProductoMySQLRepository productoMySQLRepository = new ProductoMySQLRepository(url, user, password);
        ProductoService productoService = new ProductoService(productoMySQLRepository);
        ProductoConsoleAdapter productoConsoleAdapter = new ProductoConsoleAdapter(productoService);
    
        ProveedorMySQLRepository proveedorMySQLRepository = new ProveedorMySQLRepository(url, user, password);
        ProveedorService proveedorService = new ProveedorService(proveedorMySQLRepository, productoMySQLRepository);
        ProveedorConsoleAdapter proveedorConsoleAdapter = new ProveedorConsoleAdapter(proveedorService);

       // productoConsoleAdapter.start();
        proveedorConsoleAdapter.start();
    }

    
}