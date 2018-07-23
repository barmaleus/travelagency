package by.rekuts.travelagency.dao.subjects;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class Hotel, includes fields <b>hotelId</b>, <b>name</b>, <b>stars</b>,
 * <b>website</b>, <b>latitude</b>, <b>longitude</b>, <b> features</b>
 * @author Aleh_Rekuts
 */
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

	/**
	 * Default constructor - creating new object without parameters
	 * @see Hotel#Hotel(int, String, int, String, BigDecimal, BigDecimal, List)
	 */
	public Hotel() {
	}

	/**
	 * Constructor - creating new object
	 * @param hotelId - id of hotel
	 * @param name - official name of hotel
	 * @param stars - stars of hotel. It takes values from 1 to 5
	 * @param website - web address of hotel site. String type of value
	 * @param latitude - latitude of hotel location. BigDecimal type of value.
	 *                    The Equator has a latitude of 0°, the North pole has a latitude of 90° north (written 90° N or +90°),
	 *                    and the South pole has a latitude of -90°
	 * @param longitude - longitude of hotel location. BigDecimal type of value. Longitude is in the range -180 and +180
	 *                     specifying coordinates west and east of the Prime Meridian
	 * @param features - list of features of hotel. String type. Valid features could be chosen from inner enum Features with method like Hotel.Features.A.getValue()
	 * @see Hotel#Hotel()
	 */
	public Hotel(int hotelId, String name, int stars, String website, BigDecimal latitude, BigDecimal longitude, List<String> features) {
		this.hotelId = hotelId;
		this.name = name;
		this.stars = stars;
		this.website = website;
		this.latitude = latitude;
		this.longitude = longitude;
		this.features = features;
	}

	/**
	 * Method helps get the value of field {@link Hotel#hotelId}
	 * @return id of the hotel
	 */
	public int getHotelId() {
		return hotelId;
	}

	/**
	 * Method sets the value to field {@link Hotel#hotelId}
	 * @param hotelId - id of the hotel
	 */
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Method helps get the value of field {@link Hotel#hotelId}
	 * @return id of the hotel
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method sets the value to field {@link Hotel#name}
	 * @param name - name of the hotel
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method helps get the value of field {@link Hotel#stars}
	 * @return count of stars of the hotel
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * Method sets the value to field {@link Hotel#stars}
	 * @param stars - count of stars of the hotel
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}

	/**
	 * Method helps get the value of field {@link Hotel#website}
	 * @return address of website of the hotel
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Method sets the value to field {@link Hotel#website}
	 * @param website - site address of the hotel
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Method helps get the value of field {@link Hotel#latitude}
	 * @return location latitude of the hotel
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * Method sets the value to field {@link Hotel#latitude}
	 * @param latitude - location latitude of the hotel
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * Method helps get the value of field {@link Hotel#longitude}
	 * @return location longitude of the hotel
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * Method sets the value to field {@link Hotel#longitude}
	 * @param longitude - location longitude of the hotel
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * Method helps get the value of field {@link Hotel#features}
	 * @return list of features of the hotel
	 */
	public List<String> getFeatures() {
		return features;
	}

	/**
	 * Method sets the value to field {@link Hotel#features}
	 * @param features - list of features of the hotel
	 */
	public void setFeatures(List<String> features) {
		this.features = features;
	}

	/**
	 * Method dispalys parameters of the hotel
	 * @return string with parameters
	 */
	@Override
	public String toString() {
		return "Hotel: " + name + ", id: " + hotelId + ", stars: " + stars + ", website: " +
				website + ", location: " + latitude + "," + longitude + ", features: " + features;
	}
}
