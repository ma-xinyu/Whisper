<%--
  Created by IntelliJ IDEA.
  User: 14427
  Date: 2021/12/29
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html class="js fonts-loaded" lang="en">
<head>
    <link rel="preload" as="font" type="font/woff2" crossorigin="anonymous"
          href="https://bestwebsite.gallery/assets/fonts/rubik-latin-900.woff2">
    <meta charset="UTF-8">
    <style>
        textarea{
            resize: none;
            display: inline;
            padding: 20px;
            width:100%;
            height: max-content;
            min-height: 300px;

        }
        .reply{
            display: table-cell;
            vertical-align: middle;
            text-align: center;
            width: 100%;
            height: 280px;

        }
        .c-sites__item{
            height: 200px;
        }
        @font-face {
            font-family: 'Rubik';
            font-style: normal;
            font-display: swap;
            font-weight: 400;
            src: local('Rubik'), local('Rubik'),
            url('https://bestwebsite.gallery/assets/fonts/rubik-latin-400.woff2') format('woff2');
        }

        @font-face {
            font-family: 'Rubik';
            font-style: normal;
            font-display: swap;
            font-weight: 500;
            src: local('Rubik'), local('Rubik'),
            url('https://bestwebsite.gallery/assets/fonts/rubik-latin-500.woff2') format('woff2');
        }

        @font-face {
            font-family: 'Rubik';
            font-style: normal;
            font-display: swap;
            font-weight: 700;
            src: local('Rubik'), local('Rubik'),
            url('https://bestwebsite.gallery/assets/fonts/rubik-latin-700.woff2') format('woff2');
        }

        @font-face {
            font-family: 'Rubik';
            font-style: normal;
            font-display: swap;
            font-weight: 900;
            src: local('Rubik'), local('Rubik'),
            url('https://bestwebsite.gallery/assets/fonts/rubik-latin-900.woff2') format('woff2');
        }

    </style>

    <link rel="stylesheet" href="https://bestwebsite.gallery/assets/css/app.min.css?v=3">
    <title>Whisper-我有话想对你说</title>
</head>
<body id="home" class="p-websites">

<div class="c-appWrapper">
    <div class="c-appContent">
        <div class="c-section">
            <h2 class="c-section__headline">
                <span>${selectedComment.commentContent}</span>
            </h2>
            <div class="reply">
                <h3>${childComment.commentContent}</h3>
            </div>
        </div>

        <footer class="c-appFooter">
            <div class="c-appFooter__credits">
                <div class="c-appFooter__creditsInner">©2008 – 2021
                    &nbsp;—&nbsp;managed by&nbsp;<a href="http://davidhellmann.com">David Hellmann</a>&nbsp;—&nbsp;Hosting&nbsp;<a
                            href="https://www.hetzner.de/">Hetzner</a>&nbsp;&amp;&nbsp;<a
                            href="https://forge.laravel.com/">Forge</a>
                    &nbsp;—&nbsp;CMS behind&nbsp;<a href="https://craftcms.com/">Craft CMS</a>&nbsp;—&nbsp;2615
                    Sites online.
                </div>
            </div>
        </footer>
    </div>
    <a href="/article?userId=${userId}" class="c-logo">W<span>isper</span> 悄<span>悄话</span>
    </a>
    <header class="c-appHeader">
        <div class="c-navTrigger  js-navTrigger">
            <div class="c-navTrigger__text">Filter</div>
            <div class="c-navTrigger__line  c-navTrigger__line--1"></div>
            <div class="c-navTrigger__line  c-navTrigger__line--2"></div>
            <div class="c-navTrigger__line  c-navTrigger__line--3"></div>
        </div>
        <a href="/comment?userId=${userId}&articleId=${selectedComment.commentArticleId}" class="c-appHeader__link">
            返回提问箱
        </a><a href="/article?userId=${userId}" class="c-appHeader__link">
        返回主页
    </a>
        <div class="c-search  c-search--head">
            <form action="https://bestwebsite.gallery/search/sites">
                <input type="search" name="q" placeholder="Search">
                <input type="submit" value="Go">
                <svg class="c-svgSprite c-svgSprite--icon_search" viewBox="0 0 100 100">
                    <use xlink:href="/assets/images/svg/sprite/sprite.svg#icon_search"></use>
                </svg>
            </form>
        </div>
    </header>
    <div class="c-search  c-search--nav">
        <form action="https://bestwebsite.gallery/search/sites">
            <input type="search" name="q" placeholder="Search">
            <input type="submit" value="Go">
            <svg class="c-svgSprite c-svgSprite--icon_search" viewBox="0 0 100 100">
                <use xlink:href="/assets/images/svg/sprite/sprite.svg#icon_search"></use>
            </svg>
        </form>
    </div>
</div>

</body>
</html>
