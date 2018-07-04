package com.easy.work.user.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.user.domain.UserAccountDomain;
import com.easy.work.user.model.*;
import com.easy.work.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by apple on 18/4/16.
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户登录接口
     * @param userModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@ModelAttribute UserModel userModel) throws MessageException{
        TokenModel tokenModel = userService.login(userModel);
        return ResponseUtil.success(tokenModel);
    }

    /**
     * 根据组织查找用户信息
     * @param userPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getUserByOrg", method = RequestMethod.GET)
    public ResponseEntity getUserByOrg(@ModelAttribute UserPager userPager) throws MessageException,TransformException{
        UserPager result = userService.searchUserPager(userPager);
        return ResponseUtil.pageSuccess(result.getCount(),result.getResultList());
    }

    /**
     * 查找用户信息
     * @param userInfoModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getUserByUserId", method = RequestMethod.GET)
    public ResponseEntity getUserByUserId(@ModelAttribute UserInfoModel userInfoModel) throws MessageException,TransformException{
        UserInfoModel result = userService.searchUser(userInfoModel);
        return ResponseUtil.success(result);
    }

    /**
     * 保存用户
     * @param userInfoModel
     * @return
     */
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public ResponseEntity saveUser(@ModelAttribute UserInfoModel userInfoModel) throws MessageException,TransformException{
        userService.saveUser(userInfoModel);
        return ResponseUtil.success();
    }

    /**
     * 保存用户角色
     * @param params
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/saveUserRole",method = RequestMethod.POST)
    public ResponseEntity saveUserRole(String params) throws MessageException{
        userService.saveUserRole(params);
        return ResponseUtil.success();
    }

    /**
     * 保存用户账号
     * @param userAccountModel
     * @return
     */
    @RequestMapping(value = "/saveUserAccount",method = RequestMethod.POST)
    public ResponseEntity saveUserAccount(@ModelAttribute UserAccountModel userAccountModel) throws MessageException,TransformException{
        userService.saveUserAccount(userAccountModel);
        return ResponseUtil.success();
    }

    /**
     * 删除用户
     * @param params
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/removeUser",method = RequestMethod.POST)
    public ResponseEntity removeUser(String params) throws MessageException,TransformException{
        userService.deleteUser(params);
        return ResponseUtil.success();
    }

    /**
     * 获取用户账号
     * @param userAccountModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getUserAccount",method = RequestMethod.GET)
    public ResponseEntity getUserAccount(@ModelAttribute UserAccountModel userAccountModel) throws MessageException,TransformException{
        UserAccountModel result = userService.searchUserAccount(userAccountModel);
        return ResponseUtil.success(result);
    }

    /**
     * 查找用户权限
     * @param userRoleModel
     * @return
     */
    @RequestMapping(value = "/getUserRole",method = RequestMethod.GET)
    public ResponseEntity getUserRole(@ModelAttribute UserRoleModel userRoleModel) throws MessageException,TransformException{
        List<UserRoleModel> list = userService.searchUserRole(userRoleModel);
        return ResponseUtil.success(list);
    }

    /**
     * 获取员工编号
     * @param orgId
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/getUserCode",method = RequestMethod.GET)
    public ResponseEntity getUserCode(String orgId) throws MessageException{
        Object result = userService.searchMaxUserCode(orgId);
        return ResponseUtil.success(result);
    }
}
