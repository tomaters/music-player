package musicplayer.members;

import java.util.ArrayList;

public class User extends Member {

	// object to save account information
	private ArrayList<String> accountDetailsArrayList = new ArrayList<String>();
	
	// object to save account into HashMap for login
	private AccountList accountList = new AccountList(accountDetailsArrayList);
	
	public User() {
		super();
	}
	
	public User(String name, String email) {
		super(name, email);
	}
	
	public User(String name, String username, String password, String email) {
		super(name, username, password, email);
		// add details to accountDetails
		accountDetailsArrayList.add(name);
		accountDetailsArrayList.add(username);
		accountDetailsArrayList.add(password);
		accountDetailsArrayList.add(email);
	}
	
	public void writeArrayToFile() {
		accountList.writeAccountDetails(accountDetailsArrayList);			
	}
	
}