package game;

import javax.swing.JOptionPane;

public class UserInterface {
	
	/** Visar en dialogruta med texten msg. */
	public static void printMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	/**
	* Visar en dialogruta med texten msg och och läser in ett positivt heltal. Om
	* användaren skriver något som inte kan tolkas som ett positivt heltal ska -1
	* returneras. Om användaren klickar på "Avbryt" ska -2 returneras.
	*/
	public static int askForInt(String msg) {
		
		String str = JOptionPane.showInputDialog(null, msg);
		
		if(str == null) {
			return -2;
		}
		
		try {
			return Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return -1;
		}

	}
	
	public static int difficulty() {
		String[] options = {"Lätt", "Svårt"};
		int choice = JOptionPane.showOptionDialog(null, "Välj svårighetsgrad", "Inställningar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(choice == JOptionPane.YES_OPTION) {
			return 1;
		}else if(choice == JOptionPane.NO_OPTION){
			return -1;
		}else {
			return 0;
		}
		
	}

}
