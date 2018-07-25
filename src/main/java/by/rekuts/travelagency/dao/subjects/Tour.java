package by.rekuts.travelagency.dao.subjects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Class Tour, includes fields <b>id</b>, <b>photo</b>, <b>date</b>,
 * <b>duration</b>, <b>description</b>, <b>cost</b>, <b>tourType</b>,
 * <b>hotelId</b>, <b>countryId</b>, <b>users</b>
 * @author Aleh_Rekuts
 */
public class Tour {
	private int id;
	private String photo;
	private LocalDate date;
	private int duration;
	private String description;
	private BigDecimal cost;
	private String tourType;
	private int hotelId;
	private int countryId;
	private List<User> users;

	/**
	 * Method dispalys parameters of the tour
	 * @return string with parameters
	 */
	@Override
	public String toString() {
		return "Tour{" +
				"id=" + id +
				", photo='" + photo + '\'' +
				", date=" + date +
				", duration=" + duration +
				", description='" + description + '\'' +
				", cost=" + cost +
				", tourType=" + tourType +
				", hotelId=" + hotelId +
				", countryId=" + countryId +
				", users=" + users +
				'}';
	}

	/**
	 * Constructor - creating new object via TourBuilder
	 */
	public Tour(TourBuilder tourBuilder) {
		this.id = tourBuilder.id;
		this.photo = tourBuilder.photo;
		this.date = tourBuilder.date;
		this.duration = tourBuilder.duration;
		this.description = tourBuilder.description;
		this.cost = tourBuilder.cost;
		this.tourType = tourBuilder.tourType;
		this.hotelId = tourBuilder.hotelId;
		this.countryId = tourBuilder.countryId;
		this.users = tourBuilder.users;
	}

	/**
	 * Inner to class Tour enum TourType. It stores the names of tour types in the values.
	 */
	public enum TourType{
		A("safari"),
		B("rural"),
		C("mountain"),
		D("ski"),
		E("ecotourism"),
		F("health"),
		G("cruise"),
		H("education"),
		I("adventure"),
		J("cultural");
		
		private final String tourType;
		
		TourType(String tourType) {
			this.tourType = tourType;
		}

		public String getValue() {
			return tourType;
		}
	}

	/**
	 * Method helps get the value of field {@link Tour#id}
	 * @return id of the tour
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method sets the value to field {@link Tour#id}
	 * @param id - id of the tour
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method helps get the value of field {@link Tour#photo}
	 * @return reference to the tour photo in string format
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Method sets the value to field {@link Tour#photo}
	 * @param photo - reference to the tour photo in string format
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Method helps get the value of field {@link Tour#date}
	 * @return date of tour beginning
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Method sets the value to field {@link Tour#date}
	 * @param date - date of tour beginning
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Method helps get the value of field {@link Tour#duration}
	 * @return tour duration in days
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Method sets the value to field {@link Tour#duration}
	 * @param duration - tour duration in days
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Method helps get the value of field {@link Tour#description}
	 * @return description of tour
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method sets the value to field {@link Tour#description}
	 * @param description - description of tour
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method helps get the value of field {@link Tour#cost}
	 * @return cost of tour
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Method sets the value to field {@link Tour#cost}
	 * @param cost - cost of tour
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * Method helps get the value of field {@link Tour#tourType}
	 * @return type of tour
	 */
	public String getTourType() {
		return tourType;
	}

	/**
	 * Method sets the value to field {@link Tour#tourType}
	 * @param tourType - type of tour. Valid values of types are in inner enum TourType
	 */
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	/**
	 * Method helps get the value of field {@link Tour#hotelId}
	 * @return id of hotel, which is available for this tour
	 */
	public int getHotelId() {
		return hotelId;
	}

