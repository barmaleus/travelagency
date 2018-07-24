package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.dao.subjects.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    private static final String INSERT_REVIEW_QUERY = "INSERT INTO review (id, date, text, user_id, tour_id) VALUES (?, ?, ?, ?, ?)" ;
    private static final String DELETE_REVIEW_QUERY = "DELETE FROM review WHERE id = ?";
    private static final String GET_REVIEW_BY_ID_QUERY = "SELECT id, date, text, user_id, tour_id FROM review WHERE id = ?";
    private static final String GET_ALL_REVIEWS_QUERY = "SELECT id, date, text, user_id, tour_id FROM review";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Review review) {
        jdbcTemplate.update(INSERT_REVIEW_QUERY, review.getId(), review.getDate(), review.getText(), review.getUserId(), review.getTourId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_REVIEW_QUERY, id);
    }

    @Override
    public Review getReviewById(int id) {
        return jdbcTemplate.queryForObject(GET_REVIEW_BY_ID_QUERY, new Object[]{id}, (rs, rwNumber) -> {
            Review review = new Review();
            review.setId(rs.getInt("id"));
            review.setDate(rs.getTimestamp("date").toLocalDateTime());
            review.setText(rs.getString("text"));
            review.setUserId(rs.getInt("user_id"));
            review.setTourId(rs.getInt("tour_id"));
            return review;
        });
    }

    @Override
    public List<Review> getAllReviews() {
        return jdbcTemplate.query(
                GET_ALL_REVIEWS_QUERY,
                (resultSet, i) -> {
                    Review review = new Review();
                    review.setId(resultSet.getInt(1));
                    review.setDate(resultSet.getTimestamp(2).toLocalDateTime());
                    review.setText(resultSet.getString(3));
                    review.setUserId(resultSet.getInt(4));
                    review.setTourId(resultSet.getInt(5));
                    return review;
                }
        );
    }
}
