package com.tape.dao;

import com.tape.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 用户的持久层接口
 */
@Mapper
public interface ICommentDao {
    /**
     * 统计评论数
     *
     * @return 数量
     */
    Integer countComment();

    /**
     * 获得某个文章的所有评论
     * @return 文章列表
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 根据ID删除
     *
     * @param commentId 评论ID
     * @return 影响行数
     */
    int deleteById(Integer commentId);

    /**
     * 添加评论
     * @return 文章
     */
    Integer insert(Comment comment);

    /**
     * 根据评论的Id获得评论
     */
    Comment getCommentById(Integer commentid);

    /**
     * 获得所有评论
     */
    List<Comment> listComments();
}
