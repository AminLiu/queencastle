package com.queencastle.weixin.controllers.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.goods.Category;
import com.queencastle.dao.model.weixin.Agreement;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.goods.CategoryService;
import com.queencastle.service.interf.weixin.AgreementService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/goods")
public class ProductWeiXinController<agreementService> {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AgreementService agreementService;

    @RequestMapping("/index")
    public String index(Model model, String type) {
        // 判断用户是否在同意协议名单中
        String userId = PermissionContext.getUser().getId();
        List<String> ty = agreementService.getByUserId(userId);
        if (ty.contains(type)) {

            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("type", type);

            List<Province> provinces = areaInfoService.getAllProvinces();
            model.addAttribute("provinces", provinces);

            return "/goods/productIdx";
        } else {
            return "redirect:/resources/infos/agreementIdx.html?type=" + type;
        }
    }

    @RequestMapping("/insert")
    public String insert(String type) {
        // // 将同意协议的用户插入数据库保存
        String userId = PermissionContext.getUser().getId();
        Agreement agreement = new Agreement();
        agreement.setUserId(userId);
        agreement.setType(type);
        agreementService.insert(agreement);
        return "redirect:/goods/index?type=" + type;

    }
}
