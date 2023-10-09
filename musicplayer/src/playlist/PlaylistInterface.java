package playlist;

import java.util.ArrayList;

import songs.Song;

public interface PlaylistInterface {

	void displaySongList(ArrayList<Song> playlist);
	
	boolean isSongInPlaylist(String artist, String title);
	
	void addToPlaylist(Song song);
	
	void removeFromPlaylist(int songIndex);
	
	void clearPlaylist();
}
