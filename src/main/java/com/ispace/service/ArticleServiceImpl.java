package com.ispace.service;

import com.ispace.repository.ArticleCategoryRepository;
import com.ispace.repository.ArticleDetailRepository;
import com.ispace.dto.ArticleDTO;
import com.ispace.entity.ArticleCategory;
import com.ispace.entity.ArticleDetail;
import com.ispace.repository.UserInfoRepository;
import com.ispace.repository.custom.CommonCustomRepository;
import com.ispace.search.SearchCriteria;
import com.ispace.utils.EntityDtoConvertUtil;
import com.ispace.utils.JwtUtil;
import com.ispace.entity.UserInfo;
import com.ispace.utils.SearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    private CommonCustomRepository<ArticleDetail> customRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<ArticleDTO> getArticleList(int page, int size, String search) {
        List<SearchCriteria> params = SearchUtil.extractSearchCriteria(search);
        List<ArticleDetail> articleDetails = articleDetailRepository.getArticleBrief(Pageable.ofSize(size).withPage(page), params);
        return articleDetails.stream()
                .map(articleDetail -> {
                    UserInfo author = articleDetail.getAuthor();
                    String authorName = author.getNickName().isEmpty() ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
                    return new ArticleDTO.Builder().withId(articleDetail.getId()).withTitle(articleDetail.getTitle())
                            .withDescription(articleDetail.getDescription()).withContent(articleDetail.getContent())
                            .withArticleCategory(articleDetail.getArticleCategory()).withAuthorId(author.getId())
                            .withAuthorName(authorName)
                            .withCreateTime(articleDetail.getCreateTime()).withUpdateTime(articleDetail.getUpdateTime()).build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleCategory> getArticleCategoryList(int page, int size) {
        return articleCategoryRepository.findByParentIdNotNull(Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Map<String, List<ArticleCategory>> getArticleCategoryListHierarchy() {
        List<ArticleCategory> categories = articleCategoryRepository.findAll();
        Map<String, List<ArticleCategory>> result = new HashMap<>();
        Map<Integer, List<ArticleCategory>> groups = categories.stream().filter(articleCategory -> articleCategory.getParentCategoryId() != null).collect(groupingBy(ArticleCategory::getParentCategoryId));
        categories.stream()
                .filter(articleCategory -> articleCategory.getParentCategoryId() == null)
                .forEach(articleCategory -> result.put(articleCategory.getCategoryName(), groups.get(articleCategory.getId())));
        return result;
    }

    @Override
    public ArticleDTO getArticleById(int id) {
        Optional<ArticleDetail> result = articleDetailRepository.findById(id);
        if (result.isPresent()) {
            ArticleDTO articleDTO = EntityDtoConvertUtil.convertArticleEntityToDTO(result.get());
            articleDTO.setAuthorEmail("");
            return articleDTO;
        } else {
            throw new RuntimeException("The article with id " + id + " is not found");
        }
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO, String idToken) {
        String email = JwtUtil.getCurrentUserEmailFromAuthorization(idToken);
        UserInfo author = userInfoRepository.findByEmail(email);
        articleDTO.setAuthorId(author.getId());
        ArticleDetail articleDetail = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
        articleDetail = articleDetailRepository.saveAndFlush(articleDetail);
        customRepository.refresh(articleDetail);
        return EntityDtoConvertUtil.convertArticleEntityToDTO(articleDetail);
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO articleDTO, String idToken) {
        if (!verifyCurrentUserIsTheAuthor(articleDTO.getId(), idToken)) {
            throw new RuntimeException("Current user is not the author of the article");
        }
        Optional<ArticleDetail> article = articleDetailRepository.findById(articleDTO.getId());
        if (article.isPresent()) {
            ArticleDetail entity = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
            ArticleDetail articleDetail = article.get();
            articleDetail.setTitle(articleDTO.getTitle());
            articleDetail.setDescription(articleDTO.getDescription());
            articleDetail.setContent(articleDTO.getContent());
            articleDetail.setArticleCategory(articleDTO.getArticleCategory());
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

    @Override
    public Integer getArticleCountByAuthor(String email, String idToken) {
        if (!verifyCurrentUserIsTheAuthorByEmail(email, idToken)) {
            throw new RuntimeException("Current user is not the author of the article");
        }
        return articleDetailRepository.getArticleCountByAuthor(email);
    }

    private boolean verifyCurrentUserIsTheAuthor(int id, String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Can't verify user's identity");
        }
        Optional<ArticleDetail> result = articleDetailRepository.findById(id);
        String email = JwtUtil.getCurrentUserEmailFromAuthorization(token);
        if (result.isPresent()) {
            return result.get().getAuthor().getEmail().equals(email);
        } else {
            throw new RuntimeException("The article with id " + id + " is not found");
        }
    }

    private boolean verifyCurrentUserIsTheAuthorByEmail(String email, String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Can't verify user's identity");
        }
        String tokenEmail = JwtUtil.getCurrentUserEmailFromAuthorization(token);
        return email.equals(tokenEmail);
    }
}
