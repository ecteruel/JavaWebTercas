
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Consultar produtos por nome</title>
    </head>
    <body>
        <%
            //1) Receber o nome do produto
            String nome;
            nome = request.getParameter("nome");
            try {
                //2) Conectar no Banco de dados
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoterca", "root", "teruel");

                //3) Buscar o nome do produto na tabela
                PreparedStatement st = conexao.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?");
                st.setString(1, "%" + nome + "%");
                                
                ResultSet resultado = st.executeQuery();

                //4) Se o produto for encontrado, exibir os dados
                //   Senão, exibir uma mensagem avisando que o produto não foi encontrado
                while (resultado.next()) { //Faça enquanto tiver produtos na variável resultado
                    out.print("<p> Código:" + resultado.getString("codigo") + "</p>");
                    out.print("<p> Nome:" + resultado.getString("nome") + "</p>");
                    out.print("<p> Marca:" + resultado.getString("marca") + "</p>");
                    out.print("<p> Preço:" + resultado.getString("preco") + "</p>");
                    out.print("<p><br></p>");
                }
                //5) Fechar a conexão com o banco de dados
                conexao.close();
            } catch (ClassNotFoundException x) {
                out.print("Você não colocou o driver JDBC no projeto " + x.getMessage());
            } catch (SQLException x) {
                out.println("Erro de SQL. Entre em contato com o administrador do sistema " + x.getMessage());
            }
        %>
    </body>
</html>
