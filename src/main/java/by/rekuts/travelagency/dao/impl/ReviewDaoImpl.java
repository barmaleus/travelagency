package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.dao.subjects.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    private final static String INSERT_REVIEW_QUERY = "INSERT INTO review (id, date, text, user_id, tour_id) VALUES (?, ?, ?, ?, ?)" ;
    private final static String DELETE_REVIEW_QUERY = "DELETE FROM 'review' WHERE id = ?";
    private final static String GET_REVIEW_BY_ID_QUERY = "SELECT id, date, text, user_id, tour_id FROM review WHERE id = ?";
    private final static String GET_ALL_REVIEWS_QUERY = "SELECT id, date, text, user_id, tour_id FROM review";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Review review) {
        jdbcTemplate.update(INSERT_REVIEW_QUERY, new Object[]{
                review.getId(), review.getDate(), review.getText(), review.getUserId(), review.getTourId()
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_REVIEW_QUERY, new Object[]{id});
    }

    @Override
    public Review getReviewById(int id) {
        return (Review)jdbcTemplate.queryForObject(GET_REVIEW_BY_ID_QUERY, new Object[]{id}, new RowMapper<Review>(){
            @Override
            public Review mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setDate(rs.getTimestamp("date"));
                review.setText(rs.getString("text"));
                review.setUserId(rs.getInt("user_id"));
                review.setTourId(rs.getInt("tour_id"));
                return review;
            }
        });
    }

    @Override
    public List<Review> getAllReviews() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_REVIEWS_QUERY);

        List<Review> result = new ArrayList<Review>();
        for(Map<String, Object> row:rows){
            Review review = new Review();
            review.setId((Integer)row.get("id"));
            review.setDate((Timestamp) row.get("date"));
            review.setText((String)row.get("text"));
            review.setUserId((Integer)row.get("user_id"));
            review.setTourId((Integer)row.get("tour_id"));
            result.add(review);
        }
        return result;
    }
}
