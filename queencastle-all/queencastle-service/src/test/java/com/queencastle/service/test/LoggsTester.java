package com.queencastle.service.test;

import java.util.List;

import org.junit.Test;

import com.queencastle.dao.model.goods.DemandSupplyInfo;
import com.queencastle.dao.model.loggs.LogType;
import com.queencastle.dao.model.loggs.UserLog;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class LoggsTester extends BaseTest {

	@Test
	public void inserTest() {
		String userId = IdTypeHandler.encode(1l);
		String content = "测试";

		LogType logType = LogType.ds;

		UserLog userLog = new UserLog();
		userLog.setUserId(userId);
		userLog.setContent(content);
		userLog.setLogType(logType);
		// userLogService.insert(userLog);
	}

	@Test
	public void getThreeTest() {
		String userId = IdTypeHandler.encode(222l);
		String infoId = IdTypeHandler.encode(53l);

		List<DemandSupplyInfo> list = demandSupplyInfoService.getThreeByUserId(userId, infoId);
		for (DemandSupplyInfo l : list) {
			System.out.println("======" + l.getId());
		}
	}
}
