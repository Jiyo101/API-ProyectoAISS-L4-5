package aiss.model;

public class Videogame {

	private String id;
	private String title;
	private String publisher;
	private String year;
	private String platform;
	private Genre genre;
	private Float score;

	public Videogame() {
	}

	public Videogame(String title, String publisher, String platform, String year, Genre genre, Float score) {
		this.title = title;
		this.publisher = publisher;
		this.platform = platform;
		this.year = year;
		this.genre = genre;
		this.score = score;
	}
	
	public Videogame(String title, String platform, Genre genre) {
		this.title = title;
		this.platform = platform;
		this.genre = genre;
	}
	
	public Videogame(String id, String title, String publisher, String platform, String year, Genre genre, Float score) {
		this.id=id;
		this.title = title;
		this.publisher = publisher;
		this.platform = platform;
		this.year = year;
		this.genre = genre;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	

}
