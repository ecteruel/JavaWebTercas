
package modelo;

public class Produto {
    private int codigo;
    private String nome;
    private String marca;
    private double preco;
    
    public Produto(int codigo, String nome, String marca, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
    }
    
    //metodos getter - obter os dados armazenados na capsula
    public int getCodigo(){
     return this.codigo;   
    }

    public  String getNome(){
        return this.nome;
    }
    
    public String getMarca(){
     return this.marca;
    }
    
    public double getPreco(){
        return this.preco;
    }
}
