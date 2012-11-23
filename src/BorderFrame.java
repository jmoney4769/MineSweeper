import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/** JFrame to hold all the components while playing the game
 * @author Jared Moore
 * @version Nov 15, 2012
 */
public class BorderFrame extends JFrame {

	private int rows, columns, mines;
	private MineSweeperPanel panel;
	private TopPanel top;
	
	/** Constructor for BorderPanel 
	 */
	public BorderFrame(int rows, int columns, int mines) {
		
		setLayout(new BorderLayout());
		MenuBar menu = new MenuBar(this);
		setJMenuBar(menu);
		
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // changes the look a little, my system is themed so this looks pretty sweet
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				System.err.println("You have an issue");
				e.printStackTrace();
				System.exit(1);
			}
		
		rows = (rows < 8) ? 8 : rows; // make sure input is legal
		rows = (rows > 30) ? 30 : rows;
		this.rows = rows;
		
		columns = (columns < 8) ? 8 : columns;
		columns = (columns > 24) ? 24 : columns;
		this.columns = columns;
		
		mines = (mines > (rows - 1) * (columns - 1)) ? ((rows - 1) * (columns - 1)) : mines;
		this.mines = mines;
		
		panel = new MineSweeperPanel(rows, columns, mines);
		setMinimumSize(new Dimension(45 * rows, 45 * columns)); // should make squares big enough for the numbers to be seen
		setMaximumSize(new Dimension(45 * rows, 45 * columns));
		add(panel, BorderLayout.CENTER);
		setTitle("Minesweeper");
		repaint();
		panel.repaint();
		
		top = new TopPanel(panel, this, mines);
		add(top, BorderLayout.NORTH);
		panel.setTop(top);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	public void startOver() {

		setVisible(false);
		BorderFrame frame = new BorderFrame(rows, columns, mines); // make this one disappear and make a new one appear
	}

	/** Getter for panel
	 * @return the panel
	 */
	public MineSweeperPanel getPanel() {
		return panel;
	}

	
} 
