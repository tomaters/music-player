package songs;

public class Song {

	private String artist;
	private String title;
	private String releaseDate;
	private String genre;
	private int duration;
	
	public Song() {}
	
	public Song(String artist, String title, String releaseDate, String genre, int duration){
		this.artist = artist;
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.duration = duration;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
