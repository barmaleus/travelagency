package by.rekuts.travelagency.dao.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class Review {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)     //todo to tune
	@Column(name="id", nullable = false)
	private int id;

	@Column(name="date")
	private LocalDateTime date;

	@Column(name="text")
	private String text;

	@Column(name="user_id")
	private int userId;

	@Column(name="tour_id")
	private int tourId;
}
