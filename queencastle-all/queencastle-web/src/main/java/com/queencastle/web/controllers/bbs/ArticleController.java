package com.queencastle.web.controllers.bbs;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.bbs.BBSArticle;
import com.queencastle.service.interf.bbs.BBSArticleService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/BBS")
public class ArticleController {
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	private BBSArticleService bBSArticleService;

	@RequestMapping("/articleList")
	public String articleList() {
		return "/BBS/articleList";
	}

	@ResponseBody
	@RequestMapping("/getArticByParams")
	public PageInfo<BBSArticle> getArticByParams(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSArticle> pageInfo = bBSArticleService.getBBSArticleByParams(page, rows, map);
		return pageInfo;

	}

	@ResponseBody
	@RequestMapping("/saveArticle")
	public boolean saveArticle(User author, String boardId) {
		BBSArticle article = new BBSArticle();
		User user = PermissionContext.getUser();
		article.setAuthor(author);
		article.setBoardId(boardId);
		bBSArticleService.insert(article);
		return true;
	}
}