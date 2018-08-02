package by.rekuts.travelagency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Entity
@Table(name = "review")
public class Review implements Serializable {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	@Column(name="id", nullable = false)
	private int id;

	@NotNull
	@Column(name="date")
	private LocalDateTime date;

	@Column(name="text")
	private String text;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;
}
