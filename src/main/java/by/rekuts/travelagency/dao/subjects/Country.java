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
	 * Constructor - creating new object
	 * @see Country#Country(int, String)
	 */
	public Country() {
	}

	/**
	 * Constructor - creating new object
	 * @param id - id of country. Usually it takes from database
	 * @param name - official name of country
	 * @see Country#Country()
	 */
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Method helps get the value of field {@link Country#id}
	 * @return id of country
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method sets the value to field {@link Country#id}
	 * @param id - id of country
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method helps get the value of field {@link Country#name}
	 * @return name of country
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method sets the value to field {@link Country#id}
	 * @param name - official name of country
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method dispalys name and id of country
	 * @return string of name and id
	 */
	@Override
	public String toString() {
		return "Country: " + name + ", it's id: " + id;
	}
}
