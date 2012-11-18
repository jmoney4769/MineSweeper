import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Jared Moore
 * @version Nov 16, 2012
 */
public class MenuBar extends JMenuBar {
	
	BorderFrame frame;
	JMenuItem beginner = new JMenuItem("Beginner"), intermediate = new JMenuItem("Intermediate"), expert = new JMenuItem("Expert"),
			custom = new JMenuItem("Custom"), scores = new JMenuItem("Scores"), hint = new JMenuItem("Hint");

	/** Constructor for MenuBar
	 * 
	 */
	public MenuBar(BorderFrame frame) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // changes the look a little, my system is themed so this looks pretty sweet
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.err.println("You have an issue");
			e.printStackTrace();
			System.exit(1);
		}
		this.frame = frame;
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenu newGame = new JMenu("New");
		newGame.setMnemonic('N');		
		
		Action l = new Action();
		
		beginner.addActionListener(l);
		beginner.setMnemonic('B');
		intermediate.addActionListener(l);
		intermediate.setMnemonic('I');
		expert.addActionListener(l);
		expert.setMnemonic('E');
		custom.addActionListener(l);
		custom.setMnemonic('C');
		
		newGame.add(beginner);
		newGame.add(intermediate);
		newGame.add(expert);
		newGame.add(custom);
		
		fileMenu.add(newGame);
		
		scores.setMnemonic('S');
		scores.addActionListener(l);
		
		fileMenu.add(scores);
		add(fileMenu);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		hint.addActionListener(l);
		hint.setMnemonic('I');
		helpMenu.add(hint);
		
		add(helpMenu);
		setVisible(true);
	}
	
	private class Action implements ActionListener {
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(beginner)) {
				frame.setVisible(false);
				BorderFrame newGame = new BorderFrame(8, 8, 10);
			}
			else if (e.getSource().equals(intermediate)) {
				frame.setVisible(false);
				BorderFrame newGame = new BorderFrame(16, 16, 40);				
			}
			else if (e.getSource().equals(expert)) {
				frame.setVisible(false);
				BorderFrame newGame = new BorderFrame(30, 16, 99);
			}
			else if (e.getSource().equals(custom)) {
				frame.setVisible(false);
				String text = JOptionPane.showInputDialog("Enter the number of rows, columns, and mines, separated by spaces");
				if (text == null)
					return;
				Scanner input = new Scanner(text);
				BorderFrame newGame = new BorderFrame(Integer.parseInt(input.next()), Integer.parseInt(input.next()), Integer.parseInt(input.next()));
				input.close();
			}
			else if (e.getSource().equals(hint))
				frame.getPanel().showHint();
			else if (e.getSource().equals(scores))
				frame.getPanel().displayScores();
		}
	}

}
