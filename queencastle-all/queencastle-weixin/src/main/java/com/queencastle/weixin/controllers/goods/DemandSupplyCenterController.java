package com.queencastle.weixin.controllers.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.model.User;
import com.queencastle.dao.model.goods.DemandSupplyInfo;
import com.queencastle.dao.model.goods.FeedBack;
import com.queencastle.dao.model.goods.PraiseType;
import com.queencastle.dao.model.goods.Product;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.goods.DemandSupplyInfoService;
import com.queencastle.service.interf.goods.FeedBackService;
import com.queencastle.service.interf.goods.PraiseInfoService;
import com.queencastle.service.interf.goods.ProductService;
import com.queencastle.service.sessions.PermissionContext;

/**
 * 供求中心复杂查询<br>
 * 查询参数：产品类目，价格，关注度,发布时间,默认情况下查询所有类目
 * 
 * @author YuDongwei
 *
 */
@Controller
@RequestMapping("/dsManager")
public class DemandSupplyCenterController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DemandSupplyInfoService demandSupplyInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PraiseInfoService praiseInfoService;

	/**
	 * 默认情况下的查询，只根据供求类型进行查询
	 */
	@RequestMapping("/getInfosByType")
	public String getByType(String type, Model model) {
		try {
			List<DemandSupplyInfo> infos = demandSupplyInfoService.getAllByType(type);
			List<DemandSupplyVO> vos = new ArrayList<DemandSupplyVO>();
			for (DemandSupplyInfo info : infos) {
				vos.add(convertVO(info));

			}
			model.addAttribute("vos", vos);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "/goods/infos";
	}

	/**
	 * 根据编号预览
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/preview")
	public String DsFinish(Model model, String id) {
		DemandSupplyInfo info = demandSupplyInfoService.getById(id);
		DemandSupplyVO vo = convertVO(info);

		model.addAttribute("vo", vo);
		List<FeedBack> list = new ArrayList<FeedBack>();
		list = feedBackService.getByDemondInfoId(id);
		model.addAttribute("list", list);
		// 根据用户编号查询
		String userId = info.getUserId();

		if (StringUtils.isNoneBlank(userId)) {
			// 判断此消息其关注否
			PraiseType attention = praiseInfoService.getTypeByUserId(userId, id);
			if (attention != null) {
				if (attention.equals(PraiseType.addPraise)) {
					model.addAttribute("att", attention);
				}

			}

			List<DemandSupplyInfo> ListThree = demandSupplyInfoService.getThreeByUserId(userId, id);
			if (ListThree != null && !ListThree.isEmpty()) {
				List<DemandSupplyVO> voThree = new ArrayList<DemandSupplyVO>();
				for (DemandSupplyInfo vt : ListThree) {
					voThree.add(convertVO(vt));
				}
				model.addAttribute("voThree", voThree);
				// for each convertVO
				// exclude first value object
				// order top 3
				// top 4
			}

		}

		return "DsFinish";
	}

	@RequestMapping("/attention")
	public String getAttention(Model model) {
		try {

			User currentUser = PermissionContext.getUser();
			List<DemandSupplyInfo> all = demandSupplyInfoService.getAllByUserId(currentUser.getId());
			List<DemandSupplyVO> vos = new ArrayList<DemandSupplyVO>();
			for (DemandSupplyInfo alls : all) {
				vos.add(convertVO(alls));
			}
			model.addAttribute("vos", vos);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "/attention";
	}

	@ResponseBody
	@RequestMapping("/getByQueryParams")
	public List<DemandSupplyVO> getByQueryParams(@ModelAttribute DSQueryForm form) {
		// 如果产品类别为空，字段全部在一个对象里统一封装处理，否则做对比过滤
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("type", form.getType());
		queryMap.put("timeSearch", form.getTimeSearch());
		queryMap.put("priceSearch", form.getPriceSearch());
		queryMap.put("attentionSearch", form.getAttentionSearch());
		// 得到所有供需信息
		List<DemandSupplyInfo> infos = demandSupplyInfoService.getByQueryParams(queryMap);

		// convert
		List<DemandSupplyVO> tmps = new ArrayList<DemandSupplyVO>();
		for (DemandSupplyInfo info : infos) {
			tmps.add(convertVO(info));
		}
		List<DemandSupplyVO> vos = new ArrayList<DemandSupplyVO>();
		String categoryId = form.getCategoryId();
		if (StringUtils.isNoneBlank(categoryId)) {
			// exclude
			if (vos.size() > 0) {
				for (DemandSupplyVO vo : tmps) {
					Product product = productService.getById(vo.getProductId());
					if (StringUtils.equals(product.getCategory().getId(), categoryId)) {
						vos.add(vo);
					}

				}
			}

		} else {
			vos.addAll(tmps);
		}
		return vos;
	}

	@ResponseBody
	@RequestMapping("/getInfosByParams")
	public List<DemandSupplyVO> getByParam(String type, String order, String field, String categoryId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("field", field);
		paramMap.put("sortOrder", order);
		// 得到所有供需信息
		List<DemandSupplyInfo> infos = demandSupplyInfoService.getByParams(paramMap);

		// convert
		List<DemandSupplyVO> tmps = new ArrayList<DemandSupplyVO>();
		for (DemandSupplyInfo info : infos) {
			tmps.add(convertVO(info));
		}
		List<DemandSupplyVO> vos = new ArrayList<DemandSupplyVO>();
		if (StringUtils.isNoneBlank(categoryId)) {
			// exclude
			if (vos.size() > 0) {
				for (DemandSupplyVO vo : tmps) {
					Product product = productService.getById(vo.getProductId());
					if (StringUtils.equals(product.getCategory().getId(), categoryId)) {
						vos.add(vo);
					}

				}
			}

		} else {
			vos.addAll(tmps);
		}
		return vos;
	}

	private DemandSupplyVO convertVO(DemandSupplyInfo info) {
		DemandSupplyVO vo = new DemandSupplyVO();
		User user = userService.getById(info.getId());
		User currentUser = PermissionContext.getUser();
		if (user != null) {
			vo.setUsername(user.getUsername());
		} else {
			vo.setUsername("未知");
		}
		vo.setId(info.getId());
		vo.setAmount(info.getAmount());
		vo.setPrice(info.getPrice());
		vo.setCreatedAt(info.getCreatedAt());
		vo.setStartDate(info.getStartDate());
		vo.setEndDate(info.getEndDate());
		vo.setDsType(info.getDsType());
		vo.setMemo(info.getMemo());
		vo.setAddress(info.getAddress());
		vo.setPraiseCnt(info.getPraiseCnt());
		String productId = info.getProduct().getId();
		vo.setProductId(productId);

		Product product = productService.getById(productId);
		vo.setProductName(product.getCname());
		int row = praiseInfoService.getCnt(info.getId(), currentUser.getId());
		if (row != 0) {
			PraiseType rType = praiseInfoService.getTypeByUserId(currentUser.getId(), info.getId());
			vo.setView(rType == PraiseType.addPraise);
		} else {
			vo.setView(false);

		}

		String imgs = product.getImgs();
		if (StringUtils.isNoneBlank(imgs)) {
			List<String> productImgs = new ArrayList<String>();
			String[] array = StringUtils.split(imgs, ",");
			for (String img : array) {
				productImgs.add(GlobalValue.QINIU_HOST + img);
			}
			vo.setProductImgs(productImgs);
			vo.setImg(productImgs.get(0));
		}
		return vo;
	}

}
