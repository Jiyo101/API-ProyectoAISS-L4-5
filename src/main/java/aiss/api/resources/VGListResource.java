package aiss.api.resources;

import java.net.URI;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.VGList;
import aiss.model.Videogame;
import aiss.model.repository.MapVGListRepository;
import aiss.model.repository.VGListRepository;

@Path("/lists")
public class VGListResource {
	
	/* Singleton */
	private static VGListResource _instance=null;
	VGListRepository repository;
	
	private VGListResource() {
		repository=MapVGListRepository.getInstance();

	}
	
	public static VGListResource getInstance()
	{
		if(_instance==null)
				_instance=new VGListResource();
		return _instance;
	}
	

	@GET
	@Produces("application/json")
	public Collection<VGList> getAll()
	{
		return repository.getAllVGLists();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public VGList get(@PathParam("id") String id)
	{
		VGList list = repository.getVGList(id);
		
		if (list == null) {
			throw new NotFoundException("The videogame list with id="+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addVGList(@Context UriInfo uriInfo, VGList vglist) {
		if (vglist.getName() == null || "".equals(vglist.getName()))
			throw new BadRequestException("The name of the videogame list must not be null");
		
		if (vglist.getVideogames()!=null)
			throw new BadRequestException("The videogames property is not editable.");

		repository.addVGList(vglist);

		// Builds the response. Returns the videogame list the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(vglist.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(vglist);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateVGList(VGList vglist) {
		VGList oldvglist = repository.getVGList(vglist.getId());
		if (oldvglist == null) {
			throw new NotFoundException("The videogame list with id="+ vglist.getId() +" was not found");			
		}
		
		if (vglist.getVideogames()!=null)
			throw new BadRequestException("The videogames property is not editable.");
		
		// Update name
		if (vglist.getName()!=null)
			oldvglist.setName(vglist.getName());
		
		// Update description
		if (vglist.getDescription()!=null)
			oldvglist.setDescription(vglist.getDescription());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeVGList(@PathParam("id") String id) {
		VGList toberemoved=repository.getVGList(id);
		if (toberemoved == null)
			throw new NotFoundException("The videogame list with id="+ id +" was not found");
		else
			repository.deleteVGList(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{playlistId}/{songId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addVideogame(@Context UriInfo uriInfo,@PathParam("vglistId") String vglistId, @PathParam("videogameId") String videogameId)
	{				
		
		VGList vglist = repository.getVGList(vglistId);
		Videogame videogame = repository.getVideogame(videogameId);
		
		if (vglist==null)
			throw new NotFoundException("The videogame list with id=" + vglistId + " was not found");
		
		if (videogame == null)
			throw new NotFoundException("The videogame with id=" + videogameId + " was not found");
		
		if (vglist.getVideogame(videogameId)!=null)
			throw new BadRequestException("The videogame is already included in the vglist.");
			
		repository.addVideogame(vglistId, videogameId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(vglistId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(vglist);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{playlistId}/{songId}")
	public Response removeVideogame(@PathParam("vglistId") String vglistId, @PathParam("videogameId") String videogameId) {
		VGList vglist = repository.getVGList(vglistId);
		Videogame videogame = repository.getVideogame(videogameId);
		
		if (vglist==null)
			throw new NotFoundException("The videogame list with id=" + vglistId + " was not found");
		
		if (videogame == null)
			throw new NotFoundException("The videogame with id=" + videogameId + " was not found");
		
		
		repository.removeVideogame(vglistId, videogameId);		
		
		return Response.noContent().build();
	}
}
