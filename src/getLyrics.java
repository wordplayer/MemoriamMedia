
public class getLyrics {
	public String getLyricData(String data){
		int i,j;
		char ad;
		String builder="";
		for (i = -1; (i = data.indexOf("class=\'dn\'", i + 1)) != -1; ) {    
		   
		    break;
		}
		for (j = -1; (j = data.indexOf("<!-- /SONG LYRICS -->", j + 1)) != -1; ) {    
		  
		    break;
		}
		i += 11;
		
		
		while(i<(j-6)){
		ad = data.charAt(i);
		builder+=ad;
		i++;
		}
		return(builder.replace("<br/>","\n"));
		
	}
}
