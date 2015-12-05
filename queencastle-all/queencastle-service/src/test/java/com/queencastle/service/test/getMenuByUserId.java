package com.queencastle.service.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.model.RoleMenuInfo;
import com.queencastle.dao.model.UserRoleInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class getMenuByUserId extends BaseTest {

	@Test
	@Ignore
	public void testGetMenuByUserId() {
		UserRoleInfo userRoleInfo = new UserRoleInfo();
		String userId = IdTypeHandler.encode(2L);
		userRoleInfo.setUserId(userId);

		List<UserRoleInfo> s = userRoleInfoService.getByUserId(userId);
		String roleId = null;
		for (UserRoleInfo info : s) {
			System.out.println("roleId==" + info.getRoleId());
//			System.out.println("userId==" + info.getUserId());
//			long a=IdTypeHandler.decode(info.getUserId());
//			System.out.println("id============"+a);
			roleId = info.getRoleId();
			System.out.println(roleId);
			if (!roleId.isEmpty()) {
				List<RoleMenuInfo> list = roleMenuInfoService.getByRoleId(roleId);
				String menuId = null;
				for (RoleMenuInfo info2 : list) {
					System.out.println("menuId==：" + info2.getMenuId());
					menuId = info2.getMenuId();
					if (!menuId.isEmpty()) {
						List<MenuInfo> m = menuService.getMenuByParentId(menuId);
						for (MenuInfo info3 : m) {
							System.out.println("menu" + IdTypeHandler.decode(info.getUserId())+"=="+info3.getCname());
						}

					}
				}
			}
		}

	}
}