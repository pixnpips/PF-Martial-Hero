package Model;
import javafx.scene.media.AudioClip;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class AudioPlayer {
    private static AudioClip backgroundAudio;
    public AudioPlayer(){

    }
    public void play(){
        backgroundAudio = new AudioClip(this.getClass().getResource("/sounds/685206__x1shi__video-game-music-seamless.wav").toExternalForm());
        backgroundAudio.setCycleCount(INDEFINITE);
        backgroundAudio.play();
    }
    public void stop(){
        backgroundAudio.stop();
    }


}
