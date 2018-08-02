package by.rekuts.travelagency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Entity
@Table(name = "country")
@NamedQueries(value = {
		@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
})
public class Country implements Serializable {
	@Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
	@Column(name="id", nullable = false)
	private int id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
}
