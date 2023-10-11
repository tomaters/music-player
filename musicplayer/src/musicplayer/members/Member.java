package musicplayer.members;

public class Member {

	private String name;
	private String username;
	private String password;
	private String email;
	private boolean isLoggedIn = false; 
	
	public Member(){}
	
	// forgot username / password?
	public Member(String email) {
		this(null, null, null, email);
	}
	
	public Member(String username, String password) {
		this(null, username, password, null);
	}

	public Member(String name, String username, String password, String email) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
}
