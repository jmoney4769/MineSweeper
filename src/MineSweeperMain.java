import javax.swing.JFrame;

/** File containing a main method to run the program
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class MineSweeperMain {

	/** Main method.  Initiates the program
	 * @param args Unused
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Select Difficulty");
		frame.add(new StartPanel(frame));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
