package sudoku;

/**
 * @author Lucas Månsson
 *
 */

public class Sudoku implements SudokuSolver {
	
	private int[][] board;
	

	@Override
	public void setBoard(int[][] board) {
		this.board = board;
		
	}

	@Override
	public int[][] getBoard() {
		return board;
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}
	

	private boolean solve(int row, int col) {
		
		if(row == 9) {
			return true;
		}
		
		if(col == 9) {
			return solve(row + 1, 0);
		}
		
		if(board[row][col] != 0) {
			return solve(row, col+1);
		}
		
		for(int num = 1; num <= 9; num++) {
			if(legal(row, col, num)) {
				
				board[row][col] = num;
				
				if(solve(row, col+1)) {
					return true;
				}
				
				board[row][col] = 0;
			}
		}
		
		return false;
	}

	@Override
	public boolean legal(int row, int col, int nbr) {
		if(nbr < 1 || nbr > 9) {
			return false;
		}
		
		if(row > 8 || row < 0 || col > 8 || col < 0) {
			return false;
		}
		
		//kollar ifall siffran finns på samma kolonn
		for(int i = 0; i < 9; i++) {
			if(board[row][i] == nbr) {
				return false;
			}
		}
		
		//kollar ifall siffran finns i samma rad
		for(int i = 0; i < 9; i++) {
			if(board[i][col] == nbr) {
				return false;
			}
		}
		
		//kollar ifall siffran finns i samma region
		
		int startRow = row - row % 3, 
	        startCol = col - col % 3;
	   
	    for (int i = 0; i < 3; i++) {
	    	for (int j = 0; j < 3; j++) {
	    		if (board[i + startRow][j + startCol] == nbr)
	                return false;
	    	}            
	    }	
	    
	    return true;
	}
	
	/**
	 * Returns the number at a specified row and column.
	 * 
	 * @param row
	 * @param col
	 * @return number at specified row and col
	 */
	public int getNbrAt(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Places specified number at given position.
	 * @param nbr
	 * @param row
	 * @param col
	 */
	public void placeNbr(int nbr, int row, int col) {
			board[row][col] = nbr;
	}
	
	
	/**
	 * Clears the board fully
	 */
	public void clear() {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				board[row][col] = 0;
			}
		}
	}

}
