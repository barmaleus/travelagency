package by.rekuts.travelagency.dao.subjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class Hotel, includes fields <b>hotelId</b>, <b>name</b>, <b>stars</b>,
 * <b>website</b>, <b>latitude</b>, <b>longitude</b>, <b> features</b>
 * @author Aleh_Rekuts
 */
@Getter
@Setter
@Builder
@ToString
public class Hotel {
	private int hotelId;
	private String name;
	private int stars;
	private String website;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private List<String> features;

	/**
	 * Inner to class Hotel enum Features. It stores the names of features of hotels in the values.
	 */
	public enum Features{
		A("swimming pool"),
		B("all inclusive"),
		C("fresh towel"),
		D("carpets on the walls"),
		E("free wifi"),
		F("close to sea"),
		G("free animators"),
		H("free beer"),
		I("air conditioning"),
		J("children room");
		
		private final String feature;

		Features(String feature) {
			this.feature = feature;
		}

		public String getValue() {
			return feature;
		}
	}
}
