# 一、开发环境与工具

1. 硬件环境
    1. Windows10/11
    2. intel core i7
1. 软件环境
    1. IntelliJ IDEA 2021
    2. Tomcat 9.0
    3. MySQL 8.0
2. 开发框架
    1. Spring
    2. Mybatis
    3. SpringMVC
# 二、需求分析

1. 项目背景
新媒体背景下,人们的社会交往呈现出虚拟化加剧的现象,例如微博、微信、QQ等社交软件在人们日常生活中的运用,相比于这种熟人社交平台,匿名社交软件在人际传播过程中产生了新的特点社交网络的高速发展衍生出众多细分需求的空白，匿名社交应运而生。

以综合社交为主的社交媒体市场已经进入成熟阶段，许多较小的细分需求衍生出来，社交行业不同细分领域的需求一直存在，有已经被发掘的职业、婚恋和匿名，每个行业的发展速度都非常快.而匿名社交发展是顺应用户心智而为，当用户心里的压力在微博、微信等圈子里得不到释放时，基于通讯录的熟人匿名社交粉墨登场，作为一个树洞，满足了用户的猎奇心理;而在移动应用爆炸式发展的年代，仅仅满足用户需求是远远不够的，只有占领用户的心智，真正触到用户的痛点才能成功。

	   网络聊天的出现是为了方便人们沟通，但是渐渐的，这个“方便”也只是缩短了时间和距离，而并没有实现更加坦白的沟通，社交媒体依然没有真正实现“人人皆可说“的幻境，它只是给了每个人一支关掉的话筒，怎么能打开，还得靠自己。

	   Whisper匿名提问信箱提供了一种简便真诚的社交方式，就是为了能够打破这些束缚，实现提问双方的轻松社交。

2. 功能描述
    1. 用户可以注册账号，注册好的账号会存入数据库中；
    2. 用户可以输入账号密码进行登录，与数据库信息进行对比确定是否登陆成功；
    3. 用户可以修改自己的个人信息，更新到数据库中；
    4. 用户可以选择模糊搜索某个主题的提问箱；
    5. 用户可以进入到提问箱中匿名进行提问，问题只会被提问箱的主人看到；
    6. 当提问箱的主人回答了此问题后，问题和回答会被公开所有人可见；
    7. 用户可以对自己的提问箱和其中的问题进行删除；
# 三、概要设计

1. 数据库设计
    1. 用户表
        1. user_id作为主键
        2. user_name作为用户名
        3. user_pass为用户密码
        4. user_nickname为用户昵称
        5. user_email为用户邮箱
        6. user_status为用户状态，表示是否被禁言的状态
        7. user_role为用户权限，有管理员和普通用户两种权限
    2. 提问箱
        1. article_id为提问箱id
        2. article_user_id为提问箱所有者的id
        3. article_title为提问箱的标题
        4. article_comment_count为提问箱的评论数量
        5. article_status表示提问箱是否可见
    3. 	评论表
        1. comment_id为评论id
        2. comment_pid为当前评论上一个评论（回复上一个评论）的id
        3. comment_article为评论所在的文章id
        4. comment_content为评论的内容
        5. comment_role为评论的权限，表示是否可见
        6. comment_user_id为评论作者的用户id
        
2. 功能模块设计
    1. DAO层
DAO层主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此，DAO层的设计首先是设计DAO的接口，然后在Spring的配置文件中定义此接口的实现类，然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰，DAO层的数据源配置，以及有关数据库连接的参数都在Spring的配置文件中进行配置。

    2. Service层
Service层主要负责业务模块的逻辑应用设计。同样是首先设计接口，再设计其实现的类，接着再Spring的配置文件中配置其实现的关联。这样我们就可以在应用中调用Service接口来进行业务处理。Service层的业务实现，具体要调用到已定义的DAO层的接口，封装Service层的业务逻辑有利于通用的业务逻辑的独立性和重复利用性，程序显得非常简洁。

    3. Controller层
Controller层即SpringMVC中的核心控制器部分，负责具体的前端url请求后的处理，在此层里面要调用Serice层的接口来控制业务流程，控制的配置也同样是在Spring的配置文件里面进行，针对具体的业务流程，会有不同的控制器，我们具体的设计过程中可以将流程进行抽象归纳，设计出可以重复利用的子单元流程模块，这样不仅使程序结构变得清晰，也大大减少了代码量。

    4. View层
