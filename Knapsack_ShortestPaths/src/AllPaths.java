
//link to source: http://stackoverflow.com/questions/8342101/efficient-algorithm-to-find-all-the-paths-from-a-to-z
import java.util.*;
import java.io.*;

public class AllPaths {
    private static HashMap<String, List<String>> left_map_rights;
    public static void main(String[] args){
   // 	String txt="A B\r\nU Z\r\nB A\r\nA C\r\nZ A\r\nK Z\r\nA Q\r\nD A\r\nU K\r\nP U\r\nU P\r\nB Y\r\nY R\r\nY U\r\nC R\r\nR Q\r\nA D\r\nQ Z\r\n";
   //     System.out.println(execute(txt));
    	String s="Vertex a Vertex b";
   	  System.out.println(s.substring(7, 8)+" "+s.substring(16, 17)+"\n"+s.substring(7, 8)+" "+s.substring(16, 17)+"\n");
    }

    public static String execute(String text, String source, String target){
    	try {
    		
    		writwToFile(text);
			return executeAllPaths(source, target);
	      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception";
		}
    	
    }
    public static String readFromFile(File f) throws IOException
    {
    	FileReader fr = new FileReader(f);
    	int size = (int) f.length();
    	char[] data = new char[size];
    	fr.read(data);
    	return new String(data);
    }
    public static String writwToFile(String textToWrite) throws IOException{
        File fRe=new File("routsWrite.txt") ;
        String res="";
		 try {
		FileWriter fw = new FileWriter(fRe);
		fw.append(textToWrite);	
		res=fw.toString();
		fw.close();
		return res;
	} catch (IOException e) {
		e.printStackTrace();
		return "Exception";
	}}
    public static String executeAllPaths(String src, String trgt) throws Exception {
        left_map_rights = new HashMap<>();
        BufferedReader r = new BufferedReader(new FileReader("routsWrite.txt"));
        String line;
        HashMap<String, Void> lines = new HashMap<>();
        while ((line = r.readLine()) != null) {
            if (lines.containsKey(line)) { // ensure no duplicate lines
                continue;
            }
            lines.put(line, null);
            int space_location = line.indexOf(' ');
            String left = line.substring(0, space_location);
            String right = line.substring(space_location + 1);
            if(left.equals(right)){ // rejects entries whereby left = right
                continue;
            }
            List<String> rights = left_map_rights.get(left);
            if (rights == null) {
                rights = new ArrayList<String>();
                left_map_rights.put(left, rights);
            }
            rights.add(right);
        }
        r.close();
     
        String result="";
        List<List<String>> routes = GetAllRoutes(src, trgt);
        for (List<String> route : routes) {
            result+=route+"\r\n";
        }
        return result;
    }

    public static List<List<String>> GetAllRoutes(String start, String end) {
        List<List<String>> routes = new ArrayList<>();
        List<String> rights = left_map_rights.get(start);
        if (rights != null) {
            for (String right : rights) {
                List<String> route = new ArrayList<>();
                route.add(start);
                route.add(right);
                Chain(routes, route, right, end);
            }
        }
        return routes;
    }

    public static void Chain(List<List<String>> routes, List<String> route, String right_most_currently, String end) {
        if (right_most_currently.equals(end)) {
            routes.add(route);
            return;
        }
        List<String> rights = left_map_rights.get(right_most_currently);
        if (rights != null) {
            for (String right : rights) {
                if (!route.contains(right)) {
                    List<String> new_route = new ArrayList<String>(route);
                    new_route.add(right);
                    Chain(routes, new_route, right, end);
                }
            }
        }
    }
}