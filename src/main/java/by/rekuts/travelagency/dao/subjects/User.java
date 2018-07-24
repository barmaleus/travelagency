package by.rekuts.travelagency.dao.subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Class User, includes fields <b>userId</b>, <b>login</b>, <b>password</b>,
 * <b>tours</b>
 * @author Aleh_Rekuts
 */
public class User {
	private int userId;
	private String login;
	private String password;
	private List<Tour> tours;

	/**
	 * Method dispalys parameters of the user
	 * @return string with parameters
	 */
	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", tours='" + tours + '\'' +
				'}';
	}

	/**
	 * Default constructor - creating new object without parameters
	 * @see User#User(int, String, String)
	 */
	public User() {
	}

	/**
	 * Constructor - creating new object
	 * @param userId - id of the user
	 * @param login - username of user
	 * @param password - password of user
	 * @see User#User()
	 */
	public User(int userId, String login, String password) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.tours = new ArrayList<>();
	}

	/**
	 * Method helps get the value of field {@link User@userId}
	 * @return id of the user
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Method sets the value to field {@link User#userId}
	 * @param userId - id of the user
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Method helps get the value of field {@link User@login}
	 * @return login of the user
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Method sets the value to field {@link User#login}
	 * @param login - login of the user
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Method helps get the value of field {@link User@password}
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method sets the value to field {@link User#password}
	 * @param password - password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Method helps get the value of field {@link User@tours}
     * @return list of tours, where user registered
     */
	public List<Tour> getTours() {
		return tours;
	}

    /**
     * Method sets the value to field {@link User#tours}
     * @param tours - list of tours, where user registered
     */
	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
}
