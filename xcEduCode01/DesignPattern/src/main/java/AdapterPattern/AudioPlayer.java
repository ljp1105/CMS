package AdapterPattern;

public class AudioPlayer implements MediaPlayer {
    MediaPlayer mediaPlayer;
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")){
            System.out.println("playing map3..."+fileName);
        }else if (audioType.equalsIgnoreCase("vlc")||audioType.equalsIgnoreCase("mp4")){
            mediaPlayer=new MediaAdapter(audioType);
            mediaPlayer.play(audioType,fileName);
        }else {
            System.out.println("不支持该格式的文件.....");
        }
    }
}
