package tetris;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Davi Campolina
 */
public class BlocoTetris {
    
    private int[][] formato;
    private Color cor;
    private Integer x, y;
    
    public BlocoTetris (int[][] formato, Color cor) {
        
        this.formato = formato;
        this.cor = cor;
        
    }
    
    public void spawnaBloco (int gradeLargura) {
        
        Random aleatorio = new Random();
        this.x = Integer.valueOf(new String("" + Math.round((aleatorio.nextDouble() * ((gradeLargura - 3) - 0 + 1) + 0))));
        this.y = -this.getAltura()-1;
        
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

    public Integer getX () {
        
        return x;
        
    }

    public Integer getY () {
        
        return y;
        
    }
    
    public void moveParaBaixo () {
        
        this.y++;
        
    }
    
    public void moveParaEsquerda () {
        
        this.x--;
        
    }
    
    public void moveParaDireita () {
        
        this.x++;
        
    }
    
    public int getBordaDoFundo () {
        
        return this.y + this.getAltura();
        
    }
    
}
