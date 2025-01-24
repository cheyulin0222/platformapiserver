package com.arplanets.platform.spec;

import com.arplanets.platform.dto.service.PolicyData;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicySpecification {

    public Specification<PolicyData> createSpecification(UserSearchReqSO request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Email 模糊查詢
            if (StringUtils.hasText(request.getEmail())) {
                // root 為 User 的實體
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }

            // equal
//            if (StringUtils.hasText(request.getEmail())) {
//                // root 為 User 的實體
//                predicates.add(criteriaBuilder.equal(root.get("email"), request.getEmail()));
//            }

            // 年齡範圍
            if (request.getAgeStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), request.getAgeStart()));
            }
            if (request.getAgeEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), request.getAgeEnd()));
            }

            // 創建時間範圍
            if (request.getCreatedAtStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), request.getCreatedAtStart()));
            }
            if (request.getCreatedAtEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), request.getCreatedAtEnd()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
