import javax.swing.JOptionPane;

public class SetListBD {
    static Musica leMusica () {
        String titulo = JOptionPane.showInputDialog("digite o titulo da musica");
        int duracao = Integer.parseInt(
            JOptionPane.showInputDialog("se souber digite a duracao", "0")
        );
        double avaliacao = Double.parseDouble(
            JOptionPane.showInputDialog("se tiver, digite a avaliacao", "0.0")
        );
        return new Musica(titulo, duracao, avaliacao);
    }

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = Integer.parseInt(
                JOptionPane.showInputDialog("Digite\n0 - sair\n1 - cadastrar\n2 - ver set list\n3 - alterar avaliacao\n4 - remover musica"));
            switch (opcao) {
                case 1: {
                    Musica m = leMusica();
                    m.cadastrar();
                    break;
                }
                case 2: {
                    
                    break;
                }
                case 3:{
                    
                    break;
                }
                case 4: {
                    
                    break;
                }
                case 0:
                    JOptionPane.showMessageDialog(null, "Obrigado, volte sempre");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida", "Ups", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcao != 0);
    }
}
