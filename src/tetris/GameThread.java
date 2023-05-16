package tetris;

/**
 *
 * @author Davi Campolina
 */
public class GameThread extends Thread {
    
    private AreaDeJogo ga;
    private FormatoDoJogo fj;
    private Integer pontuacao;
    private Integer nivel;
    private Integer nivelPorPonto;
    private Integer pausa;
    private Integer velocidadePorNivel;
    
    public GameThread (AreaDeJogo ga, FormatoDoJogo fj) {
        
        this.ga = ga;
        this.fj = fj;
        this.pontuacao = Integer.valueOf(0);
        this.nivel = Integer.valueOf(1);
        this.nivelPorPonto = Integer.valueOf(50);
        this.pausa = Integer.valueOf(1000);
        
    }
    
    @Override
    public void run () {
        
        while (true) {
            
            ga.spawnaBloco();
            
            while(ga.blocoQueda()) {
                
                try {

                    Thread.sleep(this.pausa.intValue());

                } catch (Exception e) {

                    System.err.println(e.getMessage());

                }
                
            }
            
            if (ga.foraDoLimite()) {
                
                System.out.println("Fim de Jogo.");
                break;
                
            }
            
            this.pontuacao += Integer.valueOf(ga.moveBlocoParaFundo());
            this.pontuacao += Integer.valueOf(ga.limpaLinhas());
            this.fj.atualizaPontuacao(this.pontuacao);
            
            int nvl = this.pontuacao / this.nivelPorPonto + 1;
            
            if (nvl > this.nivel) {
                
                this.nivel = nvl;
                fj.atualizaNivel(this.nivel);
                this.pausa -= this.velocidadePorNivel = Integer.valueOf((pausa.intValue()/100)*10);
                
            }

        }
        
    }
    
}
