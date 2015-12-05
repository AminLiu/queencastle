package com.queencastle.weixin.controllers.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.goods.Category;
import com.queencastle.dao.model.goods.DemandSupplyInfo;
import com.queencastle.dao.model.goods.DemandSupplyType;
import com.queencastle.dao.model.goods.FeedBack;
import com.queencastle.dao.model.goods.Product;
import com.queencastle.dao.model.goods.UserGoodsAddress;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.dao.utils.PinYinUtils;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.SysResourceInfoService;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.goods.CategoryService;
import com.queencastle.service.interf.goods.DemandSupplyInfoService;
import com.queencastle.service.interf.goods.FeedBackService;
import com.queencastle.service.interf.goods.PraiseInfoService;
import com.queencastle.service.interf.goods.ProductService;
import com.queencastle.service.interf.goods.userGoodsAddressService;
import com.queencastle.service.interf.relations.UserRelationService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.weixin.vos.UserRelationVO;
import com.queencastle.weixin.vos.UserVO;

/**
 * 产品，类别，供需接口
 * 
 * @author YuDongwei
 *
 */
@Controller
public class ProductCategoryController {

    private Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
    @Autowired
    private FeedBackService feedBackService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private DemandSupplyInfoService demandSupplyInfoService;

    @Autowired
    private ResourceUploadService resourceUploadService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private SysResourceInfoService sysResourceInfoService;
    @Autowired
    private PraiseInfoService praiseInfoService;
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private userGoodsAddressService userGoodsAddressService;

    /*********************** Index ******************************/

    @RequestMapping("/personalCenter")
    public String personalCenter(Model model) {
        User user = PermissionContext.getUser();
        UserDetailInfo udi = userDetailInfoService.getByUserId(user.getId());

        String img = udi.getImg();
        if (StringUtils.isNoneBlank(img)) {
            udi.setImg(GlobalValue.QINIU_HOST + img);
        }
        City city = areaInfoService.getByCityCode(udi.getCityCode());
        Province province = areaInfoService.getProvinceByprovinceCode(udi.getProvinceCode());
        model.addAttribute("user", user);
        model.addAttribute("udi", udi);
        model.addAttribute("city", city);
        model.addAttribute("province", province);
        return "personalCenter";
    }

