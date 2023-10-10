package musicplayer.members;

import java.util.ArrayList;

public class User extends Member {

	private ArrayList<String> accountDetails = new ArrayList<String>();
	
	public User(String name, String email) {
		super(name, email);
	}
	
	public User(String name, String username, String password, String email) {
		super(name, username, password, email);
		// add details to accountDetails
		accountDetails.add(name);
		accountDetails.add(username);
		accountDetails.add(password);
		accountDetails.add(email);
	}
	// method to upload details to file
	public void saveAccountDetailsToFile() {
		AccountList accountList = new AccountList(accountDetails);
		accountList.writeAccountDetails(accountDetails);		
	}
	
}
