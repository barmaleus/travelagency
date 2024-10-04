package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSpecification implements Specification<Review> {
    private Integer id;
    private Integer userId;
    private Integer tourId;

    public ReviewSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(
                    builder.equal(root.get("id"), id)
            );
        }
        if (userId != null) {
            predicates.add(
                    builder.equal(root.get("user"), userId)
            );
        }
        if (tourId != null) {
            predicates.add(
                    builder.equal(root.get("tour"), tourId)
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
