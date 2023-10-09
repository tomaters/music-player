package musicplayer.members;

public class User extends Member {

	public User(String name, String email) {
		super(name, email);
	}
	
	public User(String name, String username, String password, String email) {
		super(name, username, password, email);
	}
}
