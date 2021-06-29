package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.dao.ArticleDetailRepository;
import com.ispace.articlemanagement.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Override
    public List<ArticleDTO> getArticleList() {
        return null;
    }
}
