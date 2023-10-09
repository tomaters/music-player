package playlist;
// add number of plays sometime in the future; keeps track of plays even after logging out
import java.util.ArrayList;

import songs.Song;

public class Playlist implements PlaylistInterface {
	
	public ArrayList<Song> playlist = new ArrayList<Song>();
	public static int songCount = 0;
	
	// default constructor
	public Playlist() {}

	public String durationConverter(int duration) {
		int minutes = duration / 60;
		int seconds = duration % 60;
		return String.valueOf(minutes) + ":" + String.valueOf(seconds);
	}
	
	// prints list of songs with their properties
	@Override
	public void displaySongList(ArrayList<Song> playlist) {
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\tDisplaying song list");
		for(int i = 0; i < playlist.size(); i++) {
			Song song = playlist.get(i);
			System.out.printf("Artist: %s | Song name: %s | Release date: %s | Genre: %s | Duration: %s%n",
								song.getArtist(), song.getTitle(), song.getReleaseDate(), song.getGenre(), durationConverter(song.getDuration()));
		}
	}

	// checks if a song is in a playlist using keys artist and title
	@Override
	public boolean isSongInPlaylist(String artist, String title) {
		boolean flag = false;
		
		for(int i = 0; i < playlist.size(); i++) {
			if(artist.equals(playlist.get(i).getArtist()) && title.equals(playlist.get(i).getTitle())) {
				flag = true;
			}
		}
		return flag;
	}

	// adds a song to the playlist and increases song count
	@Override
	public void addToPlaylist(Song song) {
		playlist.add(song);
		songCount = playlist.size();
	}
	
	// adds a song to the playlist and decreases  song count
	@Override
	public void removeFromPlaylist(int songIndex) {
		playlist.remove(songIndex);
		songCount = playlist.size();
	}
	
	// clears playlist and resets song count
	@Override
	public void clearPlaylist() {
		playlist.clear();
		songCount = 0;
	}

	public ArrayList<Song> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(ArrayList<Song> playlist) {
		this.playlist = playlist;
	}

	public static int getSongCount() {
		return songCount;
	}

	public static void setSongCount(int songCount) {
		Playlist.songCount = songCount;
	}

	
}
