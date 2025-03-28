package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuUI extends JFrame{
	
	
	private Sudoku sudoku;
	private JTextField[][] grid;
	
	/**
	 * Constructor for the sudoku controller, creates and opens the window.
	 * 
	 * @param sudoku object
	 */
	public SudokuUI(Sudoku sudoku) {
		this.sudoku = sudoku;
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 1000, 1000));
	}

	private void createWindow(String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
				
		JPanel gridPanel = setupGrid();
	        
	    pane.add(gridPanel);
	        
		JPanel buttonPanel = new JPanel();
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		
		
		
		solve.addActionListener(e -> {
			
			try {
				
				for(int row = 0; row < 9; row++) {
					for(int col = 0; col < 9; col++) {
						
						int num = 0;
						
						if(!grid[row][col].getText().trim().equals("")) {
							num = Integer.parseInt(grid[row][col].getText().trim());
						}
						
						if(sudoku.legal(row, col, num) || num == 0) {
							sudoku.placeNbr(num, row, col);
						}else {
							throw new NumberFormatException();
						}
						
					}
				}
				
				if(sudoku.solve()) {
					updateUI();
					sudoku.clear();
				}else {
					JOptionPane.showMessageDialog(frame, "Finns ingen lösning");
				}
				
				
				
			}catch(NumberFormatException l) {
				sudoku.clear();
				JOptionPane.showMessageDialog(frame, "Ogiltig input");
			}
			
        });
		
		clear.addActionListener(e->{
			
			sudoku.clear();
			
			for(int row = 0; row < 9; row++) {
				for(int col = 0; col < 9; col++) {
					grid[row][col].setText("");
					
				}
			}
		});
		
		
		buttonPanel.add(solve);
		buttonPanel.add(clear);
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel setupGrid() {
		
		JPanel gridPanel = new JPanel(new GridLayout(9,9));
		
		grid = new JTextField[9][9];
		 
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new JTextField(1);
                gridPanel.add(grid[row][col]);
                if((row < 3 && col < 3) || (row > 5 && col < 3) || (row > 5 && col > 5) || (row < 3 && col > 5)|| ((row > 2 && row < 6) && (col > 2 && col<6))) {
        			grid[row][col].setBackground(Color.ORANGE);
        		}
            }
        }
        
        return gridPanel; 
	}
	
	
	
	private void updateUI() {
		for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setText(Integer.toString(sudoku.getNbrAt(row, col)));
            }
        }
	}
}
