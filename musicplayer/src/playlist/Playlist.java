package playlist;
// add number of plays sometime in the future; keeps track of plays even after logging out
import java.util.ArrayList;

import songs.Song;
import songs.SongList;

public class Playlist implements PlaylistInterface {
	
	private ArrayList<Song> userPlaylist = new ArrayList<Song>();
	public static int songCount = 0;
	// create SongList object, not to be confused with HashMap songList in SongList class
	private SongList _songList = new SongList();
	// default constructor
	public Playlist() {}
	
	// prints list of songs to add with their properties
	@Override
	public void displaySongList() {
		// reads songcatalogue.txt file and stores songs into songList HashMap
		_songList.readSongFile();
		// print available song list
		 for (Song song : _songList.getSongList().values()) {
		        System.out.printf("Song ID: [%-4s]  Artist: %-16s Song title: %-24s Release date: %-5s Genre: %-8s Duration: %-10s%n",
		                song.getSongID(), song.getArtist(), song.getTitle(), song.getReleaseDate(), song.getGenre(), song.getDuration());
		}			
	}
	// shuffle playlist by sending a random song to the end of the ArrayList songCount amount of times
	@Override
	public void shufflePlaylist() {
		int[] randomIndices = new int[songCount];
		for(int i = 0; i < songCount; i++) {
			randomIndices[i] = (int)(Math.random()*(songCount));
			// create Song to store a song to add it to the end of the ArrayList after removing it 
			Song randomSong = userPlaylist.get(randomIndices[i]);
			userPlaylist.remove(randomIndices[i]);
			userPlaylist.add(randomSong);
		}
	}
	
	// checks if a song is in a playlist using keys artist and title
	@Override
	public boolean isSongInPlaylist(String addSongSelection) {
		for(Song song : userPlaylist) {
			if(addSongSelection.toUpperCase().trim().equals(song.getSongID())) {
				return true;
			}
		}
		return false;
	}

	// adds a song to the playlist and increases song count
	@Override
	public void addToPlaylist(String addSongSelection) {
		// check if song ID matches key
		if(_songList.getSongList().containsKey(addSongSelection.toUpperCase().trim())) {
			// check if song already exists in the playlist
			if(isSongInPlaylist(addSongSelection)){
				System.out.println(" That song is already in your playlist");
				return;
			}
			// create song and add to userPlaylist
			Song songToAdd = _songList.getSongList().get(addSongSelection.toUpperCase().trim());
			userPlaylist.add(songToAdd);
			System.out.println(" The song has been added to your playlist");
			// update songCount
			songCount ++;
		} else {
			System.out.println(" Incorrect Song ID: please try again");
		}
	}
	
	@Override
	public void showUserPlaylist() {
		 for (Song song : userPlaylist) {
		        System.out.printf(" Artist: %-16s Song title: %-24s Release date: %-5s Genre: %-8s Duration: %-10s%n",
		                song.getArtist(), song.getTitle(), song.getReleaseDate(), song.getGenre(), song.getDuration());
		}	
	}
	
	@Override
	public void showUserPlaylistWithID() {
		for (Song song : userPlaylist) {
			System.out.printf(" Song ID: [%-4s]  Artist: %-16s Song title: %-24s Release date: %-5s Genre: %-8s Duration: %-10s%n",
					song.getSongID(), song.getArtist(), song.getTitle(), song.getReleaseDate(), song.getGenre(), song.getDuration());
		}	
	}
	
	// adds a song to the playlist and decreases  song count
	@Override
	public void removeFromPlaylist(String removeSongSelection) {
		for(int i = 0; i < userPlaylist.size(); i++) {
			if(removeSongSelection.toUpperCase().trim().equals(userPlaylist.get(i).getSongID())) {
				userPlaylist.remove(i);			
				System.out.println(" The song has been removed from your playlist");
			}
		}
		// update songCount
		songCount --;
	}
	
	// clears playlist and resets song count
	@Override
	public void clearUserPlaylist() {
		userPlaylist.clear();
		songCount = 0;
	}

	public ArrayList<Song> getUserPlaylist() {
		return userPlaylist;
	}

	public void setUserPlaylist(ArrayList<Song> playlist) {
		this.userPlaylist = playlist;
	}

	public static int getSongCount() {
		return songCount;
	}
	// this may be needed when an admin adds a song
	public static void setSongCount(int songCount) {
		Playlist.songCount = songCount;
	}

}
