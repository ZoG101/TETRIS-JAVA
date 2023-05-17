package tetris.blocos;

import tetris.BlocoTetris;

/**
 *
 * @author Davi Campolina
 */
public class FormatoI extends BlocoTetris {
    
    public FormatoI() {
        
        super(new int[][]{{1, 1, 1, 1}});
        
    }

    @Override
    public void rotacionaBloco() {
        
        super.rotacionaBloco();
        
        if (this.getLargura() == 1) {
            
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
            
        } else {
            
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
            
        }
        
    }
    
    
    
}
