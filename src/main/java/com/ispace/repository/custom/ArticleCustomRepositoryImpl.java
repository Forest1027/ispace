package com.ispace.repository.custom;

import com.ispace.entity.ArticleCategory;
import com.ispace.entity.ArticleDetail;
import com.ispace.entity.UserInfo;
import com.ispace.search.SearchCriteria;
import com.ispace.search.SearchQueryCriteriaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArticleDetail> getArticleBrief(Pageable pageable, List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();
        Root root = query.from(ArticleDetail.class);
        Predicate predicate = builder.conjunction();
        SearchQueryCriteriaConsumer searchConsumer = new SearchQueryCriteriaConsumer(predicate, builder, root);
        params.stream().forEach(searchConsumer);
        query.where(searchConsumer.getPredicate());
        query.multiselect(root.get("id"), root.get("title"), root.get("description"), root.get("articleCategory"), root.get("author"), root.get("createTime"),root.get("updateTime"));
        TypedQuery<Tuple> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        return typedQuery.getResultList().stream().map(item -> new ArticleDetail.Builder().withId((int)item.get(0)).withTitle(item.get(1).toString())
                .withDescription(item.get(2).toString()).withArticleCategory((ArticleCategory) item.get(3))
                .withAuthor((UserInfo) item.get(4)).withCreateTime((Date)item.get(5)).withUpdateTime((Date)item.get(6)).build()).collect(Collectors.toList());
    }
}
