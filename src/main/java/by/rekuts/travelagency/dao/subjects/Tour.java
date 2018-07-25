package by.rekuts.travelagency.dao.subjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Class Tour, includes fields <b>id</b>, <b>photo</b>, <b>date</b>,
 * <b>duration</b>, <b>description</b>, <b>cost</b>, <b>tourType</b>,
 * <b>hotelId</b>, <b>countryId</b>, <b>users</b>
 * @author Aleh_Rekuts
 */
@Getter
@Setter
@Builder
@ToString
public class Tour {
	private int id;
	private String photo;
	private LocalDate date;
	private int duration;
	private String description;
	private BigDecimal cost;
	private String tourType;
	private int hotelId;
	private int countryId;
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
