import javazoom.jl.player.Player;
import java.io.*;
public class playingMedia {
    public static void main(String args[])throws Exception
    {
        FileInputStream fis=new FileInputStream("C:\\Users\\Smart User\\Desktop\\Trip The Darkness.mp3");
        Player mp3=new Player(fis);
        mp3.play();
    }
}