View层即MVC架构中的v，也即视图层，是Web服务器处理完客户端请求后返回给客户端的信息。在SpringMVC中，View层的内容由jsp文件组成。jsp文件格式是一种在html页面内部嵌入java语句的格式，SpringMVC就是基于servlet与jsp开发的工具，能够使我们方便的在jsp中使用Controller中的数据，并进行页面间数据的交互。

# 四、详细设计

1. DAO层设计
    1. 类的设计
        1. User类
```java
package com.tape.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String userPass;
    private String userNickname;
    private String userEmail;
    private Integer userStatus;
    private String userRole;
    
    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) 
    {this.userId = userId;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) 
    {this.userName = userName;}

    public String getUserPass() {return userPass;}

    public void setUserPass(String userPass) {this.userPass = userPass;}

    public String getUserNickname() {return userNickname;}

    public void setUserNickname(String userNickname) 
    {this.userNickname = userNickname;}

    public String getUserEmail() {return userEmail;}

    public void setUserEmail(String userEmail)
    {this.userEmail=userEmail;}

    public Integer getUserStatus() {return userStatus;}

    public void setUserStatus(Integer userStatus)
    {this.userStatus = userStatus;}

    public String getUserRole() {return userRole;}

    public void setUserRole(String userRole) 
    {this.userRole = userRole;}

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userStatus=" + userStatus +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
```
根据数据库中User表格进行设计，包括userId，userName，userPass，userNickname，userEmail，userStatus和userRole属性。
        2. Article类
```java
package com.tape.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {

    private Integer articleId;
    private Integer articleUserId;
    private String articleTitle;
    private Integer articleStatus;
    private String articleContent;

    public Integer getArticleId() {return articleId;}

    public void setArticleId(Integer articleId) 
    {this.articleId = articleId;}

    public Integer getArticleUserId() {return articleUserId;}

    public void setArticleUserId(Integer articleUserId) 
    {this.articleUserId = articleUserId;}

    public String getArticleTitle() {return articleTitle;}

    public void setArticleTitle(String articleTitle) 
    {this.articleTitle = articleTitle;}

    public Integer getArticleStatus() {return articleStatus;}

    public void setArticleStatus(Integer articleStatus) 
    {this.articleStatus = articleStatus}

    public String getArticleContent() {return articleContent;}

    public void setArticleContent(String articleContent) 
    {this.articleContent = articleContent;}

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleUserId=" + articleUserId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
```
根据数据库Article表格进行设计，包括articleId，articleUserId，articleTitle，articleStatus和articleContent属性。
        3. Comment类
```java
package com.tape.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {

    private Integer commentId;
    private Integer commentPid;
    private Integer commentArticleId;
    private String commentContent;
    private Integer commentRole;
    private Integer commentUserId;
    
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) 
    {this.commentId = commentId;}

    public Integer getCommentPid() {return commentPid;}

    public void setCommentPid(Integer commentPid) 
    {this.commentPid = commentPid;}

    public Integer getCommentArticleId() {return commentArticleId;}

    public void setCommentArticleId(Integer commentArticleId) 
    {this.commentArticleId = commentArticleId;}

    public String getCommentContent() {return commentContent;}

    public void setCommentContent(String commentContent) 
    {this.commentContent = commentContent;}
    
    public Integer getCommentRole() {return commentRole;}

    public void setCommentRole(Integer commentRole) 
    {this.commentRole = commentRole;}

    public Integer getCommentUserId() {return commentUserId;}

    public void setCommentUserId(Integer commentUserId) 
    {this.commentUserId = commentUserId;}

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentPid=" + commentPid +
                ", commentArticleId=" + commentArticleId +
                ", commentContent='" + commentContent + '\'' +
                ", commentRole=" + commentRole +
                ", commentUserId=" + commentUserId +
                '}';
    }
}
```
根据数据库Comment表格进行设计，包括commentId，commentPid，commentArticleId，commentContent，commentRole和commentUserId属性。
    2. 接口设计
        1. IUseDao接口
```java
@Mapper
public interface IUserDao {

    int insert(User user);

    User getUserById(Integer userId);

    List<User> listUser() ;

    User getUserByName(String name) ;

}
```
在IUserDao中声明接口，并在Resources中的映射文件中编写sql语句与之对应，操作数据库。
        2. IArticleDao接口
