<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de produtros</title>
    </head>
    <body>
        <%
            //1) Receber o código do produto
            int codigo;
            if (request.getParameter("codigo").equalsIgnoreCase("")){
                out.print("Você não informou o código");
                return;//para a execução
            } else {
                codigo = Integer.parseInt(request.getParameter("codigo"));
            }
            try {
                //2) Conectar no Banco de dados
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoterca", "root", "teruel");

                //3) Buscar o código do produto na tabela
                PreparedStatement st = conexao.prepareStatement("SELECT * FROM produtos WHERE codigo = ? ");
                st.setInt(1, codigo);
                                
                ResultSet resultado = st.executeQuery();

                //4) Se o produto for encontrado, exibir os dados
                //   Senão, exibir uma mensagem avisando que o produto não foi encontrado
                if (resultado.next()){ //Se encontrou o produto
        %>
        <form method="post" action="SalvarAlterados.jsp">
            <p>
                <label for="codigo">Codigo:</label>
                <input type="number" readonly id="codigo" name="codigo" value="<%= resultado.getInt("codigo") %>">
            </p>
            <p>
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<%= resultado.getString("nome") %>">
            </p>
            <p>
                <label for="marca">Marca:</label>
                <input type="text" id="marca" name="marca" value="<%= resultado.getString("marca") %>">
            </p>
            <p>
                <label for="preco"> Preço: </label>
                <input type="text" name="preco" id="preco" pattern="[0-9]{1,9}\,[0-9]{1,2}$" value="<%= resultado.getString("preco").replace("." , ",") %>">                
            </p>
            <p>
                <input type="submit" value="Salvar alterações">                
            </p>
        </form>     
        <%
            } else {//Se não encontrou o produto
               out.print("<b>Produto não localizado</b>");
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
