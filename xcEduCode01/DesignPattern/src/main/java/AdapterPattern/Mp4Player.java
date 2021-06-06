package AdapterPattern;

public class Mp4Player implements AdvacedMediaPlayer{
    public void playVlc(String fileName) {

    }

    public void playMp4(String fileName) {
        System.out.println("Playint mp4"+fileName);
    }
}
