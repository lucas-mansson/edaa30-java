package sudoku;

public class TestSudoku {

	public static void main(String[] args) {
		
		int[][] board = new int[9][9];
		
		Sudoku sudoku = new Sudoku();
		sudoku.setBoard(board);
		
		SudokuUI sudokuUI = new SudokuUI(sudoku);

	}

}
