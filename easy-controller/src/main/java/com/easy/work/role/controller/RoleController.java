package com.easy.work.role.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.dictionary.model.DictionaryModel;
import com.easy.work.menu.model.MenusModel;
import com.easy.work.role.model.RoleModel;
import com.easy.work.role.model.RolePager;
import com.easy.work.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by apple on 18/6/21.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查找角色列表
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getRolePager", method = RequestMethod.GET)
    public ResponseEntity getRolePager(@ModelAttribute RolePager rolePager) throws MessageException,TransformException{
        RolePager result = roleService.searchRoleList(rolePager);
        return ResponseUtil.pageSuccess(result.getCount(),result.getResultList());
    }

    /**
     * 保存角色
     * @param roleModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "saveRole", method = RequestMethod.POST)
    public ResponseEntity saveRole(@ModelAttribute RoleModel roleModel) throws MessageException,TransformException{
        roleService.saveRole(roleModel);
        return ResponseUtil.success();
    }

    /**
     * 查找可见范围
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getRangeList", method = RequestMethod.GET)
    public ResponseEntity getRangeList() throws MessageException,TransformException{
        List<DictionaryModel> list = roleService.searchRangeList();
        return ResponseUtil.success(list);
    }

    /**
     * 删除角色
     * @param roleModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "removeRole", method = RequestMethod.GET)
    public ResponseEntity removeRole(@ModelAttribute RoleModel roleModel) throws MessageException{
        roleService.deleteRole(roleModel);
        return ResponseUtil.success();
    }

    /**
     * 根据id查找用户角色
     * @param roleModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getRoleById", method = RequestMethod.GET)
    public ResponseEntity getRoleById(@ModelAttribute RoleModel roleModel) throws MessageException,TransformException{
        RoleModel result = roleService.searchRoleByRoleId(roleModel);
        return ResponseUtil.success(result);
    }

    /**
     * 根据角色id查找所有的菜单id
     * @param roleModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getCheckedMenu", method = RequestMethod.GET)
    public ResponseEntity getCheckedMenu(@ModelAttribute RoleModel roleModel) throws MessageException{
        List<String> list = roleService.searchMenuIdById(roleModel);
        return ResponseUtil.success(list);
    }

    /**
     * 保存角色和菜单关联关系
     * @param params
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "saveRoleMenu", method = RequestMethod.POST)
    public ResponseEntity saveRoleMenu(String params) throws MessageException{
        roleService.saveRoleMenu(params);
        return ResponseUtil.success();
    }
}
