package by.rekuts.travelagency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Class Review, includes fields <b>id</b>, <b>date</b>, <b>text</b>,
 * <b>userId</b>, <b>tourId</b>
 * @author Aleh_Rekuts
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Entity
@Table
public class Review implements Serializable {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	private int id;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime date;

	@NotNull
	@Column(nullable = false)
	private String text;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "tour_id", nullable = false)
	private Tour tour;

	@Version
	private Integer version;
}
