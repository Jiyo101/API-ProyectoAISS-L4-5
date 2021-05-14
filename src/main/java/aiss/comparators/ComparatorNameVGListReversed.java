package aiss.comparators;

import java.util.Comparator;

import aiss.model.VGList;

public class ComparatorNameVGListReversed implements Comparator<VGList>{
	
	public int compare(VGList vgl1, VGList vgl2) {
		return vgl2.getName().compareTo(vgl1.getName());
	}
}
