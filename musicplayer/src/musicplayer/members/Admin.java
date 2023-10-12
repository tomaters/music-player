package musicplayer.members;

public class Admin extends Member {

	private String username = "Admin";
	private String password = "coolAdminPW";
	private boolean adminLoggedIn = false;
	
	public Admin() {
		super();
	}
	
	public Admin(String name, String email) {
		super(name, email);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public boolean isAdminLoggedIn() {
		return adminLoggedIn;
	}

	public void setAdminLoggedIn(boolean adminLoggedIn) {
		this.adminLoggedIn = adminLoggedIn;
	}
}
