package musicplayer.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import musicplayer.members.AccountList;
import musicplayer.members.Admin;
import musicplayer.members.User;
import playlist.Playlist;
import songs.SongList;

public class MusicPlayerMain {
	// number of available songs
	static final int NUM_SONGS = 10;
	
	// construct necessary objects
	static Playlist playlist = new Playlist();
	static SongList songList = new SongList();
	
	// scanner to input text
	public static Scanner scan = new Scanner(System.in);
	
	// call other classes
	static User user;
	static AccountList accountList;
	
	// conditions to exit menu
	static boolean closeLoginMenu = false;
	static boolean closeMainMenu = false;
	
	public static void main(String[] args) throws InterruptedException {
		// load account data from text file
		accountList = new AccountList(new ArrayList<String>());
		accountList.uploadSavedAccounts();
		
		// LOGIN MENU
		while(!closeLoginMenu) {
			int input = displayLoginMenu();
			scan.nextLine(); 										// clear scanner buffer
			switch(input) {
				case 0: runAdminMenu(); break;						// 0) admin menu (see runAdminMenu())
				case 1: createAccount(); break;						// 1) create account
				// login
				case 2: signIn(); 									// 2) sign in
					// prevent NullPointerException if user has not been made
				try {
					if(accountList.isLoggedIn()) {
						// MAIN MENU (after logging in)
						while(!closeMainMenu) {
							// will return a number between 1 and 8			
							int mainInput = displayMainMenu();
							scan.nextLine(); 						// clear scanner buffer
							
							switch(mainInput) {	
								case 1: viewPlaylist(); break; 		// 1) view playlist
								case 2: shufflePlaylist(); break; 	// 2) shuffle playlist
								case 3: addSong(); break; 			// 3) add song
								case 4: removeSong(); break;		// 4) remove song
								case 5: removeAllSongs(); break;	// 5) remove all songs
								case 6:	viewUserInfo(); break;		// 6) view user info
								// 7) logout (return to login menu)
								case 7: logout(); closeMainMenu = true; break;
								// 8) close program from main menu
								case 8:	close(); closeMainMenu = true; closeLoginMenu = true;
							}		
						}	
					}
				} catch (NullPointerException e) {}
					break;
				// close program from login menu
				case 3:	closeLoginMenu = true; close(); return;		// 3) close program
			}			
		}
	}
		
	// display login menu: create account, sign in, exit
	public static int displayLoginMenu() throws InterruptedException{
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\tWelcome to MusicPlayer!");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);	
		System.out.printf(" [1] Create an account%n [2] Sign in%n [3] Close MusicPlayer%n");
		while(true) {
			try {
				int input = scan.nextInt();			
				if(input >= 0 && input <= 3) return input;
				System.out.println(" Please enter a number between 1 and 3");
			} catch(InputMismatchException e){
				// clear scanner buffer
				scan.nextLine();
				System.out.println(" Please enter a number between 1 and 3");
			}			
		}
	}
	
	// 1) create user account
	public static void createAccount() throws InterruptedException {
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t   [Create account]");
		// enter new user info
		System.out.println("---------------------------------------------------------");
		Thread.sleep(750);		
		System.out.println(" Enter name: ");
		String name = scan.nextLine();
		System.out.println(" Enter username: ");
		String username = scan.nextLine();
		System.out.println(" Enter password: ");
		String password = scan.nextLine();
		System.out.println(" Enter email address: ");
		String email = scan.nextLine();
		
		// create new user
		user = new User(name, username, password, email);
		
		// create ArrayList to write user info into file
		ArrayList<String> accountDetails = new ArrayList<String>();
			accountDetails.add(name);
			accountDetails.add(username);
			accountDetails.add(password);
			accountDetails.add(email);
		
		// write user info into file
		accountList.writeAccountDetails(accountDetails);
		
		// add user info to HashMap
		accountList.addAccountToMap(username, user);
		System.out.println("[Account creation complete]");
		Thread.sleep(750);

		System.out.println("Returning to login screen...");
		Thread.sleep(750);
	}

