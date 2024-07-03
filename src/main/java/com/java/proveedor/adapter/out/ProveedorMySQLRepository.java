package com.java.proveedor.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.java.proveedor.domain.model.Proveedor;
import com.java.proveedor.infrastructure.ProveedorRepository;

public class ProveedorMySQLRepository implements ProveedorRepository {
    private final String url;
    private final String user;
    private final String password;

    
    public ProveedorMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public Optional<Proveedor> findById(int id_proveedor) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id_proveedor,nombre_proveedor,  FROM proveedor WHERE id_proveedor = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id_proveedor);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Proveedor proveedor = new Proveedor(
                                resultSet.getInt("id_proveedor"),
                                resultSet.getString("nombre_proveedor"),
                                resultSet.getInt("id_producto"));
                        return Optional.of(proveedor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void delete(int id_proveedor) {
        try(Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "DELETE FROM provedor WHERE id_proveedor = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id_proveedor);
                statement.executeUpdate();
            }
            
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }


    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> proveedor = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id_proveedor, nombre_proveedor, id_producto FROM proveedor";
            try(PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Proveedor proveedor2 = new Proveedor(
                            resultSet.getInt("id_proveedor"),
                            resultSet.getString("nombre_proveedor"),
                            resultSet.getInt("id_producto"));
                        proveedor.add(proveedor2);
                    }
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedor;
    }


        public void save(Proveedor proveedor) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = " INSERT INTO proveedor (id_proveedor, nombre_proveedor, id_producto) VALUES (?,?,?)";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, proveedor.getId_producto());
                statement.setString(2, proveedor.getNombre_proveedor());
                statement.setInt(3, proveedor.getId_producto());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




    @Override
    public void update(Proveedor proveedor) {
       try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = " UPDATE proveedor SET id_proveedor = ?, nombre_proveedor = ?, id_producto = ? WHERE id_proveedor = ?";
            try(PreparedStatement statemnet = connection.prepareStatement(query)){
                statemnet.setInt(1, proveedor.getId_producto());
                statemnet.setString(2, proveedor.getNombre_proveedor());
                statemnet.setInt(3, proveedor.getId_proveedor());
            }
       }catch (SQLException e){
        e.printStackTrace();
       }
    }
}
