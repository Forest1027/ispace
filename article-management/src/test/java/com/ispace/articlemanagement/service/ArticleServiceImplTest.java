package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.articlemanagement.repository.ArticleCategoryRepository;
import com.ispace.articlemanagement.repository.ArticleDetailRepository;
import com.ispace.articlemanagement.repository.custom.CommonCustomRepository;
import com.ispace.articlemanagement.utils.EntityDtoConvertUtil;
import com.ispace.shared.entity.UserInfo;
import com.ispace.shared.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    private static String idToken = "eyJraWQiOiJPTllnQldOb0RoLVIyUzFFUWpQNWltTzNTV2JQRi1ubV9DRVBFN3RwQlNVIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiIwMHUxNWRmMjI3bWFIdmFraTVkNyIsIm5hbWUiOiJ0ZXN0MjcgdGVzdCIsImVtYWlsIjoidGVzdDI3QGdtYWlsLnRlc3QuY29tIiwidmVyIjoxLCJpc3MiOiJodHRwczovL2Rldi04MzI1MDM2Mi5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6IjBvYXhlNWU0ZmR6T0U0cXpuNWQ2IiwiaWF0IjoxNjI1NjgzMDA3LCJleHAiOjE2MjU2ODY2MDcsImp0aSI6IklELjhTSFRpa0N2TTNKdmRYaDFDVjZjUS0yZXNudEJTS19va0tzTUp6WmdQYjgiLCJhbXIiOlsicHdkIl0sImlkcCI6IjAwb3Z4MGtkNDBKNDlBVndhNWQ2Iiwibm9uY2UiOiJEd3ZuVFVGNVlvTVRLc29XZFg2RFllTDRqVWxwTEpBY2NzTG81MTNwaWM1TUtrZVBlOG5XT2VmNW5oWlhRaWdPIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidGVzdDI3QGdtYWlsLnRlc3QuY29tIiwiYXV0aF90aW1lIjoxNjI1NjgzMDA0LCJhdF9oYXNoIjoiRUloVXVVNHpDWi01LW9YSllOS0VkUSJ9.TfQqd7g9qH3urVc4Y9dbXxY2-HfASXmzZKBsaGE8i5qHeN6N6YRfEkQtM44R6B9LkQ5uWm-eg729xHJpADSDQUz1JFCxNfTafxeSXxCDhaG14u_dF6wm0PSgO9cyuvCj-H2Jk4ltZc2wN3Fsb4ZNUWN9A5DTzTtLg1exZs4mJ6xeAWqrWgKSBT1aMcc6ZEYVAzSb_WUn31FKzyjwm3GfvPSXLxf-TEuO-pz6eOKdLa3Q_4CDouCP2YhwWOzcnZAc_z5uq_pvtTBHwCO0XiD65iGtjhUfZ1RIxjxlyafE7imXKw6T1hPjYdgwON8TroJeUTIgvd7EBtDcoAlS7zWVIA";
    private static String validEmail = "test27@gmail.test.com";

    @Mock
    private ArticleDetailRepository articleDetailRepository;

    @Mock
    private ArticleCategoryRepository articleCategoryRepository;

    @Mock
    private CommonCustomRepository<ArticleDetail> commonCustomRepository;

    @Mock
    private UserInfoRepository userInfoRepository;

    private ArticleService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ArticleServiceImpl(articleDetailRepository, articleCategoryRepository, commonCustomRepository);
    }

    @Test
    void canGetArticleList() {
        // when
        int page = 0;
        int size = 10;
        underTest.getArticleList(page, size);
        // then
        verify(articleDetailRepository).getArticleBrief(Pageable.ofSize(size).withPage(page));
    }

    @Test
    void canGetArticleCategoryList() {
        // when
        int page = 0;
        int size = 10;
        underTest.getArticleCategoryList(page, size);
        // then
        verify(articleCategoryRepository).findByParentIdNotNull(Pageable.ofSize(size).withPage(page));
    }

    @Test
    void canGetArticleById() {
        // given
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setTitle("test");
        articleDetail.setAuthor(getUserInfo(validEmail));
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.of(articleDetail));
        // when
        // then
        assertDoesNotThrow(() -> underTest.getArticleById(1));
    }

    @Test
    void willThrowWhenNoArticleFound() {
        // given
        int id = 1;
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.getArticleById(id)).isInstanceOf(RuntimeException.class).hasMessageContaining("The article with id " + id);
    }

    @Test
    void canCreateArticle() {
        // given
        ArticleDTO articleDTO = getArticleDTO(validEmail);
        // when
        underTest.createArticle(articleDTO, idToken);
        // then
        ArgumentCaptor<ArticleDetail> articleDetailArgumentCaptor = ArgumentCaptor.forClass(ArticleDetail.class);
        verify(articleDetailRepository).saveAndFlush(articleDetailArgumentCaptor.capture());
    }

    @Test
    void canUpdateArticle() {
        // given
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setAuthor(getUserInfo(validEmail));
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.of(articleDetail));
        ArticleDTO articleDTO = getArticleDTO(validEmail);
        ArticleDetail articleDetail1 = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
        // when
        underTest.updateArticle(articleDTO, idToken);
        // then
        ArgumentCaptor<ArticleDetail> articleDetailArgumentCaptor = ArgumentCaptor.forClass(ArticleDetail.class);
        verify(articleDetailRepository).save(articleDetailArgumentCaptor.capture());
        ArticleDetail captured = articleDetailArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(articleDetail1);
    }

    @Test
    void willThrowWhenUpdateArticleIsNotFound() {
        // given
        ArticleDTO articleDTO = getArticleDTO(validEmail);
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.updateArticle(articleDTO, idToken)).isInstanceOf(RuntimeException.class).hasMessageContaining("The article with id");
        verify(articleDetailRepository, never()).save(any());
    }

    @Test
    void canDeleteArticleById() {
        // given
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setAuthor(getUserInfo(validEmail));
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.of(articleDetail));
        // when
        underTest.deleteArticleById(1, idToken);
        // then
        verify(articleDetailRepository).deleteById(anyInt());
    }

    @Test
    void willThrowWhenIdTokenNull() {
        // then
        assertThatThrownBy(() -> underTest.deleteArticleById(1, null)).isInstanceOf(RuntimeException.class).hasMessageContaining("Can't verify user's identity");
    }

    @Test
    void willThrowWhenIdTokenNotMatch() {
        // given
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setAuthor(getUserInfo("invalid@gmail.com"));
        given(articleDetailRepository.findById(anyInt())).willReturn(Optional.of(articleDetail));
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteArticleById(1, idToken)).isInstanceOf(RuntimeException.class).hasMessageContaining("Current user is not the author of the article");
    }

    private static ArticleDTO getArticleDTO(String email) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("test");
        articleDTO.setAuthorEmail(email);
        return articleDTO;
    }

    private static UserInfo getUserInfo(String email) {
        UserInfo author = new UserInfo();
        author.setEmail(email);
        return author;
    }
}