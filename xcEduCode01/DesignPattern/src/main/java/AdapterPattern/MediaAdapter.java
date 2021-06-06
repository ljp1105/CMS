package AdapterPattern;

public class  MediaAdapter implements MediaPlayer{
    AdvacedMediaPlayer advacedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")){
            advacedMediaPlayer=new VlcPlayer();
        }
        if (audioType.equalsIgnoreCase("mp4")){
            advacedMediaPlayer=new Mp4Player();
        }
    }

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")){
            advacedMediaPlayer.playVlc(fileName);
        }
        if (audioType.equalsIgnoreCase("mp4")){
            advacedMediaPlayer.playMp4(fileName);
        }
    }
}
