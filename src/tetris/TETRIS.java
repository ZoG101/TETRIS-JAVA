package tetris;

import javax.swing.JOptionPane;

/**
 *
 * @author Davi Campolina
 */
public class TETRIS {
    
    private static FormatoDoJogo fj;
    private static FormaInicio fi;
    private static PlacarDeLideres pl;
    
    public static void comecar () {
        
        fj.setVisible(Boolean.TRUE);
        fj.comecarJogo();
        
    }
    
    public static void mostraPlacarLideres () {
        
        pl.setVisible(Boolean.TRUE);
        
    }
    
    public static void mostraInicio () {
        
        TETRIS.fi.setVisible(Boolean.TRUE);
        
    }
    
    public static void fimDeJogo (Integer pontos) {
        
        String nomeJogador = JOptionPane.showInputDialog("Fim de Jogo!\nDigite o seu nome:");
        TETRIS.pl.adicionaNome(nomeJogador, pontos.intValue());
        
    }

    public static void main (String[] args) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater (new Runnable() {
            
            @Override
            public void run() {
                
                TETRIS.fj = new FormatoDoJogo();
                TETRIS.fi = new FormaInicio();
                TETRIS.pl = new PlacarDeLideres();

                TETRIS.fi.setVisible(Boolean.TRUE);
                
            }
            
        });
        
    }
    
}
