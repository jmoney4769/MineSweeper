import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class Tile extends JToggleButton implements MineTile {

	private static Tile[][] array;
	private final int ROW, COLUMN;
	private boolean hasMine, mineClicked = false, isPressed = false;
	private static ImageIcon mineImage = new ImageIcon("res/mine.jpg"), // image from http://nifty.stanford.edu/2004/LehmanMinesweeper/instructor.htm
			flagImage = new ImageIcon("res/flag.png"); // image from http://dougx.net/sweeper/sweeper.php;
	private static MineSweeperPanel panel;
	
	/** Constructor for Tile
	 */
	public Tile(int row, int column, MineSweeperPanel pan) {
		ROW = row;
		COLUMN = column;
		panel = pan;
		addMouseListener(new MouseAdapter() {
			
			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (getIcon() != flagImage) {
						getModel().setArmed(false);
						getModel().setPressed(false);
						setIcon(flagImage);
					}
					else {
						setIcon(null);
						getModel().setArmed(true);
					}
				}
			}
		});
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getIcon() != flagImage) {
					getModel().setArmed(false);
					getModel().setPressed(true);
					if (hasMine) 
						panel.mineClicked(ROW, COLUMN);					
					else
						openTile();
				}
			}
		});
	}
	
	/**
	 * 
	 */
	public void openTile() {
		array = panel.getTiles();
		getModel().setPressed(true);
		getModel().setArmed(false);
		int mines = getAdjacent();
		
		if (mines > 0) {
			setText(Integer.toString(mines));
			setFont(new Font("Arial", Font.BOLD, 10));
			switch (mines) { // set to appropriate color
			case 8:
				setForeground(Color.GRAY);
				break;
			case 7:
				setForeground(Color.BLACK);
				break;
			case 6:
				setForeground(new Color(0, 245, 255)); // Turquoise
				break;
			case 5:
				setForeground(new Color(255, 52, 179)); // Maroon
				break;
			case 4:
				setForeground(new Color(128, 0, 128)); // Purple
				break;
			case 3:
				setForeground(Color.RED);
				break;
			case 2:
				setForeground(Color.GREEN);
				break;
			case 1:
				setForeground(Color.BLUE);
				break;
			// Colors from http://www.sporcle.com/games/patrickstar92/minesweeper_colors/results
			}
		}
		else 
			panel.openTiles(this);
		isPressed = true;
		
	}

	/** Getter for isPressed
	 * @return the isPressed
	 */
	public boolean isPressed() {
		return isPressed;
	}

	/** Setter for hasMine
	 * @param hasMine the hasMine to set
	 */
	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
	}

	/* (non-Javadoc)
	 * @see MineTile#getRow()
	 */
	@Override
	public int getRow() {
		return ROW;
	}

	/* (non-Javadoc)
	 * @see MineTile#getCol()
	 */
	@Override
	public int getCol() {
		return COLUMN;
	}

	/* (non-Javadoc)
	 * @see MineTile#getAdjacent()
	 */
	@Override
	public int getAdjacent() {
		
		int numOfMines = 0;
		if (ROW > 0 && COLUMN > 0) // northwest tile
			if (array[ROW - 1][COLUMN - 1].hasMine)
				numOfMines++;
		if (ROW > 0) // north tile
			if (array[ROW - 1][COLUMN].hasMine)
				numOfMines++;
		if (ROW > 0 && COLUMN < array[ROW].length - 1) // northeast tile
			if (array[ROW - 1][COLUMN + 1].hasMine)
				numOfMines++;
		if (COLUMN > 0) // west tile
			if (array[ROW][COLUMN - 1].hasMine)
				numOfMines++;
		if (COLUMN < array[ROW].length - 1) // east tile
			if (array[ROW][COLUMN + 1].hasMine)
				numOfMines++;
		if (ROW < array.length - 1 && COLUMN > 0) // southwest tile
			if (array[ROW + 1][COLUMN - 1].hasMine)
				numOfMines++;
		if (ROW < array.length - 1) // south tile
			if (array[ROW + 1][COLUMN].hasMine)
				numOfMines++;
		if (ROW < array.length - 1 && COLUMN < array[ROW].length - 1) // southeast tile
			if (array[ROW + 1][COLUMN + 1].hasMine)
				numOfMines++;
		return numOfMines;
	}

	/** Getter for array
	 * @return the array
	 */
	public static Tile[][] getArray() {
		return array;
	}

	/** Setter for array
	 * @param array the array to set
	 */
	public static void setArray(Tile[][] array) {
		Tile.array = array;
	}

	/** Getter for rOW
	 * @return the rOW
	 */
	public int getROW() {
		return ROW;
	}

	/** Getter for cOLUMN
	 * @return the cOLUMN
	 */
	public int getCOLUMN() {
		return COLUMN;
	}

	/** Getter for hasMine
	 * @return the hasMine
	 */
	public boolean hasMine() {
		return hasMine;
	}

	/** Getter for mineClicked
	 * @return the mineClicked
	 */
	public boolean isMineClicked() {
		return mineClicked;
	}

	/** Getter for mineImage
	 * @return the mineImage
	 */
	public static ImageIcon getMineImage() {
		return mineImage;
	}

	/** Getter for flagImage
	 * @return the flagImage
	 */
	public static ImageIcon getFlagImage() {
		return flagImage;
	}

}