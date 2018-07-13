package by.rekuts.travelagency.dao.subjects;

import java.time.LocalDateTime;

public class Review {
	int id;
	LocalDateTime date;
	String text;
	int userId;
	int tourId;

	public Review() {

	}

	public Review(int id, LocalDateTime date, String text, int userId, int tourId) {
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
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
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
