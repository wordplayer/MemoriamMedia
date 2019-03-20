import java.io.IOException;


public class listBand {
	public String listBands(String data) throws IOException{
		String val="";
		for (int i = -1; (i = data.indexOf("</a></td><td><a href=\"", i + 1)) != -1; ) {    
		    loadLyricHTML in = new loadLyricHTML();
		    val = in.loadLyricHTMLData(i, data);
		    break;
		}
		return val;
	}
}
