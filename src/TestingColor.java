
import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;


public class TestingColor {
    public static void main(String args[])throws Exception
    {
        /*File f=new File("C:\\Users\\Smart User\\Desktop\\1 The Beatles.jpg");
        ImageInputStream is=ImageIO.createImageInputStream(f);
        Iterator iter=ImageIO.getImageReaders(is);
        if(!iter.hasNext())
        {
            System.out.println("Cannot find specified file: "+f);
            System.exit(1);
        }
        ImageReader ir=(ImageReader)iter.next();
        ir.setInput(is);
        BufferedImage img=ir.read(0);*/
        Mp3File mp3=new Mp3File("C:\\Users\\Smart User\\Downloads\\(1996) Morningrise\\Black Rose Immortal.mp3");
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        try
        {
           byte imageData[]=id3v2Tag.getAlbumImage();
           BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
           int height=img.getHeight();
        int width=img.getWidth();
        Map m=new HashMap();
        if(m==null)
            System.out.println("Empty!");
        for(int i=0;i<width;i++)
        {
          // for(int j=0;j<height;j++)
            //{
               int rgb=img.getRGB(i,0);
               int rgbArr[]=getRGBArr(rgb);
               
               //if(!isGray(rgbArr))
               //{
                   Integer counter=(Integer)m.get(rgb);
                   if(counter==null)
                       counter=0;
                   counter++;
                   m.put(rgb,counter);
                   //System.out.println(counter);
              // } 
           // }    
        }
        //System.out.println(Integer.toHexString(img.getRGB(width-1,height-1)));
        String colourHex=getMostCommonColour(m);
        System.out.println(colourHex);
     }
        catch(Exception e)
        {
            System.out.println("Exception occured.");
            System.out.println(e);
            e.printStackTrace();
        }    
        
    }
    public static String getMostCommonColour(Map map)throws Exception
    {
        List list=new LinkedList(map.entrySet());
        System.out.println(list.size());
        Collections.sort(list,new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable)((Map.Entry)(o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue());
            }
        });
        Map.Entry me=(Map.Entry)list.get(list.size()-1);
        int rgb[]=getRGBArr((Integer)me.getKey());
        return Integer.toHexString(rgb[0])+" "+Integer.toHexString(rgb[1])+" "+Integer.toHexString(rgb[2]);
     } 
    public static int[] getRGBArr(int pixel)throws Exception
    {
        int alpha=(pixel>>24)&0xff;
        int red=(pixel>>16)&0xff;
        int green=(pixel>>8)&0xff;
        int blue=(pixel)&0xff;
       // System.out.println(alpha+" "+red+" "+green+" "+blue);
        return new int[] {red,green,blue};
    } 
    public static boolean isGray(int rgbArr[])throws Exception
    {
        int rgDiff=rgbArr[0]-rgbArr[1];
        int rbDiff=rgbArr[1]-rgbArr[2];
        int tolerance=100;
        if(rgDiff>tolerance||rgDiff<-tolerance)
        if(rbDiff>tolerance||rbDiff<-tolerance)
        {
            return false;
        }
        return true;
    }        
}
