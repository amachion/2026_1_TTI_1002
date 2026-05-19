import java.sql.Connection;
import java.sql.PreparedStatement;

public class Musica {
    private String titulo;
    private int duracao; //em segundos
    private double avaliacao;
    public Musica (String titulo) {
        this.titulo = titulo;
    }
    public Musica (String titulo, int duracao) {
        //this.titulo = titulo;
        this(titulo);
        this.duracao = duracao;
    }
    public Musica (String titulo, int duracao, double avaliacao) {
        // this.titulo = titulo;
        // this.duracao = duracao;
        this(titulo, duracao);
        setAvaliacao(avaliacao);
    }
    public void setAvaliacao (double avaliacao) {
        if (avaliacao >= 10)
            this.avaliacao = 10;
        else if (avaliacao <= 0)
            this.avaliacao = 0;
        else
            this.avaliacao = avaliacao;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public double getAvaliacao() {
        return avaliacao;
    }
    @Override
    public String toString() {
        return "[titulo=" + titulo + ", duracao=" + 
        duracao + ", avaliacao=" + avaliacao + "]";
    }
    @Override
    public boolean equals (Object o) {
        // 1. verificamos se temos as mesmas referências
        if (this == o) return true;
        // 2. verificamos se a referência é nula ou não é da mesma classe 
        // que o objeto de comparação this
        if (o == null || this.getClass() != o.getClass()) return false;
        // 3. verifica se os atributos-chave são iguais
        Musica musica = (Musica) o;
        return this.titulo.equals(musica.titulo);
    }
    // iniciar os métodos para conversar com o banco
    public void cadastrar() {
        //1. definir o insert como uma String
        String sql = "INSERT INTO tb_musica (titulo, duracao, avaliacao)"+
               "VALUES (?, ?, ?)";
        //2. Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory();
        //try com recursos: são liberados automaticamente
        try (Connection c = factory.obtemConexao()){
            //2.1 pré compilar o comando sql
            PreparedStatement ps = c.prepareStatement(sql);
            //2.2 preencher os placeholders, ou seja, os valores
            ps.setString(1, titulo);
            ps.setInt(2, duracao);
            ps.setDouble(3, avaliacao);
            //3. executar o comando
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