```java
@Mapper
public interface IArticleDao {

    List<Article> getArticleByUserId(Integer userid);

    List<Article> listAllNotWithContent();

    Integer countArticle(@Param(value = "status") Integer status);

    Integer insert(Article article);

    Integer deleteById(Integer articleId);

    Article getArticleById(Integer id) ;
}
```
在IArticleDao中声明接口，并在Resources中的映射文件中编写sql语句与之对应，操作数据库。
        3. ICommentDao接口
```java
@Mapper
public interface ICommentDao {

    Integer countComment();

    List<Comment> listCommentByArticleId(Integer articleId);

    int deleteById(Integer commentId);

    Integer insert(Comment comment);
    
    Comment getCommentById(Integer commentid);
    
    List<Comment> listComments();
}
```
在ICommentDao中声明接口，并在Resources中的映射文件中编写sql语句与之对应，操作数据库。
2. Service层设计
    1. Service接口设计
        1. UserService接口
```java
public interface UserService {

    List<User> listUser();

    User getUserByNameService(String username);

    User getUserByIdService(Integer userId);

}
```
在UserService声明函数接口，具体实现在UserServiceImpl中实现。
        2. ArcticleService接口
```java
public interface ArticleService {

    Integer countArticle(Integer status);

    List<Article> listAllNotWithContent();

    List<Article> getArticleByUserIdService(Integer userid);

    Integer insertService(Article article);

    List<Article> listOthersArticle(Integer userId);

    Article getArticleByIdService(Integer articleId);

}
```
在ArticleService声明函数接口，具体实现在ArticleServiceImpl中实现。
        3. CommentService接口
```java
public interface CommentService {

    Integer countComment();

    List<Comment> listCommentByArticleIdService(Integer articleId);

    Comment getCommentByIdService(Integer commentId);

    Integer insertService(Comment comment) ;

    Comment getChildById(Integer commentId);

    public List<Comment> getHaveReplyComment(Integer articleid);

}
```
在CommentService声明函数接口，具体实现在CommentServiceImpl中实现。
    2. Service实现接口
        1. UserServiceImpl
```java
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<User> listUser() {
        List<User> userList = userDao.listUser();
        return userList;
    }

    @Override
    public User getUserByNameService(String username)
    {return userDao.getUserByName(username)；}

    @Override
    public User getUserByIdService(Integer userId)
    {return userDao.getUserById(userId);}

}
```
listUser函数返回数据库中所有的User，并将它们整理成列表返回；
getUserByName函数通过得到的userName参数，取得相对应的一条User数据；

getUserByIdService函数通过得到的userId参数，取得相对应的一条User数据。

        2. ArticleServiceImpl
```java
@Service
public class ArticelServiceImpl implements ArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICommentDao commentDao;

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        count = articleDao.countArticle(status);
        return count;
    }

    @Override
    public Integer insertService(Article article)
    {return articleDao.insert(article);}

    @Override
    public List<Article> listAllNotWithContent() 
    {return articleDao.listAllNotWithContent();}

    @Override
    public List<Article> getArticleByUserIdService(Integer userid) 
    {return articleDao.getArticleByUserId(userid)；}

    @Override
    public List<Article> listOthersArticle(Integer userId){
        List<Article> allArticle = articleDao.listAllNotWithContent();
        List<Article> otherAticle = new ArrayList<Article>();
        for(Article article : allArticle){
            if(article.getArticleUserId() != userId)
            {
                otherAticle.add(article);
            }
        }
        return otherAticle;
    }

    @Override
    public Article getArticleByIdService(Integer articleId) 
    {return articleDao.getArticleById(articleId);}
}
```
countArticle函数记录所有的提问箱数量；
insertService函数增添一个新的提问箱；

listAllNotWithContent取到数据库中所有的提问箱，并以列表形式返回；

getArticleByUserIdService通过得到的userId参数，取得相对应的所有提问箱；

listOthersArticle通过得到的userId，取得相对应的所有不属于此用户的提问箱；

getArticleByIdService通过得到的articleId参数，取得相对应的一个提问箱。

        3. CommentServiceImpl
