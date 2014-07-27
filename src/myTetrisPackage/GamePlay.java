package myTetrisPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePlay extends JPanel implements GameConstants {

	private int ANIMATION_DELAY = 450;// 1200;
	private Random rgen = new Random();
	private TetrisPiece currPiece;
	private Timer animationTimer;
	private int randPiece;
	public static GameBoard tetrisBoard;
	private int score;
	public boolean loop = true;
	private int level;
	private int init_level;
	private String levelStr;
	private static String scoreStr;

	public GamePlay() {

		randPiece = rgen.nextInt(6);
		currPiece = new TetrisPiece(randPiece);
		// currPiece = new TetrisPiece(J_PIECE);
		tetrisBoard = new GameBoard(NBRICKSperROW, NROWS);
		score = 0;
		init_level = 0;
		level = 0;

		startAnimation();
	}// end

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);

		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		this.setBackground(Color.yellow);
		Graphics2D g = (Graphics2D) gr;
		g.setColor(Color.white);
		g.drawRect(0, 0, GAMEWIDTH, GAMEHEIGHT);
		g.translate(0, APPLICATION_HEIGHT); // Move the origin to the lower left
		g.scale(1.0, -1.0); // Flip the sign of the coordin

		if (tetrisBoard.nFreeLines() > 1) {
			tetrisBoard.draw(g);

			if (tetrisBoard.checkFullRows() != -1) {
				tetrisBoard.removeRow(tetrisBoard.checkFullRows());
				score++;
				level = score / 10;
				if (level != init_level) {
					ANIMATION_DELAY -= 50;
					animationTimer.setDelay(ANIMATION_DELAY);
					init_level = level;
					System.out.println("level changer" + ANIMATION_DELAY);
				}

			}
			scoreStr = Integer.toString((score * 100));
			levelStr = Integer.toString(level);
			GameMain.scoreLabel.setText("SCORE " + scoreStr + "    LEVEL "
					+ levelStr);

		} 
		else {
			g.setColor(Color.black);
			g.drawString("GAME END", GAMEWIDTH / 2, GAMEHEIGHT / 2);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(GAMEWIDTH + 20, GAMEHEIGHT + 20);
	}

	public void startAnimation() {
		if (animationTimer == null) {
			// create timer

			animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());
			animationTimer.start(); // start Timer
		} // end if
		else // animationTimer already exists, restart animation
		{
			if (!animationTimer.isRunning()) {
				animationTimer.restart();
			}
		} // end else
	} // end method startAnimation

	private class TimerHandler implements ActionListener {
		// respond to Timer's event
		public void actionPerformed(ActionEvent actionEvent) {
			if (!currPiece.move(DOWN)) {
				// animationTimer.stop();
				//currPiece = new TetrisPiece(I_PIECE);
				 currPiece= TetrisPiece.getNextPiece();
			}
			repaint(); // 
		}// end method actionPerformed
	}

	public void moveCurrentPiece(int direction) {
		currPiece.move(direction);
		repaint();
	}

	public TetrisPiece getCurrPiece() {
		return currPiece;
	}
	public void setCurrPiece(TetrisPiece currPiece) {
		this.currPiece = currPiece;
	}

}
