package musicplayer.members;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AccountList {
	
	private final HashMap<String, User> accountListMap = new HashMap<String, User>();
	// four properties: name, username, password, email
	private final int NUM_USER_PROPERTIES = 4;
	
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
		// save account details into accountList array right away
		saveAccountDetails();
	}

	// read written account details and save into accountList array
	public void saveAccountDetails() {
		try {
			// create FileReader to read text file
			FileReader fileReader = new FileReader("accountlist.txt");
			// construct BufferedReader for improved performance
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//variables to store values from text into
			String userID = null;
			String[] userInfo = new String[NUM_USER_PROPERTIES];
			// store info into userInfo[] and store that into accountList array
			while((userID = bufferedReader.readLine()) != null) {
				userInfo[0] = userID;
				userInfo[1] = bufferedReader.readLine();
				userInfo[2] = bufferedReader.readLine();
				userInfo[3] = bufferedReader.readLine();
				// insert data into HashMap
				accountListMap.put(userInfo[0], new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3]));
		
			}
			// close bufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// HashMap search key function
	public boolean checkLogin(String username, String password) {
		System.out.println("test");
		boolean checkLogin = false;
		// generate keySet of accountListMap
		Set<String> keySet = accountListMap.keySet();
		// search keySet using ID/PW
		for(String key : keySet) {
			if(username.equals(key) && password.equals(accountListMap.get(key).toString())) {
				checkLogin = true;
			}
		}
		return checkLogin;
	}
	
	// HashMap getter
	public HashMap<String, User> getAccountListMap() {
		return accountListMap;
	}

}
