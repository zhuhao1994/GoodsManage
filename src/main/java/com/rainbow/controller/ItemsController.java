package com.rainbow.controller;

import com.rainbow.entity.Items;
import com.rainbow.entity.ItemsCustomer;
import com.rainbow.entity.ItemsQueryVo;
import com.rainbow.service.ItemsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by rainbow on 2016/6/24.
 * 一事专注，便是动人；一生坚守，便是深邃！
 */
@Controller
//@RequestMapping(value = "/items")
public class ItemsController {
    //注入service
    @Autowired
    private ItemsService itemsService;

    @RequestMapping(value = "/queryItems")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
        //测试forward方法request是共享的，获取到id
        System.out.println("共享的id是：" + request.getParameter("id"));
        System.out.println("调用service方法获取数据");
        List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

        //System.out.println("得到的数据：" + itemsList.get(1).getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("items/itemsList");
        return modelAndView;

    }

    //商品修改信息页面的展示
    @RequestMapping(value = "/editItems")
    public ModelAndView editItems(@RequestParam(value = "id") Integer items_id) throws Exception {
        //调用service根据id查出商品信息
        ItemsCustomer itemsCustomer = itemsService.findById(items_id);
        //返回modelAndView
        ModelAndView modelAndView = new ModelAndView();
        //将商品信息放入model
        modelAndView.addObject("itemsCustomer", itemsCustomer);
        modelAndView.setViewName("items/editItems");
        return modelAndView;
    }

    //商品信息提交
    @RequestMapping(value = "/editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request, Integer id,
                                  ItemsCustomer itemsCustomer, MultipartFile items_pic) throws Exception {
        System.out.println("提交的id是：" + request.getParameter("id"));
        System.out.println("传递进来的name：" + request.getParameter("name"));
//调用service更新商品信息，页面需要将商品信息传递到此方法
/**
        //上传图片
        if(items_pic!=null){
            //上传图片物理路径
            String pic_path="C:\\javaWeb\\tmp\\";
            //拿到上传图片的原始名称
            String originalFileName=items_pic.getOriginalFilename();
            //生成一个新的图片名称
            String newFileName= UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
            //新图片
            File newFile=new File(pic_path+newFileName);
            //将内存中的数据写入磁盘
            items_pic.transferTo(newFile);
            //如果上传成功，要将图片写入到itemsCustomer
            itemsCustomer.setPic(newFileName);
        }
 **/
        itemsService.updateItems(id, itemsCustomer);
//        ModelAndView modelAndView = new ModelAndView();
////测试返回一个成功页面
//        modelAndView.setViewName("success");
//        return modelAndView;
        // return "redirect:queryItems";


        return "forward:queryItems";

    }

    //插入数据
    @RequestMapping(value = "/insertItems")
    public String insertItems(HttpServletRequest request, Items items) throws Exception {
        itemsService.insertItems(items);
        return "redirect:queryItems";
    }

    //删除
    @RequestMapping(value = "/deleteItems")
    public String deleteItems(HttpServletRequest request, Integer id) throws Exception {
        itemsService.deleteById(id);
        System.out.println("删除的id是：" + request.getParameter("id"));

        return "forward:queryItems";
    }

    //批量删除，使用数据的方式传入id
    public String delList(Integer[] items_id) throws Exception {
        //删除业务逻辑代码
        return "success";
    }

    //跳转到表单页面
    @RequestMapping(value = "/form")
    public String form(HttpServletRequest request) {
        return "items/itemsForm";
    }

    //批量修改商品的展示页面
    @RequestMapping(value = "/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
        List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);
        System.out.println("得到的数据是：" + itemsList.size() + "条");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("items/editItemsQuery");

        return modelAndView;
    }

    //批量提交商品信息
    //通过itemsQueryVo接收批量的商品信息，将商品信息批量的存储到itemsQueryVo中的itemsList属性
    @RequestMapping(value = "/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
    //业务逻辑代码


        return "success";
    }
}
