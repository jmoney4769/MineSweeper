import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 * @author Jared Moore
 * @version Nov 16, 2012
 */
public class MenuBar extends JMenuBar {
	
	BorderFrame frame;
	JMenu beginner = new JMenu("Beginner"), intermediate = new JMenu("Intermediate"), expert = new JMenu("Expert"),
			custom = new JMenu("Custom"), scores = new JMenu("Scores"), hint = new JMenu("Hint");

	/** Constructor for MenuBar
	 * 
	 */
	public MenuBar(BorderFrame fra) {
		
		frame = fra;
		
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
		}
	}

}
