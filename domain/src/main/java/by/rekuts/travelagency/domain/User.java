package by.rekuts.travelagency.domain;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Entity
@Table(name = "\"user\"")
@TypeDef(
        name = "role_enum",
        typeClass = RoleEnumType.class
)
public class User implements Serializable {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	@Column(name="\"id\"", nullable = false)
	private int id;

	@NotNull
	@Column(name="login", nullable = false)
	private String login;

	@Column(name="password")
	private String password;

	@Column(name = "role")
	@Type(type="role_enum")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "user_tour",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "tour_id") }
	)
	private List<Tour> tours;

	@Version
	private Integer version;

	/**
	 * Constructor - creating new object
	 * @param id - id of the user
	 * @param login - username of user
	 * @param password - password of user
	 * @see User#User()
	 */
	public User(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.tours = new ArrayList<>();
	}

	public enum UserRole {
		ROLE_MEMBER,
		ROLE_ADMIN
	}
}
