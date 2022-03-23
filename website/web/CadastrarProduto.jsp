<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            // 1) Receber os dados vindos do formulário CadastrarProdutos.html
            int codigo;
            String nome, marca;
            double preco;
            codigo = Integer.parseInt(request.getParameter("codigo"));
            nome = request.getParameter("nome");
            marca = request.getParameter("marca");
            preco = Double.parseDouble(request.getParameter("preco"));

            try {
                // 2) Conectar com o Banco de dados

                Class.forName("com.mysql.jdbc.Driver");
                Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoterca", "root", "teruel");

                // 3) Enviar os dados para a tabela do banco de dados
                PreparedStatement st = conexao.prepareStatement("INSERT INTO produtos VALUES(?, ?, ?, ?)");
                st.setInt(1, codigo);
                st.setString(2, nome);
                st.setString(3, marca);
                st.setDouble(4, preco);
                st.executeUpdate(); // executa o INSERT na tabela do BD
                out.print("<p>Dados salvos com sucesso</p>");

                // 4) Desconectar do banco de dados
                conexao.close();

            } catch (ClassNotFoundException x) {
                out.print("Você não colocou o driver JDBC no projeto " + x.getMessage());
            } catch (SQLException x) {
                out.print("Você digitou alguma coisa de SQL errado " + x.getMessage());
            }
        %>
    </body>    
</html>
