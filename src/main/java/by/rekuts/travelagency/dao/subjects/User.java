package by.rekuts.travelagency.dao.subjects;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;
	private String login;
	private String password;
	private List<Tour> tours;

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", tours='" + tours + '\'' +
				'}';
	}

	public User() {
	}

	public User(int userId, String login, String password) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.tours = new ArrayList<>();
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
	public List<Tour> getTours() {
		return tours;
	}
	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
}
