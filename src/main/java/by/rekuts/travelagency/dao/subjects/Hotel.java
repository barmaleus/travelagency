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
	 * Constructor - creating new object via HotelBuilder
	 */
	public Hotel(HotelBuilder hotelBuilder) {
		this.hotelId = hotelBuilder.hotelId;
		this.name = hotelBuilder.name;
		this.stars = hotelBuilder.stars;
		this.website = hotelBuilder.website;
		this.latitude = hotelBuilder.latitude;
		this.longitude = hotelBuilder.longitude;
		this.features = hotelBuilder.features;
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

	/**
	 * Inner static class-creator of Hotel Class. Implements Builder Pattern.
	 */
	public static class HotelBuilder {
		private int hotelId;
		private String name;
		private int stars;
		private String website;
		private BigDecimal latitude;
		private BigDecimal longitude;
		private List<String> features;

		/**
		 * Constructor which creates new object of HotelBuilder with required parameters <b>hotelId</b> and <b>name</b>
		 * @param hotelId - id of the hotel
		 * @param name - official name of the hotel
		 */
		public HotelBuilder(int hotelId, String name) {
			this.hotelId = hotelId;
			this.name = name;
		}

		/**
		 * Method defines parameter <b>stars</b> of the HotelBuilder object
		 * @param stars - stars of hotel. It takes values from 1 to 5
		 * @return object of HotelBuilder class with defined parameter <b>stars</b>
		 */
		public HotelBuilder withOptionalStars(int stars) {
			this.stars = stars;
			return this;
		}

		/**
		 * Method defines parameter <b>website</b> of the HotelBuilder object
		 * @param website - web address of hotel site. String type of value
		 * @return object of HotelBuilder class with defined parameter <b>website</b>
		 */
		public HotelBuilder withOptionalWebsite(String website) {
			this.website = website;
			return this;
		}

		/**
		 * Method defines parameters <b>latitude</b> and <b>longitude</b> of the HotelBuilder object
		 * @param latitude - latitude of hotel location. BigDecimal type of value.
		 *                       The Equator has a latitude of 0°, the North pole has a latitude of 90° north (written 90° N or +90°),
		 *                       and the South pole has a latitude of -90°
		 * @param longitude - longitude of hotel location. BigDecimal type of value. Longitude is in the range -180 and +180
		 * 	                     specifying coordinates west and east of the Prime Meridian
		 * @return object of HotelBuilder class with defined parameters <b>latitude</b> and <b>longitude</b>
		 */
		public HotelBuilder withOptionalLocation(BigDecimal latitude, BigDecimal longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
			return this;
		}

		/**
		 * Method defines parameter <b>features</b> of the HotelBuilder object
		 * @param features - list of features of hotel. String type. Valid features could be chosen from inner enum Features with method like Hotel.Features.A.getValue()
		 * @return object of HotelBuilder class with defined parameter <b>features</b>
		 */
		public HotelBuilder withOptionalFeatures(List<String> features) {
			this.features = features;
			return this;
		}

		/**
		 * Method creates Hotel type object from the HotelBuilder object
		 * @return object of Hotel class
		 */
		public Hotel buildHotel() {
			return new Hotel(this);
		}
	}

}
