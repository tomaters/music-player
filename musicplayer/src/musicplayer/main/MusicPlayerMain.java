package musicplayer.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import musicplayer.members.User;
import playlist.Playlist;
import songs.Song;

public class MusicPlayerMain {
	
	public static Scanner scan = new Scanner(System.in);
	// number of available songs
	static final int NUM_SONGS = 10;
	// call user class
	static User user;
	static Playlist playlist;

	// create thread to insert pauses
	static Thread thread = new Thread();
	
	public static void main(String[] args) throws InterruptedException {

		// initial variables

		ArrayList<Song> userPlaylist = new ArrayList<Song>();
		boolean close = false;
		
		// begin loop
		while(!close) {
			// login menu: create user, sign in, close program
			int input = displayLoginMenu();
			// clear scanner buffer
			scan.nextLine(); 
			
			switch(input) {
			// create account
			case 1: createAccount(); 
					break;
					
			// login
			case 2: signIn(); 
				if(user.isLoggedIn()) {
					// will return a number between 1 and 7				
					boolean mainClose = false;
					
					// main menu after logging in
					while(!mainClose) {
						int mainInput = displayMainMenu();
						// clear scanner buffer
						scan.nextLine(); 
						
						switch(mainInput) {
						
						// view playlist
						case 1: 
							viewPlaylist();
							if(userPlaylist.size() > 0) playlist.displaySongList(userPlaylist);
							else System.out.println("Your playlist is empty");
							break;
							
							// shuffle playlist
						case 2:
							shufflePlaylist();
							break;
							
							// add song
						case 3:
							addSong();
							break;
							
							// remove song
						case 4:
							removeSong();
							break;
							
							// view user info
						case 5:
							viewUserInfo();
							break;
							
							// logout (return to login menu)
						case 6: logout();
							mainClose = true;
							break;
							
							// close program from main menu
						case 7:	close();
							mainClose = true;
							close = true;
						}		
					}	
				}
				break;
					
			// close program from login menu
			case 3:	close = true;
				close();
				return;
			}			
		}
	}
		
	// login menu: create account, sign in, exit
	public static int displayLoginMenu() throws InterruptedException{
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\tWelcome to MusicPlayer!");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);	
		System.out.printf(" [1] Create an account%n [2] Sign in%n [3] Close MusicPlayer%n");
		while(true) {
			try {
				int input = scan.nextInt();			
				if(input == 1 || input == 2 || input == 3) return input;
				System.out.println("Please enter a number between 1 and 3");
			} catch(InputMismatchException e){
				// clear scanner buffer
				scan.nextLine();
				System.out.println("Please enter a number between 1 and 3");
			}			
		}
	}

	public static void createAccount() throws InterruptedException {
		
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t   [Create account]");
		// enter new user info
		System.out.println("---------------------------------------------------------");
		Thread.sleep(750);		
		System.out.println("Enter name: ");
		String name = scan.nextLine();
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		System.out.println("Enter email address: ");
		String email = scan.nextLine();
		
		// save user info
		user = new User(name, username, password, email);
		System.out.println("[Account creation complete]");
		Thread.sleep(750);

		System.out.println("Returning to login screen...");
		Thread.sleep(750);
	}
	
	public static void signIn() throws InterruptedException{
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t      [Sign in]");
		System.out.println("---------------------------------------------------------");
		boolean close = false;

		while(!close) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			if(username.toLowerCase().equals("x")) {
				System.out.println("Returning to login menu...");
				Thread.sleep(1000);
				close = true;
				break;
			}
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			// if input matches id/pw, set isLoggedIn to true and exit loop
			try {
				if(username.equals(user.getUsername()) && password.equals(user.getPassword())) { 			
					System.out.println("Login successful");
					Thread.sleep(750);
					user.setLoggedIn(true);
					close = true;
					break;
				}				
			} catch(NullPointerException e) {
				System.out.println("Error: please create an account first");
				Thread.sleep(1000);
				System.out.println("Returning to login menu...");
				Thread.sleep(1000);
				close = true;
				break;
			}
			
			System.out.println("Username and password do not match. Please try again\n"
					+ "(Enter 'x' to return to login menu)");
		}
	}
	
	// main menu: (add, remove song, display playlist, shuffle, user info, logout, close program
	public static int displayMainMenu() throws InterruptedException{
		
		System.out.println("---------------------------------------------------------");	
		System.out.printf("\tHello, %s! Welcome to Music Player%n", user.getName());
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);	
		System.out.printf("[1] View playlist\t\t[5] View user info\n");
		System.out.printf("[2] Shuffle playlist\t\t[6] Log out\n");
		System.out.printf("[3] Add song to playlist\t[7] Close MusicPlayer\n");
		System.out.printf("[4] Remove song from playlist\n");
		while(true) {
			try {
				int input = scan.nextInt();
				if(input < 1 || input > 7) {
					System.out.println("Please enter a number between 1 and 7");
					continue;
				}
				return input;
			} catch(InputMismatchException e){
				// clear scanner buffer
				scan.nextLine();
				System.out.println("Please enter a number between 1 and 7");
			} 		
		}
	}
	
	public static void logout() throws InterruptedException {
		System.out.println("Logging out...");
		user.setLoggedIn(false);
		Thread.sleep(1000);
		System.out.println("Returning to login menu...");
		Thread.sleep(1000);
	}
	
	public static void close() throws InterruptedException {
		System.out.println("Thank you for using MusicPlayer");
		Thread.sleep(1000);
		System.out.println("[Program closed]");
		scan.close();
	}
	
	private static void viewPlaylist() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.printf("   Displaying %n's playlist", user.getName());
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
	}
	private static void shufflePlaylist() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("   Shuffle playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		System.out.println("Would  you like to shuffle your playlist? Y | N");
		String input = scan.nextLine();
		if(input.toLowerCase().equals("y")) {
			// CODE TO SHUFFLE PLAYLIST
			System.out.println("Your playlist has been shuffled");
		}
		Thread.sleep(1000);
		System.out.println("Returning to main menu...");
	}
	
	private static void addSong() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("   Add song to playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		// CODE TO ADD SONG
	}
	
	private static void removeSong() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("   Remove song from playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		// CODE TO REMOVE SONG
	}
	// displays user information
	private static void viewUserInfo() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\tViewing user information");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		System.out.printf("Name: %s%n", user.getName());
		System.out.printf("Username: %s%n", user.getUsername());
		System.out.printf("Email: %s%n", user.getEmail());
		Thread.sleep(500);
		System.out.println("[Enter any key to return to main menu]");
		scan.nextLine();
		System.out.println("Returning to main menu...");
		Thread.sleep(1000);
	}
	
}