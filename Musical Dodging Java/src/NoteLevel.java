import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class NoteLevel extends JPanel{
	private int noteLength;
	private int noteSpeed;
	private int noteWidth;
	private int noteAmount;
	private int score;
	private int notesPlayed;
	private int beginningNoteSpeed;
	private ArrayList<Note> noteList;
	private ArrayList<Color> colorList; 
	private Point lastPoint;
	private boolean gameOver;
	private Random noteLocation = new Random();
	private Timer animator = new Timer();
	private Timer updater = new Timer();
	private Timer scorer = new Timer();
	public NoteLevel (int noteSpeed, int noteWidth, int noteLength, int noteAmount) {
		
		
		
		playSound();
		this.noteSpeed = noteSpeed;
		this.noteWidth = noteWidth;
		this.noteLength = noteLength;
		this.noteAmount = noteAmount;
		beginningNoteSpeed = noteSpeed;
		lastPoint = new Point(0,0);
		createNotes();
		setBackground (Color.black);
		setPreferredSize (new Dimension(1920,1080));
		animator.schedule(new AnimatorTask(), 1750, 7);
		updater.schedule(new UpdaterTask(), 1750, 7);
		scorer.schedule(new ScorerTask(), 0, 1000);
		
	}
	
		
	
					
	
	public void paintComponent (Graphics page)  {
		
		super.paintComponent(page);		
		
		page.setColor(Color.white);
		page.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		page.drawString("Score: " + score + ". Notes Played: " + notesPlayed, 5, 15);
		
		for(Note drawNote : noteList) {
			page.setColor(drawNote.getColor());
			page.fillRect(drawNote.x, drawNote.y, drawNote.width, drawNote.height);
		}
		
	}
	
	
	private void createNotes() {
		noteList = new ArrayList<Note>();
		Random noteLocation = new Random();
		Random colorGenerator = new Random();
		int randomLocationX;
		int randomLocationY;
		int r = 0;
		int g = 0;
		int b = 0;
		
		for (int x = 0; x < noteAmount; x++) {
			randomLocationX = noteLocation.nextInt(1920);
			randomLocationY = noteLocation.nextInt(1400) - 1700;
			
			r = colorGenerator.nextInt(255);
			g = colorGenerator.nextInt(255);
			b = colorGenerator.nextInt(255);
			noteList.add(new Note(randomLocationX, randomLocationY, noteWidth, noteLength, new Color(r,g,b)));
		}
		
	}
	
	public void updateGame() {
		
			lastPoint = MouseInfo.getPointerInfo().getLocation();
			
		try {
			for(Note drawNote : noteList) {
		
				if (drawNote.contains(lastPoint)) {
					if (gameOver) {
					//	break;
					}
				//	gameOver=true;
					//animator.cancel();
					//updater.cancel();
				//	scorer.cancel();
					
				//	noteSpeed = 0;
				//	JOptionPane.showMessageDialog(null, "Your score is " + score + ". There were a total of " + notesPlayed + " notes played.");
				} 

			
				drawNote.y+=noteSpeed;
				
				if (drawNote.y > 1080) {
					drawNote.y = noteLocation.nextInt(700) - 800;
					notesPlayed+=1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			repaint();
		}
			
			
	}


	public class AnimatorTask extends TimerTask {
		public void run() {
			if (score % 10 == 0) {
				noteAmount+=10*score;
				AddNote(noteList.size());
				score+=1;
			}
			repaint();	
		}	
	}

	public class UpdaterTask extends TimerTask {
		public void run() {
			updateGame();
		}	
	}

	public class ScorerTask extends TimerTask {
		public void run() {
			score+=1;
			
			if (score % 10 == 0) {
				noteSpeed+=5;
			}
				
		}	
	}
	

	
	
	
	public void playSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/MusicalDodging/DeathWaltz.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	private void AddNote(int previousIndex) {
		Random noteLocation = new Random();
		Random colorGenerator = new Random();
		int randomLocationX;
		int randomLocationY;
		int r = 0;
		int g = 0;
		int b = 0;
		
		for (int x = previousIndex; x < noteAmount; x++) {
			randomLocationX = noteLocation.nextInt(1920);
			randomLocationY = noteLocation.nextInt(1080) - 1080;
			
			r = colorGenerator.nextInt(255);
			g = colorGenerator.nextInt(255);
			b = colorGenerator.nextInt(255);
			noteList.add(new Note(randomLocationX, randomLocationY, noteWidth, noteLength, new Color(r,g,b)));
		}
		
	}
	
	
	
	
	


}

