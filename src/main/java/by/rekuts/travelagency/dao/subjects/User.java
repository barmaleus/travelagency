package by.rekuts.travelagency.dao.subjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Class User, includes fields <b>userId</b>, <b>login</b>, <b>password</b>,
 * <b>tours</b>
 * @author Aleh_Rekuts
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	private int userId;
	private String login;
	private String password;
	private List<Tour> tours;

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
}
