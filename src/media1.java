import javazoom.jl.player.Player;
import java.io.*;

public class media1 {
   public static void main(String args[])throws Exception
   {
       InputStreamReader in=new InputStreamReader(System.in);
       BufferedReader buf=new BufferedReader(in);
       System.out.println("Enter the name of the audio file you wish to play.");
       String name=(buf.readLine()).toLowerCase();
       int count=0;
       for(int i=0;i<name.length();i++)
       {
           if(name.charAt(i)==' ')
           count++;    
       }
       String nameAr[]=new String[count+1];
       int beg=0;
       if(count!=0)
       {    
       for(int i=0;i<count+1;i++)
       {
           if(name.charAt(i)==' ')
           {    
              nameAr[i]=name.substring(beg,i);
              beg=i+1;
           }
       }
       }
       else
       nameAr[0]=name;    
       File f=new File("E:\\Music and Movie Trailers");
       File lof[]=f.listFiles();
       //System.out.println("The audio files in the folder:");
       count=0;
       int countw=0;
       String name1="",w="";
       beg=0;
       for(int i=0;i<lof.length;i++)
       {
           if(lof[i].getName().endsWith(".mp3"))
           {
               name1=(lof[i].getName()).toLowerCase();
               System.out.println(name1);
               for(int j=0;j<name1.length();j++)
               {
                   if(name1.charAt(j)==' '||name1.charAt(j)=='.'||name1.charAt(j)=='-'||(name1.charAt(j)>='0'&& name1.charAt(j)<='9'))
                   {
                       w=name1.substring(beg,j);
                       beg=j+1;
                      for(int k=0;k<nameAr.length;k++)
                      {
                        if(nameAr[k].compareTo(w)==0)
                        count++;    
                      }
                      if(count>0)
                      countw++;
                      count=0;
                   } 
               }
             if(countw>0 && countw<=nameAr.length)
             {
               FileInputStream fis=new FileInputStream("E:\\Music and Movie Trailers\\"+lof[i].getName());
               Player mp3=new Player(fis);
               mp3.play();
             }    
           }
        }
    }
}   
   
