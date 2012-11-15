import javax.swing.JFrame;

/**
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class MineSweeperMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Select Difficulty");
		frame.add(new StartPanel(frame));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
