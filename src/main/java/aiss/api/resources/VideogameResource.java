package aiss.api.resources;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Genre;
import aiss.model.Videogame;
import aiss.model.repository.MapVGListRepository;
import aiss.model.repository.VGListRepository;

@Path("/videogames")
public class VideogameResource {

	public static VideogameResource _instance=null;
	VGListRepository repository;
	
	public VideogameResource(){
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
		return repository.getAllVideogames();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Videogame get(@PathParam("id") String videogameId)
	{
		
		Videogame videogame = repository.getVideogame(videogameId);
		
		if (videogame == null) {
			throw new NotFoundException("The videogame with id="+ videogameId +" was not found");			
		}
		
		return videogame;
	}
	

	@GET
	@Path("/get/{genre}")
	@Produces("application/json")
	public Set<Videogame> getGenre(@PathParam("genre") Genre genre)
	{
		Set<Videogame> lv = repository.getAllVideogames().stream().collect(Collectors.toSet());
		Set<Videogame> res = new HashSet<>();
		for (Videogame v:lv) {
			if (v == null) {
				throw new NotFoundException("The videogame was not found");			
			}
			if(v.getGenre()==genre) {
				res.add(v);
			}
		}
		return res;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVideogame(@Context UriInfo uriInfo, Videogame videogame) {
		if (videogame.getTitle() == null || "".equals(videogame.getTitle()))
			throw new BadRequestException("The title of the videogame must not be null");

		repository.addVideogame(videogame);

		// Builds the response. Returns the videogame the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(videogame.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(videogame);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateVideogame(Videogame videogame) {
		Videogame oldvideogame = repository.getVideogame(videogame.getId());
		if (oldvideogame == null) {
			throw new NotFoundException("The videogame with id="+ videogame.getId() +" was not found");			
		}
		
		// Update name
		if (videogame.getTitle()!=null)
			oldvideogame.setTitle(videogame.getTitle());
		
		// Update description
		if (videogame.getScore()!=null)
			oldvideogame.setScore(videogame.getScore());
		
		// Update description
		if (videogame.getYear() != null)
			oldvideogame.setYear(videogame.getYear());

		// Update description
		if (videogame.getPlatform() != null)
			oldvideogame.setPlatform(videogame.getPlatform());

		// Update description
		if (videogame.getGenre() != null)
			oldvideogame.setGenre(videogame.getGenre());
		
		// Update description
		if (videogame.getPublisher() != null)
			oldvideogame.setPublisher(videogame.getPublisher());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeVideogame(@PathParam("id") String videogameId) {
		Videogame toberemoved=repository.getVideogame(videogameId);
		if (toberemoved == null)
			throw new NotFoundException("The videogame with id="+ videogameId +" was not found");
		else
			repository.deleteVideogame(videogameId);
		
		return Response.noContent().build();
	}
	
}

