package by.rekuts.travelagency.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Class Tour, includes fields <b>id</b>, <b>photo</b>, <b>date</b>,
 * <b>duration</b>, <b>description</b>, <b>cost</b>, <b>tourType</b>,
 * <b>hotelId</b>, <b>countryId</b>, <b>users</b>
 * @author Aleh_Rekuts
 */
@Data @NoArgsConstructor
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Entity
@Table(name = "tour")
@TypeDef(
        name = "tour_enum",
        typeClass = TourEnumType.class
)
public class Tour implements Serializable {
    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
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

    @PositiveOrZero
    @Column(name = "cost")
	private BigDecimal cost;

    @Column(name = "tour_type")
    @Type(type = "tour_enum")
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToMany(mappedBy = "tours", cascade = CascadeType.ALL)
	private List<User> users;

    @Version
    private Integer version;

	/**
	 * Inner to class Tour enum TourType. It stores the names of tour types in the values.
	 */
	public enum TourType {
	    safari,
        rural,
        mountain,
        ski,
        ecotourism,
        health,
        cruise,
        education,
        adventure,
        cultural
	}
}
