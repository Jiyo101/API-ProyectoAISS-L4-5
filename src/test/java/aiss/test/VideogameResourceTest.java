package aiss.test;

import static org.junit.Assert.*;
import java.util.Collection;

import javax.ws.rs.core.UriInfo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.model.Genre;
import aiss.model.VGList;
import aiss.model.Videogame;
import aiss.api.resources.VideogameResource;

public class VideogameResourceTest {
	
	static Videogame videogame1, videogame2, videogame3;
	static VideogameResource vgr = new VideogameResource();
	private static UriInfo uri = null;
	
	@BeforeClass
	public static void setup() throws Exception {
		
		// Test song 1
		videogame1 = vgr.addVideogame(uri,new Videogame("Test title1", "Test Publisher", "Test Platform", "2021", Genre.SPORTS, 5.0f));
		
		// Test song 2
		song2 = sr.addSong(new Song("Test title 2","Test artist 2","Test album 2","2017"));
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sr.deleteSong(song1.getId());
		sr.deleteSong(song3.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Song> songs = sr.getAll();
		
		assertNotNull("The collection of songs is null", songs);
		
		// Show result
		System.out.println("Listing all songs:");
		int i=1;
		for (Song s : songs) {
			System.out.println("Song " + i++ + " : " + s.getTitle() + " (ID=" + s.getId() + ")");
		}
	}

	@Test
	public void testGetSong() {
		Song s = sr.getSong(song1.getId());
		
		assertEquals("The id of the playlists do not match", song1.getId(), s.getId());
		assertEquals("The name of the playlists do not match", song1.getTitle(), s.getTitle());
		
		// Show result
		System.out.println("Playlist id: " +  s.getId());
		System.out.println("Playlist name: " +  s.getTitle());
	}

	@Test
	public void testAddSong() {
		
		//TODO

	}

	@Test
	public void testUpdateSong() {
		
		String songTitle = "Update song test title";
		String songArtist = "Update song test artist";
		String songAlbum = "Update song test album";
		String songYear = "1995";
		
		// Update song
		song1.setTitle(songTitle);
		song1.setArtist(songArtist);
		song1.setAlbum(songAlbum);
		song1.setYear(songYear);
		
		boolean success = sr.updateSong(song1);
		
		assertTrue("Error when updating the song", success);
		
		Song song  = sr.getSong(song1.getId());
		assertEquals("The song's title has not been updated correctly", songTitle, song.getTitle());
		assertEquals("The song's artist has not been updated correctly", songArtist, song.getArtist());
		assertEquals("The song's album has not been updated correctly", songAlbum, song.getAlbum());
		assertEquals("The song's year has not been updated correctly", songYear, song.getYear());
	}

	@Test(expected = ResourceException.class)
	public void testDeleteSong() {
		
		// Delete songs
		boolean success = sr.deleteSong(song2.getId());
		
		assertTrue("Error when deleting the song", success);
		
		Song song  = sr.getSong(song2.getId());
		assertNull("The song has not been deleted correctly", song);
	}

}
