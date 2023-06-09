package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetris.blocos.*;

/**
 *
 * @author Davi Campolina
 */
public class AreaDeJogo extends JPanel {
    
    private Integer linhas;
    private Integer coluna;
    private Integer tamanhoCelula;
    private BlocoTetris bloco; 
    private Color[][] fundo;
    private BlocoTetris[] blocos;

    /**
     * Creates new form AreaDeJogo
     * @param placeholder
     */
    public AreaDeJogo(JPanel placeholder, Integer colunas) {
        
        initComponents();
        //placeholder.setVisible(Boolean.FALSE);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        this.coluna = Integer.valueOf(colunas.intValue());
        this.tamanhoCelula = Integer.valueOf(this.getBounds().width) / colunas.intValue();
        this.linhas = this.getBounds().height / tamanhoCelula.intValue();
        
        this.blocos = new BlocoTetris[]{new FormatoI(), new FormatoJ(), new FormatoL(), new FormatoO(), new FormatoS(), new FormatoT(), new FormatoZ()};
        
    }
    
    public void iniciaFundo () {
        
        this.fundo = new Color[this.linhas][this.coluna];
        
    }
    
    public void spawnaBloco () {
        
        Random aleatorio = new Random();
        this.bloco = this.blocos[aleatorio.nextInt(this.blocos.length)];
        bloco.spawnaBloco(this.coluna);
        
    }
    
    public Boolean blocoQueda () {
        
        if (!this.checaFundo()) { 
           
            return Boolean.FALSE;
            
        }
        
        this.bloco.moveParaBaixo();
        this.repaint();
        
        return Boolean.TRUE;
        
    }
    
    public void moveBlocoEsquerda () {
        
        if (this.bloco == null) return;
        if (!this.checaEsquerda()) return;
            
        this.bloco.moveParaEsquerda();
        this.repaint();   
        
    }
    
    public void moveBlocoDireita () {
        
        if (this.bloco == null) return;
        if (!this.checaDireita()) return;
            
        this.bloco.moveParaDireita();
        this.repaint();   
           
    }
    
    public void rotacionaBloco () {
        
        if (this.bloco == null) return;
        
        this.bloco.rotacionaBloco();
        
        if (this.bloco.getBordaEsquerda() < 0) this.bloco.setX(0);
        if (this.bloco.getBordaDireita() >= this.coluna) this.bloco.setX(this.coluna - this.bloco.getLargura());
        if (this.bloco.getBordaDoFundo() >= this.linhas) this.bloco.setY(this.linhas - this.bloco.getAltura());
        
        this.repaint();
        
    }
    
    public void dropaBloco () {
        
        if (this.bloco == null) return;
        while (this.checaFundo()) {
            
            this.bloco.moveParaBaixo();
            this.repaint();
            
        }
        
    }
    
    private Boolean checaFundo () {
        
        if (this.bloco.getBordaDoFundo() == this.linhas) return Boolean.FALSE;
        
        int[][] formato = this.bloco.getFormato();
        int l = this.bloco.getLargura();
        int a = this.bloco.getAltura();
        
        for (int col = 0; col < l; col++) {
            
            for (int linha = a - 1; linha >= 0; linha--) {
                
                if (formato[linha][col] != 0) {
                    
                    int x = col + this.bloco.getX();
                    int y = linha + this.bloco.getY() + 1;
                    if (y < 0) break;
                    if (this.fundo[y][x] != null) return Boolean.FALSE;
                    break;
                    
                }
                
            }
            
        }
        
        return Boolean.TRUE;
        
    }
    
    private Boolean checaEsquerda () {
        
        if (this.bloco.getBordaEsquerda() == 0) return Boolean.FALSE;
        
        int[][] formato = this.bloco.getFormato();
        int l = this.bloco.getLargura();
        int a = this.bloco.getAltura();
        
        for (int linha = 0; linha < a; linha++) {
            
            for (int col = 0; col < l; col++) {
                
                if (formato[linha][col] != 0) {
                    
                    int x = col + this.bloco.getX() - 1;
                    int y = linha + this.bloco.getY();
                    if (y < 0) break;
                    if (this.fundo[y][x] != null) return Boolean.FALSE;
                    break;
                    
                }
                
            }
            
        }
        
        return Boolean.TRUE;
 
    }
    
