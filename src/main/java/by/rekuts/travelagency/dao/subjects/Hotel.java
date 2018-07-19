package by.rekuts.travelagency.dao.subjects;

import java.math.BigDecimal;
import java.sql.Array;

public class Hotel {
	private int hotelId;
	private String name;
	private int stars;
	private String website;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Array features;
	
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
	
	public Hotel() {
	}
	
	public Hotel(int hotelId, String name, int stars, String website, BigDecimal latitude, BigDecimal longitude, Array features) {
		this.hotelId = hotelId;
		this.name = name;
		this.stars = stars;
		this.website = website;
		this.latitude = latitude;
		this.longitude = longitude;
		this.features = features;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Array getFeatures() {
		return features;
	}

	public void setFeatures(Array features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		return "Hotel: " + name + ", id: " + hotelId + ", stars: " + stars + ", website: " +
				website + ", location: " + latitude + "," + longitude + ", features: " + features;
	}
}
