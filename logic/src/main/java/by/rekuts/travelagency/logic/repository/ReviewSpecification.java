package by.rekuts.travelagency.logic.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ReviewSpecification implements Specification{
    private Integer id;
    private Integer userId;
    private Integer tourId;

    public ReviewSpecification(Integer id) {
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
        return predicates;
    }
}
