package dev.kisanhelp.project_kh.specification;

import org.springframework.data.jpa.domain.Specification;

import dev.kisanhelp.project_kh.entity.Crop;
import dev.kisanhelp.project_kh.util.SoilType;
import jakarta.persistence.criteria.JoinType;

public class CropSpecification {

    public static Specification<Crop> hasSoilType(SoilType soilType) {
        return (root, query, cb) -> {
            if (soilType == null)
                return null;

            query.distinct(true);
            var join = root.join("soilSuitabilities", JoinType.INNER);

            return cb.and(
                    cb.equal(join.get("soilType"), soilType),
                    cb.isTrue(join.get("isSuitable")));
        };
    }

    public static Specification<Crop> hasSeason(String season) {
        return (root, query, cb) -> {
            if (season == null)
                return null;

            query.distinct(true);
            var join = root.join("seasons", JoinType.LEFT);
            return cb.equal(join.get("name"), season);
        };
    }

    public static Specification<Crop> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null)
                return null;

            return cb.equal(root.get("category"), category);
        };
    }
}
