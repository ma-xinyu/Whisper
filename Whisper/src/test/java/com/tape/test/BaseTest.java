package com.tape.test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml","classpath:spring/spring-mvc.xml","classpath:mybatis/mybatis-config.xml"})
public class BaseTest {
}
