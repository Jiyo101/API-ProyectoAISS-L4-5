package aiss;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideogameServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(VideogameServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// Sample log
		log.log(Level.FINE, "Processing GET request");
		 
		resp.setContentType("text/plain");
		resp.getWriter().println("VIDEOGAMES!");
	}
}
