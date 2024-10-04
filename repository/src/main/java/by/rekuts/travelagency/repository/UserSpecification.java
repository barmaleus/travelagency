package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.User;
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
public class UserSpecification implements Specification<User> {
    Integer id;
    String login;

    public UserSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
