package com.queencastle.service.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.config.DaoConfig;
import com.queencastle.dao.config.JedisConfig;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.service.config.ServiceConfig;
import com.queencastle.service.interf.MenuService;
import com.queencastle.service.interf.MsgInfoService;
import com.queencastle.service.interf.PropertyDictService;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.RoleMenuInfoService;
import com.queencastle.service.interf.RoleService;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserRoleInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.bbs.BBSArticleService;
import com.queencastle.service.interf.bbs.BBSBoardService;
import com.queencastle.service.interf.bbs.BBSCommentService;
import com.queencastle.service.interf.data.SaleDataService;
import com.queencastle.service.interf.goods.CategoryService;
import com.queencastle.service.interf.goods.DemandSupplyInfoService;
import com.queencastle.service.interf.goods.PraiseInfoService;
import com.queencastle.service.interf.goods.userGoodsAddressService;
import com.queencastle.service.interf.loggs.UserLogService;
import com.queencastle.service.interf.relations.UserAuditService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.service.interf.relations.UserQRCodeService;
import com.queencastle.service.interf.relations.UserRelationService;
import com.queencastle.service.interf.shop.CustomerService;
import com.queencastle.service.interf.shop.OrderItemService;
import com.queencastle.service.interf.shop.ProductStandardService;
import com.queencastle.service.interf.shop.ShopAddressService;
import com.queencastle.service.interf.shop.ShopBrandService;
import com.queencastle.service.interf.shop.ShopItemService;
import com.queencastle.service.interf.shop.ShopProductService;
import com.queencastle.service.interf.weixin.WeiXinService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = { DaoConfig.class, JedisConfig.class, ServiceConfig.class })
public abstract class BaseTest {

	@Autowired
	protected RestTemplate restTemplate;
	@Autowired
	protected UserService userService;
	@Autowired
	protected ResourceUploadService resourceUploadService;
	@Autowired
	protected MenuService menuService;
	@Autowired
	protected RoleService roleService;

	@Autowired
	protected RoleMenuInfoService roleMenuInfoService;

	@Autowired
	protected UserRoleInfoService userRoleInfoService;

	@Autowired
	protected MsgInfoService msgInfoService;
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected AreaInfoService areaInfoService;

	@Autowired
	protected ObjectJedisCache provinceCache;

	@Autowired
	protected ObjectJedisCache cityCache;
	@Autowired
	protected UserRelationService userRelationService;

	@Autowired
	protected UserGroupService userGroupService;
	@Autowired
	protected UserMemberService userMemberService;
	@Autowired
	protected DemandSupplyInfoService demandSupplyInfoService;
	@Autowired
	protected PraiseInfoService praiseInfoService;
	@Autowired
	protected UserLogService userLogService;
	@Autowired
	protected UserQRCodeService userQRCodeService;
	@Autowired
	protected WeiXinService weiXinService;
	@Autowired
	protected userGoodsAddressService userGoodsAddressService;
	@Autowired
	protected BBSArticleService bbsArticleService;
	@Autowired
	protected BBSCommentService bbsCommentService;
	@Autowired
	protected SaleDataService saleDataService;
	@Autowired
	protected BBSBoardService bbsBoardService;
	@Autowired
	protected PropertyDictService propertyDictService;

	@Autowired
	protected UserDetailInfoService userDetailInfoService;

	@Autowired
	protected UserAuditService userAuditService;

	@Autowired
	protected ShopProductService shopProductService;

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected ProductStandardService productStandarService;

	@Autowired
	protected ShopAddressService shopAddressService;

	@Autowired
	protected ShopBrandService shopBrandService;

	@Autowired
	protected OrderItemService orderItemService;

	@Autowired
	protected ShopItemService shopItemService;
}
