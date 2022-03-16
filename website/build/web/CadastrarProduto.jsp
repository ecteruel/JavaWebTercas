<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         // 1) Receber os dados vindos do formulário
         String codigo, nome, marca;
         double preco;
         codigo = request.getParameter("codigo");
         nome = request.getParameter("nome");
         marca = request.getParameter("marca");
         preco = Double.parseDouble(request.getParameter("preco"));         
         
         // 2) Conectar com o Banco de dados
         // 3) Enviar os dados para a tabela do banco de dados
         // 4) Desconectar do banco de dados
        %>
    </body>    
</html>
