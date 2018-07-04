package com.easy.work.menu.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.menu.model.MenusModel;
import com.easy.work.menu.model.MenuModel;
import org.activiti.bpmn.model.Message;

import java.util.List;

/**
 * Created by apple on 18/6/28.
 */
public interface MenuService {

    /**
     * 查找菜单
     * @return
     * @throws MessageException
     */
    public List<MenusModel> searchMenuList() throws MessageException;

    /**
     * 查找所有菜单
     * @return
     * @throws MessageException
     */
    public List<MenusModel> searchMenuAllList() throws MessageException;

    /**
     * 获取根节点菜单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<MenuModel> searchMenuParent() throws MessageException,TransformException;

    /**
     * 添加菜单
     * @param menuModel
     * @throws MessageException
     */
    public void saveMenu(MenuModel menuModel) throws MessageException,TransformException;

    /**
     * 删除菜单
     * @param menuModel
     * @throws MessageException
     */
    public void deleteMenu(MenuModel menuModel) throws MessageException;

    /**
     * 查找菜单列表
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<MenuModel> searchMenuById(MenuModel menuModel) throws MessageException,TransformException;

    /**
     * 查找菜单详细
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public MenuModel searchMenu(MenuModel menuModel) throws MessageException,TransformException;
}
