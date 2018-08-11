package by.rekuts.travelagency.logic.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface Specification<T> {
    List<Predicate> getPredicates(Root<T> root, CriteriaBuilder builder);
}
