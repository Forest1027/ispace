package com.ispace.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

public class SearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root root;

    public SearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root root) {
        this.predicate = predicate;
        this.builder = builder;
        this.root = root;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    @Override
    public void accept(SearchCriteria param) {
        System.out.println("params:"+param.getKey()+"-"+param.getOperation()+"-"+param.getValue());
        if(param.getKey().equalsIgnoreCase("authorEmail")) {
            predicate = builder.and(predicate, builder.equal(root.get("author").get("email"), param.getValue().toString()));
        }else if(param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
        } else if(param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
        } else if(param.getOperation().equalsIgnoreCase("==")) {
            predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue().toString()));
        } else if(param.getOperation().equalsIgnoreCase(":")) {
            predicate = builder.and(predicate, builder.like(
                    root.get(param.getKey()), "%" + param.getValue() + "%"));
        }
    }
}
