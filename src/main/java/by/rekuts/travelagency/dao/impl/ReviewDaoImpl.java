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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Review review) {
        String sql = "INSERT INTO review (id, date, text, user_id, tour_id) VALUES (?, ?, ?, ?, ?)" ;
        jdbcTemplate.update(sql, new Object[]{
                review.getId(), review.getDate(), review.getText(), review.getUserId(), review.getTourId()
        });
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM review WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public Review getReviewById(int id) {
        String sql = "SELECT id, date, text, user_id, tour_id FROM review WHERE id = ?";
        return (Review)jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Review>(){
            @Override
            public Review mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setDate(rs.getTimestamp("date"));  //TODO review column in db - date to timestamp
                review.setText(rs.getString("text"));
                review.setUserId(rs.getInt("user_id"));
                review.setTourId(rs.getInt("tour_id"));
                return review;
            }
        });
    }

    @Override
    public List<Review> getAllReviews() {
        String sql = "SELECT id, date, text, user_id, tour_id FROM review";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

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
