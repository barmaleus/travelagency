package by.rekuts.travelagency.dao.subjects;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Class Hotel, includes fields <b>hotelId</b>, <b>name</b>, <b>stars</b>,
 * <b>website</b>, <b>latitude</b>, <b>longitude</b>, <b> features</b>
 * @author Aleh_Rekuts
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "hotel")
@TypeDefs({@TypeDef(
        name = "features",
        typeClass = EnumArrayType.class
)})
public class Hotel {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)     //todo to tune
	@Column(name="id", nullable = false)
	private int hotelId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "stars")
	private int stars;

	@Column(name = "website")
	private String website;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "longitude")
	private BigDecimal longitude;

	@Column(name = "features")
    @Type(type="features")
	private List<String> features;  //todo

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
