package com.java.producto.adapter.in;

import java.util.Optional;
//import java.util.Scanner;

import com.java.producto.application.ProductoService;
import com.java.producto.domain.model.Producto;
import com.java.utility.Validations;

public class ProductoConsoleAdapter {
    private final ProductoService productoService;
    //private final Scanner scanner;
    private final Validations validations;

    public ProductoConsoleAdapter(ProductoService productoService) {
        this.productoService = productoService;
       // this.scanner = new Scanner(System.in);
        this.validations = new Validations();
    }

    public void start(){
        boolean bandera = true;
        while (bandera){
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Bscar ciudad por Id");
            System.out.println("4. Eliminar Ciudad");
            System.out.println("5. Listar todas las ciudades");
            System.out.println("6. Salir");
            int choice = validations.validarInt("Seleccione una opcion: ");
        
            switch (choice) {
                case 1:
                    registrar();
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    listar();
                    break;
                case 6:
                    exit();
                    bandera = false;
                default:
                    System.out.println("Opcion incorrecta, seleccione una opcion valid");
                    break;
            }
        }
    }

    private void registrar(){
        //Se solicitan los datos a registrar
        int crearId = validations.validarInt("Ingrese el id de la ciudad");
        String crearNombre = validations.campObligatorio("Ingrese el nombre del producto: ");
        double crearPrecio = validations.validarDouble("Ingrese el precio del producto:  $");
        
        //se crea uno nuevo con los datos
        Producto newProducto = new Producto(crearId, crearNombre, crearPrecio);
        productoService.save(newProducto);

        //Se Verifica la actualizacion
        System.out.println("Producto ingresado con exito");
    }

    private void actualizar(){
        //Se listan todos lo productos registrados
        listar();

        //Se solicita la info que desea actualizar
        int actualizarId = validations.validarInt("Ingrese el id que desea actualizar");
        String actualizarNombre = validations.campObligatorio("Ingrese el nombre que desea asignarle");
        double actualizarPrecio = validations.validarDouble("Ingrese el nuevo precio");

        //Se actualizan los datos
        Producto updateProducto = new Producto(actualizarId, actualizarNombre, actualizarPrecio);
        productoService.update(updateProducto);

        //Se Verifica la actualizacion
        System.out.println("Producto actualizado correctamente");
        
    }

    private void eliminar(){
        //Se solicita el id del producto a eliminar
        int eliminarProducto = validations.validarInt("Ingrese el id del producto que desea eliminar");
        
        //Se actualizar la lista sin el producto eliminado
        productoService.delete(eliminarProducto);

        //Se verifica la eliminacion 
        System.out.println("El producto fue eliminado correctamente");
        
    }

    //Buscar un producto por si Id
    private void buscar(){
        int buscarId = validations.validarInt("Ingrese el id del producto que desea buscar");
        Optional<Producto> producto = productoService.findById(buscarId);
        producto.ifPresentOrElse(
            c -> System.out.println("ID: " + c.getId_producto() + ", Nombre: " + c.getNombre_producto() + ", Precio: " + c.getPrecio()),
            ()-> System.out.println("Ciudad no encontrada"));
        
    }

    //Listar los todos los productos
    private void listar(){
        productoService.findAll().forEach(c->{
            System.out.println(
                "ID: " + c.getId_producto()+ ", Nombre" + c.getNombre_producto()+ ", precio: $" + c.getPrecio()
            );
        });
        
    }

    //Salir del programa
    private void exit(){
        System.out.println("Volviendo al menu anterior");
        
    }

}
