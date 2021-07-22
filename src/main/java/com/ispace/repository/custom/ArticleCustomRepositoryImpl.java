package com.ispace.repository.custom;

import com.ispace.entity.ArticleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArticleDetail> getArticleBrief(Pageable pageable) {
        Query query = entityManager.createQuery("SELECT new com.ispace.entity.ArticleDetail(a.id, a.title, a.description, a.articleCategory, a.author, a.createTime, a.updateTime) FROM ArticleDetail a");
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
}
