import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainMenu{
	

	
	public static void main (String[] args) {
		JFrame noteFrame = new JFrame ("Musical Dodging");
		noteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		noteFrame.getContentPane().add(new NoteLevel(8,20,90,50));		//public NoteLevel (int noteSpeed, int noteWidth, int noteLength, int noteAmount) {
		noteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		noteFrame.setResizable(false);
		noteFrame.setUndecorated(true);
		noteFrame.pack();
		noteFrame.setVisible(true);
		
	}
	
}


