package by.rekuts.travelagency.dao.subjects;

public class User {
	int userId;
	String login;
	String password;

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public User() {
	}

	public User(int userId, String login, String password) {

		this.userId = userId;
		this.login = login;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
