package aiss.comparators;

import java.util.Comparator;

import aiss.model.Videogame;

public class ComparatorNameVideogame implements Comparator<Videogame>{
	
	public int compare(Videogame v1, Videogame v2) {
		return v1.getTitle().compareTo(v2.getTitle());
	}
}
