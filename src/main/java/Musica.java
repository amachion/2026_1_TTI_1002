import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Musica {
    private String titulo;
    private int duracao; //em segundos
    private double avaliacao;
    public Musica () {} //a JVM inicializa cada atributo com seu valor padrão
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
    public Musica (String titulo, double avaliacao) {
        this(titulo);
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
        String sql = "INSERT INTO tb_musica (titulo, duracao, avaliacao) "+
               "VALUES (?, ?, ?)";
        //2. Invocando a fábrica de conexões
        ConnectionFactory factory = new ConnectionFactory();
        //try com recursos: são liberados automaticamente = pede a conexão
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
    public void atualizar () {
        //1 - Definir a string de atualização (só para avaliação)
        String sql = "UPDATE tb_musica SET avaliacao = ? WHERE titulo = ?";
        //2 - Invoca a fábrica de conexões
        ConnectionFactory factory = new ConnectionFactory();
        //2.1 obtem a conexão, dentro do try
        try (Connection c = factory.obtemConexao()) {
            //2.2 pre-compila
            PreparedStatement ps = c.prepareStatement(sql);
            //3 - preencher os placehlders
            ps.setDouble(1, avaliacao);
            ps.setString(2, titulo);
            //4 - executar
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void remover() {
        String sql = "DELETE FROM tb_musica WHERE titulo = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String listar () {
        String sql = "SELECT * FROM tb_musica";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao()) {
            PreparedStatement ps = c.prepareStatement(sql);
            //para receber o resultado, temos um conjunto de resultados
            ResultSet rs = ps.executeQuery();
            //iterar sobre o conjunto
            String saidaFinal = "";
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                int duracao = rs.getInt("duracao");
                double avaliacao = rs.getDouble("avaliacao");
                String saida = String.format ("Titulo: %s, Duração: %d, Avaliação: %.2f\n",
                        titulo, duracao, avaliacao);
                saidaFinal += saida;
            }
            return saidaFinal;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "set list vazio";
    }
}

