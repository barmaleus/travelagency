package by.rekuts.travelagency.domain;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Class Hotel, includes fields <b>hotelId</b>, <b>name</b>, <b>stars</b>,
 * <b>website</b>, <b>latitude</b>, <b>longitude</b>, <b> features</b>
 * @author Aleh_Rekuts
 */
@Data @NoArgsConstructor
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Entity
@Table
@TypeDef(
        name = "features",
        typeClass = HotelEnumArrayType.class
)
public class Hotel implements Serializable {
	@Id
	@SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	private int id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@Min(1) @Max(5)
	private int stars;

	private String website;

	@Min(-90) @Max(90)
	private BigDecimal latitude;

	@Min(-180) @Max(180)
	private BigDecimal longitude;

    @Type(type="features")
	private List<String> features;

	@Version
	private Integer version;

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
