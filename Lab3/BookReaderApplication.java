package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		
		
	    //ArrayList<TextProcessor> tpList = new ArrayList<TextProcessor>();

	    Scanner sBanned = new Scanner(new File("undantagsord.txt"));
	    Set<String> stopWords = new HashSet<String>();
	    sBanned.findWithinHorizon("\uFEFF", 1);
	    sBanned.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
	    
	    while(sBanned.hasNext()) {
	    	String bannedWord = sBanned.next().toLowerCase();
	    	stopWords.add(bannedWord);
	    }
	    
	    sBanned.close();
	    
	    
	    GeneralWordCounter counter = new GeneralWordCounter(stopWords);
	    
	    
	    Scanner s = new Scanner(new File("nilsholg.txt"));
	    s.findWithinHorizon("\uFEFF", 1);
	    s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
	    
	    //tpList.add(counter);
	    
	    while (s.hasNext()) {
	       // String word = s.next().toLowerCase();

	       // for (TextProcessor tp : tpList) {
	            counter.process(s.next().toLowerCase());
	      //  }
 
	    }

	    s.close();

	    BookReaderController controller = new BookReaderController(counter);

		
		//controller.createWindow(counter, "lista", 100, 100);

	}

}
