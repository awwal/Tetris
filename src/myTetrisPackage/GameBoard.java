package myTetrisPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class GameBoard implements GameConstants {
	private int nX;
	private int nY;
	private int[][] boardGrid;
	/**
	 * GameBoard is like a cartesian graph, the no X is the column needed in an
	 * array the no Y needed is the no of row of an array
	 * 
	 * @param rows
	 * @param cols
	 */

	public GameBoard(int x, int y) {
		nX = x;
		nY = y;
		boardGrid = new int[nX][nY];
		int i, j;
		for (i = 0; i < nX; i++) {
			for (j = 0; j < nY; j++) {
				boardGrid[i][j] = EMPTY_BLOCK;
			}
		}

	}
	public boolean canAddPiece(TetrisPiece piece) {

		final Point focusPoint = piece.getFocusPoint();
		final Point[] blocks = piece.getBlockRelPoints();

		for (int i = 0; i < 4; i++) {
			int x = (focusPoint.x / BRICKWIDTH + blocks[i].x);
			int y = (focusPoint.y / BRICKHEIGHT) + blocks[i].y;
			// System.out.println("canadd? x "+x+" y "+y);
			if (x < 0 || x >= nX || y < 0 || y >= nY)
				return false;
			if (boardGrid[x][y] != EMPTY_BLOCK) {

				return false;
			}
		}

		// System.out.println(result);
		return true;

	}

	public int checkFullRows() {
		int j;
		for (j = 0; j < nY; j++) {
			if (isRowFull(j) == true) {
				// System.out.println("full row "+j);
				return j;
			}
		}
		return -1;

	}
	public int nFreeLines() {
		int freeLines = nY;
		for (int j = 0; j < nY; j++) {
			if (isRowOccupied(j))
				freeLines--;
		}
		return freeLines;
	}

	private boolean isRowOccupied(int rowIdx) {
		boolean result = false;
		for (int i = 0; i < nX; i++) {
			if (boardGrid[i][rowIdx] != EMPTY_BLOCK) {
				result = true;

			}
		}
		return result;
	}

	private boolean isRowFull(int rowIdx) {
		boolean result = true;
		for (int i = 0; i < nX; i++) {
			if (boardGrid[i][rowIdx] == EMPTY_BLOCK) {
				result = false;
				// printRow(rowIdx);
			}
		}
		// System.out.println("isRowFull return "+result);
		return result;

	}

	public void removeRow(int rowIdx) {

		/** move everything above the row down */
		for (; rowIdx < nY - 1; rowIdx++) {
			for (int i = 0; i < nX; i++) {
				boardGrid[i][rowIdx] = boardGrid[i][rowIdx + 1];
			}
		}
		/* make uppermost row empty */
		for (int i = 0; i < nX; i++)
			boardGrid[i][nY - 1] = EMPTY_BLOCK;

	}

	public void addPiece(TetrisPiece piece) {
		if (piece != null) {
			final Point focusPoint = piece.getFocusPoint();
			final Point[] blocks = piece.getBlockRelPoints();
			for (int i = 0; i < 4; i++) {
				int x = (focusPoint.x / BRICKWIDTH + blocks[i].x);
				int y = (focusPoint.y / BRICKHEIGHT) + blocks[i].y;
				setPoints(x, y, piece.getPieceType());
				// System.out.println("piece focus y "+focusPoint.y);
				// System.out.println("piece added at "+x+","+y);
			}
		}
	}

	public void removePiece(TetrisPiece piece) {
		if (piece != null) {
			final Point focusPoint = piece.getFocusPoint();
			final Point[] blocks = piece.getBlockRelPoints();

			for (int i = 0; i < 4; i++) {
				int x = (focusPoint.x / BRICKWIDTH + blocks[i].x);
				int y = (focusPoint.y / BRICKHEIGHT) + blocks[i].y;
				setPoints(x, y, EMPTY_BLOCK);
			}
		}

	}

	public void setPoints(int rowIdx, int colIdx, int pieceType) {
		// System.out.println("setpts i "+rowIdx+" j "+colIdx);
		boardGrid[rowIdx][colIdx] = pieceType;
	}

	public int getnRows() {
		return nX;
	}

	public int getnCols() {
		return nY;
	}

	private Color brickColor = new Color(196, 26, 26);
	public void draw(Graphics2D g) {
		int i, j;

		g.setColor(brickColor);
		for (i = 0; i < nX; i++) {
			for (j = 0; j < nY; j++) {
				if (boardGrid[i][j] != EMPTY_BLOCK) {
					g.fillRect(i * BRICKWIDTH, j * BRICKHEIGHT, BRICKWIDTH - 2,
							BRICKHEIGHT - 2);
					// g.setColor(Color.BLACK);
					g.drawRect(i * BRICKWIDTH, j * BRICKHEIGHT, BRICKWIDTH,
							BRICKHEIGHT);
				}

			}
		}

	}

	public int getPieceAt(int i, int j) {
		return boardGrid[i][j];
	}

}
