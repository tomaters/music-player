package musicplayer.members;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountList extends Member {
	
	private HashMap<String, User> accountListMap = new HashMap<String, User>();
	// four properties: name, username, password, email
	private final int NUM_USER_PROPERTIES = 4;
	// amount of lines in text file
	private int lineCount;
	
	// default constructor
	public AccountList(ArrayList<String> accountInfo) {}
	
	// upon sign-up, write account details into a text file
	public void writeAccountDetails(ArrayList<String> accountInfo) {
		FileWriter fileWriter;
		try {
			// if the text file already exists, write on top of it
			if(new File("accountlist.txt").exists()) {
				// if false, will overwrite
				fileWriter = new FileWriter("accountlist.txt", true);
			// if it does not exist, create a new one
			} else { 
				fileWriter = new FileWriter("accountlist.txt");
			}
			// construct BufferedWriter for improved performance
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			// write data into text file
			for(String data : accountInfo) {
			bufferedWriter.write(data + "\n");
			}
			// close bufferedWriter
			bufferedWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// returns lineCount, the number of lines in accountlist.txt
	public void lineCounter(){
		// if file doesnt exist, create a new one
		if(!new File("accountlist.txt").exists()) {
			return;
		} 
		String file = "accountlist.txt"; // Replace with the path to your text file
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)) ) {
			while ((bufferedReader.readLine()) != null) {
				lineCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// take file and create accounts, to be processed at the beginning only
	// all newly created accounts will be updated to accountListMap right away
	public void uploadSavedAccounts() {
		// if file doesnt exist, create a new one
		if(!new File("accountlist.txt").exists()) {
			return;
		} 
		// retrieve amount of lines in text file
		lineCounter();
		try {
			// create FileReader to read text file
			FileReader fileReader = new FileReader("accountlist.txt");
			// construct BufferedReader for improved performance
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// lineCount will be in multiples of four (4, 8, 12, etc.)
			int amountAccounts = lineCount / NUM_USER_PROPERTIES;
			for(int index = 0; index < amountAccounts; index++) {				
				// array to store user info
				String[] userInfo = new String[NUM_USER_PROPERTIES];
				// store four newly read lines into userInfo[]
				for(int i = 0; i < NUM_USER_PROPERTIES; i++) {
					userInfo[i] = bufferedReader.readLine();
					// creating user inserts the data into HashMap 
					User user = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
					addAccountToMap(userInfo[1], user);
				}
			}
			// close bufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// update account to HashMap
	public void addAccountToMap(String username, User user) {
		accountListMap.put(username, user);
	}
	
	// HashMap search key function
	public boolean checkLogin(String username, String password) {
		boolean checkLogin = false;
		// if username exists
		if(accountListMap.containsKey(username)) {
			// store according password to keyPassword
			String keyPassword = accountListMap.get(username).getPassword();
			// compare passwords
			if(password.equals(keyPassword)) {
				checkLogin = true;
				User loggedInUser = accountListMap.get(username);
				super.setName(loggedInUser.getName());
				super.setUsername(loggedInUser.getUsername());
				super.setPassword(loggedInUser.getPassword());
				super.setEmail(loggedInUser.getEmail());
			} 
		}
		return checkLogin;
	}

}
