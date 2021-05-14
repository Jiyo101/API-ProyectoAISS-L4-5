package aiss.comparators;

import java.util.Comparator;

import aiss.model.Videogame;

public class ComparatorNameVideogameReversed implements Comparator<Videogame>{
	
	public int compare(Videogame v1, Videogame v2) {
		return v2.getTitle().compareTo(v1.getTitle());
	}
}
