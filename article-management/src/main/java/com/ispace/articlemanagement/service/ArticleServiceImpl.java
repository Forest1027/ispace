package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.repository.ArticleCategoryRepository;
import com.ispace.articlemanagement.repository.ArticleDetailRepository;
import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleCategory;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.articlemanagement.utils.EntityDtoConvertUtil;
import com.ispace.articlemanagement.utils.JwtUtil;
import com.ispace.shared.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public List<ArticleDTO> getArticleList(int page, int size) {
        List<ArticleDetail> articleDetails = articleDetailRepository.getArticleBrief(Pageable.ofSize(size).withPage(page));
        System.out.println(articleDetails.size());
        return articleDetails.stream()
                .map(articleDetail -> {
                    UserInfo author = articleDetail.getAuthor();
                    String authorName = author.getNickName().isEmpty() ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
                    return new ArticleDTO(
                            articleDetail.getId(),
                            articleDetail.getTitle(),
                            articleDetail.getDescription(),
                            articleDetail.getContent(),
                            articleDetail.getArticleCategory(),
                            author.getId(),
                            authorName,
                            author.getEmail(),
                            articleDetail.getCreateTime(),
                            articleDetail.getUpdateTime()
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleCategory> getArticleCategoryList(int page, int size) {
        return articleCategoryRepository.findByParentIdNotNull(Pageable.ofSize(size).withPage(page));
    }

    @Override
    public ArticleDTO getArticleById(int id) {
        Optional<ArticleDetail> result = articleDetailRepository.findById(id);
        if (result.isPresent()) {
            return EntityDtoConvertUtil.convertArticleEntityToDTO(result.get());
        } else {
            throw new RuntimeException("The article with id " + id + " is not found");
        }
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO, String idToken) {
        String email = JwtUtil.getCurrentUserPayload(idToken).get("email").toString();
        articleDTO.setAuthorEmail(email);
        ArticleDetail articleDetail = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
        ArticleDetail detail = articleDetailRepository.save(articleDetail);
        return EntityDtoConvertUtil.convertArticleEntityToDTO(detail);
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO articleDTO, String idToken) {
        if (!verifyCurrentUserIsTheAuthor(articleDTO.getId(), idToken)) {
            throw new RuntimeException("Current user is not the author of the article");
        }
        Optional<ArticleDetail> article = articleDetailRepository.findById(articleDTO.getId());
        if (article.isPresent()) {
            ArticleDetail entity = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
            articleDetailRepository.save(entity);
        } else {
            throw new RuntimeException("The article with id " + articleDTO.getId() + " is not found");
        }
        return articleDTO;
    }

    @Override
    public String deleteArticleById(int id, String idToken) {
        if (!verifyCurrentUserIsTheAuthor(id, idToken)) {
            throw new RuntimeException("Current user is not the author of the article");
        }
        articleDetailRepository.deleteById(id);
        return "deleted article " + id;
    }

    private boolean verifyCurrentUserIsTheAuthor(int id, String token) {
        if (token.isEmpty()) {
            throw new RuntimeException("Can't verify user's identity");
        }
        Optional<ArticleDetail> result = articleDetailRepository.findById(id);
        String email = JwtUtil.getCurrentUserPayload(token).get("email").toString();
        if (result.isPresent()) {
            return result.get().getAuthor().getEmail().equals(email);
        } else {
            throw new RuntimeException("The article with id " + id + " is not found");
        }
    }
}
