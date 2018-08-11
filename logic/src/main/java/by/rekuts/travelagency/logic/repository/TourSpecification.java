package by.rekuts.travelagency.logic.repository;

import by.rekuts.travelagency.logic.domain.Hotel;
import by.rekuts.travelagency.logic.domain.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class TourSpecification implements Specification {
    private Integer id;
    private Integer countryId;
    private LocalDate date;
    private Integer duration;
    private BigDecimal minCost;
    private BigDecimal maxCost;
    private Tour.TourType tourType;
    private Integer stars;
    private Integer userId;

    public TourSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public List<Predicate> getPredicates(Root root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(
                    builder.equal(root.get("id"), id)
            );
        }
        if (countryId != null) {
            predicates.add(
                    builder.equal(root.get("country"), countryId)
            );
        }
        if (date != null) {
            predicates.add(
                    builder.equal(root.get("date"), date)
            );
        }
        if (duration != null) {
            predicates.add(
                    builder.equal(root.get("duration"), duration)
            );
        }
        if (minCost != null) {
            predicates.add(
                    builder.ge(root.get("cost"), minCost)
            );
        }
        if (maxCost != null) {
            predicates.add(
                    builder.le(root.get("cost"), maxCost)
            );
        }
        if(tourType != null) {
            predicates.add(
                    builder.equal(root.get("tourType"), tourType)
            );
        }
        if (stars != null) {
            Join<Tour, Hotel> hotelJoin = root.join("hotel");
            predicates.add(
                    builder.equal(hotelJoin.get("stars"), stars)
            );
        }
        if (userId != null) {
            predicates.add(
                    builder.equal(root.join("users"), userId)
            );
        }
        return predicates;
    }
}
