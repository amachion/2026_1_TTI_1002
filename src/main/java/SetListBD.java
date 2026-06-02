
import javax.swing.JOptionPane;

public class SetListBD {

    static Musica leMusica() {
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
                    String setList = Musica.listar();
                    JOptionPane.showMessageDialog(null, setList);
                    break;
                }
                case 3: {
                    String titulo = JOptionPane.showInputDialog("Digite o titulo da musica a alterar");
                    if (!Musica.existe(titulo)) {
                        JOptionPane.showMessageDialog(null, 
                        "Musica não encontrada", "Ups", JOptionPane.WARNING_MESSAGE);
                        break; //early exit
                    }
                    double novaAvaliacao = Double.parseDouble(
                        JOptionPane.showInputDialog("Digite a nova avaliacao")
                    );
                    Musica m = new Musica(titulo, novaAvaliacao);
                    m.atualizar();
                    JOptionPane.showMessageDialog(null, "Avaliacao atualizada!");
                    break;
                }
                case 4: {
                    String titulo = JOptionPane.showInputDialog("Digite o titulo da musica a remover");
                    if (!Musica.existe(titulo)) {
                        JOptionPane.showMessageDialog(null, 
                        "musica nao encontrada", "Ups", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        Musica m = new Musica(titulo);
                        m.remover();
                        JOptionPane.showMessageDialog(null, "Musica removida!");
                    }
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
