import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//System.out.println(email2Name());
		printNameList();
	}

	private static void printNameList() {
		ArrayList<String> nameList = getNameList();
		for (int i = 1; i < nameList.size(); i++) 
			System.out.println(nameList.get(i));
	}
	
	private static ArrayList<String> getNameList() {
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.clear();
		final String address = "http://www.ecs.soton.ac.uk/people/";
		String str = getWebpage(address,750);
		str = str.substring(str.indexOf("<tbody class=\"list\">"));
		str = str.substring(1,str.indexOf("</tbody>"));
		
		while(str.indexOf("class=\"js-tableSort-name\"") != -1) {
			str = str.substring(str.indexOf("class=\"js-tableSort-name\"")+1);
			String curName = str.substring(str.indexOf("href=\"/people/"), str.indexOf("</a>"));
			curName = curName.substring(curName.indexOf(">")+1);
			nameList.add(curName);
		}
		
		return nameList;
	}
	
	private static String email2Name() {
		final String address = "http://www.ecs.soton.ac.uk/people/";
		String str = getWebpage(address + getInput(),100);
		str = str.substring(str.indexOf("property=\"name\">"));
		str = str.substring(16,str.indexOf("<"));
		return str;
	}
	
	private static String getInput() {
		String str = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			str = in.readLine();
			in.close();
		} catch (IOException e) { str = e.toString(); }
		
		return str;
	}
	
	private static String getWebpage(String linkStr, Integer lineNum) {
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
