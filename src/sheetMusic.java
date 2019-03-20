import javax.swing.JFrame;
import abc.notation.*;
import abc.ui.swing.JScoreComponent;
import abc.parser.TuneBook;
import java.io.*;
public class sheetMusic {
    public static void main(String args[])throws Exception
    {
        File f=new File("C:\\Users\\Smart User\\Desktop\\Trip The Darkness.mp3");
        TuneBook tb=new TuneBook(f);
        Tune tune=tb.getTune(60);
        JScoreComponent jscore=new JScoreComponent();
        jscore.setTune(tune);
        //scoreUI.setTune(tune);
        JFrame j=new JFrame();
        j.add(jscore);
        j.pack();
        j.setVisible(true);
        
    }       
}
