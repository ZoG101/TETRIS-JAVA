package tetris;

/**
 *
 * @author Davi Campolina
 */
public class GameThread extends Thread {
    
    private AreaDeJogo ga;
    
    public GameThread (AreaDeJogo ga) {
        
        this.ga = ga;
        
    }
    
    @Override
    public void run () {
        
        while (true) {
            
            ga.spawnaBloco();
            
            while(ga.blocoQueda()) {
                
                try {

                    Thread.sleep(1000);

                } catch (Exception e) {

                    System.err.println(e.getMessage());

                }
                
            }
            
            if (ga.foraDoLimite()) {
                
                System.out.println("Fim de Jogo.");
                break;
                
            }
            
            ga.moveBlocoParaFundo();
            ga.limpaLinhas();

        }
        
    }
    
}
