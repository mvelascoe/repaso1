package com.java.proveedor.adapter.in;

import java.util.List;
import java.util.Optional;

import com.java.producto.domain.model.Producto;
import com.java.proveedor.application.ProveedorService;
import com.java.proveedor.domain.model.Proveedor;
import com.java.utility.Validations;

public class ProveedorConsoleAdapter {
    private final ProveedorService proveedorService;
    private final Validations validations;

    public ProveedorConsoleAdapter(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
        this.validations = new Validations();
    }

    public void start() {
        boolean bandera = true;
        while (bandera) {
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Buscar proveedor por Id");
            System.out.println("4. Eliminar proveedor");
            System.out.println("5. Listar todos los proveedores");
            System.out.println("6. Salir");
            int choice = validations.validarInt("Seleccione una opción: ");

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
                    break;
                default:
                    System.out.println("Opción incorrecta, seleccione una opción válida");
                    break;
            }
        }
    }

    private void registrar() {
        String crearNombre = validations.campObligatorio("Ingrese el nombre del proveedor: ");
        int idProducto = validations.validarInt("Ingrese el id del producto asociado: ");

        Proveedor newProveedor = new Proveedor(0, crearNombre, idProducto); //id proveedor autoincrementado

        proveedorService.save(newProveedor);

        System.out.println("Proveedor ingresado con éxito");
    }

    private void actualizar() {
        listar();

        int actualizarId = validations.validarInt("Ingrese el id que desea actualizar");
        String actualizarNombre = validations.campObligatorio("Ingrese el nombre que desea asignarle");
        int actualizarProductoId = validations.validarInt("Ingrese el id del producto asociado");

        Proveedor updateProveedor = new Proveedor(actualizarId, actualizarNombre, actualizarProductoId);
        proveedorService.update(updateProveedor);

        System.out.println("Proveedor actualizado correctamente");
    }

    private void eliminar() {
        int eliminarId = validations.validarInt("Ingrese el id del proveedor que desea eliminar");

        proveedorService.delete(eliminarId);

        System.out.println("El proveedor fue eliminado correctamente");
    }

    private void buscar() {
        int buscarId = validations.validarInt("Ingrese el id del proveedor que desea buscar");
        Optional<Proveedor> proveedor = proveedorService.findById(buscarId);
        proveedor.ifPresentOrElse(
            p -> System.out.println("ID: " + p.getId_proveedor() + ", Nombre: " + p.getNombre_proveedor() + ", ID Producto: " + p.getId_producto()),
            () -> System.out.println("Proveedor no encontrado")
        );
    }

    private void listar() {
        proveedorService.findAll().forEach(p -> {
            System.out.println(
                "ID: " + p.getId_proveedor() + ", Nombre: " + p.getNombre_proveedor() + ", ID Producto: " + p.getId_producto()
            );
        });
    }

    private void exit() {
        System.out.println("Volviendo al menú anterior");
    }
}

