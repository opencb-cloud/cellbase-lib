package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

public class Position {
	private String chromosome;
	private int position;

	public Position(String chromosome, int position) {
		this.chromosome = chromosome;
		this.position = position;
	}

	public static List<Position> parsePosition(String positionString) {
		List<Position> positions = new ArrayList<Position>();
		String[] positionItems = positionString.split(",");
		for(String posString: positionItems) {
			String[] fields = posString.split(":", -1);
			if(fields.length == 2) {
				positions.add(new Position(fields[0], Integer.parseInt(fields[1])));
			}else {
				positions.add(null);
			}
		}
		return positions;
	}

	@Override
	public String toString() {
		return chromosome+":"+position;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}
