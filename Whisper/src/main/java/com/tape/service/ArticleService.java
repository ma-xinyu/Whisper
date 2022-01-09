package com.tape.service;


import com.tape.entity.Article;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {
    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    List<Article> listAllNotWithContent();

    List<Article> getArticleByUserIdService(Integer userid);

    Integer insertService(Article article);

    List<Article> listOthersArticle(Integer userId);

    Article getArticleByIdService(Integer articleId);

}
