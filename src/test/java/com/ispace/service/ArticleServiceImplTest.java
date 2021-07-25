package com.ispace.service;

import com.ispace.dto.ArticleDTO;
import com.ispace.entity.ArticleDetail;
import com.ispace.repository.ArticleCategoryRepository;
import com.ispace.repository.ArticleDetailRepository;
import com.ispace.repository.custom.CommonCustomRepository;
import com.ispace.search.SearchCriteria;
import com.ispace.utils.EntityDtoConvertUtil;
import com.ispace.entity.UserInfo;
import com.ispace.repository.UserInfoRepository;
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

    private String idToken = "Bearer eyJraWQiOiJPTllnQldOb0RoLVIyUzFFUWpQNWltTzNTV2JQRi1ubV9DRVBFN3RwQlNVIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlpRQVo0N3d1SjNuLU5xX0FMWkVuRnJpeEVxeThwTDNDbF9tVWdEblExQ1UiLCJpc3MiOiJodHRwczovL2Rldi04MzI1MDM2Mi5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2MjU1OTcxNTMsImV4cCI6MTYyNTYwMDc1MywiY2lkIjoiMG9heGU1ZTRmZHpPRTRxem41ZDYiLCJ1aWQiOiIwMHUxNWRmMjI3bWFIdmFraTVkNyIsInNjcCI6WyJwcm9maWxlIiwib3BlbmlkIiwiZW1haWwiXSwic3ViIjoidGVzdDI3QGdtYWlsLnRlc3QuY29tIn0.DDQ72J01klPybwD0Mdb1l2ij1B9uEHbkDEYnbNYFagsCFGLNlCZ67sE0ADHFDtJfE1kOyszDn7sH5_w8pQTPKIalRpRfNFU-74o82btmmqK9LrXFK5Qkt9_uYZAiqumNEqZxAbLuSOFG5gArfrUbzK_6nn4I-ORrt40ThNPGTkXcqihUhp3bqZHx4eXnAvCr7YaLYVXXbzh-G7u4z6PCWOH66iDoc_HyEhW14aLdNIMa2V8JwrUf-avmLkQluzcBAVW0UZxIQTYigiylnQ9LNRmcUcrqVIKIQWE5tzRxOMXPA5VQrHnttD31ec39zIq_Vt3OpByqbnrT16yFD-oenw";
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
        int page = 1;
        int size = 10;
        String search = "authorEmail=="+validEmail;
        underTest.getArticleList(page, size, search);
        // then
        ArgumentCaptor<List<SearchCriteria>> paramArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(articleDetailRepository).getArticleBrief(pageableArgumentCaptor.capture(), paramArgumentCaptor.capture());
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
        ArticleDetail articleDetail = new ArticleDetail.Builder().withTitle("test").withAuthor(getUserInfo(validEmail)).build();
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
        return new ArticleDTO.Builder().withTitle("test").withAuthorEmail(email).build();
    }

    private static UserInfo getUserInfo(String email) {
        UserInfo author = new UserInfo();
        author.setEmail(email);
        return author;
    }
}