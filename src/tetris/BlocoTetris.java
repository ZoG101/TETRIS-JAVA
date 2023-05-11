package tetris;

import java.awt.Color;

/**
 *
 * @author Davi Campolina
 */
public class BlocoTetris {
    
    private int[][] formato;
    private Color cor;
    
    public BlocoTetris (int[][] formato, Color cor) {
        
        this.formato = formato;
        this.cor = cor;
        
    }

    public int[][] getFormato () {
        
        return formato;
        
    }

    public Color getCor () {
        
        return cor;
        
    }
    
    public int getAltura () {
        
        return formato.length;
        
    }
    
    public int getLargura () {
        
        return formato[0].length;
        
    }
    
}
