package by.rekuts.travelagency.dao.subjects;

import java.util.List;

public class Hotel {
	int hotelId;
	String name;
	short stars;
	String website;
	double latitude;
	double longitude;
	List<Features> features;
	
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
		
		private Features(String feature) {
			this.feature = feature;
		}
	}
	
	public Hotel() {
		
	}
	
	public Hotel(int hotelId, String name, short stars, String website, double latitude, double longitude, List<Features> features) {
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

	public short getStars() {
		return stars;
	}

	public void setStars(short stars) {
		this.stars = stars;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<Features> getFeatures() {
		return features;
	}

	public void setFeatures(List<Features> features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		return "Hotel: " + name + ", id: " + hotelId + ", stars: " + stars + ", website: " +
				website + ", location: " + latitude + "," + longitude + ", features: " + features;
	}
}
