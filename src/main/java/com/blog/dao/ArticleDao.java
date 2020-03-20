package com.blog.dao;


import com.blog.bean.Article;

import java.util.List;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    Article selectLastArticle(Integer id);

    Article selectNextArticle(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int countAllNum();


    List<Article> queryAll();

    List<Article> selectByWord(String word);
}