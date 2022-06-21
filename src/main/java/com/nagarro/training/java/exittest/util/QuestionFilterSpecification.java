package com.nagarro.training.java.exittest.util;

import com.nagarro.training.java.exittest.entity.Question;
import com.nagarro.training.java.exittest.enums.QuestionStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.text.MessageFormat;

public final class QuestionFilterSpecification {


    public static Specification<Question> getSpecificationForSearchText(String searchText) {
        return Specification.where(containsSubject(searchText)).or(containsBody(searchText).or(containsProductName(searchText)));
    }

    public static Specification<Question> getSpecificationForQuestionStatus(QuestionStatus questionStatus) {
        return Specification.where(equalsQuestionStatus("status", questionStatus.name()));
    }

    private static Specification<Question> containsBody(String text) {
        return containsProperty("body", text);
    }

    private static Specification<Question> containsSubject(String text) {
        return containsProperty("subject", text);
    }

    private static Specification<Question> containsProductName(String text) {
        return containsProperty("product.name", text);
    }

    private static Specification<Question> containsProperty(String property, String value) {
        return (Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.like(getPath(root, property).as(String.class), contains(value));
        };
    }

    private static Specification<Question> equalsQuestionStatus(String property, String value) {
        return (Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(getPath(root, property).as(String.class), value);
        };
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    /**
     * This is a generic implementation to get path of the property in case a filter needs to be applied to a child
     * entity of the table.
     *
     * @param root
     * @param key
     * @return
     */
    private static Path<Question> getPath(Root<Question> root, String key) {
        Path<Question> path;
        if (key.contains(".")) {
            String[] split = key.split("\\.");
            path = root.get(split[0]);
            for (int i = 1; i < split.length; i++) {
                path = path.get(split[i]);
            }
        } else {
            path = root.get(key);
        }
        return path;
    }
}
