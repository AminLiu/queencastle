package com.queencastle.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.bbs.BBSArticleMapper;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.bbs.ArticleType;
import com.queencastle.dao.model.bbs.BBSArticle;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class BBSTest extends BaseTest {
	@Autowired
	private BBSArticleMapper bbsArticleMapper;

	@Test
	@Ignore
	public void getBBSArticleCountByParamsTest() {
		Map<String, Object> map = null;
		System.out.println("=============" + bbsArticleMapper.getBBSArticleCountByParams(map));

	}

	@Test
	@Ignore
	public void getBBSArticleByParamsTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSArticle> pageInfo = bbsArticleService.getBBSArticleByParams(1, 10, map);
		if (pageInfo != null) {
			System.out.println("============" + pageInfo.getRows().size());

		} else {
			System.out.println("+++++++++++++++");
		}

	}

	@Test
	@Ignore
	public void bbsArticleTest() {

		String id = IdTypeHandler.encode(1l);
		BBSArticle Result = bbsArticleService.getById(id);
		if (Result != null) {

			System.out.println("======" + Result.getId());
			System.out.println("======" + Result.getBoardId());
			System.out.println("======" + Result.getContent());
			System.out.println("======" + Result.getImg());
			System.out.println("======" + Result.getAttentionCnt());
			System.out.println("======" + Result.getCreatedAt());
		} else {
			System.out.println("=======================s");
		}

	}

	@Test
	@Ignore
	public void bbsInserTest() {

		String boardId = IdTypeHandler.encode(1l);
		User user = new User();
		user.setId(boardId);
		String author = "hhhh";
		String title = "jjjj";
		String img = "ssss";
		String content = "ssssss";
		int p = 2;
		long page = 3;
		int att = 4;
		int com = 5;
		ArticleType type = ArticleType.normal;
		BBSArticle bBSArticle = new BBSArticle();
		bBSArticle.setBoardId(boardId);
		bBSArticle.setAuthor(user);
		bBSArticle.getAuthor().getId();
		bBSArticle.setCommentCnt(com);
		bBSArticle.setContent(content);
		bBSArticle.setImg(img);
		bBSArticle.setPageView(page);
		bBSArticle.setPraiseCnt(p);
		bBSArticle.setAttentionCnt(att);
		bBSArticle.setTop(false);
		System.out.println("++++++++++++" + bBSArticle.getPraiseCnt());
		bBSArticle.setTitle(title);
		bBSArticle.setType(ArticleType.normal);
		bbsArticleService.insert(bBSArticle);

	}

}
