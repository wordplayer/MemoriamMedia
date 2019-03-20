import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class LoadData {
	public String dataHTML(String search_string) throws IOException{
	String line;
	String builder="";
	search_string = search_string.replaceAll(" ","+");
	URL myUrl = new URL("http://www.metrolyrics.com/search.html?search=" + search_string);

    BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        myUrl.openStream()));


    while ((line = in.readLine()) != null){
    	builder+=line;
    }
    
    listBand listOb = new listBand();
    return(listOb.listBands(builder));
    
    
	}
}
                        
