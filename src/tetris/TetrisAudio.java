package tetris;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Davi Campolina
 */
public class TetrisAudio {
    
    private String pasta = "sounds" + File.separator;
    private String limpaLinhaCaminho = pasta + "clear.wav";
    private String gameoverCaminho = pasta + "success.wav";
    
    private Clip limpaLinhaSom, gameoverSom;
    
    public TetrisAudio () {
        
        try {
            
            limpaLinhaSom = AudioSystem.getClip();
            gameoverSom = AudioSystem.getClip();
            
            limpaLinhaSom.open(AudioSystem.getAudioInputStream(new File(limpaLinhaCaminho).getAbsoluteFile()));
            gameoverSom.open(AudioSystem.getAudioInputStream(new File(gameoverCaminho).getAbsoluteFile()));
            
        } catch (Exception ex) {
            
            Logger.getLogger(TetrisAudio.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            
        }
        
    }
    
    public void tocaLimpaLinha () {
        
        limpaLinhaSom.setFramePosition(0);
        limpaLinhaSom.start();
        
    }
    
    public void tocaGameOver () {
        
        gameoverSom.setFramePosition(0);
        gameoverSom.start();
        
    }
    
}
