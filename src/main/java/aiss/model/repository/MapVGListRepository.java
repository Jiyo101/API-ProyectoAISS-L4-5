package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Genre;
import aiss.model.Type;
import aiss.model.VGList;
import aiss.model.Videogame;

public class MapVGListRepository implements VGListRepository{

	Map<String, VGList> vglistMap;
	Map<String, Videogame> videogameMap;
	private static MapVGListRepository instance=null;
	private int index=0;
	
	
	public static MapVGListRepository getInstance() {
		
		if (instance==null) {
			instance = new MapVGListRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		vglistMap = new HashMap<String,VGList>();
		videogameMap = new HashMap<String,Videogame>();
		
		// Create videogames
		Videogame v1=new Videogame();
		v1.setTitle("Wii Sports");
		v1.setPublisher("Nintendo");
		v1.setPlatform("Wii");
		v1.setYear("2006");
		v1.setGenre(Genre.SPORTS);
		v1.setScore(7.2f);
		addVideogame(v1);
		
		Videogame v2=new Videogame();
		v2.setTitle("League of Legends");
		v2.setPublisher("Riot Games");
		v2.setPlatform("PC");
		v2.setYear("2009");
		v2.setGenre(Genre.MISC);
		v2.setScore(9.2f);
		addVideogame(v2);
		
		Videogame v3=new Videogame();
		v3.setTitle("Marvel Spider-man");
		v3.setPublisher("Insomniac Games");
		v3.setPlatform("PS4");
		v3.setYear("2018");
		v3.setGenre(Genre.ACTION);
		v3.setScore(10f);
		addVideogame(v3);
		
		Videogame v4=new Videogame();
		v4.setTitle("Grand Theft Auto V");
		v4.setPublisher("Rockstar");
		v4.setPlatform("PS4");
		v4.setYear("2013");
		v4.setGenre(Genre.ACTION);
		v4.setScore(8.7f);
		addVideogame(v4);
		
		Videogame v5=new Videogame();
		v5.setTitle("Rocket League");
		v5.setPublisher("Psyonix");
		v5.setPlatform("PC");
		v5.setYear("2015");
		v5.setGenre(Genre.SPORTS);
		v5.setScore(9f);
		addVideogame(v5);
		
		// Create vglist
		VGList vgl1=new VGList();
		vgl1.setName("Mis juegos favoritos");
		vgl1.setDescription("Estos son mis juegos favoritos de los que he jugado");
		vgl1.setAuthor("Paco");
		vgl1.setType(Type.FAVOURITES);
		addVGList(vgl1);
		
		VGList vgl2=new VGList();
		vgl2.setName("Juegos a los que quiero jugar");
		vgl2.setDescription("Estoy esperando a tener dinero para jugarme estos juegos");
		vgl2.setAuthor("Alberto");
		vgl2.setType(Type.WISHLIST);
		addVGList(vgl2);
		
		VGList vgl3=new VGList();
		vgl3.setName("Juegos a los que estoy viciando");
		vgl3.setDescription("Estos son los juegos en los que gasto mi tiempo en vez de estudiar");
		vgl3.setAuthor("Javi");
		vgl3.setType(Type.PLAYING);
		addVGList(vgl3);
		
		// Add videogames to vglist
		addVideogame(vgl1.getId(), v5.getId());
		addVideogame(vgl1.getId(), v3.getId());
		
		addVideogame(vgl2.getId(), v1.getId());
		addVideogame(vgl2.getId(), v5.getId());
		
		addVideogame(vgl3.getId(), v2.getId());
		addVideogame(vgl3.getId(), v3.getId());
	}
	
	// VGList related operations
	@Override
	public void addVGList(VGList vgl) {
		String id = "vgl" + index++;	
		vgl.setId(id);
		vglistMap.put(id,vgl);
	}
	
	@Override
	public Collection<VGList> getAllVGLists() {
			return vglistMap.values();
	}

	@Override
	public VGList getVGList(String id) {
		return vglistMap.get(id);
	}
	
	@Override
	public void updateVGList(VGList vgl) {
		vglistMap.put(vgl.getId(),vgl);
	}

	@Override
	public void deleteVGList(String id) {	
		vglistMap.remove(id);
	}
	

	@Override
	public void addVideogame(String vglistId, String videogameId) {
		VGList vglist = getVGList(vglistId);
		vglist.addVideogame(videogameMap.get(videogameId));
	}

	@Override
	public Collection<Videogame> getAll(String vglistId) {
		return getVGList(vglistId).getVideogames();
	}

	@Override
	public void removeVideogame(String vglistId, String videogameId) {
		getVGList(vglistId).deleteVideogame(videogameId);
	}

	
	// Videogame related operations
	
	@Override
	public void addVideogame(Videogame v) {
		String id = "v" + index++;
		v.setId(id);
		videogameMap.put(id, v);
	}
	
	@Override
	public Collection<Videogame> getAllVideogames() {
			return videogameMap.values();
	}

	@Override
	public Videogame getVideogame(String videogameId) {
		return videogameMap.get(videogameId);
	}

	@Override
	public void updateVideogame(Videogame v) {
		Videogame videogame = videogameMap.get(v.getId());
		videogame.setTitle(v.getTitle());
		videogame.setPublisher(v.getPublisher());
		videogame.setYear(v.getYear());
		videogame.setGenre(v.getGenre());
		videogame.setPlatform(v.getPlatform());
		videogame.setScore(v.getScore());
	}

	@Override
	public void deleteVideogame(String videogameId) {
		videogameMap.remove(videogameId);
	}
	
}
