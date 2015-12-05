package com.queencastle.web.controllers.relations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.model.relations.UserRelation;
import com.queencastle.dao.utils.MD5Util;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.relations.AreaGroupService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.service.interf.relations.UserRelationService;

@Controller
@RequestMapping("/ur")
public class UserRelationController {
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private UserService userService;
	@Autowired
	private UserDetailInfoService userDetailInfoService;
	@Autowired
	private UserMemberService userMemberService;
	@Autowired
	private AreaGroupService areaGroupService;


    @RequestMapping("/urList")
    public String agencyList() {
        return "/relations/urList";
    }

    @ResponseBody
    @RequestMapping("/getAgencyByParams")
    public PageInfo<UserRelationVO> getAgencyByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageInfo<UserRelation> results = userRelationService.getAgencyByParams(page, rows, map);
        PageInfo<UserRelationVO> pageInfo = new PageInfo<UserRelationVO>();
        pageInfo.setPage(results.getPage());
        pageInfo.setTotal(results.getTotal());
        List<UserRelation> list = results.getRows();
        List<UserRelationVO> vos = new ArrayList<UserRelationVO>();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserRelation ur : list) {
                UserRelationVO vo = new UserRelationVO();
                vo.setId(ur.getId());
                vo.setUserId(ur.getUserId());
                vo.setParentId(ur.getParentId());
                vo.setCreatedAt(ur.getCreatedAt());

                User userU = userService.getById(ur.getUserId());

                if (userU == null) {
                    userU = new User();
                    userU.setAdmin(false);
                    userU.setPhone(RandomStringUtils.randomNumeric(11));
                    userU.setUsername("资料不全");
                    String password = "123456";
                    password = MD5Util.MD5Encode(password);
                    userU.setPassword(password);
                    userU.setSource("fake");
                    userU.setOutNick(RandomStringUtils.randomAlphanumeric(10));
                    userService.insert(userU);
                }
                vo.setUsername(userU.getUsername());
                User userP = userService.getById(ur.getUserId());
                if (userP == null) {
                    userP = new User();
                    userP.setAdmin(false);
                    userP.setPhone(RandomStringUtils.randomNumeric(11));
                    userP.setUsername("资料不全");
                    String password = "123456";
                    password = MD5Util.MD5Encode(password);
                    userP.setPassword(password);
                    userP.setSource("fake");
                    userP.setOutNick(RandomStringUtils.randomAlphanumeric(10));
                    userService.insert(userP);
                }
                vo.setParentName(userP.getUsername());
                vos.add(vo);
            }
        }
        pageInfo.setRows(vos);
        return pageInfo;
    }

	public String getMaster(String parentId, String userId) {

		UserDetailInfo parentDetail = userDetailInfoService.getByUserId(parentId);
		if (parentDetail == null) {
			// 完善个人信息
			return null;
		}
		String parentArea = parentDetail.getProvinceCode();

		UserDetailInfo userDetai = userDetailInfoService.getByUserId(userId);
		if (userDetai == null) {
			// 完善个人信息
			return null;
		}
		String userArea = userDetai.getProvinceCode();

		if (parentArea == userArea) {
			// 是上下级关系 逻辑处理---------

		} else {
			// 将用户所在地的群查到
			AreaGroupInfo groupCodes = areaGroupService.getByAreaIdAndType(AreaType.province, userArea);
			String groupCode = groupCodes.getCode();

			// 从而找到该群的管理员
			List<UserMember> list = userMemberService.getUserIdByCodeAndType(groupCode, MemberType.master);
			String masterUserId = null;
			for (UserMember um : list) {
				masterUserId = um.getUserId();
			}

			return masterUserId;
		}
		return null;

	}

}
