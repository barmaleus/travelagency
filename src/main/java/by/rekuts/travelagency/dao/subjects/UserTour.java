package by.rekuts.travelagency.dao.subjects;

public class UserTour {
	int userId;
	int tourId;

	@Override
	public String toString() {
		return "UserTour{" +
				"user_id=" + userId +
				", tour_id=" + tourId +
				'}';
	}

	public UserTour() {
	}

	public UserTour(int userId, int tourId) {

		this.userId = userId;
		this.tourId = tourId;
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
}
