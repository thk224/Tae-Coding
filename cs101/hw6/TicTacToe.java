//Tae Kim (tk2686)
public class TicTacToe {
	private char[][] board = new char[3][3]; //no need to write constructor
	
	public void place(char mark, int n, int m) {
		//determine whether the char input is valid, then place it to the index n,m
		if ((mark == 'X' || mark == 'O') && n >= 0 && n < board.length && m >= 0 && m < board[n].length) {board[n][m] = mark;
		 	board[n][m] = mark;
		}
	}
	
	@Override
	public String toString() {
		//construct a string builder
		StringBuilder sb = new StringBuilder("+---+\n");
		
		//append elements to the string builder
		for (int i = 0; i < 3; i++) {
			sb.append('|');
			
			for (int j = 0; j < 3; j++) {
				//determine whether the index is not empty
				if (board[i][j] == 'O' || board[i][j] == 'X') {
					sb.append(board[i][j]);
				} else {
					sb.append(' ');
				}
			}
			
			sb.append("|\n");
		}
		
		sb.append("+---+");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		//test case
		TicTacToe board = new TicTacToe();
		board.place('X', 1, 1);
		board.place('O', 2, 2);
		board.place('X', 2, 1);
		board.place('O', 1, 0);
		board.place('X', 0, 1);
		board.place('A', -1, 3); // does not place a mark
		System.out.print(board);
	}
	
}
