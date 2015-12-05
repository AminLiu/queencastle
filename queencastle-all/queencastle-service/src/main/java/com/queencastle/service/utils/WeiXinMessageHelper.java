package com.queencastle.service.utils;

import java.util.ArrayList;
import java.util.List;

import com.queencastle.dao.model.weixin.Article;
import com.queencastle.dao.model.weixin.NewsMessage;

public class WeiXinMessageHelper {

    public static NewsMessage getNewsMessage(String url, String picUrl, String fromUser,
            String toUser) {
        NewsMessage newsMessage = new NewsMessage();
        Article article = new Article();
        article.setTitle("推荐二维码");
        article.setDescription("一次扫描永久获利");
        article.setUrl(url);
        article.setPicUrl(picUrl);
        newsMessage.setFromUserName(fromUser);
        newsMessage.setToUserName(toUser);
        newsMessage.setArticleCount(1);
        List<Article> articles = new ArrayList<Article>();
        articles.add(article);
        newsMessage.setArticles(articles);
        return newsMessage;
    }
}
