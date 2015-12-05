package com.queencastle.dao.model.weixin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 图文消息
 * 
 * @author YuDongwei
 *
 */
public class NewsMessage extends BaseMessage {
    // 图文消息个数，限制为10条以内
    private int articleCount;
    // 多条图文消息信息，默认第一个item为大图
    private List<Article> articles;


    @Override
    public String getMsgType() {
        return NEWS_TYPE;
    }

    @Override
    public String getXmlBody() {
        List<String> list = new ArrayList<String>();
        list.add("<ArticleCount>" + getArticleCount() + "</ArticleCount>");
        list.add("<Articles>");
        List<Article> articleList = getArticles();
        for (Article article : articleList) {
            list.add("<item>");
            list.add("<Title><![CDATA[" + article.getTitle() + "]]></Title> ");
            list.add("<Description><![CDATA[" + article.getDescription() + "]]></Description>");
            list.add("<PicUrl><![CDATA[" + article.getPicUrl() + "]]></PicUrl>");
            list.add("<Url><![CDATA[" + article.getUrl() + "]]></Url>");
            list.add("</item>");
        }
        list.add("</Articles>");
        return StringUtils.join(list, " ");
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
