package by.rekuts.travelagency.dao.subjects;

/**
 * Class Country with fields <b>id</b> and <b>name</b>
 * @author Aleh_Rekuts
 */

public class Country {

	/** Field id*/
	private int id;

	/** Field name*/
	private String name;

	/**
	 * Default constructor - creating new object without parameters
	 * @see Country#Country(int, String)
	 */
	public Country() {
	}

	/**
	 * Constructor - creating new object
	 * @param id - id of the country
	 * @param name - official name of the country
	 * @see Country#Country()
	 */
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Method helps get the value of field {@link Country#id}
	 * @return id of the country
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method sets the value to field {@link Country#id}
	 * @param id - id of the country
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method helps get the value of field {@link Country#name}
	 * @return name of the country
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method sets the value to field {@link Country#id}
	 * @param name - official name of the country
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method dispalys name and id of the country
	 * @return string of name and id
	 */
	@Override
	public String toString() {
		return "Country: " + name + ", it's id: " + id;
	}
}
