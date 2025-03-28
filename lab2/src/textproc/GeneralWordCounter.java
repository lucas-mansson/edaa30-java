package textproc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
	
	Set<String> banned;
	Map<String, Integer> map = new HashMap<String, Integer>();
	
	public GeneralWordCounter(Set<String> banned) {
		this.banned = banned;
	}

	public void process(String w) {
		if(!banned.contains(w)) {
			map.putIfAbsent(w, 0);
			map.computeIfPresent(w, (k, v) -> v + 1);	
		}		
	}

	
	public void report() {
	/*	for(String key: map.keySet()) {
			if(map.get(key) > 199 ) {
				System.out.println(key + ": " + map.get(key));
			}
		}	*/
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		
		wordList.sort((a, b) ->{
			if(a.getValue() == b.getValue()) {
				return a.getKey().compareTo(b.getKey());
			}
			return b.getValue().compareTo(a.getValue());
		});
		
		for(int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i));
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList(){
		
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		return wordList;
		
	}
	
}
