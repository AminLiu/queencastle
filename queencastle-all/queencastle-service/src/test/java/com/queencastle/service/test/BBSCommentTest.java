package com.queencastle.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.bbs.BBSCommentMapper;
import com.queencastle.dao.model.bbs.BBSComment;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class BBSCommentTest extends BaseTest {

	@Autowired
	private BBSCommentMapper bbsCommentMapper;

	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(1l);
		BBSComment result = bbsCommentService.getById(id);
		if (result != null) {

			System.out.println("=========" + result.getId());
			System.out.println("=========" + result.getContent());
			System.out.println("=========" + result.getUserId());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void bbsCommentinsertTest() {

		BBSComment bbsComment = new BBSComment();
		String articleId = "005";
		String content = "无评论dd";
		String userId = "019";
		bbsComment.setArticleId(articleId);
		bbsComment.setContent(content);
		bbsComment.setUserId(userId);
		bbsCommentService.insert(bbsComment);
	}

	@Test
	// @Ignore
	public void getBBSCommentCountByParamsTest() {
		Map<String, Object> map = null;
		System.out.println("=============" + bbsCommentMapper.getBBSCommentCountByParams(map));

	}

	@Test
	// @Ignore
	public void getBBSCommentByParamsTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSComment> pageInfo = bbsCommentService.getBBSCommentByParams(1, 10, map);
		if (pageInfo != null) {
			System.out.println("============" + pageInfo.getRows().size());

		} else {
			System.out.println("+++++++++++++++");
		}

	}

}