    private Boolean checaDireita () {
        
        if (this.bloco.getBordaDireita() == this.coluna) return Boolean.FALSE;
        
        int[][] formato = this.bloco.getFormato();
        int l = this.bloco.getLargura();
        int a = this.bloco.getAltura();
        
        for (int linha = 0; linha < a; linha++) {
            
            for (int col = l - 1; col >= 0; col--) {
                
                if (formato[linha][col] != 0) {
                    
                    int x = col + this.bloco.getX() + 1;
                    int y = linha + this.bloco.getY();
                    if (y < 0) break;
                    if (this.fundo[y][x] != null) return Boolean.FALSE;
                    break;
                    
                }
                
            }
            
        }
        
        return Boolean.TRUE;
        
    }
    
    public Integer limpaLinhas () {
        
        Boolean linhaCompleta;
        int linhasLimpas = 0;
        int multiplicador = 1;
        
        for (int l = this.linhas - 1; l >= 0; l--) {
            
            linhaCompleta = Boolean.TRUE;
            
            for (int c = 0; c < this.coluna; c++) {
                
                if (this.fundo[l][c] == null) {
                    
                    linhaCompleta = Boolean.FALSE;
                    break;
                    
                }
                
                if (c == this.coluna - 1) multiplicador += (c + 1) * 0.1;
                
            }
            
            if (linhaCompleta.booleanValue()) {
            
                linhasLimpas += this.coluna * multiplicador;
                this.limpaLinha(l);
                this.desceBloco(l);
                this.limpaLinha(0);
                
                l++;
                
                this.repaint();
                
            }
            
        }
        
        if (linhasLimpas > 0) TETRIS.tocaLimpaLinha();
        
        return linhasLimpas;
        
    }
    
    private void limpaLinha (Integer l) {
        
        for (int c = 0; c < this.coluna; c++) {
                    
            this.fundo[l][c] = null;
                    
        }
        
    }
    
    private void desceBloco (Integer linhas) {
        
        for (int l = linhas; l > 0; l--) {
            
            for (int c = 0; c < this.coluna; c++) {
                
                this.fundo[l][c] = this.fundo[l-1][c];
                
            }
            
        }
        
    }
    
    public int moveBlocoParaFundo () {
        
        int blocoPosicionado = 0;
        int[][] forma = this.bloco.getFormato();
        int a = this.bloco.getAltura();
        int l = this.bloco.getLargura();
        
        int xPos = this.bloco.getX();
        int yPos = this.bloco.getY();
        
        Color cor = this.bloco.getCor();
        
        for (int r = 0; r < a; r++) {
            
            for (int c = 0; c < l; c++) {
                
                if (forma[r][c] == 1) {
                    
                    this.fundo[r + yPos][c + xPos] = cor;
                    blocoPosicionado++;
                }
                
            }
            
        }
        
        return blocoPosicionado;
        
    }
    
    public Boolean foraDoLimite () {
        
        if (this.bloco.getY() < 0) {
            
            this.bloco = null;
            return Boolean.TRUE;
            
        }
        return Boolean.FALSE;
        
    }
    
    private void desenhaBloco (Graphics g) {
        
        int altura = this.bloco.getAltura();
        int largura = this.bloco.getLargura();
        int[][] formato = this.bloco.getFormato();
        Color cor = this.bloco.getCor();
        
        for (int i = 0; i < altura; i++) {
            
            for (int j = 0; j < largura; j++) {
                
                if (formato[i][j] == 1){ 
                    
                    int x = (bloco.getX() + j) * this.tamanhoCelula;
                    int y = (bloco.getY() + i) * this.tamanhoCelula;
                    
                    this.desenhaBlocoCelula(g, cor, x, y);
                    
                }
                
            }
            
        }
        
    }
    
    private void desenhaFundo (Graphics g) {
        
        Color cor;
        
        for (int r = 0; r < this.linhas; r++) {
            
            for (int c = 0; c < this.coluna; c++) {
                
                cor = this.fundo[r][c];
                
                if (cor != null) {
                    
                    int x = c * this.tamanhoCelula;
                    int y = r * this.tamanhoCelula;
                    
                    this.desenhaBlocoCelula(g, cor, x, y);
                    
                }
                
            }
            
        }
        
    }
    
    private void desenhaBlocoCelula (Graphics g, Color cor, int x, int y) {
        
        g.setColor(cor);
        g.fillRect(x, y, tamanhoCelula.intValue(), tamanhoCelula.intValue());
        g.setColor(Color.BLACK);
        g.drawRect(x, y, tamanhoCelula.intValue(), tamanhoCelula.intValue());
        
    }
    
    @Override
    protected void paintComponent (Graphics g) {
        
        super.paintComponent(g);
        this.desenhaFundo(g);
        this.desenhaBloco(g);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
