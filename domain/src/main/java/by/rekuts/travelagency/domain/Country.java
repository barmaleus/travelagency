package by.rekuts.travelagency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Class Country with fields <b>id</b> and <b>name</b>
 * @author Aleh_Rekuts
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Entity
@Table
@NamedQueries(value = {
		@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
})
public class Country implements Serializable {
	@Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	private int id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@Version
	private Integer version;
}
