import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private String usuario = "root";
    private String senha = "123456"; //tinCTrom
    private String host = "localhost";
    private String porta = "3306";
    private String bd = "db_spotfy";
    
    public Connection obtemConexao () {
        try {
            //Connection c = DriverManager.getConnection(
            //    "jdbc:mysql://" + host + ":" + porta + "/" + bd,
            //    usuario,
            //    senha
            //); faltou o gerenciador de Timezone
            String url = String.format(
            "jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC",
            host, porta, bd);
            return DriverManager.getConnection(url, usuario, senha);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
