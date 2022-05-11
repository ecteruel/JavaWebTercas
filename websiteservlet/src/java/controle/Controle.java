package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Produto;
import modelo.ProdutoDao;

@WebServlet(name = "Controle", urlPatterns = {"/Controle"})
public class Controle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //Declarando as variáveis
        String flag, nome, marca;
        int codigo;
        double preco;
        Produto prod;
        ProdutoDao dao;
        boolean conectou;
        
        //Recebendo a flag do formulário CadastrarProdutos.html
        flag = request.getParameter("flag");
        
        //Verificando se eu vim do botão Salvar do formulário CadastrarProdutos.html
        if (flag.equalsIgnoreCase("salvar")){

            //Recebendo os demais dados do formulário CadastrarProdutos.html
            codigo = Integer.parseInt(request.getParameter("codigo"));
            nome = request.getParameter("nome");
            marca = request.getParameter("marca");
            preco = Double.parseDouble(request.getParameter("preco").replace("," , "."));
            
            //Encapsula os dados em um objeto prod da classe Produto.java
            prod = new Produto(codigo, nome, marca, preco);
            
            //Conectar com o banco de dados bancoterca
            dao = new ProdutoDao();
            conectou = dao.conectar();
            
            out.print(conectou);
            
            
        } else if (flag.equalsIgnoreCase("excluir")){
            //Fazer a parte de exclusão
        } else if (flag.equalsIgnoreCase("consultar")){
            //Fazer a parte de consulta
        } else if (flag.equalsIgnoreCase("alterar")){
        
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
