package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class TourSpecification implements Specification<Tour> {

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

    public TourSpecification(Integer id, Integer countryId, LocalDate date, Integer duration,
                             BigDecimal minCost, BigDecimal maxCost, Tour.TourType tourType,
                             Integer stars, Integer userId) {
        this.id = id;
        this.countryId = countryId;
        this.date = date;
        this.duration = duration;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.tourType = tourType;
        this.stars = stars;
        this.userId = userId;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            predicates.add(builder.equal(root.get("id"), id));
        }
        if (countryId != null) {
            predicates.add(builder.equal(root.get("country"), countryId));
        }
        if (date != null) {
            predicates.add(builder.equal(root.get("date"), date));
        }
        if (duration != null) {
            predicates.add(builder.equal(root.get("duration"), duration));
        }
        if (minCost != null) {
            predicates.add(builder.ge(root.get("cost"), minCost));
        }
        if (maxCost != null) {
            predicates.add(builder.le(root.get("cost"), maxCost));
        }
        if (tourType != null) {
            predicates.add(builder.equal(root.get("tourType"), tourType));
        }
        if (stars != null) {
            Join<Tour, Hotel> hotelJoin = root.join("hotel");
            predicates.add(builder.equal(hotelJoin.get("stars"), stars));
        }
        if (userId != null) {
            predicates.add(builder.equal(root.join("users"), userId));
        }

        // Combine predicates into a single Predicate
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
