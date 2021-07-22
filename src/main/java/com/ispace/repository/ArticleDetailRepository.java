package com.ispace.repository;

import com.ispace.repository.custom.ArticleCustomRepository;
import com.ispace.entity.ArticleDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Integer>, ArticleCustomRepository {

    @Query("SELECT a.id, a.title, a.description, a.articleCategory, a.author, a.createTime, a.updateTime FROM ArticleDetail a")
    List<ArticleDetail> findByArticleBrief(Pageable pageable);
}
