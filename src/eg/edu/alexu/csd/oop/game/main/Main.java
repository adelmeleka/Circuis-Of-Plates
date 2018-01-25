package eg.edu.alexu.csd.oop.game.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.world.Circuis;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		music();
		//InputStream test=new FileInputStream("C:\\Users\\pc\\Desktop\\finalproject 3\\res\\circussound.wav");
		//Sound.getInstance();
		//Sound.sound.startCircusSound();
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");
int level = 0;

		JMenuBar menuBar = new JMenuBar();
		;
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		menu.add(newMenuItem);
		menu.add(exitMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
		Object[] possibilities = { "easy", "medium", "hard" };
		 JFrame frame = new JFrame("InputDialog Example #1");
		String s = (String) JOptionPane.showInputDialog(frame, "select one choice \n" ,	"choose difficulty ", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
		if(s.equals("easy")){
			level=1;
		}
		else if(s.equals("medium")){
			level=2;
		}
		else if(s.equals("hard")){
			level=3;
		}
		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code",
				Circuis.getinstance(level), menuBar, Color.orange);


		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				gameController.resume();
			}
		});
	}
	

	public static void music() 
	    {       
	        AudioPlayer MGP = AudioPlayer.player;
	        AudioStream BGM;
	        AudioData MD;

	        ContinuousAudioDataStream loop = null;

	        try
	        {
	            InputStream test = new FileInputStream("C:\\Users\\adelm\\Desktop\\assignment3_3940_3960_4441_3959__3861_3847\\finalproject 3\\res\\circussound.wav");
	            BGM = new AudioStream(test);
	            AudioPlayer.player.start(BGM);
	            //MD = BGM.getData();
	            //loop = new ContinuousAudioDataStream(MD);

	        }
	        catch(FileNotFoundException e){
	            System.out.print(e.toString());
	        }
	        catch(IOException error)
	        {
	            System.out.print(error.toString());
	        }
	        MGP.start(loop);
	    }


	}


