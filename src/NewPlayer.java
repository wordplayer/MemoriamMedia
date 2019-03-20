import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class NewPlayer extends Application{
   static Nodes start;
   static int count;
   public NewPlayer(Nodes start)throws Exception
   {
       this.start=start;
   } 
   public void insert(String data1,String data2)throws Exception
   {
       if(this.start==null)
       this.start=new Nodes(data1,data2,this.start);
       else
       {
           Nodes temp=this.start;
           while(temp.getNext()!=null)
           temp=temp.getNext();
           Nodes newNode=new Nodes(data1,data2,null);
           temp.setNext(newNode);
       }     
   }
   public static void mainList(char ch)throws Exception
   {
       String file=ch+":\\";
       File f=new File(file);
       File lof[]=f.listFiles();
       try
       {
           for(int i=0;i<lof.length;i++)
           {
               if(lof[i].getName().endsWith(".mp3"))
               {
                   Mp3File mp3=new Mp3File(file+lof[i].getName());
                   if(mp3.hasId3v2Tag())
                   {
                       ID3v2 id3v2Tag=mp3.getId3v2Tag();
                       new NewPlayer(start).insert(id3v2Tag.getTitle(), file+lof[i].getName());
                       count++;
                   }
                   else if(new File(file+lof[i].getName()).isDirectory())
                   new NewPlayer(start).getFilename(file+lof[i].getName());
               }    
           }    
       }    
       catch(Exception e)
       {
           ;
       }    
   }
   public void getFilename(String file)throws Exception
   {
       try
       {
          File f=new File(file);
          File lof[]=f.listFiles();
          for(int i=0;i<lof.length;i++)
          {
              if(lof[i].getName().endsWith(".mp3"))
              {
                  Mp3File mp3=new Mp3File(file+lof[i].getName());
                  if(mp3.hasId3v2Tag())
                  {
                      ID3v2 id3v2Tag=mp3.getId3v2Tag();
                      new NewPlayer(start).insert(id3v2Tag.getTitle(),file+lof[i].getName());
                      count++;
                  }
              } 
              else if(new File(file+lof[i].getName()).isDirectory())
              new NewPlayer(start).getFilename(file+lof[i].getName()+"\\");    
          }    
       }
       catch(Exception e)
       {
           ;
       }    
   }
     public static void main(String args[])throws Exception
    {
        //artistList obj=new artistList(null);
        //songList obj1=new songList();
        char ch='C';
        int count=0;
        //System.out.println("ARTISTS:");
        System.out.println("SONGS:");
        while(true)
        {
            if(new File(ch+":\\").isDirectory())
           // obj.main(ch);
            new NewPlayer(null).mainList(ch);    
            else
            count++;
            ch++;
            if(count>2)
            break;    
       }
       launch(NewPlayer.class,args); 
   }       
    @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("MemoriamMedia"); 
       stage.setMaxHeight(1000);
       stage.setMaxWidth(800);
       BorderPane border=new BorderPane();
       border.setLeft(addVBox());
       Scene scene=new Scene(border);
       stage.setScene(scene);
       stage.show();
    }
    public VBox addVBox()throws Exception
    {
        VBox vbox=new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Hyperlink options[]=new Hyperlink[] {
          new Hyperlink("Music"),
          new Hyperlink("Video"),
          new Hyperlink("Pictures")
        };
        options[0].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
            }
        });
        for(int i=0;i<options.length;i++)
        {
            vbox.setMargin(options[i],new Insets(0,0,0,8));
            vbox.getChildren().add(options[i]);
        }    
        return vbox;
    }        
  
}
