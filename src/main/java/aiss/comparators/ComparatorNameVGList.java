package aiss.comparators;

import java.util.Comparator;

import aiss.model.VGList;

public class ComparatorNameVGList implements Comparator<VGList>{
	
	public int compare(VGList vgl1, VGList vgl2) {
		return vgl1.getName().compareTo(vgl2.getName());
	}
}
