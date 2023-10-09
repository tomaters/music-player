package musicplayer.members;

public class Admin extends Member {

	private String username = "Admin";
	private String password = "coolAdminPW";
	
	public Admin(String name, String email) {
		super(name, email);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
