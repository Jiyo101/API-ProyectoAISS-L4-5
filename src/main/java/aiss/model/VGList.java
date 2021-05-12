package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class VGList {

	private String id;
	private String name;
	private String author;
	private String description;
	private Type type;
	private List<Videogame> videogames;
	
	public VGList() {}
	
	public VGList(String name) {
		this.name = name;
	}
	
	public VGList(String name, String description, Type type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}
	
	protected void setVideogames(List<Videogame> v) {
		videogames = v;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Videogame> getVideogames() {
		return videogames;
	}
	
	public Videogame getVideogame(String id) {
		if (videogames==null)
			return null;
		
		Videogame videogame=null;
		for(Videogame v: videogames)
			if (v.getId().equals(id))
			{
				videogame=v;
				break;
			}
		
		return videogame;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void addVideogame(Videogame v) {
		if (videogames==null)
			videogames = new ArrayList<Videogame>();
		videogames.add(v);
	}
	
	public void deleteVideogame(Videogame v) {
		videogames.remove(v);
	}
	
	public void deleteVideogame(String id) {
		Videogame v = getVideogame(id);
		if (v!=null)
			videogames.remove(v);
	}

}