```java
@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public Integer countComment() {
        Integer commentCount = null;
        commentCount = commentDao.countComment();
        return commentCount;
    }

    @Override
    public List<Comment> listCommentByArticleIdService(Integer articleId)
    {return commentDao.listCommentByArticleId(articleId);}

    @Override
    public Comment getCommentByIdService(Integer commentId)
    {return commentDao.getCommentById(commentId);}

    @Override
    public Integer insertService(Comment comment)
    {return commentDao.insert(comment);}

    @Override
    public Comment getChildById(Integer commentId) {
        List<Comment> comments = commentDao.listComments();
        for (Comment comment : comments) {
            if (comment.getCommentPid() == commentId) 、
            {return comment；}
        }
        return null;
    }

    @Override
    public List<Comment> getHaveReplyComment(Integer articleid){
        List<Comment> havereplycomments = new ArrayList<Comment>();
        List<Comment> bigcomments = commentDao.listCommentByArticleId(articleid);
        List<Comment> smallcomments = commentDao.listComments();
        for(Comment bigcomment : bigcomments){
            int bigcommentId = bigcomment.getCommentId();
            for(Comment smallcomment : smallcomments){
                if(smallcomment.getCommentPid() == bigcommentId){
                    havereplycomments.add(bigcomment);
                    break;
                }
            }
        }
        return havereplycomments;
    }
}
```
countComment函数记录评论的总数；
listCommentByArticleIdService函数通过得到的articleId参数，取得相对应的所有评论；

getCommentByIdService函数通过得到的commentId参数，取得相对应的一条评论；

insertService函数添加一条新的评论；

getChildById函数通过得到的articleId参数，得到相对应的子评论即回复；

getHaveReplyComment函数得到所有被回复的评论，并以列表形式返回。

3. Controller层设计
    1. LoginCotroller
        1. LoginController类声明与成员变量
```java
@Controller
public class LoginController {

    @Autowired
    public UserServiceImpl userService;

```
使用bean注入userService，调用Service层的相关方法。
        2. loginCheck()函数
```java
@RequestMapping("login")
    public String loginCheck(User user)
    {
        System.out.println("loginController执行了");
        System.out.println(user);
        User trueUser = userService.getUserByNameService(user.getUserName());
        System.out.println(trueUser);
        if(trueUser != null && Objects.equals(trueUser.getUserPass(), user.getUserPass()))
        {
            System.out.println("登陆成功");
            int id = trueUser.getUserId();
            return "redirect:article?userId="+id;
        }
        else return "redirect:";
    }
```
该函数用来检查用户名和密码是否正确。首先根据用户名得到数据库中的一个用户，再进行比对得到的密码与数据库得到的用户的密码是否一致，一致则转发至提问箱展示视图。
    2. ArticleController
        1. ArticleController类声明与成员变量
```java
@Controller("ArticleController")
@RequestMapping("article")
public class ArticleController {
    @Autowired
    public ArticleService articleService;

    @Autowired
    public UserService userService;
```
使用@Controller声明为控制器。使用@RequestMapping设置请求解析路径。其内部有两个成员变量：articleService和userService，用以访问和调用Service层函数，同时注意其使用注解被自动由Spring的bean容器注入。
        2. showAll()函数
```java
@RequestMapping("")
    public ModelAndView showAll(int userId)
    {
        System.out.println("articlecontroller执行了");
        List<Article> articles = articleService.getArticleByUserIdService(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","我的");
        mv.setViewName("articles");
        return mv;
    }
```
使用@RequestMapping设置请求解析路径"article/"。该函数负责通过一个已登录用户的id获得并展示已登录用户的全部提问箱，使用ModelAndView返回模型与视图。
        3. create()函数
```java
@RequestMapping("create")
    public ModelAndView create(int userId)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.setViewName("create");
        return mv;
    }
```
使用@RequestMapping设置请求解析路径"article/create"。该函数通过已登录用户的id，为用户展示创建评论箱的界面，界面使用ModelAndView返回。
        4. createCommit()函数
```java
@RequestMapping("commit")
    public String createCommit(Article article)
    {
        //articleService.insert(article);
        System.out.println("创建成功"+article.getArticleUserId());
        articleService.insertService(article);
        return "redirect:/article?userId="+article.getArticleUserId();
    }
```
使用@RequestMapping设置请求解析路径为"article/commit"。该函数通过实现将表单提交的一个已创建的article插入数据库，实现创建评论箱的功能。
        5. showOthers()函数
```java
@RequestMapping("all")
    public ModelAndView showOthers(int userId)
    {
        ModelAndView mv =new ModelAndView();
        List<Article> articles = articleService.listAllNotWithContent();
        mv.addObject("articles",articles);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("who","全部的");
        mv.setViewName("articles");
        return mv;
    }
```
使用@RequestMapping设置请求解析路径为"article/all"。该函数实现获取全部提问箱并展示的功能，使用modelAndView返回相应的视图。
        6. 工具函数userName()
