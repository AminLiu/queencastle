package com.queencastle.web.controllers.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
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
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.SysResourceInfoService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.goods.CategoryService;
import com.queencastle.service.interf.goods.DemandSupplyInfoService;
import com.queencastle.service.interf.goods.FeedBackService;
import com.queencastle.service.interf.goods.ProductService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/goods")
public class ProductCategoryController {
    private static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ResourceUploadService resourceUploadService;
    @Autowired
    private SysResourceInfoService sysResourceInfoService;
    @Autowired
    private DemandSupplyInfoService demandSupplyInfoService;
    @Autowired
    private FeedBackService feedBackService;
    @Autowired
    private AreaInfoService areaInfoService;

    @RequestMapping("/productList")
    public String productList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/goods/productList";
    }

    @RequestMapping("/categoryList")
    public String categoryList() {
        return "/goods/categoryList";
    }

    @RequestMapping("/demandSupplyList")
    public String demandSupplyList() {
        return "/goods/demandSupplyList";
    }

    @RequestMapping("/feedbackList")
    public String feedbackList() {
        return "/goods/feedbackList";
    }


    /*************************************************************************/
    @ResponseBody
    @RequestMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @ResponseBody
    @RequestMapping("/getCategorysByParams")
    public PageInfo<Category> getCategorysByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Category> pageInfo = categoryService.getCategorysByParams(page, rows, map);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping("/getProductsByParams")
    public PageInfo<Product> getProductsByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Product> pageInfo = productService.getProductsByParams(page, rows, map);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping("/getDemandSupplysByParams")
    public PageInfo<DemandSupplyVO> getDemandSupplysByParams(int page, int rows, String pname,
            String startDate, String endDate, String dsType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNoneBlank(pname)) {
            map.put("pname", pname);
        }
        if (StringUtils.isNoneBlank(startDate)) {
            Date startDay = DateUtils.getYmdDate(startDate);
            map.put("startDay", startDay);
        }
        if (StringUtils.isNoneBlank(endDate)) {
            Date endDay = DateUtils.getYmdDate(endDate);
            map.put("endDay", endDay);
        }
        if (StringUtils.isNoneBlank(dsType)) {
            DemandSupplyType type = DemandSupplyType.getByName(dsType);
            map.put("type", type);
        }


        PageInfo<DemandSupplyInfo> pageInfo =
                demandSupplyInfoService.getDemandSupplysByParams(page, rows, map);
        List<DemandSupplyInfo> list = pageInfo.getRows();
        for (DemandSupplyInfo info : list) {
            String productId = info.getProduct().getId();
            Product product = productService.getById(productId);
            info.setProduct(product);
        }
        PageInfo<DemandSupplyVO> results = new PageInfo<DemandSupplyVO>();
        results.setPage(pageInfo.getPage());
        results.setTotal(pageInfo.getTotal());
        List<DemandSupplyVO> vos = new ArrayList<DemandSupplyVO>();
        for (DemandSupplyInfo info : list) {
            DemandSupplyVO vo = new DemandSupplyVO();
            vo.setCheck(info.getCheck());
            vo.setId(info.getId());
            vo.setCname(info.getProduct().getCname());
            vo.setStartDate(info.getStartDate());
            vo.setEndDate(info.getEndDate());
            vo.setAmount(info.getAmount());
            vo.setPrice(info.getPrice());
            vo.setMemo(info.getMemo());
            vo.setCreatedAt(info.getCreatedAt());
            vo.setDsType(info.getDsType());
            vos.add(vo);
        }
        results.setRows(vos);
        return results;
    }

    @ResponseBody
    @RequestMapping("/getFeedbackByParams")
    public PageInfo<FeedBack> getFeedbackByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<FeedBack> pageInfo = feedBackService.getFeedbackByParams(page, rows, map);
        return pageInfo;
    }


    /*************************************************************************/
    @ResponseBody
    @RequestMapping("/saveCategory")
    public boolean saveCategory(String cname) {
        Category category = new Category();
        category.setCname(cname);
        category.setEname(PinYinUtils.fullPinyin(cname));
        categoryService.insert(category);
        return true;
    }

    @ResponseBody
    @RequestMapping("/saveProduct")
    public boolean saveProduct(String cname, String categoryId, String intro, @RequestParam(
            value = "imgs", required = false) MultipartFile[] imgs) {
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
            for (MultipartFile mfile : imgs) {
                byte[] bytes = mfile.getBytes();
                String originName = mfile.getOriginalFilename();
                String fileName = DateUtils.getCurrFullTime() + "_" + originName;
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

            productService.insert(product);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/auditDemandInfo")
    public boolean auditDemandInfo(String check, String id) {
        if (StringUtils.equals(check, "1")) {
            demandSupplyInfoService.updateCheck(1, id);
            return true;
        } else if (StringUtils.equals(check, "2")) {
            demandSupplyInfoService.updateCheck(2, id);
            return true;
        }

        return false;
    }

    public boolean addDemandInfo(String productId, int amount, String startDate, String endDate,
            String dsType, String memo, String provinceId, String cityId, String areaId,
            String address) {
        DemandSupplyInfo dsInfo = new DemandSupplyInfo();
        Product product = productService.getById(productId);
        dsInfo.setProduct(product);
        dsInfo.setAmount(amount);
        dsInfo.setStartDate(DateUtils.getYmdDate(startDate));
        dsInfo.setEndDate(DateUtils.getYmdDate(endDate));
        dsInfo.setDsType(DemandSupplyType.getByName(dsType));
        dsInfo.setMemo(memo);
        dsInfo.setCheck(0);
        demandSupplyInfoService.insert(dsInfo);
        UserGoodsAddress uga = new UserGoodsAddress();
        if (StringUtils.isNoneBlank(provinceId)) {
            Province province = areaInfoService.getProvinceById(provinceId);
            uga.setProvince(province);
        }
        if (StringUtils.isNoneBlank(cityId)) {
            City city = areaInfoService.getCityById(cityId);
            uga.setCity(city);
        }
        if (StringUtils.isNoneBlank(areaId)) {
            Area area = areaInfoService.getAreaById(areaId);
            uga.setArea(area);
        }
        uga.setAddress(address);


        return true;
    }



}
