import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class loadLyricHTML {
	public String loadLyricHTMLData(int val,String data) throws IOException{
		char ad = 'a';
		val+=22;
		String build = null;
		String builder = "";
		while(ad != ' ')
		{
			ad = data.charAt(val);
			val++;
			builder+=ad;	
		}
		builder = builder.substring(0,builder.length()-2);
		builder = "http://www.metrolyrics.com" + builder;
		
		URL myUrl = new URL(builder);
	    BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                        myUrl.openStream()));


	    String line;
		while ((line = in.readLine()) != null){
	    	build+=line;
	    }
		
		getLyrics lyricOb = new getLyrics();
		return(lyricOb.getLyricData(build));
		
	}
}
