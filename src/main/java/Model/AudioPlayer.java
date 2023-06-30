package Model;
import javafx.scene.media.AudioClip;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class AudioPlayer {
    private static AudioClip background;
    private static AudioClip punch1;
    private static AudioClip punch2;
    private static AudioClip dead;
    public AudioPlayer(){

    }
    public void playBackgroundAudio(){
        background = new AudioClip(this.getClass().getResource("/sounds/685206__x1shi__video-game-music-seamless.wav").toExternalForm());
        background.setCycleCount(INDEFINITE);
        background.setVolume(0.5);
        background.play();
    }
    public void playPunch1(){
        punch1 = new AudioClip(this.getClass().getResource("/sounds/615793__parret__arcade-punch-01.wav").toExternalForm());
        punch1.setVolume(50.0);
        punch1.play();
    }
    public void playPunch2(){
        punch2 = new AudioClip(this.getClass().getResource("/sounds/615792__parret__arcade-punch-02.wav").toExternalForm());
        punch2.setVolume(50.0);
        punch2.play();
    }
    public void playDeadAudio(){
        dead = new AudioClip(this.getClass().getResource("/sounds/624880__sonically_sound__old-video-game-2.wav").toExternalForm());
        dead.play();
    }
    public void stop(){
        background.stop();
    }


}
