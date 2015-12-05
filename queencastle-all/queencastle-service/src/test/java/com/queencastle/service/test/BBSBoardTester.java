package com.queencastle.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.bbs.BBSBoardMapper;
import com.queencastle.dao.model.bbs.BBSBoard;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class BBSBoardTester extends BaseTest {

	@Autowired
	private BBSBoardMapper bbsBoardMapper;

	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(4l);
		BBSBoard result = bbsBoardService.getById(id);
		if (result != null) {

			System.out.println("=========" + result.getId());
			System.out.println("=========" + result.getTitle());
			System.out.println("=========" + result.getImg());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void bbsBoardsertTest() {

		BBSBoard bbsBoard = new BBSBoard();
		String title = "呵呵";
		String img = "哈哈";
		bbsBoard.setTitle(title);
		bbsBoard.setImg(img);
		bbsBoardService.insert(bbsBoard);
	}

	@Test
	// @Ignore
	public void getBBSBoardCountByParamsTest() {
		Map<String, Object> map = null;
		System.out.println("=============" + bbsBoardMapper.getBBSBoardCountByParams(map));

	}

	@Test
	// @Ignore
	public void getBBSBoardByParamsTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<BBSBoard> pageInfo = bbsBoardService.getBBSBoardByParams(1, 10, map);
		if (pageInfo != null) {
			System.out.println("============" + pageInfo.getRows().size());

		} else {
			System.out.println("+++++++++++++++");
		}

	}

}
