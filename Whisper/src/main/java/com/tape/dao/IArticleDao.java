package com.tape.dao;

import com.tape.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 用户的持久层接口
 */
@Mapper
public interface IArticleDao {

    /**
     * 获得所有的文章
     * @return 文章列表
     */
    List<Article> getArticleByUserId(Integer userid);

    /**
     * 文章归档
     * @return
     */
    List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(@Param(value = "status") Integer status);

    /**
     * 添加文章
     *
     * @param article 文章
     * @return 文章
     */
    Integer insert(Article article);

    /**
     * 根据ID删除
     *
     * @param articleId 文章ID
     * @return 影响函数
     */
    Integer deleteById(Integer articleId);

    /**
     * 获取当前单条文章的信息
     * @return
     */
    Article getArticleById(Integer id) ;
}
