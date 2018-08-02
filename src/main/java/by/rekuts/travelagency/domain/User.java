package by.rekuts.travelagency.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class User, includes fields <b>userId</b>, <b>login</b>, <b>password</b>,
 * <b>tours</b>
 * @author Aleh_Rekuts
 */
@Data @ToString(exclude = "tours")
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	@Column(name="\"id\"", nullable = false)
	private int userId;

	@NotNull
	@Column(name="login", nullable = false)
	private String login;

	@Column(name="password")
	private String password;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "user_tour",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "tour_id") }
	)
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
