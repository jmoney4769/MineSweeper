import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/** The top panel that holds the amount of mines, flags, and the smiley face
 * @author Jared Moore
 * @version Nov 15, 2012
 */
public class TopPanel extends JPanel {

	private int time = 0, numOfFlags;
	private JLabel timeLabel, flags;
	private JButton smiley = new JButton();
	private BorderFrame frame;
	private MineSweeperPanel panel;
	private Timer timer;
	private static ImageIcon normal = new ImageIcon("res/normal.gif"), finished = new ImageIcon("res/finished.gif"),
			lost = new ImageIcon("res/lost.gif"), uncertain = new ImageIcon("res/uncertain.gif"); // all three images from http://www.personal.kent.edu/~bherzog/tao.html

	/** Constructor for TopPanel 
	 */
	public TopPanel(MineSweeperPanel minePanel, BorderFrame borderFrame, int mines) {
		
		panel = minePanel;
		numOfFlags = mines;
		this.frame = borderFrame;
		
		timeLabel = new JLabel("Time:");
		flags = new JLabel("Flags Left:" + numOfFlags);
		
		smiley.setIcon(TopPanel.getNormal());
		smiley.addActionListener(new ActionListener() { // starts the game over if the player clicks the face
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.startOver();
				time = 0;
				timeLabel.setText("Time:");
				flags.setText("Flags Left:");
				smiley.setIcon(TopPanel.getNormal());
			}
		});
		
		timer = new Timer(1000, new ActionListener() { // update the time and check for victory every second
			
			@Override
			public void actionPerformed(ActionEvent e) {
				time++;
				timeLabel.setText("Time:\n" + time);
				if (panel.hasWon())
					timer.stop();
			}
		});
		timer.start();
		add(timeLabel);
		add(smiley);
		add(flags);
		
	}

	/** Increase or decrease the amount of flags
	 * @param increment Whether to increase the flags
	 */
	public void changeFlags(boolean increment) {
		
		if (increment)
			numOfFlags++;
		else {
			numOfFlags--;				
		}
		flags.setText("Flags Left:\n" + numOfFlags);
		repaint();
	}
	
	/** Getter for normal
	 * @return the normal
	 */
	public static ImageIcon getNormal() {
		return normal;
	}

	/** Getter for timer
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/** Getter for finished
	 * @return the finished
	 */
	public static ImageIcon getFinished() {
		return finished;
	}

	/** Getter for flags
	 * @return the flags
	 */
	public JLabel getFlags() {
		return flags;
	}

	/** Getter for lost
	 * @return the lost
	 */
	public static ImageIcon getLost() {
		return lost;
	}

	/** Getter for smiley
	 * @return the smiley
	 */
	public JButton getSmiley() {
		return smiley;
	}

	/** Getter for time
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/** Getter for numOfFlags
	 * @return the numOfFlags
	 */
	public int getNumOfFlags() {
		return numOfFlags;
	}
	
	/** Getter for uncertain
	 * @return the uncertain
	 */
	public static ImageIcon getUncertain() {
		return uncertain;
	}
}
