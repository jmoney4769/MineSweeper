import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class StartPanel extends JPanel {

	private JFrame frame;
	private JRadioButton beginner, intermediate, expert, custom; 
	private JButton button = new JButton("Start");
	
	/** Constructor for StartPanel
	 */
	public StartPanel(JFrame frame) {
		
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // changes the look a little, my system is themed so this looks pretty sweet
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.err.println("You have an issue");
			e.printStackTrace();
			System.exit(1);
		}*/
		
		this.frame = frame;
		frame.setPreferredSize(new Dimension(300, 200));
		frame.setMinimumSize(new Dimension(300, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// sizes and rules for buildTiles are based on the rules found at 
		// http://en.wikipedia.org/wiki/Minesweeper_(Windows)
		beginner = new JRadioButton("Beginner:  8 x 8 with 10 mines");
		beginner.setSelected(true);
		intermediate = new JRadioButton("Intermediate:  16 x 16 with 40 mines");
		expert = new JRadioButton("Expert:  30 x 16 with 99 mines");
		custom = new JRadioButton("Custom");
		
		RadioListener listener = new RadioListener();
		button.addMouseListener(listener);
		beginner.addMouseListener(listener);
		intermediate.addMouseListener(listener);
		expert.addMouseListener(listener);
		custom.addMouseListener(listener);
		add(beginner);
		add(intermediate);
		add(expert);
		add(custom);
		add(button);
		
	}
	
	private class RadioListener extends MouseAdapter {
		
		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if ((e.getSource().equals(button)) && ((beginner.isSelected()) || 
					(intermediate.isSelected()) || (expert.isSelected()) || (custom.isSelected())))
				if (beginner.isSelected())
					buildTiles(8, 8, 10);
				else if (intermediate.isSelected())
					buildTiles(16, 16, 40);
				else if (expert.isSelected())
					buildTiles(30, 16, 99);
				else if (custom.isSelected()) {
					
					String text = JOptionPane.showInputDialog("Enter the number of rows, columns, and mines, separated by spaces");
					if (text == null)
						return;
					Scanner input = new Scanner(text);
					buildTiles(Integer.parseInt(input.next()), Integer.parseInt(input.next()), Integer.parseInt(input.next()));
					input.close();
				}
			
			if ((e.getSource() instanceof JRadioButton) && ((beginner.isSelected()) || 
					(intermediate.isSelected()) || (expert.isSelected()) || (custom.isSelected())))
				if (beginner.isSelected())
					beginner.setSelected(false);
				if (intermediate.isSelected())
					intermediate.setSelected(false);
				if (expert.isSelected())
					expert.setSelected(false);
				if (custom.isSelected())
					custom.setSelected(false);
			
			if (beginner.equals(e.getSource()))
				beginner.setSelected(true);
			else if (intermediate.equals(e.getSource()))
				intermediate.setSelected(true);
			else if (expert.equals(e.getSource()))
				expert.setSelected(true);
			else if (custom.equals(e.getSource()))
				custom.setSelected(true);
			
		}
	}

	/**
	 * @param rows
	 * @param columns
	 * @param mines
	 */
	public void buildTiles(int rows, int columns, int mines) {
		
		BorderFrame border = new BorderFrame(rows, columns, mines);
		frame.setVisible(false);
		
	}

}
