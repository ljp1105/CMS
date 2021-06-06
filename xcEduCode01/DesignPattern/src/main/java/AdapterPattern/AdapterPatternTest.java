package AdapterPattern;

public class AdapterPatternTest {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3","凤凰传奇");
        audioPlayer.play("MP4","小沈阳");
        audioPlayer.play("vlc","李诞");
        audioPlayer.play("txt","大幂幂");
    }
}
