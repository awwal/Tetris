package myTetrisPackage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMain implements GameConstants, KeyListener {

	private GamePlay playTetris = new GamePlay();
	private JFrame TetrisFrame = new JFrame("wole tetris");
	private  JButton startButton;
	public static JLabel scoreLabel;
	public GameMain() {
		
		startButton= new JButton("START");
		scoreLabel = new JLabel("score ");
		Container pane = TetrisFrame.getContentPane();
		
		pane.setLayout(new BorderLayout());		
		
		pane.add(playTetris, BorderLayout.CENTER);
		//pane.add(startButton, BorderLayout.SOUTH);
		pane.add(scoreLabel, BorderLayout.NORTH);
		
		startButton.requestFocus(false);
		playTetris.requestFocus(true);
		playTetris.requestFocusInWindow();
		playTetris.addKeyListener(this);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				playTetris.requestFocus();
			}
		});
		
		
		TetrisFrame.addKeyListener(this);
		TetrisFrame.pack();
		TetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		TetrisFrame.setVisible(true);
		


	}

	public static void main(String[] args) {

		new GameMain();
	}

	public void keyPressed(KeyEvent e) {
		//if (DEBUG)System.out.println("key pressed");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				playTetris.moveCurrentPiece(LEFT);
				break;
			case KeyEvent.VK_RIGHT :
				playTetris.moveCurrentPiece(RIGHT);
				break;
			case KeyEvent.VK_UP :
				playTetris.moveCurrentPiece(ROTATE);
				break;
			case KeyEvent.VK_DOWN :
				playTetris.moveCurrentPiece(DOWN);
				break;
			case KeyEvent.VK_SHIFT :
				playTetris.moveCurrentPiece(FALL);
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
