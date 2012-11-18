import java.io.Serializable;

/**
 * @author Jared Moore
 * @version Nov 17, 2012
 */
public class Score implements Comparable<Score>, Serializable {

	/** Used to make sure the objects in the file are the correct version of this class 
	 */
	private static final long serialVersionUID = -6220082138832090333L;
	private double ratio;
	private int time, rows, columns, mines;
	private String name;
	
	/** Constructor for Score
	 * 
	 */
	public Score(int rows, int columns, int mines, int time, String name) {
		
		ratio = ((double) mines) / (rows * columns);
		this.time = time;
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Score other) {
		
		if (ratio < other.getRatio())
			return -1;
		else if (ratio > other.getRatio())
			return 1;
		else if (ratio == other.getRatio())
			if (time > other.getTime())
				return 1;
			else if (time < other.getTime())
				return -1;
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return String.format("%-12s%d x %d with %d mines in %d seconds", name, rows, columns, mines, time);
	}

	/** Getter for ratio
	 * @return the ratio
	 */
	public double getRatio() {
		return ratio;
	}

	/** Getter for time
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

}