	/**
	 * Method sets the value to field {@link Tour#hotelId}
	 * @param hotelId - id of hotel, which is available for this tour
	 */
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Method helps get the value of field {@link Tour#countryId}
	 * @return id of voyage country
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * Method sets the value to field {@link Tour#countryId}
	 * @param countryId - id of country, where will be tour
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * Method helps get the value of field {@link Tour#users}
	 * @return list of users id, who are registered for this tour
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Method sets the value to field {@link Tour#users}
	 * @param users - list of users id, who are registered for this tour
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Inner static class-creator of Tour Class. Implements Builder Pattern.
	 */
	public static class TourBuilder {
		private int id;
		private String photo;
		private LocalDate date;
		private int duration;
		private String description;
		private BigDecimal cost;
		private String tourType;
		private int hotelId;
		private int countryId;
		private List<User> users;

		/**
		 * Constructor which creates new object of TourBuilder with required parameter <b>id</b>
		 * @param id - id of the tour
		 */
		public TourBuilder(int id) {
			this.id = id;
		}

		/**
		 * Method defines parameter <b>photo</b> of the TourBuilder object
		 * @param photo - reference to the tour photo in string format
		 * @return object of TourBuilder class with defined parameter <b>photo</b>
		 */
		public TourBuilder withOptionalPhoto(String photo) {
			this.photo = photo;
			return this;
		}

		/**
		 * Method defines parameter <b>date</b> of the TourBuilder object
		 * @param date - date of tour beginning
		 * @return object of TourBuilder class with defined parameter <b>date</b>
		 */
		public TourBuilder withOptionalDate(LocalDate date) {
			this.date = date;
			return this;
		}

		/**
		 * Method defines parameter <b>duration</b> of the TourBuilder object
		 * @param duration - tour duration in days
		 * @return object of TourBuilder class with defined parameter <b>duration</b>
		 */
		public TourBuilder withOptionalDuration(int duration) {
			this.duration = duration;
			return this;
		}

		/**
		 * Method defines parameter <b>description</b> of the TourBuilder object
		 * @param description - description of the tour
		 * @return object of TourBuilder class with defined parameter <b>description</b>
		 */
		public TourBuilder withOptionalDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Method defines parameter <b>cost</b> of the TourBuilder object
		 * @param cost - cost of the tour
		 * @return object of TourBuilder class with defined parameter <b>cost</b>
		 */
		public TourBuilder withOptionalCost(BigDecimal cost) {
			this.cost = cost;
			return this;
		}

		/**
		 * Method defines parameter <b>tourType</b> of the TourBuilder object
		 * @param tourType - type of tour. Valid values of types are located in inner enum TourType
		 * @return object of TourBuilder class with defined parameter <b>tourType</b>
		 */
		public TourBuilder withOptionalTourType(String tourType) {
			this.tourType = tourType;
			return this;
		}

		/**
		 * Method defines parameter <b>hotelId</b> of the TourBuilder object
		 * @param hotelId - id of hotel, which is available for this tour
		 * @return object of TourBuilder class with defined parameter <b>hotelId</b>
		 */
		public TourBuilder withOptionalHotelId(int hotelId) {
			this.hotelId = hotelId;
			return this;
		}

		/**
		 * Method defines parameter <b>countryId</b> of the TourBuilder object
		 * @param countryId - id of country, where will be tour
		 * @return object of TourBuilder class with defined parameter <b>countryId</b>
		 */
		public TourBuilder withOptionalCountryId(int countryId) {
			this.countryId = countryId;
			return this;
		}

		/**
		 * Method defines parameter <b>users</b> of the TourBuilder object
		 * @param users - list of users id, who are registered for this tour
		 * @return object of TourBuilder class with defined parameter <b>users</b>
		 */
		public TourBuilder withOptionalUsersList(List<User> users) {
			this.users = users;
			return this;
		}

		/**
		 * Method creates Tour type object from the TourBuilder object
		 * @return object of Tour class
		 */
		public Tour buildTour() {
			return new Tour(this);
		}
	}
}
