package com.easy.work.menu.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.menu.model.MenuModel;
import com.easy.work.menu.model.MenusModel;
import com.easy.work.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

/**
 * Created by apple on 18/6/28.
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查找菜单
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public ResponseEntity getMenuList() throws MessageException{
        List<MenusModel> list = menuService.searchMenuList();
        return ResponseUtil.success(list);
    }

    /**
     * 获取所有菜单
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/getMenuAllList", method = RequestMethod.GET)
    public ResponseEntity getMenuAllList() throws MessageException{
        List<MenusModel> list = menuService.searchMenuAllList();
        return ResponseUtil.success(list);
    }

    /**
     *
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    public ResponseEntity saveMenu(@ModelAttribute MenuModel menuModel) throws MessageException,TransformException{
        menuService.saveMenu(menuModel);
        return ResponseUtil.success();
    }

    /**
     * 查找菜单列表
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getMenuById", method = RequestMethod.GET)
    public ResponseEntity getMenuById(@ModelAttribute MenuModel menuModel) throws MessageException,TransformException{
        List<MenuModel> list = menuService.searchMenuById(menuModel);
        return ResponseUtil.success(list);
    }

    /**
     * 查找根节点菜单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getMenuParent", method = RequestMethod.GET)
    public ResponseEntity getMenuParent() throws MessageException,TransformException{
        List<MenuModel> list = menuService.searchMenuParent();
        return ResponseUtil.success(list);
    }

    /**
     * 查找菜单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public ResponseEntity getMenu(@ModelAttribute MenuModel menuModel) throws MessageException,TransformException{
        MenuModel result = menuService.searchMenu(menuModel);
        return ResponseUtil.success(result);
    }

    /**
     * 删除菜单
     * @param menuModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/removeMenu", method = RequestMethod.GET)
    public ResponseEntity removeMenu(@ModelAttribute MenuModel menuModel) throws MessageException{
        menuService.deleteMenu(menuModel);
        return ResponseUtil.success();
    }
}
