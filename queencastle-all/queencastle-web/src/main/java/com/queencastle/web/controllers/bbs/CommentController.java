package com.queencastle.web.controllers.bbs;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.bbs.BBSComment;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.interf.bbs.BBSCommentService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/BBS")
public class CommentController {
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private BBSCommentService bBSCommentService;

	@RequestMapping("/commentList")
	public String commentList() {
		return "/BBS/commentList";
	}

	@ResponseBody
	@RequestMapping("/getCommentByParams")
	public PageInfo<BBSComment> getCommentByParams(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSComment> pageInfo = bBSCommentService.getBBSCommentByParams(page, rows, map);
		return pageInfo;

	}

	@ResponseBody
	@RequestMapping("/saveComment")
	public boolean saveComment(String articleId, String content) {
		BBSComment comment = new BBSComment();

		articleId = IdTypeHandler.encode(RandomUtils.nextLong(1, 2000));

		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setUserId(PermissionContext.getUser().getId());
		bBSCommentService.insert(comment);
		return true;
	}

}