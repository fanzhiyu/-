package com.easy.work.menu.service.impl;

import com.easy.core.bean.UserBean;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.DateUtils;
import com.easy.core.util.StringUtils;
import com.easy.core.util.TransUtils;
import com.easy.core.util.UserUtils;
import com.easy.work.core.constant.EasyConstant;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.menu.dao.MenuDao;
import com.easy.work.menu.domain.MenuDomain;
import com.easy.work.menu.model.MenusModel;
import com.easy.work.menu.model.MenuModel;
import com.easy.work.menu.service.MenuService;
import com.easy.work.org.dao.OrgMenuDao;
import com.easy.work.org.domain.OrgMenuDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 18/6/28.
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private OrgMenuDao orgMenuDao;

    @Autowired
    private UserUtils userUtils;

    /**
     * 查找左侧菜单
     * @return
     * @throws MessageException
     */
    @Override
    public List<MenusModel> searchMenuList() throws MessageException {
        UserBean userBean = userUtils.getUser();
        return getMenuList(EasyConstant.STATUS_ON,userBean.getRoleIds(),null);
    }

    /**
     * 查找所有菜单
     * @return
     * @throws MessageException
     */
    @Override
    public List<MenusModel> searchMenuAllList() throws MessageException {
        UserBean userBean = userUtils.getUser();
        return getMenuList(null,null,userBean.getUserCode());
    }

    /**
     * 获取根节点菜单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<MenuModel> searchMenuParent() throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setParentId(EasyConstant.PARENT_ID);
        menuDomain.setOrgId(userBean.getOrgCode());
        menuDomain.setCreater(userBean.getUserCode());
        List<MenuDomain> list = menuDao.findListCondition(menuDomain);
        return TransUtils.formatList(MenuModel.class,list);
    }

    /**
     * 添加菜单
     * @param menuModel
     * @throws MessageException
     */
    @Override
    public void saveMenu(MenuModel menuModel) throws MessageException,TransformException {
        UserBean userBean = userUtils.getUser();
        MenuDomain menuDomain = TransUtils.formatObject(MenuDomain.class,menuModel);
        menuDomain.setUpdater(userBean.getUserCode());
        menuDomain.setUpdateTime(DateUtils.getSystemDate());
        menuDomain.setMenuUrl(StringUtils.isNotNull(menuModel.getObjId()) ? "/list?objId="+menuModel.getObjId() : menuModel.getMenuUrl());
        if(StringUtils.isNull(menuDomain.getMenuId())){
            menuDomain.setCreater(userBean.getUserCode());
            menuDomain.setCreateTime(DateUtils.getSystemDate());
            menuDomain.setRuleNumber(DateUtils.getYYmm());
            menuDomain.setParentId(StringUtils.isNull(menuModel.getParentId()) ? EasyConstant.PARENT_ID : menuModel.getParentId());
            menuDao.insert(menuDomain);
            OrgMenuDomain orgMenuDomain = new OrgMenuDomain();
            orgMenuDomain.setOrgId(userBean.getOrgCode());
            orgMenuDomain.setMenuId(menuDomain.getMenuId());
            orgMenuDomain.setCreater(userBean.getUserCode());
            orgMenuDomain.setCreateTime(DateUtils.getSystemDate());
            orgMenuDomain.setUpdater(userBean.getUserCode());
            orgMenuDomain.setUpdateTime(DateUtils.getSystemDate());
            orgMenuDao.insert(orgMenuDomain);
        }else{
            menuDao.update(menuDomain);
        }
    }

    /**
     * 删除菜单
     * @param menuModel
     * @throws MessageException
     */
    @Override
    public void deleteMenu(MenuModel menuModel) throws MessageException {
        checkMenuId(menuModel);
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setMenuId(menuModel.getMenuId());
        menuDao.delete(menuDomain);
        OrgMenuDomain orgMenuDomain = new OrgMenuDomain();
        orgMenuDomain.setOrgMenuId(menuModel.getMenuId());
        orgMenuDao.delete(orgMenuDomain);
    }

    /**
     * 查找菜单列表
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<MenuModel> searchMenuById(MenuModel menuModel) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setParentId(menuModel.getMenuId());
        menuDomain.setOrgId(userBean.getOrgCode());
        List<MenuDomain> list = menuDao.findListCondition(menuDomain);
        return TransUtils.formatList(MenuModel.class,list);
    }

    /**
     * 查找菜单详细
     * @param menuModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public MenuModel searchMenu(MenuModel menuModel) throws MessageException, TransformException {
        checkMenuId(menuModel);
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setMenuId(menuModel.getMenuId());
        MenuDomain result = menuDao.findCondition(menuDomain);
        return TransUtils.formatObject(MenuModel.class,result);
    }

    /**
     * 获取子
     * @param parentId
     * @return
     */
    private List<MenusModel> getChildren(String parentId,String status,List<String> roleIds,String userCode){
        UserBean userBean = userUtils.getUser();
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setParentId(parentId);
        menuDomain.setOrgId(userBean.getOrgCode());
        menuDomain.setMenuStatus(status);
        menuDomain.setRoleIds(roleIds);
        menuDomain.setCreater(userCode);
        List<MenuDomain> list = menuDao.findListCondition(menuDomain);
        if(list.size() == 0){ return null;}
        return getMenus(list,status,roleIds,userCode);
    }

    /**
     * 对象转换
     * @param list
     * @return
     */
    private List<MenusModel> getMenus(List<MenuDomain> list,String status,List<String> roleIds,String userCode){
        List<MenusModel> resultList = new ArrayList<MenusModel>();
        MenusModel menusModel = null;
        for(MenuDomain domain : list){
            menusModel = new MenusModel();
            menusModel.setId(domain.getMenuId());
            menusModel.setText(domain.getMenuName());
            menusModel.setMenuCode(domain.getMenuCode());
            menusModel.setMenuIcon(domain.getMenuIcon());
            menusModel.setMenuUrl(domain.getMenuUrl());
            menusModel.setChildren(getChildren(domain.getMenuId(),status,roleIds,userCode));
            resultList.add(menusModel);
        }
        return resultList;
    }

    /**
     * 获取菜单
     * @param status
     * @return
     */
    private List<MenusModel> getMenuList(String status,List<String> roleIds,String userCode){
        UserBean userBean = userUtils.getUser();
        MenuDomain menuDomain = new MenuDomain();
        menuDomain.setParentId(EasyConstant.PARENT_ID);
        menuDomain.setMenuStatus(status);
        menuDomain.setOrgId(userBean.getOrgCode());
        menuDomain.setRoleIds(roleIds);
        menuDomain.setCreater(userCode);
        List<MenuDomain> list = menuDao.findListCondition(menuDomain);
        return getMenus(list, status, roleIds,userCode);
    }

    /**
     * 验证菜单id是否填写
     * @param menuModel
     * @throws MessageException
     */
    private void checkMenuId(MenuModel menuModel) throws MessageException{
        if(StringUtils.isNull(menuModel.getMenuId())){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1018)));
        }
    }
}
