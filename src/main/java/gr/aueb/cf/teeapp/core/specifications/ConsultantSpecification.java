package gr.aueb.cf.teeapp.core.specifications;


import gr.aueb.cf.teeapp.model.Consultant;
import gr.aueb.cf.teeapp.model.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ConsultantSpecification {

    private ConsultantSpecification() {

    }

    public static Specification<Consultant> consultantUserVatIs(String vat) {
        return ((root, query, criteriaBuilder) -> {
            if (vat == null || vat.isBlank())
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            Join<Consultant, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("vat"), vat);
        });
    }

    public static Specification<Consultant> consultantUserIsActive(Boolean isActive) {
        return (root, query, builder) -> {
            if (isActive == null) {
                return builder.isTrue(builder.literal(true));
            }

            Join<Consultant, User> user = root.join("user");

            return builder.equal(user.get("isActive"), isActive);
        };
    }

    public static Specification<Consultant> consultantStringFieldLike(String field, String value) {
        return (root, query, builder) -> {
            if (value == null || value.trim().isEmpty()) return builder.isTrue(builder.literal(true));
            return builder.like(builder.upper(root.get(field)), "%" + value.toUpperCase() + "%");
        };
    }
}


