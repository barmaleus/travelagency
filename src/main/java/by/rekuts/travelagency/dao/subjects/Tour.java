package by.rekuts.travelagency.dao.subjects;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Class Tour, includes fields <b>id</b>, <b>photo</b>, <b>date</b>,
 * <b>duration</b>, <b>description</b>, <b>cost</b>, <b>tourType</b>,
 * <b>hotelId</b>, <b>countryId</b>, <b>users</b>
 * @author Aleh_Rekuts
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)     //todo to tune
    @Column(name="id", nullable = false)
	private int id;

    @Column(name = "photo")
	private String photo;

    @Column(name = "date")
	private LocalDate date;

    @Column(name = "duration")
	private int duration;

    @Column(name = "description")
	private String description;

    @Column(name = "cost")
	private BigDecimal cost;

    @Column(name = "tour_type")
	private String tourType;

    @Column(name = "hotel_id")
	private int hotelId;

    @Column(name = "country_id")
	private int countryId;

    @Transient //todo ???
	private List<User> users;

	/**
	 * Inner to class Tour enum TourType. It stores the names of tour types in the values.
	 */
	public enum TourType{
		A("safari"),
		B("rural"),
		C("mountain"),
		D("ski"),
		E("ecotourism"),
		F("health"),
		G("cruise"),
		H("education"),
		I("adventure"),
		J("cultural");
		
		private final String tourType;
		
		TourType(String tourType) {
			this.tourType = tourType;
		}

		public String getValue() {
			return tourType;
		}
	}
}
