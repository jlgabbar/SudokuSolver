import java.util.Scanner;
import java.io.File;

public class sudoku {
	static Scanner scan;

	public static void main(String[] args) throws Exception {
		int i = 0;
		while (i < 2) {
			if (i == 0) {
				scan = new Scanner(
						new File(
								"/Volumes/Mac HD/users/jgabbard/Desktop/workspace/Sudoku Solver/src/numbers1"));
			} else
				scan = new Scanner(
						new File(
								"/Volumes/Mac HD/users/jgabbard/Desktop/workspace/Sudoku Solver/src/numbers"));
			int sudo[][] = new int[9][9];
			String line = scan.nextLine();
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					sudo[y][x] = Character.getNumericValue(line.charAt(x * 2)); // populate
																				// the
																				// array
					if (x == 8 && scan.hasNextLine()) { // end of first row
						line = scan.nextLine(); // next row
					}
				}
			}
			System.out.println("Sudoku Puzzle Number " + (i+1));
			solve(sudo, 0, 0); // start at the top left
			i++;
		}
	}

	private static void solve(int[][] sudoku, int x, int y) {
		if (y > 8) {
			printSudoku(sudoku);
			System.out.println();
		} else {
			int nextX = x;
			int nextY = y;
			if (x == 8) {
				nextX = 0; // set back to 0
				nextY++; // start at next row
			} else {
				nextX++;
			}

			if (sudoku[y][x] != 0) {
				solve(sudoku, nextX, nextY);
			} else {
				for (int checkNum = 1; checkNum < 10; checkNum++) {
					if (checkBox(sudoku, x, y, checkNum)) {
						if (checkRow(sudoku, y, checkNum)) {
							if (checkCol(sudoku, x, checkNum)) {
								sudoku[y][x] = checkNum;
								solve(sudoku, nextX, nextY);
							}
						}
					}
				}
				sudoku[y][x] = 0;
			}

		}
	}

	private static boolean checkBox(int[][] sudoku, int reqX, int reqY,
			int check) {
		int row;
		int col;

		if (reqX < 3) {
			col = 0;
		} else if (reqX < 6) {
			col = 3;
		} else {
			col = 6;
		}
		if (reqY < 3) {
			row = 0;
		} else if (reqY < 6) {
			row = 3;
		} else {
			row = 6;
		}
		// the code above gets us in the right 3x3 box
		for (int y = row; y < row + 3; y++) {
			for (int x = col; x < col + 3; x++) {
				if (sudoku[y][x] == check) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkRow(int[][] sudoku, int rowY, int check) {
		for (int x = 0; x < 9; x++) {
			if (check == sudoku[rowY][x]) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkCol(int[][] sudoku, int colX, int check) {
		for (int y = 0; y < 9; y++) {
			if (check == sudoku[y][colX]) {
				return false;
			}
		}
		return true;
	}

	private static void printSudoku(int sudoku[][]) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				System.out.print(sudoku[y][x] + " ");
				if (x == 8) {
					System.out.println();
				}
			}
		}
	}
}
