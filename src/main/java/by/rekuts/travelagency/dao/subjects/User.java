package by.rekuts.travelagency.dao.subjects;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class User, includes fields <b>userId</b>, <b>login</b>, <b>password</b>,
 * <b>tours</b>
 * @author Aleh_Rekuts
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)     //todo to tune
	@Column(name="\"id\"", nullable = false)
	private int userId;

	@Column(name="login", nullable = false)
	private String login;

	@Column(name="password")
	private String password;

	@Transient	//todo to think
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
