package myTetrisPackage;

import java.awt.Point;
import java.util.Random;

public class TetrisPiece implements GameConstants {
	
	private Point focusPoint = new Point(GAMEWIDTH/2,GAMEHEIGHT-(4*BRICKHEIGHT));
	private Point[] blockRelPoints = new Point[4];
	private int nRotation;
	private int pieceType;	
	private static Random rgen = new Random();
	

	public TetrisPiece(int type) {
		pieceType = type;
		initializeBlocks();
	}
	
	public static TetrisPiece getNextPiece() {
		return new TetrisPiece(rgen.nextInt(7));
		
	}
	private void initializeBlocks() {
		switch (pieceType) {
			case I_PIECE :
				blockRelPoints[0] = new Point(0, 0);
				blockRelPoints[1] = new Point(0, 1);
				blockRelPoints[2] = new Point(0, 2);
				blockRelPoints[3] = new Point(0, 3);
				nRotation = 2;
				break;

			case L_PIECE :
				blockRelPoints[0] = new Point(0, 0);
				blockRelPoints[1] = new Point(0, 1);
				blockRelPoints[2] = new Point(0,2);
				blockRelPoints[3] = new Point(1, 0);
				nRotation = 4;
				break;

			case J_PIECE :
				blockRelPoints[0] = new Point(0, 0);
				blockRelPoints[1] = new Point(1, 0);
				blockRelPoints[2] = new Point(0, 1);
				blockRelPoints[3] = new Point(0, 2);
				nRotation = 4;
				break;

			case Z_PIECE :
				blockRelPoints[0] = new Point(1, 0);
				blockRelPoints[1] = new Point(2, 0);
				blockRelPoints[2] = new Point(0, 1);
				blockRelPoints[3] = new Point(1, 1);
				nRotation = 2;
				break;

			case S_PIECE :
				blockRelPoints[0] = new Point(0, 0);
				blockRelPoints[1] = new Point(1, 0);
				blockRelPoints[2] = new Point(1, 1);
				blockRelPoints[3] = new Point(2, 1);
				nRotation = 2;
				break;

			case T_PIECE :
				blockRelPoints[0] = new Point(0, 1);
				blockRelPoints[1] = new Point(1, 1);
				blockRelPoints[2] = new Point(2, 1);
				blockRelPoints[3] = new Point(1, 0);
				nRotation = 1;
				break;

			case  O_PIECE :
				blockRelPoints[0] = new Point(0, 0);
				blockRelPoints[1] = new Point(1, 0);
				blockRelPoints[2] = new Point(1, 1);
				blockRelPoints[3] = new Point(0, 1);
				nRotation = 4;
				break;
		}
	}


	
	

	

	public boolean move(int direction) {
		boolean moveSuccessful= true;
		
		GamePlay.tetrisBoard.removePiece(this);
		
		switch (direction) {
			case LEFT :	focusPoint.translate(-BRICKWIDTH, 0);break; // Move left
			case RIGHT :focusPoint.translate(BRICKWIDTH, 0);break; // Move right
			case DOWN :	focusPoint.translate(0, -BRICKHEIGHT);break; // Drop				
			case UP :focusPoint.translate(0, BRICKHEIGHT);break; // UNDrop
			case ROTATE :rotateClockwise();	break;

		}
		if(GamePlay.tetrisBoard.canAddPiece(this)) {
			GamePlay.tetrisBoard.addPiece(this);}
		else // undo
		{
			moveSuccessful=false;
			//System.out.println("cant add");
			switch (direction) {
			case RIGHT :	focusPoint.translate(-BRICKWIDTH, 0);break; // Move left
			case LEFT :focusPoint.translate(BRICKWIDTH, 0);break; // Move right
			case UP :	focusPoint.translate(0, -BRICKHEIGHT);break; // Drop				
			case DOWN :focusPoint.translate(0, BRICKHEIGHT);break; // UNDrop
			case ROTATE :rotateAntiClockwise();	break;

			}
		}
		GamePlay.tetrisBoard.addPiece(this);
		return moveSuccessful;
	}

	private void rotateClockwise() {
		rotateClockwiseNow();

	}
	  private void rotateAntiClockwise()
	    {
	        rotateClockwise();
	        rotateClockwise();
	        rotateClockwise();
	    }
	private void rotateClockwiseNow() {
		for (int i = 0; i < 4; i++) {
			final int temp = blockRelPoints[i].x;

			blockRelPoints[i].x = -blockRelPoints[i].y;
			blockRelPoints[i].y = temp;
		}
	}
	public Point getFocusPoint() {
		return focusPoint;
	}

	public void setFocusPoint(Point focusPoint) {
		this.focusPoint = focusPoint;
	}

	public Point[] getBlockRelPoints() {
		return blockRelPoints;
	}

	public void setBlockRelPoints(Point[] blockRelPoints) {
		this.blockRelPoints = blockRelPoints;
	}

	public int getnRotation() {
		return nRotation;
	}

	public void setnRotation(int nRotation) {
		this.nRotation = nRotation;
	}

	public int getPieceType() {
		return pieceType;
	}

	public void setPieceType(int pieceType) {
		this.pieceType = pieceType;
	}
}
