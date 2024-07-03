package com.java.producto.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.java.producto.domain.model.Producto;
import com.java.producto.infrastucture.ProductoRepository;


public class ProductoMySQLRepository implements ProductoRepository{
    private final String url;
    private final String user;
    private final String password;

    
    public ProductoMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void delete(int id_producto) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM producto WHERE id_producto =?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id_producto);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> producto = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id_producto, nombre_producto, precio FROM producto";
            try(PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()){
                        Producto producto2 = new Producto(
                            resultSet.getInt("id_producto"),
                            resultSet.getString("nombre_producto"),
                            resultSet.getDouble("precio"));
                        producto.add(producto2);
                    }
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public Optional<Producto> findById(int id_producto) {
       try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = " SELECT id_producto, nombre_producto, precio FROM producto WHERE id_producto = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id_producto);
                try(ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next()){
                        Producto producto = new Producto(
                            resultSet.getInt("id_producto"),
                            resultSet.getString("nombre_producto"),
                            resultSet.getDouble("precio"));
                        return Optional.of(producto);
                    }
                }
            }
       } catch(SQLException e){
            e.printStackTrace();
       }
        return Optional.empty();
    }


    @Override
    public void save(Producto producto) {
       try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = " INSERT INTO producto (id_producto, nombre_producto, precio) VALUES(?,?,?) ";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, producto.getId_producto());
                statement.setString(2, producto.getNombre_producto());
                statement.setDouble(3, producto.getPrecio());
                statement.executeUpdate();
            }
       }catch (SQLException e ){
        e.printStackTrace();
       }
        
    }

    @Override
    public void update(Producto producto) {
       try( Connection connection = DriverManager.getConnection(url, user, password)){
            String query = " UPDATE producto SET nombre_producto = ?, precio = ? WHERE Id_producto = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, producto.getNombre_producto());
                statement.setDouble(2, producto.getPrecio());
                statement.setInt(3, producto.getId_producto());
                statement.executeLargeUpdate();
            }
       }catch (SQLException e ){
            e.printStackTrace();
       }
        
    }
}
