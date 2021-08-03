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

    @Query("select count(a) from ArticleDetail a where a.author.email = ?1")
    Integer getArticleCountByAuthor(String email);
}
