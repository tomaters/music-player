package songs;

public class Song {
	private String songID;
	private String artist;
	private String title;
	private String releaseDate;
	private String genre;
	private String duration;
	
	public Song() {}
	
	public Song(String songID, String artist, String title, String releaseDate, String genre, String duration){
		this.songID = songID;
		this.artist = artist;
		this.title = title;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.duration = duration;
	}

	// getters/setters
	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