	// 2) sign into user account
	public static void signIn() throws InterruptedException{
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t      [Sign in]");
		System.out.println("---------------------------------------------------------");
		boolean closeLoginPrompt = false;

		while(!closeLoginPrompt) {
			System.out.println(" Enter username: ");
			String username = scan.nextLine();
			if(username.toLowerCase().equals("x")) {
				System.out.println(" Returning to login menu...");
				Thread.sleep(1000);
				closeLoginPrompt = true;
				break;
			}
			System.out.println(" Enter password: ");
			String password = scan.nextLine();
			
			// if input matches id/pw, checkLogin returns true, exits loop
			try {
				if(accountList.checkLogin(username, password)) {
					Thread.sleep(750);
					// create user if there is none
					if(user == null) {
						user = new User();
					}
					accountList.setLoggedIn(true);
					closeMainMenu = false;
					System.out.println("Login successful");
					closeLoginPrompt = true;
					break;					
				}
			} catch(Exception e) {}
			
			// if login failed, try again
			System.out.println("Username and password do not match. Please try again\n"
					+ "(Enter 'x' to return to login menu)");
			System.out.println("---------------------------------------------------------");
		}
	}
	
	// 3, 8) close program
	public static void close() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println(" Thank you for using MusicPlayer");
		Thread.sleep(1000);
		System.out.println(" [Program closed]");
		scan.close();
	}
	
	// display main menu: (add, remove song, display playlist, shuffle, user info, logout, close program
	public static int displayMainMenu() throws InterruptedException{
		System.out.println("---------------------------------------------------------");	
		System.out.printf("\tHello, %s! Welcome to Music Player%n", accountList.getName());
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);	
		System.out.printf(" [1] View playlist\t\t[5] Remove all songs from playlist\n");
		System.out.printf(" [2] Shuffle playlist\t\t[6] View user info\n");
		System.out.printf(" [3] Add song to playlist\t[7] Log out\n");
		System.out.printf(" [4] Remove song from playlist\t[8] Close MusicPlayer\n");
		while(true) {
			try {
				int input = scan.nextInt();
				if(input < 1 || input > 8) {
					System.out.println("Please enter a number between 1 and 8");
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
	
	// 1) display playlist
	private static void viewPlaylist() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.printf("\tDisplaying %s's playlist%n", accountList.getName());
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		if(playlist.getUserPlaylist().size() > 0) {
			playlist.showUserPlaylist();
			System.out.println("---------------------------------------------------------");
			System.out.printf(" You have %d song(s) in your playlist%n", Playlist.getSongCount());
		}
		else System.out.println(" Your playlist is empty");
		enterToReturn();
	}
	
	// 2) shuffle playlist
	private static void shufflePlaylist() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\t   Shuffle playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		if(playlist.getUserPlaylist().size() > 0) {
			System.out.println(" Would  you like to shuffle your playlist? Y | N");
			String input = scan.nextLine();
			if(input.toLowerCase().equals("y")) {
				playlist.shufflePlaylist();
				System.out.println(" Your playlist has been shuffled");
			}	
			enterToReturn();
			return;
		} else { System.out.println(" Your playlist is empty");
		enterToReturn();
		}
	}
	
	// 3) add song to playlist
	private static void addSong() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\t  Add song to playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);
		// view song list
		playlist.displaySongList();
		System.out.println("---------------------------------------------------------");
		System.out.println(" Enter the Song ID that you would like to add to your playlist: ");
		System.out.println(" 'X' to cancel");
		String addSongSelection = scan.nextLine();
		// x to cancel
		if (!addSongSelection.toUpperCase().equals("X")){
			// if not x, call method
			playlist.addToPlaylist(addSongSelection);			
		}
		enterToReturn();
	}
	
	// 4) remove one song from playlist
	private static void removeSong() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\tRemove song from playlist");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		if(playlist.getUserPlaylist().size() > 0) {
			playlist.showUserPlaylistWithID();
			System.out.println("---------------------------------------------------------");
			System.out.println(" Enter the Song ID that you would like to remove from your playlist: ");
			System.out.println(" 'X' to cancel");
			String removeSongSelection = scan.nextLine();
			// x to cancel
			if (!removeSongSelection.toUpperCase().equals("X")){
				// if not cancelled, call method to remove a song
				playlist.removeFromPlaylist(removeSongSelection);			
			} else { 
				enterToReturn(); return;
			}
		}
		// if playlist is already empty, exit
		else System.out.println(" Your playlist is empty");
		enterToReturn();
	}
	
	// 5) remove all songs from playlist
	private static void removeAllSongs() throws InterruptedException {
		if(playlist.getUserPlaylist().size() == 0) {
			System.out.println(" Your playlist is empty");
			enterToReturn(); return;
		}
		System.out.println(" Would you like to remove all songs from your playlist? Y | N");
		String input = scan.nextLine();
		if(input.toUpperCase().trim().equals("Y")) {
			playlist.clearUserPlaylist();
			System.out.println(" All songs have been removed from your playlist");
		}
		enterToReturn();
	}
	
	// 6) displays user information
	private static void viewUserInfo() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\tViewing user information");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(500);
		System.out.printf(" Name: %s%n", accountList.getName());
		System.out.printf(" Username: %s%n", accountList.getUsername());
		System.out.printf(" Email: %s%n", accountList.getEmail());
		enterToReturn();
	}
	
	// 7) log out of user account
	public static void logout() throws InterruptedException {
		System.out.println(" Logging out...");
		accountList.setLoggedIn(false);
		Thread.sleep(1000);
		System.out.println(" Returning to login menu...");
		Thread.sleep(1000);
	}
	
	// method to insert pauses require enter key to return to menu
	private static void enterToReturn() throws InterruptedException {
		Thread.sleep(500);
		System.out.println(" [Press Enter to return to menu]");
		scan.nextLine();
		System.out.println(" Returning to menu...");
		Thread.sleep(1000);
	}
	
	// ADMIN MENU: login, add/remove songs from songList
	private static void runAdminMenu() throws InterruptedException {
		System.out.println("---------------------------------------------------------");	
		System.out.println("\t\t  Administrator Login");
		System.out.println("---------------------------------------------------------");
		Thread.sleep(1000);	
		// create admin
		Admin admin = new Admin();
		boolean closeAdminLoginPrompt = false;
		// ADMIN LOGIN PROMPT
		while(!closeAdminLoginPrompt) {
			System.out.println(" (Enter 'x' to return to login menu)");
			System.out.println(" Enter username: ");
			String username = scan.nextLine();
			// if x is entered, return to login menu
			if(username.toLowerCase().equals("x")) {
				System.out.println(" Returning to login menu...");
				Thread.sleep(1000);
				closeAdminLoginPrompt = true;
				break;
			}
			System.out.println(" Enter password: ");
			String password = scan.nextLine();
			
			// if input matches id/pw, set adminLoggedIn to true
			try {
				// compare id/pw to username and password in Admin class
				if(username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
					Thread.sleep(500);
					admin.setAdminLoggedIn(true);
					System.out.println(" Login successful");
					break;
				}
				else { 
					System.out.println(" Username and password do not match. Please try again\n");
					System.out.println("---------------------------------------------------------");
					Thread.sleep(750);
				}
			} catch(Exception e) {};
		}
			
		// ADMIN MAIN MENU
		while(admin.isAdminLoggedIn()) {
			System.out.println("---------------------------------------------------------");	
			System.out.println("\t     Displaying Administrator Menu");
			System.out.println("---------------------------------------------------------");
			System.out.printf(" [1] Add a new song%n [2] Remove a song (DEBUGGING)%n"
					+ " [3] Log out%n");
			try {
				int input = scan.nextInt();			
				if(input >= 1 && input <= 3) {
					scan.nextLine(); // clear scanner buffer
					
					switch(input) {
					case 1: 											// 1) add song to songcatalogue
						System.out.println("---------------------------------------------------------");
						System.out.println("\t\t[Admin] Add a Song");
						System.out.println("---------------------------------------------------------");
						System.out.println(" Would you like to add a song to songcatalogue.txt? Y | N");
						String _input = scan.nextLine();
						if(_input.toUpperCase().trim().equals("Y")) {
							songList.addSongToFile();				 
						}
						enterToReturn(); break;						
					case 2: 											// 2) remove song from songcatalogue
//						System.out.println("---------------------------------------------------------");
//						System.out.println("\t\t[Admin] Remove a Song");
//						System.out.println("---------------------------------------------------------");
//						Thread.sleep(1000);
//						playlist.displaySongList();
//						System.out.println("---------------------------------------------------------");
//						Thread.sleep(500);
//						System.out.println(" Enter ID of song you would like to remove: ");
//						System.out.println(" 'X' to cancel");
//						String removeSongID = scan.nextLine();
//						if(removeSongID.toLowerCase().trim().equals("x")) {
//							enterToReturn(); break;
//						}
						enterToReturn(); break;							
					case 3: 											// 3) log out of Admin
						admin.setAdminLoggedIn(false); // returns to login menu
						System.out.println(" Logged out of Administrator account");
						Thread.sleep(500);
						enterToReturn(); break;	
					}
				} else {
					Thread.sleep(200);
					System.out.println(" Please enter a number between 1 and 3");	
					Thread.sleep(1000);
				}
			} catch(InputMismatchException e){
				// clear scanner buffer
				scan.nextLine();
				Thread.sleep(200);
				System.out.println(" Please enter a number between 1 and 3");
				Thread.sleep(1000);
			}			
		}
	}
} 
