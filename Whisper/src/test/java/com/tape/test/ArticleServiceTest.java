package com.tape.test;

import com.tape.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.List;

/**
 */
@WebAppConfiguration("src/main/resources")
public class ArticleServiceTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void countArticle() {
        int count = articleService.countArticle(1);
        System.out.println(count);
        Assert.state(count > 0, "已发布文章数量为0");
    }
}
