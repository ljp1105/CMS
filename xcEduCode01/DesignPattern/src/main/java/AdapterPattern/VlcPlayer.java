package AdapterPattern;

public class VlcPlayer implements AdvacedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playint vlc"+fileName);
    }

    public void playMp4(String fileName) {

    }
}
