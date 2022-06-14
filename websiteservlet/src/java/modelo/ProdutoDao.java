package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDao {

    private Connection conecta;
    private PreparedStatement st;

    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoterca", "root", "teruel");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    public int salvar(Produto prod) {
        try {
            st = conecta.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1, prod.getCodigo());
            st.setString(2, prod.getNome());
            st.setString(3, prod.getMarca());
            st.setDouble(4, prod.getPreco());
            st.executeUpdate(); //Executa o INSERT
            return 1;
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1062){ //tentativa de cadastrar código já existente
               return 1062;                
            } else {
               return 0;
            }
        }
   }
    
    public int excluir(int codigo){
        int resultado; 
        try {
            st = conecta.prepareStatement("DELETE FROM produtos WHERE codigo = ?");
            st.setInt(1, codigo);
            resultado = st.executeUpdate();
            if (resultado==1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            return 2;
        }
    }
    
    public ArrayList consultar (String nome){
        
        return null;
    }

}
