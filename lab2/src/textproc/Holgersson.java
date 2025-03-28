package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
	    ArrayList<TextProcessor> tpList = new ArrayList<TextProcessor>();
	    Set<String> stopWords = new HashSet<String>();
	    

	    Scanner s = new Scanner(new File("nilsholg.txt"));
	    Scanner sBanned = new Scanner(new File("undantagsord.txt"));
	    
	    s.findWithinHorizon("\uFEFF", 1);
	    s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
	    
	    while(sBanned.hasNext()) {
	    	String bannedWord = sBanned.next().toLowerCase();
	    	stopWords.add(bannedWord);
	    }
	    
	    tpList.add(new SingleWordCounter("nils"));
	    tpList.add(new SingleWordCounter("norge"));
	    tpList.add(new MultiWordCounter(REGIONS));
	    tpList.add(new GeneralWordCounter(stopWords));

	    while (s.hasNext()) {
	        String word = s.next().toLowerCase();

	        for (TextProcessor tp : tpList) {
	            tp.process(word);
	        }
 
	    }

	    s.close();
	    sBanned.close();

	    for (TextProcessor tp : tpList) {
	        tp.report();
	    }
	    
	    long t1 = System.nanoTime();
	    System.out.println("Förfluten tid: " + (t1-t0)/1000000.0 + " ms");
	}
}