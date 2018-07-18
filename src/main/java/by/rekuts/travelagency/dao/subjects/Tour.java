package by.rekuts.travelagency.dao.subjects;

import java.time.LocalDate;

public class Tour {
	private int id;
	private String photo;
	private LocalDate date;
	private short duration;
	private String description;
	private int cost;
	private TourType tourType;
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

	public Tour(int id, String photo, LocalDate date, short duration, String description, int cost, TourType tourType, int hotelId, int countryId) {

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
		
		private final String tourType;
		
		private TourType(String tourType) {
			this.tourType = tourType;
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

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public TourType getTourType() {
		return tourType;
	}

	public void setTourType(TourType tourType) {
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
