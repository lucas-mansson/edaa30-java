package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Position;
import javax.swing.*;

public class BookReaderController {
	
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));

	}
	
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		
		JFrame frame = new JFrame(title); //skapar en JFrame som är grunden för fösntret som skapas
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //tillser att fönstret stängs 
		
		Container pane = frame.getContentPane();
		
		List<Map.Entry<String, Integer>> wordList = counter.getWordList();   //hämtar listan från counter som ska vara argument för SLM
		
		wordList.removeIf(e -> (Character.isDigit(e.getKey().charAt(0)))); //går genom wordlist och tar bort ordet ifall det börjar på en siffra
		
		SortedListModel slm = new SortedListModel<>(wordList); // skapar sortedListModel
		JList<SortedListModel> jList = new JList<SortedListModel>(slm); //JList är för listan i vårt fönster
		
		
		JScrollPane scroll = new JScrollPane(jList); 
		pane.add(scroll);//skapar scroll - funktion och lägger till i fönstret
		
		//Lägg till knappar nere
		JPanel buttonPanel = new JPanel();
		JRadioButton alpha = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");
		
		buttonPanel.add(alpha);
		buttonPanel.add(freq);
		
		pane.add(buttonPanel, BorderLayout.SOUTH); // tillser att panelen som skapas placeras vid botten
		
		//lägg till sökfält och knapp
		JPanel nPanel = new JPanel();
		JButton searchButton = new JButton("Search");
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(600, 25));
		
		nPanel.add(searchButton);
		nPanel.add(searchField);
		
		pane.add(nPanel, BorderLayout.NORTH);
		
		//searchButton.addActionListener(e ->{
		//	jList.ensureIndexIsVisible(jList.getNextMatch(searchField.getText(), 0, Position.Bias.Forward));
		//});
		
		searchButton.addActionListener(e ->{
			jList.removeSelectionInterval(0, slm.getSize());
			
			for (int i = 0; i < slm.getSize(); i ++) {
                if(searchField.getText().toLowerCase().trim().equals(((Map.Entry<String, Integer>) (slm.getElementAt(i))).getKey())) {
                    jList.addSelectionInterval(i, i); //markerar raden
                    jList.ensureIndexIsVisible(i); //visar raden
                }
            }	
		});
		
		
		searchField.addActionListener(e ->{
			jList.removeSelectionInterval(0, slm.getSize());
			
			for (int i = 0; i < slm.getSize(); i ++) {
                if(searchField.getText().toLowerCase().trim().equals(((Map.Entry<String, Integer>) (slm.getElementAt(i))).getKey())) {
                    jList.addSelectionInterval(i, i); //markerar raden
                    jList.ensureIndexIsVisible(i); //visar raden
                }
            }
		});
		
		alpha.addActionListener(e -> slm.sort((a, b) ->
        ((Map.Entry<String, Integer>) a).getKey().compareTo(((Map.Entry<String, Integer>) b).getKey())));
		
		freq.addActionListener(e -> slm.sort((a, b) ->
        ((Map.Entry<String, Integer>) b).getValue() - ((Map.Entry<String, Integer>) a).getValue()));
		
		
		
		frame.pack();
		frame.setVisible(true);
		
	}


}
