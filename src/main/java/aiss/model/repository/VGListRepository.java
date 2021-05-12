package aiss.model.repository;

import java.util.Collection;

import aiss.model.VGList;
import aiss.model.Videogame;

public interface VGListRepository {
	
	// Videogames
	public void addVideogame(Videogame v);
	public Collection<Videogame> getAllVideogames();
	public Videogame getVideogame(String videogameId);
	public void updateVideogame(Videogame v);
	public void deleteVideogame(String videogameId);
	
	// VGLists
	public void addVGList(VGList vgl);
	public Collection<VGList> getAllVGLists();
	public VGList getVGList(String vglistId);
	public void updateVGList(VGList vgl);
	public void deleteVGList(String vglistId);
	public Collection<Videogame> getAll(String vglistId);
	public void addVideogame(String vglistId, String videogameId);
	public void removeVideogame(String vglistId, String videogameId);

	
	
}
