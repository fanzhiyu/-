package com.easy.work.role.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.easy.work.dictionary.model.DictionaryModel;
import com.easy.work.dictionary.service.DictionaryService;
import com.easy.work.role.dao.RoleDao;
import com.easy.work.role.dao.RoleMenuDao;
import com.easy.work.role.dao.RoleOrgDao;
import com.easy.work.role.domain.RoleDomain;
import com.easy.work.role.domain.RoleMenuDomain;
import com.easy.work.role.domain.RoleOrgDomain;
import com.easy.work.role.model.RoleModel;
import com.easy.work.role.model.RolePager;
import com.easy.work.role.service.RoleService;
import com.easy.work.user.dao.UserRoleDao;
import com.easy.work.user.domain.UserRoleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 18/6/21.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleOrgDao roleOrgDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查找角色列表
     * @param rolePager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public RolePager searchRoleList(RolePager rolePager) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        RoleDomain roleDomain = TransUtils.formatObject(RoleDomain.class,rolePager);
        roleDomain.setOrgId(userBean.getOrgCode());
        RoleDomain result = roleDao.findPaging(roleDomain);
        rolePager.setCount(result.getCount());
        List<RolePager> list = TransUtils.formatList(RolePager.class,result.getResultList());
        rolePager.setResultList(list);
        return rolePager;
    }

    /**
     * 保存角色
     * @param roleModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveRole(RoleModel roleModel) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        RoleDomain roleDomain = TransUtils.formatObject(RoleDomain.class,roleModel);
        roleDomain.setUpdater(userBean.getUserCode());
        roleDomain.setUpdateTime(DateUtils.getSystemDate());
        if(StringUtils.isNull(roleDomain.getRoleId())){
            roleDomain.setCreater(userBean.getUserCode());
            roleDomain.setCreateTime(DateUtils.getSystemDate());
            roleDomain.setRuleNumber(DateUtils.getYYmm());
            roleDao.insert(roleDomain);
            RoleOrgDomain roleOrgDomain = new RoleOrgDomain();
            roleOrgDomain.setRoleId(roleDomain.getRoleId());
            roleOrgDomain.setOrgId(userBean.getOrgCode());
            roleOrgDomain.setCreater(userBean.getUserCode());
            roleOrgDomain.setCreateTime(DateUtils.getSystemDate());
            roleOrgDomain.setUpdater(userBean.getUserCode());
            roleOrgDomain.setUpdateTime(DateUtils.getSystemDate());
            roleOrgDomain.setRuleNumber(DateUtils.getYYmm());
            roleOrgDao.insert(roleOrgDomain);
        }else{
            roleDao.update(roleDomain);
        }
    }

    /**
     * 查找可见范围
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<DictionaryModel> searchRangeList() throws MessageException, TransformException {
        return dictionaryService.searchDictionaryList(EasyConstant.DIC_TYPE_RANGE);
    }

    /**
     * 删除角色
     * @throws MessageException
     */
    @Override
    public void deleteRole(RoleModel roleModel) throws MessageException {
        checkRoleId(roleModel);
        checkIsDelRole(roleModel);
        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setRoleId(roleModel.getRoleId());
        roleDao.delete(roleDomain);
    }

    /**
     * 查找角色详细
     * @param roleModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public RoleModel searchRoleByRoleId(RoleModel roleModel) throws MessageException, TransformException {
        checkRoleId(roleModel);
        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setRoleId(roleModel.getRoleId());
        RoleDomain result = roleDao.findCondition(roleDomain);
        return TransUtils.formatObject(RoleModel.class, result);
    }

    /**
     * 根据角色id查找菜单id
     * @param roleModel
     * @return
     * @throws MessageException
     */
    @Override
    public List<String> searchMenuIdById(RoleModel roleModel) throws MessageException {
        checkRoleId(roleModel);
        RoleMenuDomain roleMenuDomain = new RoleMenuDomain();
        roleMenuDomain.setRoleId(roleModel.getRoleId());
        List<RoleMenuDomain> resultList = roleMenuDao.findListCondition(roleMenuDomain);
        List<String> menuIds = new ArrayList<String>();
        for(RoleMenuDomain domain : resultList){
            menuIds.add(domain.getMenuId());
        }
        return menuIds;
    }

    /**
     * 保存角色和菜单关联关系
     * @param params
     * @throws MessageException
     */
    @Override
    public void saveRoleMenu(String params) throws MessageException {
        UserBean userBean = userUtils.getUser();
        JSONObject object = JSONObject.parseObject(params);
        String roleId = object.getString("roleId");
        if(StringUtils.isNull(roleId)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1016)));
        }
        JSONArray list = JSONArray.parseArray(object.getString("list"));
        List<RoleMenuDomain> insertDomain = new ArrayList<RoleMenuDomain>();
        RoleMenuDomain domain = null;
        for(int i=0;i<list.size();i++){
            domain = new RoleMenuDomain();
            String menuId = list.get(i).toString();
            domain.setMenuId(menuId);
            domain.setRoleId(roleId);
            domain.setCreater(userBean.getUserCode());
            domain.setCreateTime(DateUtils.getSystemDate());
            domain.setUpdater(userBean.getUserCode());
            domain.setUpdateTime(DateUtils.getSystemDate());
            insertDomain.add(domain);
        }
        RoleMenuDomain roleMenuDomain = new RoleMenuDomain();
        roleMenuDomain.setRoleId(roleId);
        roleMenuDao.delete(roleMenuDomain);
        roleMenuDao.insert(insertDomain);
    }

    /**
     * 验证角色编号
     * @param roleModel
     * @throws MessageException
     */
    private void checkRoleId(RoleModel roleModel) throws MessageException{
        if(StringUtils.isNull(roleModel.getRoleId())){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1016)));
        }
    }

    /**
     * 判断是否允许删除
     * @param roleModel
     * @throws MessageException
     */
    private void checkIsDelRole(RoleModel roleModel) throws MessageException{
        UserRoleDomain userRoleDomain = new UserRoleDomain();
        userRoleDomain.setRoleId(roleModel.getRoleId());
        List<UserRoleDomain> list = userRoleDao.findListCondition(userRoleDomain);
        if(list.size() > 0){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1017));
        }
    }
}
