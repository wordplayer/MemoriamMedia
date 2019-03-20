import com.mpatric.mp3agic.*;
import java.net.*;
import java.io.*;
public class ExtractHTML {
    public static void main(String args[])throws Exception
    {
        String source="C:\\Users\\Smart User\\Downloads\\A Rocket To The Moon - Baby Blue Eyes.mp3";
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        String songtitle=id3v2Tag.getTitle();
        songtitle=songtitle.replaceAll(" ", "+");
        URL lyric=new URL("http://www.lyricsmode.com/search.php?search="+songtitle);
        BufferedReader buf=new BufferedReader(new InputStreamReader(lyric.openStream()));
        String inputLine="";
        String builder="";
        String link="";
        while((inputLine=buf.readLine())!=null)
        {
            builder+=inputLine;
        }    
        int i=-1,j=-1;
        int flag=0;
        do
        {
            i=builder.indexOf("class=\"search_highlight\">",i+1);
            //System.out.println(i);
            j=builder.indexOf("</a></td><td><a href=\"",j+1);
            //System.out.println(j);
            String name=builder.substring(i+1,j);
            //System.out.println(name);
            int si=name.indexOf('>');
            name=name.substring(si+1,name.length());
            //System.out.println(name+" "+id3v2Tag.getArtist());
            if(name.compareTo(id3v2Tag.getArtist())==0)
            {
                builder=builder.substring(j);
                int start=builder.indexOf('=');
               /*String builder1=builder.substring(start+1);
                int end=builder1.indexOf('"');*/
                link=builder.substring(start+2,builder.indexOf('.',start+1));
                String lyricLink="http://www.lyricsmode.com/"+link+".html";
                URL lyricmain=new URL(lyricLink);
                BufferedReader br=new BufferedReader(new InputStreamReader(lyricmain.openStream()));
                String b1="";
                String in="";
                while((in=br.readLine())!=null)
                b1+=in;
                int k=b1.indexOf("class=\"ui-annotatable\">");
                b1=b1.substring(k,b1.indexOf("</p><p id=\"lyrics_text_selected\""));
                
                b1=b1.substring(b1.indexOf('>')+1);
                
                String lyrics=b1.replaceAll("<br />","\n");
                int l=lyrics.indexOf("<a");
                int m=lyrics.indexOf(">");
                if(l!=-1 && m!=-1)
                {
                    String fil1=lyrics.substring(0,l);
                    String fil2=lyrics.substring(m+1);
                   String fil=fil1+fil2;
                   fil=fil.replaceAll("</a>","");
                   System.out.println(fil);
                }    
                else
                System.out.println(lyrics);    
                flag=1;
                break;
            }    
        }
        while(flag==0);
    }
    
}
