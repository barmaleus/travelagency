package by.rekuts.travelagency.dao.subjects;

import java.time.LocalDateTime;


/**
 * Class Review, includes fields <b>id</b>, <b>date</b>, <b>text</b>,
 * <b>userId</b>, <b>tourId</b>
 * @author Aleh_Rekuts
 */
public class Review {
	private int id;
	private LocalDateTime date;
	private String text;
	private int userId;
	private int tourId;

	/**
	 * Default constructor - creating new object without parameters
	 * @see Review#Review(int, LocalDateTime, String, int, int)
	 */
	public Review() {
	}

	/**
	 * Constructor - creating new object
	 * @param id - id of the review
	 * @param date - date and time of review publication
	 * @param text - body of review
	 * @param userId - id of user, who wrote the review
	 * @param tourId - id of reviewed tour
	 * @see Review#Review()
	 */
	public Review(int id, LocalDateTime date, String text, int userId, int tourId) {
		this.id = id;
		this.date = date;
		this.text = text;
		this.userId = userId;
		this.tourId = tourId;
	}

	/**
	 * Method helps get the value of field {@link Review#id}
	 * @return id of the review
	 */
	public int getId() {
		return id;
	}


	/**
	 * Method sets the value to field {@link Review#id}
	 * @param id - id of the review
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method helps get the value of field {@link Review#date}
	 * @return date and time of the review publication
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Method sets the value to field {@link Review#date}
	 * @param date - date and time of the review publication
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Method helps get the value of field {@link Review#text}
	 * @return review text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method sets the value to field {@link Review#text}
	 * @param text - review text (body)
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method helps get the value of field {@link Review#userId}
	 * @return id of review author
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Method sets the value to field {@link Review#userId}
	 * @param userId - id of user, who is review author
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Method helps get the value of field {@link Review#tourId}
	 * @return id of reviewed tour
	 */
	public int getTourId() {
		return tourId;
	}

	/**
	 * Method sets the value to field {@link Review#tourId}
	 * @param tourId - id of reviewed tour
	 */
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	/**
	 * Method dispalys parameters of the review
	 * @return string with parameters
	 */
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
