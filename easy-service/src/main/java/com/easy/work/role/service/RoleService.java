package com.easy.work.role.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.dictionary.model.DictionaryModel;
import com.easy.work.menu.model.MenusModel;
import com.easy.work.role.model.RoleModel;
import com.easy.work.role.model.RolePager;

import java.util.List;

/**
 * Created by apple on 18/6/21.
 */
public interface RoleService {

    /**
     * 查找角色列表
     * @param rolePager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public RolePager searchRoleList(RolePager rolePager) throws MessageException,TransformException;


    /**
     * 保存角色
     * @param roleModel
     * @throws MessageException
     * @throws TransformException
     */
    public void saveRole(RoleModel roleModel) throws MessageException,TransformException;

    /**
     * 查找可见范围
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<DictionaryModel> searchRangeList() throws MessageException,TransformException;

    /**
     * 删除角色
     * @throws MessageException
     */
    public void deleteRole(RoleModel roleModel) throws MessageException;

    /**
     * 查找角色详细
     * @param roleModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public RoleModel searchRoleByRoleId(RoleModel roleModel) throws MessageException,TransformException;

    /**
     * 根据角色id查找菜单id
     * @param roleModel
     * @return
     * @throws MessageException
     */
    public List<String> searchMenuIdById(RoleModel roleModel) throws MessageException;

    /**
     * 保存角色和菜单关联关系
     * @param params
     * @throws MessageException
     */
    public void saveRoleMenu(String params) throws MessageException;

}
