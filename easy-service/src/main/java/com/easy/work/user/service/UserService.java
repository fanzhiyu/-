package com.easy.work.user.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.user.model.*;

import java.util.List;

/**
 * Created by apple on 18/4/16.
 */
public interface UserService {

    /**
     * 用户登录
     * @param userModel
     * @return
     * @throws MessageException
     */
    public TokenModel login(UserModel userModel) throws MessageException;

    /**
     * 查找用户列表
     * @param userPager
     * @return
     * @throws MessageException
     */
    public UserPager searchUserPager(UserPager userPager) throws MessageException, TransformException;

    /**
     * 查找用户信息
     * @param userInfoModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public UserInfoModel searchUser(UserInfoModel userInfoModel) throws MessageException,TransformException;

    /**
     * 查找用户账号信息
     * @param userAccountModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public UserAccountModel searchUserAccount(UserAccountModel userAccountModel) throws MessageException,TransformException;

    /**
     * 保存用户
     * @param userInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    public void saveUser(UserInfoModel userInfoModel) throws MessageException,TransformException;

    /**
     * 保存用户角色
     * @param params
     * @throws MessageException
     */
    public void saveUserRole(String params) throws MessageException;

    /**
     * 保存账号
     * @param userAccountModel
     * @throws MessageException
     * @throws TransformException
     */
    public void saveUserAccount(UserAccountModel userAccountModel) throws MessageException,TransformException;

    /**
     * 删除用户
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    public void deleteUser(String params) throws MessageException,TransformException;


    /**
     * 查找用户角色
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<UserRoleModel> searchUserRole(UserRoleModel userRoleModel) throws MessageException,TransformException;

    /**
     * 获取人员编号
     * @param orgId
     * @return
     * @throws MessageException
     */
    public String searchMaxUserCode(String orgId) throws MessageException;
}