```java
 String userName(int userId)
    {
        return userService.getUserByIdService(userId).getUserName();
    }
```
函数内私有，通过已登录用户的id获取每个已登录用户的用户名，用以在各个页面上展示。
    3. CommentController
        1. CommentController类声明与成员变量
```java
@Controller("CommentController")
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ArticelServiceImpl articelService;
    @Autowired
    UserServiceImpl userService;
```
使用@Controller声明为控制器。使用@RequestMapping设置请求解析路径。其内部有两个成员变量：articleService、commentService和userService，用以访问和调用Service层函数，同时注意其使用注解被自动由Spring的bean容器注入。
        2. showComments()函数
```java
@RequestMapping("")
    public ModelAndView showComments(int userId, int articleId)
    {
        ModelAndView mv = new ModelAndView();
        Article article = articelService.getArticleByIdService(articleId);
        List<Comment> comments;
        if(userId == article.getArticleUserId())
        {
            comments=commentService.listCommentByArticleIdService(articleId);
        }
        else
        {
            comments=commentService.getHaveReplyComment(articleId);
        }
        mv.addObject("comments",comments);
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);
        mv.setViewName("comments");
        return mv;
    }
```
设置请求解析路径为"comment/"，该函数会判断当前用户是否是当前所选提问箱的主人，如果是则展示全部悄悄话（评论），否则只展示被评论之后的悄悄话（评论）。
        3. replyComment()函数
```java
   @RequestMapping("/reply")
    public ModelAndView replyComment(int userId, int commentId)
    {
        ModelAndView mv = new ModelAndView();
        Comment selectedComment = commentService.getCommentByIdService(commentId);
        Comment childComment = commentService.getChildById(commentId);
        Article article = articelService.getArticleByIdService(selectedComment.getCommentArticleId());
        if(childComment == null && userId == article.getArticleUserId())
        {
            mv.addObject("selectedComment", selectedComment);
            mv.addObject("userId",userId);
            mv.addObject("userName",userName(userId));
            mv.setViewName("reply");
            return mv;
        }
        else
        {
            mv.addObject("selectedComment",selectedComment);
            mv.addObject("childComment",childComment);
            mv.addObject("userName",userName(userId));
            mv.setViewName("replied");
            return mv;
        }
    }
```
设置请求解析路径为"comment/reply"，该函数判断某条评论是否被评论，如果被评论则返回被评论的视图，否则返回未被评论的视图（被提问的用户可以回复）。
        4. replyCommit()函数
```java
@RequestMapping("/reply/commit")
    public ModelAndView replyCommit(Comment comment)
    {
        System.out.println("replyCommit执行了");
        ModelAndView mv = new ModelAndView();
        commentService.insertService(comment);
        Comment selectedComment = commentService.getCommentByIdService(comment.getCommentPid());
        mv.addObject("selectedComment",selectedComment);
        mv.addObject("childComment",comment);
        mv.addObject("userId",comment.getCommentUserId());
        mv.setViewName("replied");
        return mv;
    }
```
设置请求路径为"comment/reply/commit"，在reply页面下提交表单获得的comment被插入数据库后，返回到已被回复的视图。
        5. createTape()函数
```java
@RequestMapping("/create")
    public ModelAndView createTape(Integer userId, Integer articleId)
    {
        System.out.println("createTapes执行了");
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("userName",userName(userId));
        mv.addObject("articleId",articleId);

        mv.setViewName("newComment");
        return mv;
    }
```
设置请求路径为"comment/create"，返回创建新悄悄话的视图。
        6. createCommit()函数
```java
@RequestMapping("/create/commit")
    public String createCommit(Comment comment)
    {
        System.out.println("createCommit执行了"+comment);
        commentService.insertService(comment);
        return "redirect:/comment?userId="+comment.getCommentUserId()+"&articleId="+comment.getCommentArticleId();
    }
```
设置请求解析路径为"/create/commit"，创建成功并插入数据库后，将重定向到展示所有comment的页面。
4. View层设计
    1. 整体设计
为了防止视图资源的直接访问，将所有视图（除index外）放置于WEB-INF的pages包下。将CSS、JavaScript、imgs放置于webapp根目录下的resources资源包内。

*部分CSS、JavaScript资源来自网络上的开源内容

    2. articles.jsp
