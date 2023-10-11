package songs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SongList {
	// song properties: songID, artist, title, releaseDate, genre, duration
	private final int NUM_SONG_PROPERTIES = 6;
	private int numSongs = 9;
	private HashMap<String, Song> songList = new HashMap<>();
	
	public SongList() {
		super();
	}
	
	public void readSongFile() {
		try {
			 FileReader fileReader = new FileReader("songcatalogue.txt");
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
				for(int index = 0; index < numSongs; index++) {				
					// array to store user info
					String[] songInfo = new String[NUM_SONG_PROPERTIES];
					// store four newly read lines into userInfo[]
					for(int i = 0; i < NUM_SONG_PROPERTIES; i++) {
						songInfo[i] = bufferedReader.readLine();
						// create song using properties read from file 
						Song song = new Song(songInfo[0], songInfo[1], songInfo[2], songInfo[3], songInfo[4], songInfo[5]);
						// put song into HashMap with songID as key
						songList.put(songInfo[0], song);
					}
				}
	            bufferedReader.close();
	        } catch (IOException e){}
	}
	// songList getter and setter
	public HashMap<String, Song> getSongList() {
		return songList;
	}

	public void setSongList(HashMap<String, Song> songList) {
		this.songList = songList;
	}
	
}
