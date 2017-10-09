import java.io.*;
import java.net.*;

public class Main {

	public static void main(String[] args) {
		//email2Name();
		printNameList();
	}

	private static void printNameList() {
		final String address = "http://www.ecs.soton.ac.uk/people/";
		String str = getWebpage(address,750);
		str = str.substring(str.indexOf("<tbody class=\"list\">"));
		str = str.substring(1,str.indexOf("</tbody>"));
		
		while(str.indexOf("class=\"js-tableSort-name\"") != -1) {
			str = str.substring(str.indexOf("class=\"js-tableSort-name\"")+1);
			String curName = str.substring(str.indexOf("href=\"/people/"), str.indexOf("</a>"));
			curName = curName.substring(curName.indexOf(">")+1);
			System.out.println(curName);
		}
	}
	
	public static void email2Name() {
		final String address = "http://www.ecs.soton.ac.uk/people/";
		String str = getWebpage(address + getInput(),100);
		str = str.substring(str.indexOf("property=\"name\">"));
		str = str.substring(16,str.indexOf("<"));
		
		System.out.println(str);
	}
	
	public static String getInput() {
		String str = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			str = in.readLine();
			in.close();
		} catch (IOException e) { str = e.toString(); }
		
		return str;
	}
	
	public static String getWebpage(String linkStr, Integer lineNum) {
		lineNum = lineNum != null ? lineNum : -1;
		String str = "";
		
		try 
		{
			URL userURL;
			String curLine = "";
			int count = 0;
			userURL = new URL(linkStr);
			BufferedReader in = new BufferedReader(new InputStreamReader(userURL.openStream()));
		
			while (curLine != null && (count <= lineNum || lineNum == -1)) {
				str += curLine+"\n";
				curLine = in.readLine();
				count++;
			}
	    
			in.close();
		} 
		catch(IOException e) { str = e.toString(); }
		
		return str;
		
	}
	
}
