package com.queencastle.service.test.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.utils.PinYinUtils;
import com.queencastle.service.test.BaseTest;

public class AppCore extends BaseTest {

	@Test
	// @Ignore
	public void testInitRootMenu() {
		insertSingleMenu("系统菜单", 0, 0, "");
	}

	@Test
	// @Ignore
	public void testInsertFirstLevelMenu() {
		List<String> list = new ArrayList<String>();

		list.add("用户管理");
		list.add("系统管理");
		list.add("系统设置");
		list.add("供求管理");
		list.add("商城管理");
		int idx = 1;
		for (String menu : list) {
			insertSingleMenu(menu, idx++, 1, "");
		}
	}

	@Test
	// @Ignore
	public void testInsertSecondMenus() {
		/**********************************************************************/
		// 只有超级管理员可见
		insertSingleMenu("用户管理", 1, 2, "users/userList");
		// 客服
		insertSingleMenu("会员审核", 2, 2, "userAudit/index");
		// 客服 群主 只有超级管理员可见
		insertSingleMenu("会员群管理", 3, 2, "relations/userGroupList");
		// 客服 群主
		insertSingleMenu("自建群管理", 4, 2, "");

		// 客服 群主
		insertSingleMenu("会员管理", 5, 2, "relations/userMemberList");
		// 客服
		insertSingleMenu("推荐管理", 6, 2, "ur/urList");
		// 客服 群主
		insertSingleMenu("我的群", 7, 2, "relations/myGroupList");
		// 客服 群主
		insertSingleMenu("我的团队", 8, 2, "relations/myMemberList");

		/**********************************************************************/
		// 群主
		insertSingleMenu("角色管理", 1, 3, "users/roleList");
		// 群主
		insertSingleMenu("菜单管理", 2, 3, "");
		/**********************************************************************/
		insertSingleMenu("群地区编号", 1, 4, "areaGroups/agList");
		insertSingleMenu("家纺数据", 2, 4, "data/index");
		insertSingleMenu("属性字典", 3, 4, "/shop/propertyDict");
		// ////////////////////////////
		// insertSingleMenu("我的社群", 1, 5, "relations/myGroupList");
		// insertSingleMenu("我的团队", 2, 5, "relations/myMemberList");
		// ////////////////////////////
		insertSingleMenu("类别管理", 1, 5, "goods/categoryList");
		insertSingleMenu("产品管理", 2, 5, "goods/productList");
		insertSingleMenu("供求管理", 3, 5, "goods/demandSupplyList");
		insertSingleMenu("订单管理", 4, 5, "");

	}

	public void insertSingleMenu(String cname, int idx, int pid, String href) {
		MenuInfo info = new MenuInfo();
		String ename = PinYinUtils.fullPinyin(cname);
		info.setCname(cname);
		info.setEname(ename);
		info.setRank(idx++);
		info.setHref(href);
		info.setParentId(IdTypeHandler.encode(pid));
		menuService.insert(info);
	}
}
