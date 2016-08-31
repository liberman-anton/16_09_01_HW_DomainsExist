import java.io.*;
import java.net.*;
import java.util.*;
public class HttpProtocolTestAppl {
	
	private static final String FILE_NAME_DEFAULT = "domains.txt";
	public static LinkedList<String> domains = new LinkedList<>();

	public static void main(String[] args) throws MalformedURLException {
		if(args.length == 0)
			return;
		String file;
		file = args.length == 1 ? FILE_NAME_DEFAULT : args[1];
		try {
			initDomains(file);
		} catch (IOException e1) {
			System.out.println("Wrong file name");
		}
		boolean flagIsExist = false;
		for(String domain : domains) {
			String str = getStr(args[0],domain);
			URL url = new URL(str);
			try {
				InputStream stream = url.openStream();
			} catch (IOException e) {
				continue;
			}
			System.out.println(str);
			flagIsExist = true;
		}
		if(!flagIsExist)
			System.out.println("the site doesn’t exist");
	}

	private static void initDomains(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while(reader.ready()){
			String line = reader.readLine();
			domains.add(line);
		}
		reader.close();
	}

	private static String getStr(String name, String domain) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(name);
		sb.append(".");
		sb.append(domain);
		return sb.toString();
	}
}
