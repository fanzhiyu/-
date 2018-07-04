package com.easy.work.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easy.core.bean.UserBean;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.*;
import com.easy.work.core.constant.EasyConstant;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.core.util.DateUtil;
import com.easy.work.org.dao.OrgDao;
import com.easy.work.org.domain.OrgDomain;
import com.easy.work.user.dao.*;
import com.easy.work.user.domain.*;
import com.easy.work.user.model.*;
import com.easy.work.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 18/4/16.
 */
@Repository("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private UserArchivesDao userArchivesDao;

    @Autowired
    private UserOrgDao userOrgDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserUtils userUtils;

    /**
     * 用户登录
     * @param userModel
     * @return
     * @throws MessageException
     */
    @Override
    public TokenModel login(UserModel userModel) throws MessageException {
        checkUser(userModel);
        UserAccountDomain accountDomain = new UserAccountDomain();
        accountDomain.setLoginName(userModel.getUsername());
        accountDomain.setPassword(userModel.getPassword());
        UserAccountDomain resultAccount = userAccountDao.findCondition(accountDomain);
        if(StringUtils.isNull(resultAccount)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1002,Manager.getMessage(EasyMessage.EASY1003)));
        }
        UserRoleDomain userRoleDomain = new UserRoleDomain();
        userRoleDomain.setUserId(resultAccount.getUserId());
        List<UserRoleDomain> roleList = userRoleDao.findListCondition(userRoleDomain);
        if(StringUtils.isNull(roleList) || roleList.size() == 0){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1019));
        }
        List<String> roleIds = new ArrayList<String>();
        for(UserRoleDomain domain : roleList){
            roleIds.add(domain.getRoleId());
        }

        UserBean userBean = new UserBean();
        userBean.setUsername(resultAccount.getUserName());
        userBean.setUserCode(resultAccount.getUserCode());
        userBean.setOrgCode(resultAccount.getOrgId());
        userBean.setUserMobile(resultAccount.getUserTel());
        userBean.setOrgParent(resultAccount.getParentId());
        userBean.setOrgNo(resultAccount.getOrgNo());
        userBean.setRoleIds(roleIds);
        TokenModel tokenModel = null;
        try {
            String subject = jwtUtil.generalSubject(userBean);
            String token = jwtUtil.createJWT(EasyConstant.JWT_ID,subject);
            String refreshToken = jwtUtil.createJWT(EasyConstant.JWT_ID,subject,EasyConstant.JWT_REFRESH_TTL);
            tokenModel = new TokenModel();
            tokenModel.setToken(token);
            tokenModel.setRefreshToken(refreshToken);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return tokenModel;
    }

    /**
     * 查找用户列表
     * @param userPager
     * @return
     * @throws MessageException
     */
    @Override
    public UserPager searchUserPager(UserPager userPager) throws MessageException, TransformException {
        UserDomain userDomain = new UserDomain();
        userDomain.setOrgId(userPager.getOrgId());
        userDomain.setPageNo(userPager.getPageNo());
        userDomain.setPageSize(userPager.getPageSize());
        UserDomain resultDomain = userDao.findPaging(userDomain);
        userPager.setCount(resultDomain.getCount());
        List<UserPager> list = TransUtils.formatList(UserPager.class,resultDomain.getResultList());
        userPager.setResultList(list);
        return userPager;
    }

    /**
     * 查找用户信息
     * @param userInfoModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public UserInfoModel searchUser(UserInfoModel userInfoModel) throws MessageException, TransformException {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userInfoModel.getUserId());
        UserDomain result = userDao.findCondition(userDomain);
        return TransUtils.formatObject(UserInfoModel.class,result);
    }

    /**
     * 查找用户账号信息
     * @param userAccountModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public UserAccountModel searchUserAccount(UserAccountModel userAccountModel) throws MessageException, TransformException {
        UserAccountDomain userAccountDomain = new UserAccountDomain();
        userAccountDomain.setUserId(userAccountModel.getUserId());
        UserAccountDomain result = userAccountDao.findCondition(userAccountDomain);
        if(StringUtils.isNotNull(result)){
            return TransUtils.formatObject(UserAccountModel.class,result);
        }
        return null;
    }

    /**
     * 保存用户
     * @param userInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveUser(UserInfoModel userInfoModel) throws MessageException, TransformException {
        checkOrgId(userInfoModel.getOrgId());
        UserBean userBean = userUtils.getUser();
        UserDomain userDomain = TransUtils.formatObject(UserDomain.class,userInfoModel);
        userDomain.setUpdateTime(DateUtils.getSystemDate());
        userDomain.setUpdater(userBean.getUserCode());
        UserArchivesDomain userArchivesDomain = TransUtils.formatObject(UserArchivesDomain.class,userInfoModel);
        userArchivesDomain.setUpdateTime(DateUtils.getSystemDate());
        userArchivesDomain.setUpdater(userBean.getUserCode());
        if(StringUtils.isNull(userInfoModel.getUserId())){
            userDomain.setCreater(userBean.getUserCode());
            userDomain.setCreateTime(DateUtils.getSystemDate());
            userDomain.setRuleNumber(DateUtils.getYYmm());
            userDomain.setUserCode(this.searchMaxUserCode(userInfoModel.getOrgId()));
            userDao.insert(userDomain);
            userArchivesDomain.setUserId(userDomain.getUserId());
            userArchivesDomain.setCreater(userBean.getUserCode());
            userArchivesDomain.setCreateTime(DateUtils.getSystemDate());
            userArchivesDomain.setRuleNumber(DateUtils.getYYmm());
            userArchivesDao.insert(userArchivesDomain);
            UserOrgDomain userOrgDomain = new UserOrgDomain();
            userOrgDomain.setOrgId(getOrgId(userInfoModel.getOrgId()));
            userOrgDomain.setUserId(userDomain.getUserId());
            userOrgDomain.setCreater(userBean.getUserCode());
            userOrgDomain.setCreateTime(DateUtils.getSystemDate());
            userOrgDomain.setUpdateTime(DateUtils.getSystemDate());
            userOrgDomain.setUpdater(userBean.getUserCode());
            userOrgDomain.setRuleNumber(DateUtils.getYYmm());
            userOrgDao.insert(userOrgDomain);
        }else{
            userDao.update(userDomain);
            userArchivesDao.update(userArchivesDomain);
        }
    }

    /**
     * 保存用户角色
     * @param params
     * @throws MessageException
     */
    @Override
    public void saveUserRole(String params) throws MessageException {
        JSONObject jsonObject = JSON.parseObject(params);
        String userId = jsonObject.getString("userId");
        checkUserId(userId);
        JSONArray list = JSONArray.parseArray(jsonObject.getString("list"));
        List<UserRoleDomain> inserList = new ArrayList<UserRoleDomain>();
        UserBean userBean = userUtils.getUser();
        for(int i=0; i<list.size(); i++){
            JSONObject object = JSONObject.parseObject(list.get(i).toString());
            String roleId = object.getString("roleId");
            UserRoleDomain userRoleDomain = new UserRoleDomain();
            userRoleDomain.setRoleId(roleId);
            userRoleDomain.setUserId(userId);
            userRoleDomain.setRuleNumber(DateUtils.getYYmm());
            userRoleDomain.setCreateTime(DateUtils.getSystemDate());
            userRoleDomain.setCreater(userBean.getUserCode());
            userRoleDomain.setUpdateTime(DateUtils.getSystemDate());
            userRoleDomain.setUpdater(userBean.getUserCode());
            inserList.add(userRoleDomain);
        }
        UserRoleDomain delete = new UserRoleDomain();
        delete.setUserId(userId);
        userRoleDao.delete(delete);
        userRoleDao.insert(inserList);
    }

    /**
     * 保存账号
     * @param userAccountModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveUserAccount(UserAccountModel userAccountModel) throws MessageException, TransformException {
        checkUserId(userAccountModel.getUserId());
        UserBean userBean = userUtils.getUser();
        UserAccountDomain userAccountDomain = TransUtils.formatObject(UserAccountDomain.class,userAccountModel);
        userAccountDomain.setUpdater(userBean.getUserCode());
        userAccountDomain.setUpdateTime(DateUtils.getSystemDate());
        if(StringUtils.isNull(userAccountDomain.getAccountId())){
            userAccountDomain.setCreater(userBean.getUserCode());
            userAccountDomain.setRuleNumber(DateUtils.getYYmm());
            userAccountDomain.setCreateTime(DateUtils.getSystemDate());
            userAccountDao.insert(userAccountDomain);
        }else{
            userAccountDao.update(userAccountDomain);
        }

    }

    /**
     * 删除用户
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void deleteUser(String params) throws MessageException, TransformException {
        JSONArray list = JSON.parseArray(params);
        List<UserDomain> deleteUserList = new ArrayList<UserDomain>();
        List<UserRoleDomain> deleteRoleList = new ArrayList<UserRoleDomain>();
        List<UserAccountDomain> deleteAccountList = new ArrayList<UserAccountDomain>();
        List<UserOrgDomain> deleteOrgList = new ArrayList<UserOrgDomain>();
        List<UserArchivesDomain> deleteArchivesList = new ArrayList<UserArchivesDomain>();
        UserDomain userDomain = null;
        UserRoleDomain userRoleDomain = null;
        UserAccountDomain userAccountDomain = null;
        UserOrgDomain userOrgDomain = null;
        UserArchivesDomain userArchivesDomain = null;
        for (int i=0;i<list.size();i++){
            JSONObject object = JSONObject.parseObject(list.get(i).toString());
            String userId = object.getString("userId");
            checkUserId(userId);
            userDomain = new UserDomain();
            userDomain.setUserId(userId);
            deleteUserList.add(userDomain);
            userRoleDomain = new UserRoleDomain();
            userRoleDomain.setUserId(userId);
            deleteRoleList.add(userRoleDomain);
            userAccountDomain = new UserAccountDomain();
            userAccountDomain.setUserId(userId);
            deleteAccountList.add(userAccountDomain);
            userOrgDomain = new UserOrgDomain();
            userOrgDomain.setUserId(userId);
            deleteOrgList.add(userOrgDomain);
            userArchivesDomain = new UserArchivesDomain();
            userArchivesDomain.setUserId(userId);
            deleteArchivesList.add(userArchivesDomain);
        }
        userDao.delete(deleteUserList);
        userAccountDao.delete(deleteAccountList);
        userArchivesDao.delete(deleteArchivesList);
        userRoleDao.delete(deleteRoleList);
        userOrgDao.delete(deleteOrgList);
    }

    /**
     * 查找用户角色
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<UserRoleModel> searchUserRole(UserRoleModel userRoleModel) throws MessageException, TransformException {
        checkUserId(userRoleModel.getUserId());
        UserRoleDomain userRoleDomain = new UserRoleDomain();
        userRoleDomain.setUserId(userRoleModel.getUserId());
        List<UserRoleDomain> list = userRoleDao.findListCondition(userRoleDomain);
        return TransUtils.formatList(UserRoleModel.class,list);
    }

    /**
     * 获取人员编号
     * @param orgId
     * @return
     * @throws MessageException
     */
    @Override
    public String searchMaxUserCode(String orgId) throws MessageException {
        checkOrgId(orgId);
        // 判断orgid是否是部门如果是部门如父级的orgid
        orgId = getOrgId(orgId);
        UserDomain userDomain = new UserDomain();
        userDomain.setOrgId(orgId);
        String userCode = userDao.findMaxUserCode(userDomain);
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setOrgId(orgId);
        OrgDomain result = orgDao.findCondition(orgDomain);
        StringBuffer newId = new StringBuffer();
        if(StringUtils.isNotNull(result)){
            if(StringUtils.isNull(userCode)){
                newId.append(result.getOrgNo());
                newId.append("0001");
            }else{
                int index = result.getOrgNo().length();
                String maxId = userCode.substring(index,userCode.length());
                String subId = String.valueOf(Integer.parseInt(maxId)+1);
                newId.append(result.getOrgNo());
                for(int i=0;i<maxId.length()-subId.length();i++){
                    newId.append("0");
                }
                newId.append(subId);
            }
        }
        return newId.toString();
    }

    /**
     * 检查用户名和密码
     * @param userModel
     * @throws MessageException
     */
    private void checkUser(UserModel userModel) throws MessageException{
        if(StringUtils.isNull(userModel.getUsername())){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1007)));
        }
        if(StringUtils.isNull(userModel.getPassword())){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1008)));
        }
    }

    /**
     * 验证用户编号
     * @param userId
     * @throws MessageException
     */
    private void checkUserId(String userId) throws MessageException{
        if(StringUtils.isNull(userId)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1014)));
        }
    }

    /**
     * 验证组织id
     * @param orgId
     * @throws MessageException
     */
    private void checkOrgId(String orgId) throws MessageException{
        if(StringUtils.isNull(orgId)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1020)));
        }
    }

    /**
     * 判断orgId是否是部门如果是部门获取父级id
     * @param orgId
     * @return
     */
    private String getOrgId(String orgId){
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setOrgId(orgId);
        OrgDomain result = orgDao.findCondition(orgDomain);
        if(StringUtils.isNotNull(result) && StringUtils.isEquals(EasyConstant.ORG_TYPE_DEP,result.getOrgType())){
            orgId = result.getParentId();
        }
        return orgId;
    }
}
