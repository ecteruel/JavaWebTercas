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
        int codigo, resultado;
        double preco;
        Produto prod;
        ProdutoDao dao;
        boolean conectou;
        int excluiu;

        //Recebendo a flag do formulário CadastrarProdutos.html
        flag = request.getParameter("flag");

        //Verificando se eu vim do botão Salvar do formulário CadastrarProdutos.html
        if (flag.equalsIgnoreCase("salvar")) {

            //Recebendo os demais dados do formulário CadastrarProdutos.html
            codigo = Integer.parseInt(request.getParameter("codigo"));
            nome = request.getParameter("nome");
            marca = request.getParameter("marca");
            preco = Double.parseDouble(request.getParameter("preco").replace(",", "."));

            //Encapsula os dados em um objeto prod da classe Produto.java
            prod = new Produto(codigo, nome, marca, preco);

            //Conectar com o banco de dados bancoterca
            dao = new ProdutoDao();
            conectou = dao.conectar();

            //Verificar se conectou ou não
            if (conectou) { //conectado com o BD
                //Salvar os dados do produto
                resultado = dao.salvar(prod);
                if(resultado==1){
                    out.print("Produto cadastrado com sucesso");
                }else if (resultado==1062) {
                    out.print("Código já cadastrado");
                }else {
                    out.print("Erro ao tentar salvar o produto");
                }
                
            } else { //se não conectou com o BD
                out.print("<h3>Erro na conexão com o BD</h3>");
            }

        } else if (flag.equalsIgnoreCase("excluir")) {
            //Recebe o código digitado no formulário
            codigo = Integer.parseInt(request.getParameter("codigo"));
            
            //Conectar com o banco de dados bancoterca
            dao = new ProdutoDao();
            conectou = dao.conectar();
            if(conectou){
                excluiu = dao.excluir(codigo);
                if(excluiu==1){
                    out.print("Produto excluído com sucesso");
                }else if (excluiu==0){
                    out.print("Este produto não está cadastrado");
                }else{
                    out.print("Erro ao tentar excluir o produto");
                }
            }else {
                out.print("Erro na conexão com o banco de dados");
            }
            
        } else if (flag.equalsIgnoreCase("consultar")) {
            //Receber o nome que veio do formulário
            nome = request.getParameter("nome");
            //fazer a conexão com o banco de dados
            dao = new ProdutoDao();
            conectou = dao.conectar();
            //Verifcar se conectou
            if(conectou){
                //chamar o método consultar da classe ProdutoDao
            }else{
                out.print("Erro na conexão");
            }
        } else if (flag.equalsIgnoreCase("alterar")) {

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
