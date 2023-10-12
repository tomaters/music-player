package songs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
// import java.util.Iterator;
import java.util.Scanner;

public class SongList {
	// song properties: songID, artist, title, releaseDate, genre, duration
	private final int NUM_SONG_PROPERTIES = 6;
	
	// number of lines in song catalogue
	private int lineCount;
	// number of songs in text file
	private int numSongs;
	// HashMap that contains song catalogue
	private HashMap<String, Song> songList = new HashMap<>();
	// scanner for admin
	public static Scanner scan = new Scanner(System.in);
	
	// default constructor
	public SongList() {
		super();
		// set lineCount and numSongs of song catalogue 
		lineCounter();
	}
	
	// read songcatalogue.txt and insert into songList HashMap
	public void readSongFile() {
		try {
			 FileReader fileReader = new FileReader("songcatalogue.txt");
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            // run loop numSongs amount of times; read 6 lines for each song
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
	
	// returns lineCount, the number of lines in songcatalogue.txt
	public void lineCounter(){
		// if file doesnt exist, create a new one
		if(!new File("songcatalogue.txt").exists()) {
			return;
		} 
		String file = "songcatalogue.txt"; // replace with the path to text file
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)) ) {
			while ((bufferedReader.readLine()) != null) {
				lineCount++;
			}
		} catch (IOException e) {} 
		numSongs = lineCount / NUM_SONG_PROPERTIES;
	}
	
	// add a new song to songcatalogue.txt; only accessible by admin
	public void addSongToFile() {
		FileWriter fileWriter;
		try {
			// if the text file already exists
			if(new File("songcatalogue.txt").exists()) {
				// if it exists, will write on it
				fileWriter = new FileWriter("songcatalogue.txt", true);
			// if it does not exist, create a new one
			} else { 
				fileWriter = new FileWriter("songcatalogue.txt");
			}
			// construct BufferedWriter for improved performance
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			// input song properties through scanner and store as string values
			System.out.println(" Enter song ID (Format > A#A#): ");
			String newSongID = scan.nextLine();
			System.out.println(" Enter song artist: ");
			String newSongArtist = scan.nextLine();
			System.out.println(" Enter song title: ");
			String newSongTitle = scan.nextLine();
			System.out.println(" Enter song release date (Format > YYYY): ");
			String newSongReleaseDate = scan.nextLine();
			System.out.println(" Enter song genre: ");
			String newSongGenre = scan.nextLine();
			System.out.println(" Enter song duration (Format > ##:##:##): ");
			String newSongDuration = scan.nextLine();
			
			// write properties into songcatalogue.txt
			bufferedWriter.write(newSongArtist + "\n");
			bufferedWriter.write(newSongID + "\n");
			bufferedWriter.write(newSongTitle + "\n");
			bufferedWriter.write(newSongReleaseDate + "\n");
			bufferedWriter.write(newSongGenre + "\n");
			bufferedWriter.write(newSongDuration + "\n");
			System.out.println(" Song info uploaded into songcatalogue.txt");
			// save song into HashMap
			Song newSong = new Song(newSongID, newSongArtist, newSongTitle, newSongReleaseDate, newSongGenre, newSongDuration);
			// put song into HashMap with songID as key
			songList.put(newSongID, newSong);
			// update numSongs and lineCount
			numSongs ++;
			lineCount += 6;
			System.out.println(numSongs + " " + lineCount);
			// close bufferedWriter
			bufferedWriter.close();
		} catch(IOException e) {}
	}
	
//	public void removeSongFromFile(String removeSongID) {
//		Iterator<String> iterator = songList.keySet().iterator();
//		while(iterator.hasNext()) {
//			String key = iterator.next();
//			if(removeSongID.toUpperCase().trim().equals(key)) {
//				iterator.remove();
//			//	numSongs --;
//				System.out.println(" The song has been removed from the song list");
//				return;
//			}
//		}
//		System.out.println(" Failed to find Song ID. Try again");
//	}
	
	// songList getter and setter
	public HashMap<String, Song> getSongList() {
		return songList;
	}

	public void setSongList(HashMap<String, Song> songList) {
		this.songList = songList;
	}
}
