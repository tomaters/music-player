package playlist;

public interface PlaylistInterface {

	void displaySongList() throws InterruptedException;
	
	void showUserPlaylist();
	
	void showUserPlaylistWithID();
	
	boolean isSongInPlaylist(String addSongSelection);
	
	void addToPlaylist(String addsongSelection);
	
	void removeFromPlaylist(String removesongSelection);
	
	void clearUserPlaylist();
	
	void shufflePlaylist();
}