```xml
<c:forEach items="${articles}" var="article" varStatus="st">
                <div class="c-sites__item">
                    <div class="c-sites__itemWrapper">
                        <div class="c-sites__itemInner">
                            <div class="c-sites__body"><h2 class="c-sites__title">
                                <a href="/comment?userId=${userId}&articleId=${article.articleId}">
                                ${article.articleTitle}
                                </a>
                                </h2>
                                <div class="c-sites__meta">
                                <div class="c-sites__metaItem  c-sites__metaItem--date">
                                    uid:${article.articleUserId}
                                </div>
                                <br></div>
                            </div>
                        </div>
                    </div>
                </div></c:forEach>
```
展示全部提问箱功能部分，使用taglib的forEach标签遍历所有提问箱。
    3. comments.jsp
```xml
<c:forEach items="${comments}" var="comment" varStatus="st">
                    <div class="c-sites__item">
                        <div class="c-sites__itemWrapper">
                            <div class="c-sites__itemInner">
                                <div class="c-sites__body"><h2 class="c-sites__title">
                                    <a href="comment/reply?userId=${userId}&commentId=${comment.commentId}">
                                            ${comment.commentContent}
                                    </a><span class="c-colors">
                                </div>
                            </div>
                        </div>
                    </div></c:forEach>
```
展示指定提问箱下符合条件的全部留言，同上使用taglib中的forEach标签。
    4. create.jsp
```xml
<div class="reply">
                <label>为你的提问箱起个名字吧！</label>
                <br>
                <br>
                <br><form action="commit" method="post">
                <input type="hidden" name="articleUserId" value="${userId}">
                <input type="text" name="articleTitle">
                <input type="hidden" name="articleStatus" value="1">
                <input type="submit" value="保存"></form>
            </div>
```
提交表单，创建新的提问箱。
    5. newComment.jsp
```xml
<div class="reply">
                <label>写下你的悄悄话</label>
                <br>
                <br>
                <br><form action="create/commit" method="post">
                <input type="hidden" name="commentPid" value="0">
                <input type="hidden" name="commentArticleId" value="${articleId}">
                <input type="text" name="commentContent">

                <input type="hidden" name="commentUserId" value="${userId}">
                <input type="submit" value="保存"></form>
            </div>
```
提交表单，创建新的悄悄话，向他人提问。
    6. replied.jsp
```xml
<h2 class="c-section__headline">
                <span>${selectedComment.commentContent}</span>
            </h2>
            <div class="reply">
                <h3>${childComment.commentContent}</h3>
            </div>
```
对于已经被回复的评论，展示评论内容和回复内容。
    7. reply.jsp
```xml
<form action="reply/commit" method="post" style="width: 100%; margin-left: 100px;
            margin-right:100px;">
                <input type="hidden" name="commentPid" value="${selectedComment.commentId}">
                <input type="hidden" name="commentPname" value="${selectedComment.commentPname}">
                <input type="hidden" name="commentArticleId" value="0">
                <input type="hidden" name="commentUserId" value="${userId}">
                <textarea name="commentContent"></textarea>
                <center><input type="submit" style="margin-right: auto;" value="回复此消息"></center></form>
```
对于未被回复的评论，提问箱主人可以提交表单进行回复。

# 六、开发会议记录

1. 第一次讨论
第一次讨论主要围绕着项目选题进行。一开始三人都希望能够依托于网络上已有的教学项目、开源项目进行，这样能够最大限度地减少出错的可能性，所以最早定的选题是制作博客。但是大家一是觉得写博客网站枯燥无趣、缺乏创意，二是觉得依托于网络上的资源太没有挑战性，不能学到东西。

这时想到了匿名社交软件。经过分析后发现仿tape的匿名社交软件是可以实现的，框架仍是简单的评论、回复，并且此选题新颖、有趣。因此我们组最终决定制作类似于tape的匿名社交软件。

2. 第二次讨论
第二次讨论主要围绕着数据库设计和项目分工。因为功能主要是围绕用户、提问箱、评论，经过仔细讨论后发现基础功能应该只需要建立这三张表。一开始的时候关于评论与子评论的关系产生了争论，最终决定使用类似于链表的形式使每个评论保存被评论的评论的id。

关于分工，选择了对前端了解较多的同学负责前端和MVC，选择了Spring和Mybatis比较熟练的同学负责DAO，选择了对业务逻辑理解清楚的负责Service层。最后的文档共同合作。

3. 第三次讨论
第三次讨论是围绕着每个人负责的内容同步进度、合并代码。在合并时出现了一些配置上的问题，但是大家一起合作、在网络上寻找解决方案，最终解决。之后根据当前进度调整了项目进度计划，并对之后要合作完成的小组文档、开发文档进行了讨论、分工。

