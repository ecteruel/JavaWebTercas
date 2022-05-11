
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
        
public class ProdutoDao {
    private Connection conecta;
    private PreparedStatement st;
    
    public boolean conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoterca", "root", "teruel");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }
}
