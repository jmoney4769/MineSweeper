import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author Jared Moore
 * @version Nov 14, 2012
 */
public class MineSweeperPanel extends JPanel {
	
	private boolean firstClick;
	private Tile[][] tiles;

	/** Constructor for MineSweeperPanel 
	 */
	public MineSweeperPanel(int rows, int columns, int mines) {

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
						}
			}
			return;
		}
		
		tiles[row][column].setIcon(Tile.getMineImage());
	}

	/**
	 * @param tile
	 * @param mines
	 */
	public void openTiles(Tile tile) {
		
		ArrayList<Tile> array = MinesweeperUtils.expandTile(tile, tiles);
		for (Tile i : array)
			if (!i.isPressed())
				i.openTile();
			else
				return;
		
	}

	/** Getter for tiles
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}


}
