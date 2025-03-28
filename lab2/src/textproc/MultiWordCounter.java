package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	
	private Map<String, Integer> map = new HashMap<>();;
	
	public MultiWordCounter(String[] words) {
		for(String word: words) {
			map.put(word, 0);
		}	
	}
	
	public void process(String w) {
		 map.computeIfPresent(w, (k, v) -> v + 1);
	}

	public void report() {
		for(String key: map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}	
	}
	
	
	
}
