import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class SetList {
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
        List <Musica> musicas = new ArrayList<>(); //tamanho 10
        int opcao;
        do {
            opcao = Integer.parseInt(
                JOptionPane.showInputDialog("Digite\n0 - sair\n1 - inserir\n2 - avaliar\n3 - ver set list\n4 - ordenar lista"));
            switch (opcao) {
                case 1: {
                    Musica m = leMusica();
                    musicas.add(m);
                    //musicas.add(leMusica());
                    break;
                }
                case 2: {
                    String titulo = JOptionPane.showInputDialog("Digite o titulo da música a ser avaliada");
                    Musica musicaParaAvaliar = null;
                    for (Musica m: musicas) { //percorrer o "vetor" procurando a música pelo título
                        if (m.getTitulo().equals(titulo)) {
                            musicaParaAvaliar = m;
                            break;
                        }
                    }
                    if (musicaParaAvaliar == null) {
                        JOptionPane.showMessageDialog(null, "musica nao encontrada", "Ups", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        double novaAvaliacao = Double.parseDouble(
                            JOptionPane.showInputDialog("digite a sua avaliacao para a musica " + titulo)
                        );
                        musicaParaAvaliar.setAvaliacao(novaAvaliacao);
                        JOptionPane.showMessageDialog(null, musicaParaAvaliar, "Avaliacao realizada com sucesso", JOptionPane.PLAIN_MESSAGE, null);
                    }
                    break;
                }
                case 3:{
                    if (musicas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Lista vazia, vamos escolher novas musicas");
                    }
                    else {
                        String s = "";
                        for (Musica m: musicas) {
                            s += m + "\n"; 
                        }
                        JOptionPane.showMessageDialog(null, s);
                    }
                    break;
                }
                case 4: {
                    Collections.sort(musicas, new Comparator<Musica>() {
                        @Override
                        public int compare (Musica m1, Musica m2) {
                            return m1.getTitulo().compareToIgnoreCase(m2.getTitulo());
                        }
                    });
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
