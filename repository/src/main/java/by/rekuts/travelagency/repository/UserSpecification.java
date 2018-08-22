package by.rekuts.travelagency.repository;

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
public class UserSpecification implements Specification {
    Integer id;
    String login;

    public UserSpecification(Integer id) {
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
        if (login != null) {
            predicates.add(
                    builder.equal(root.get("login"), login)
            );
        }
        return predicates;
    }
}
