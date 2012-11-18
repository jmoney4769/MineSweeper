import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
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

	/** Setter for top
	 * @param top the top to set
	 */
	public void setTop(TopPanel top) {
		this.top = top;
	}

	/**
	 * @param row
	 * @param column
	 */
	public void mineClicked(int row, int column) {
		
		if (firstClick) {
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

	/**
	 * @param tile
	 * @param mines
	 */
	public void openTiles(Tile tile) {
		
		if (tile.openTile() == 0);
			ArrayList<Tile> array = MinesweeperUtils.expandTile(tile, tiles);
			for (Tile i : array)
				if (i.openTile() == 0)
					openTiles(i);
	
	}

	/** Getter for tiles
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * @return
	 */
	public boolean isFlagLeft() {
		
		return (top.getNumOfFlags() > 0);
	}

	/**
	 * @param add 
	 * 
	 */
	public void addFlag(boolean add) {
		
		top.changeFlags(!add);
	}

	/**
	 * @param isDown
	 */
	public void pressed(boolean isDown) {
	
		if (isDown) 
			top.getSmiley().setIcon(TopPanel.getUncertain());
		else 
			top.getSmiley().setIcon(TopPanel.getNormal());
	}

	/**
	 * @return
	 */
	public boolean hasWon() {
		for (Tile[] i : tiles)
			for (Tile j : i)
				/*if (!(j.hasMine() && j.hasFlag()) && !(!j.hasMine() && !j.hasFlag())) {
					System.out.println(j.getRow() + "" + j.getCol());
					return false;
				}*/
				if (!(!j.hasMine() && j.isPressed())) 
					if (!j.hasMine()) {
						return false;
					}
		
		top.getSmiley().setIcon(TopPanel.getFinished());
		top.getTimer().stop();
		board.add(top.getTime(), tiles.length, tiles[0].length, mines, JOptionPane.showInputDialog("Congratulations!  You Won!  What is your name?"));
		return true;
	}
	
	/**
	 * 
	 */
	public void showHint() {
		
		for (Tile[] i : tiles)
			for (Tile j : i)
				if ((j.getIcon() == null || !j.getIcon().equals(Tile.getFlagImage())) && j.hasMine()) {
					j.setIcon(Tile.getMineImage());
					return;
				}
	}
	
	public void displayScores() {
		
		board.display();
	}
}
