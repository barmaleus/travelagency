package by.rekuts.travelagency.dao.subjects;

import java.sql.Timestamp;

public class Review {
	private int id;
	private Timestamp date;
	private String text;
	private int userId;
	private int tourId;

	public Review() {

	}

	public Review(int id, Timestamp date, String text, int userId, int tourId) {
		this.id = id;
		this.date = date;
		this.text = text;
		this.userId = userId;
		this.tourId = tourId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTourId() {
		return tourId;
	}
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	@Override
	public String toString() {
		return "Review{" +
				"id=" + id +
				", date=" + date +
				", text='" + text + '\'' +
				", userId=" + userId +
				", tourId=" + tourId +
				'}';
	}
}
