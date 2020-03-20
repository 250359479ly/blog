package com.blog.service;

import java.util.List;

import com.blog.bean.Article;

public interface ArticleService {

    Article selectById (Integer id);

    Article selectLastArticle (Integer id);

    Article selectNextArticle (Integer id);

    List<Article> queryAll();

    int countAllNum();

    boolean updateArticle(Article article);

    int deleteById(Integer id);

    int selectCount();

    List<Article> selectByWord(String word);

    boolean insert(Article article);
}