    @RequestMapping("/attentionDetail")
    public String getAttentionDetail(String infoId, Model model) {
        try {
            List<String> userIdList = praiseInfoService.getUserIdByInfoId(infoId);
            if (!CollectionUtils.isEmpty(userIdList)) {
                List<UserVO> vos = new ArrayList<UserVO>();
                for (String userId : userIdList) {
                    // 拿到用户核心信息
                    User user = userService.getById(userId);
                    if (user == null) {
                        continue;
                    }
                    UserVO vo = new UserVO();
                    vo.setHeadImg(user.getHeadImg());
                    vo.setUserId(user.getId());
                    String username = user.getUsername();
                    if (StringUtils.isBlank(username)) {
                        username = user.getOutNick();
                    }
                    vo.setUsername(username);
                    vo.setAddress("");
                    vos.add(vo);

                }
                model.addAttribute("vos", vos);
                return "/attentionDetail";
            } else {
                return "/empty";
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "/exception";
        }

    }

    @RequestMapping("/recommend")
    public String getRecommend(Model model) {
        try {
            User currentUser = PermissionContext.getUser();
            String parentId = currentUser.getId();
            List<String> userIdList = userRelationService.getUserIdByParentId(parentId);
            if (!CollectionUtils.isEmpty(userIdList)) {
                List<UserRelationVO> vos = new ArrayList<UserRelationVO>();
                for (String userId : userIdList) {
                    // 拿到用户核心信息
                    User user = userService.getById(userId);
                    if (user == null) {
                        continue;
                    }
                    UserRelationVO vo = new UserRelationVO();
                    vo.setHeadImg(user.getHeadImg());
                    vo.setUserId(user.getId());
                    vos.add(vo);

                }
                model.addAttribute("vos", vos);
                return "/recommend";
            } else {
                return "/empty";
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "/exception";
        }

    }

    @RequestMapping("/demandSupplyIdx")
    public String demandSupplyIdx(Model model, String type) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("type", type);
        return "addDemandSupply";
    }

    @RequestMapping("/queenIdx")
    public String queenIdx(Model model) {
        User user = PermissionContext.getUser();
        if (user != null) {
            model.addAttribute("uid", user.getId());
        }
        return "/queenIdx";
    }

    @ResponseBody
    @RequestMapping("/addOrMinus")
    public boolean addOrMinus(String infoId, int score) {
        User user = PermissionContext.getUser();
        if (StringUtils.isNoneBlank(infoId)) {
            // 点赞 取消点赞 计入数据库，计入日志
            // demandSupplyInfoService.addPaiseCnt(score, infoId, user.getId());
            return demandSupplyInfoService.addAttention(score, infoId, user.getId()) == 0;

        }
        return false;
    }

    /*********************** Add ******************************/

    @ResponseBody
    @RequestMapping("/addContent")
    public boolean addContent(String content, String infoId) {
        FeedBack feedBack = new FeedBack();
        feedBack.setContent(content);
        User user = PermissionContext.getUser();
        if (user != null) {
            feedBack.setInfoId(infoId);
            feedBack.setUserId(user.getId());
            feedBackService.insert(feedBack);
            return true;
        }
        return false;

    }

    @ResponseBody
    @RequestMapping("/addCategory")
    public Category addCategory(String cname) {
        Category category = new Category();
        category.setCname(cname);
        String ename = PinYinUtils.fullPinyin(cname);
        category.setEname(ename);
        categoryService.insert(category);
        return category;
    }

    @Transactional
    @RequestMapping("/addAllInfo")
    public String addAllInfo(String cname, String categoryId, String intro, @RequestParam(
            value = "imgs", required = false) MultipartFile[] imgs, int amount, int price,
            String startDate, String endDate, String memo, String type, String address,
            Integer praiseCnt, String provinceCode, String cityCode, String areaCode) {
        String productId = addProduct(cname, categoryId, intro, imgs);
        String s = addAddress(provinceCode, cityCode, areaCode, address);
        if (StringUtils.isNoneBlank(productId)) {
            praiseCnt = praiseCnt == null ? 0 : praiseCnt;
            String dsId =
                    addDemandSupplyInfo(productId, amount, price, startDate, endDate, memo, type,
                            address, praiseCnt);
            return "redirect:/dsManager/preview?id=" + dsId;
        }
        return "/exception";
    }

    // 直辖市一类的是没有具体到县级地区编码的
    public String addAddress(String provinceCode, String cityCode, String areaCode, String address) {
        UserGoodsAddress userGoodsAddress = new UserGoodsAddress();
        User currentUser = PermissionContext.getUser();
        String userId = currentUser.getId();
        userGoodsAddress.setUserId(userId);

        Province provice = areaInfoService.getProvinceByprovinceCode(provinceCode);
        userGoodsAddress.setProvince(provice);
        City city = areaInfoService.getByCityCode(cityCode);
        userGoodsAddress.setCity(city);
        Area area = areaInfoService.getAllAreasByAreaCode(areaCode);
        userGoodsAddress.setArea(area);
        userGoodsAddress.setAddress(address);
        userGoodsAddressService.insert(userGoodsAddress);
        return null;

    }

    public String addProduct(String cname, String categoryId, String intro, MultipartFile[] imgs) {
        try {
            Product product = new Product();
            product.setCname(cname);
            String ename = PinYinUtils.fullPinyin(cname);
            product.setEname(ename);
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
            product.setIntro(intro);
            User currentUser = PermissionContext.getUser();
            List<String> productImgs = new ArrayList<String>();
            for (MultipartFile mfile : imgs) {
                byte[] bytes = mfile.getBytes();
                if (bytes.length > 0) {
                    String originName = mfile.getOriginalFilename();
                    String fileName = DateUtils.getCurrFullTime() + "_" + originName;
                    productImgs.add(fileName);
                    String key = resourceUploadService.uploadBytes(bytes, fileName);
                    if (StringUtils.isNoneBlank(key)) {
                        SysResourceInfo info = new SysResourceInfo();
                        info.setFileKey(key);
                        info.setFileName(fileName);
                        info.setOriginName(originName);
                        String ext = originName.substring((originName.lastIndexOf(".") + 1));
                        info.setFileExt(ext);
                        if (currentUser != null) {
                            info.setUserId(currentUser.getId());
                        } else {
                            info.setUserId(IdTypeHandler.encode(1L));
                        }
                        sysResourceInfoService.insert(info);
                    }

                }

            }
            if (!CollectionUtils.isEmpty(productImgs)) {
                product.setImgs(StringUtils.join(productImgs.toArray(), ","));
            }

            productService.insert(product);
            return product.getId();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public String addDemandSupplyInfo(String productId, int amount, int price, String startDate,
            String endDate, String memo, String type, String address, int praiseCnt) {
        try {
            DemandSupplyInfo demandSupplyInfo = new DemandSupplyInfo();
            demandSupplyInfo.setAmount(amount);
            demandSupplyInfo.setPrice(price);
            demandSupplyInfo.setStartDate(DateUtils.getYmdDate(startDate));
            demandSupplyInfo.setEndDate(DateUtils.getYmdDate(endDate));
            Product product = new Product();
            product.setId(productId);
            User user = PermissionContext.getUser();
            demandSupplyInfo.setUserId(user.getId());
            demandSupplyInfo.setProduct(product);
            demandSupplyInfo.setMemo(memo);
            demandSupplyInfo.setDsType(DemandSupplyType.getByName(type));
            demandSupplyInfo.setAddress(address);
            demandSupplyInfo.setPraiseCnt(0);
            demandSupplyInfoService.insert(demandSupplyInfo);
            return demandSupplyInfo.getId();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /*********************** Search ******************************/
    @ResponseBody
    @RequestMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
