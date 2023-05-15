package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

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

    /**
     * Creates new form AreaDeJogo
     * @param placeholder
     */
    public AreaDeJogo(JPanel placeholder, Integer colunas) {
        
        initComponents();
        placeholder.setVisible(Boolean.FALSE);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        this.coluna = Integer.valueOf(colunas.intValue());
        this.tamanhoCelula = Integer.valueOf(this.getBounds().width) / colunas.intValue();
        this.linhas = this.getBounds().height / tamanhoCelula.intValue();
        
        this.fundo = new Color[this.linhas][this.coluna];
        
    }
    
    public void spawnaBloco () {
        
        this.bloco = new BlocoTetris(new int[][]{{1,0}, {1,0}, {1,1}}, this.corAleatoria ());
        bloco.spawnaBloco(this.coluna);
        
    }
    
    public Boolean blocoQueda () {
        
        if (!this.checaFundo()) { 
            
            this.moveBlocoParaFundo();
            return Boolean.FALSE;
            
        }
        
        this.bloco.moveParaBaixo();
        this.repaint();
        
        return Boolean.TRUE;
        
    }
    
    public void moveBlocoEsquerda () {
        
        if (!this.checaEsquerda()) return;
            
        this.bloco.moveParaEsquerda();
        this.repaint();   
        
    }
    
    public void moveBlocoDireita () {
        
        if (!this.checaDireita()) return;
            
        this.bloco.moveParaDireita();
        this.repaint();   
           
    }
    
    public void rotacionaBloco () {
        
        this.bloco.rotacionaBloco();
        this.repaint();
        
    }
    
    public void dropaBloco () {
        
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
    
    private void moveBlocoParaFundo () {
        
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
                    
                }
                
            }
            
        }
        
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
    
    private Color corAleatoria () {
        
        Random aleatorio = new Random();
        String r = "" + Math.round((aleatorio.nextDouble() * (255 - 0 + 1) + 0));
        String g = "" + Math.round((aleatorio.nextDouble() * (255 - 0 + 1) + 0));
        String b = "" + Math.round((aleatorio.nextDouble() * (255 - 0 + 1) + 0));
        
        Color cor = new Color(Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b));
        return cor;
        
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
