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
    private int[][][] formatos;
    private int rotacaoAtual;
    
    public BlocoTetris (int[][] formato, Color cor) {
        
        this.formato = formato;
        this.cor = cor;
        
        this.iniciaFormatos();
        
    }
    
    private void iniciaFormatos () {
        
        this.formatos = new int[4][][];
        
        for (int i = 0; i < 4; i++) {
            
            int l = this.formato[0].length;
            int c = this.formato.length;
            
            this.formatos[i] = new int[l][c];
            
            for (int y = 0; y < l; y++) {
                
                for (int x = 0; x < c; x++) {
                    
                    this.formatos[i][y][x] = this.formato[c - x - 1][y];
                    
                }
                
            }
            
            this.formato = this.formatos[i];
            
        }
        
    }
    
    public void spawnaBloco (int gradeLargura) {
        
        this.rotacaoAtual = 3;
        this.formato = formatos[this.rotacaoAtual];
        
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
    
    public void rotacionaBloco () {
        
        this.rotacaoAtual++;
        if (this.rotacaoAtual > 3) this.rotacaoAtual = 0;
        this.formato = this.formatos[rotacaoAtual];
        
    }
    
    public int getBordaDoFundo () {
        
        return this.y + this.getAltura();
        
    }
    
}
