package aiss.api.resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import aiss.model.Videogame;
import aiss.model.repository.MapVGListRepository;
import aiss.model.repository.VGListRepository;

@Path("/songs")
public class VideogameResource {

	public static VideogameResource _instance=null;
	VGListRepository repository;
	
	private VideogameResource(){
		repository=MapVGListRepository.getInstance();
	}
	
	public static VideogameResource getInstance()
	{
		if(_instance==null)
			_instance=new VideogameResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Videogame> getAll()
	{
		return null;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Videogame get(@PathParam("id") String videogameId)
	{
		
		return null;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVideogame(@Context UriInfo uriInfo, Videogame videogame) {
		return null;
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateVideogame(Videogame videogame) {
		return null;
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeVideogame(@PathParam("id") String videogameId) {
		return null;
	}
	
}

