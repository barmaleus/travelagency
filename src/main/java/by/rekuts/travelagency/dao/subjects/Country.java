package by.rekuts.travelagency.dao.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
//	@GeneratedValue(strategy = GenerationType.IDENTITY)     //todo to tune
	@Column(name="id", nullable = false)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;
}
