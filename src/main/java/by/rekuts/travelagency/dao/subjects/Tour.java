package by.rekuts.travelagency.dao.subjects;

import java.math.BigDecimal;
import java.time.LocalDate;

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
				'}';
	}

	public Tour() {
	}

	public Tour(int id, String photo, LocalDate date, int duration, String description, BigDecimal cost, String tourType, int hotelId, int countryId) {

		this.id = id;
		this.photo = photo;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.cost = cost;
		this.tourType = tourType;
		this.hotelId = hotelId;
		this.countryId = countryId;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	
}
