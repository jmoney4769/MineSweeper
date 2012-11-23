import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** Panel to hold the Mines, as JToggleButtons
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class MineSweeperPanel extends JPanel {
	
	private boolean firstClick = true;
	private Tile[][] tiles;
	private TopPanel top;
	private LeaderBoard board;
	private int mines;

	/** Constructor for MineSweeperPanel 
	 */
	public MineSweeperPanel(int rows, int columns, int mines) {

		this.mines = mines;
		board = new LeaderBoard();
		setLayout(new GridLayout(rows, columns));
		
		tiles = new Tile[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				tiles[i][j] = new Tile(i, j, this);
				add(tiles[i][j]);
			}
		
		Random random = new Random(); 
		int temp = mines;
		while (temp > 0) {
			int i = random.nextInt(rows - 1);
			int j = random.nextInt(columns - 1);
			if (!tiles[i][j].hasMine()) {
				tiles[i][j].setHasMine(true);
				temp--;
			}
		}
		
		repaint();
	}

	/** Handle the event where a mine is clicked.  If it is on the first click, movie it to the top left corner
	 * @param row The row of the tile
	 * @param column The column of the tile
	 */
	public void mineClicked(int row, int column) {
		
		if (firstClick) { // if there was a mine on the first click
			tiles[row][column].setHasMine(false);
			for (boolean placed = false; placed;) {
				for (int i = 0; i < tiles.length; i++)
					for (int j = 0; j < tiles[i].length; j++) 
						if (!tiles[i][j].hasMine() && ((i != row) && (j != column))) {
							tiles[i][j].setHasMine(true);
							tiles[row][column].setHasMine(false);
							placed = true;
							openTiles(tiles[i][j]);
						}
			}
			firstClick = false;
			return;
		}
		// otherwise, the player loses
		tiles[row][column].setIcon(Tile.getMineImage());
		tiles[row][column].setBackground(Color.RED);
		for (Tile[] i : tiles)
			for (Tile j : i) {
				j.getModel().setEnabled(false);
				if (j.hasMine())
					j.setIcon(Tile.getMineImage());
			}
		top.getSmiley().setIcon(TopPanel.getLost());
		top.getTimer().stop();
	}

	/** Getter for firstClick
	 * @return the firstClick
	 */
	public boolean isFirstClick() {
		return firstClick;
	}

	/** Setter for firstClick
	 * @param firstClick the firstClick to set
	 */
	public void setFirstClick(boolean firstClick) {
		this.firstClick = firstClick;
	}

	/** Opens tile(s) around the one that was clicked
	 * @param tile The tile that was clicked
	 */
	public void openTiles(Tile tile) {
		
		if (tile.openTile() == 0);
			ArrayList<Tile> array = MinesweeperUtils.expandTile(tile, tiles);
			for (Tile i : array)
				if (i.openTile() == 0)
					openTiles(i);
	
	}

	/** Makes sure the number of flags doesn't go below zero
	 * @return Whether or not there are more than zero flags
	 */
	public boolean isFlagLeft() {
		
		return (top.getNumOfFlags() > 0);
	}

	/** Adds or removes a flag
	 * @param add Whether to increment (true) or decrement (false) the number of flags 
	 */
	public void addFlag(boolean add) {
		
		top.changeFlags(!add);
	}

	/** Changes the smiley icon when one pushes down the left mouse button
	 * @param isDown Whether or not the mouse is down (true) or has been released
	 */
	public void pressed(boolean isDown) {
	
		if (isDown) 
			top.getSmiley().setIcon(TopPanel.getUncertain());
		else 
			top.getSmiley().setIcon(TopPanel.getNormal());
	}

	/** Checks to see if the user has won by clicked all the tiles without mines
	 * @return Whether or not the user has won
	 */
	public boolean hasWon() {
		for (Tile[] i : tiles)
			for (Tile j : i)
				if (!(!j.hasMine() && j.isPressed())) 
					if (!j.hasMine()) {
						return false;
					}
		// will only run if has not returned false
		top.getSmiley().setIcon(TopPanel.getFinished());
		top.getTimer().stop();
		board.add(top.getTime(), tiles.length, tiles[0].length, mines, JOptionPane.showInputDialog("Congratulations!  You Won!  What is your name?"));
		return true;
	}
	
	/** Shows the user where a single, unflagged mine is 
	 */
	public void showHint() {
		
		for (Tile[] i : tiles)
			for (Tile j : i)
				if ((j.getIcon() == null || !j.getIcon().equals(Tile.getFlagImage())) && j.hasMine()) {
					j.setIcon(Tile.getMineImage());
					return;
				}
	}
	
	/** Displays the high score leaderboard
	 */
	public void displayScores() {
		
		board.display();
	}
	
	/** Setter for top
	 * @param top the top to set
	 */
	public void setTop(TopPanel top) {
		this.top = top;
	}
	
	/** Getter for tiles
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}
}
