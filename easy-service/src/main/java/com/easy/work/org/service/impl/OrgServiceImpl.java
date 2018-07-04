package com.easy.work.org.service.impl;

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
import com.easy.work.core.util.DateUtil;
import com.easy.work.org.dao.OrgDao;
import com.easy.work.org.dao.OrgMenuDao;
import com.easy.work.org.domain.OrgDomain;
import com.easy.work.org.domain.OrgMenuDomain;
import com.easy.work.org.model.OrgInfoModel;
import com.easy.work.org.model.OrgModel;
import com.easy.work.org.model.OrgsModel;
import com.easy.work.org.service.OrgService;
import com.easy.work.user.dao.UserOrgDao;
import com.easy.work.user.domain.UserOrgDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/6/14.
 */
@Transactional
@Service("orgService")
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private OrgMenuDao orgMenuDao;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserOrgDao userOrgDao;

    /**
     * 查找组织架构
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public OrgModel searchOrgList() throws MessageException {
        UserBean userBean = userUtils.getUser();
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setOrgId(userBean.getOrgCode());
        List<OrgDomain> list = orgDao.findListCondition(orgDomain);
        OrgModel orgModel = new OrgModel();
        orgModel.setId(list.size() > 0 ? list.get(0).getOrgId() : null);
        orgModel.setText(list.size() > 0 ? list.get(0).getOrgName() : null);
        String parentId = list.size() > 0 ? list.get(0).getOrgId() : null;
        orgModel.setChildren(getChildren(parentId));
        return orgModel;
    }

    /**
     * 根据组织id查找组织名称
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public OrgInfoModel searchOrgByOrgId(String orgId) throws MessageException, TransformException {
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setOrgId(orgId);
        OrgDomain result = orgDao.findCondition(orgDomain);
        if(StringUtils.isNotNull(result)){
            return TransUtils.formatObject(OrgInfoModel.class,result);
        }
        return null;
    }

    /**
     * 保存组织
     * @param orgInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveOrg(OrgInfoModel orgInfoModel) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        OrgDomain orgDomain = TransUtils.formatObject(OrgDomain.class,orgInfoModel);
        orgDomain.setUpdater(userBean.getUserCode());
        orgDomain.setUpdateTime(DateUtils.getSystemDate());
        if(StringUtils.isNull(orgDomain.getOrgId())){
            orgDomain.setCreater(userBean.getUserCode());
            orgDomain.setCreateTime(DateUtils.getSystemDate());
            orgDomain.setRuleNumber(DateUtils.getYYmm());
            orgDomain.setOrgNo(searchOrgNo(orgDomain.getParentId()));
            orgDao.insert(orgDomain);
        }else{
            orgDao.update(orgDomain);
        }
    }

    /**
     * 根据父节点查找下面的组织
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<OrgsModel> searchOrgByParentId(String parentId) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        OrgDomain orgDomain = new OrgDomain();
        if(StringUtils.isNull(parentId)){
            orgDomain.setOrgId(userBean.getOrgCode());
        }
        orgDomain.setParentId(parentId);
        List<OrgDomain> list = orgDao.findListCondition(orgDomain);
        return TransUtils.formatList(OrgsModel.class,list);
    }

    /**
     * 查找部门
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<OrgModel> searchDepByParentId(String parentId) throws MessageException, TransformException {
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setDepId(parentId);
        orgDomain.setOrgType(EasyConstant.ORG_TYPE_DEP);
        List<OrgDomain> list = orgDao.findListCondition(orgDomain);
        return TransUtils.formatList(OrgModel.class,list);
    }

    /**
     * 查找组织编号
     * @param parentId
     * @return
     * @throws MessageException
     */
    @Override
    public String searchOrgNo(String parentId) throws MessageException {
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setParentId(parentId);
        StringBuffer newId = new StringBuffer();
        String maxId = orgDao.findMaxOrgNo(orgDomain);
        if(StringUtils.isNull(maxId)){
            orgDomain.setOrgId(parentId);
            OrgDomain result = orgDao.findCondition(orgDomain);
            if(StringUtils.isNotNull(result)){
                if(StringUtils.isNotNull(result.getOrgNo())){
                    newId.append(result.getOrgNo());
                    newId.append(EasyConstant.ORG_INIT_NO);
                }else{
                    newId.append(EasyConstant.ORG_ZORE);
                    newId.append(new SimpleDateFormat("yyyy").format(DateUtils.getSystemDate()).substring(2));
                }
            }
            OrgInfoModel resultModel = new OrgInfoModel();
            resultModel.setOrgNo(newId.toString());
        }else{
            Long id = Long.parseLong(maxId);
            String strId = String.valueOf(id + 1);
            for (int i=0;i<maxId.length()-strId.length();i++){
                newId.append(EasyConstant.ORG_ZORE);
            }
            newId.append(strId);
        }
        return newId.toString();
    }

    /**
     * 删除组织
     * @param orgInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void deleteOrg(OrgInfoModel orgInfoModel) throws MessageException {
        checkOrgId(orgInfoModel.getOrgId());
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setParentId(orgInfoModel.getOrgId());
        List<OrgDomain> orgList = orgDao.findListCondition(orgDomain);
        if(orgList.size() > 0){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1021));
        }
        UserOrgDomain userOrgDomain = new UserOrgDomain();
        userOrgDomain.setOrgId(orgInfoModel.getOrgId());
        List<UserOrgDomain> userOrgList = userOrgDao.findListCondition(userOrgDomain);
        if(userOrgList.size() > 0){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1022));
        }
        orgDomain.setOrgId(orgInfoModel.getOrgId());
        orgDao.delete(orgDomain);
    }

    /**
     * 查找组织和菜单关联id
     * @param orgInfoModel
     * @return
     * @throws MessageException
     */
    @Override
    public List<String> searchMenuByOrgId(OrgInfoModel orgInfoModel) throws MessageException {
        OrgMenuDomain orgMenuDomain = new OrgMenuDomain();
        orgMenuDomain.setOrgId(orgInfoModel.getOrgId());
        List<OrgMenuDomain> list = orgMenuDao.findListCondition(orgMenuDomain);
        List<String> menuIds = new ArrayList<String>();
        for(OrgMenuDomain domain : list){
            menuIds.add(domain.getMenuId());
        }
        return menuIds;
    }

    /**
     * 添加组织和菜单关系
     * @param params
     * @throws MessageException
     */
    @Override
    public void saveOrgMenu(String params) throws MessageException {
        UserBean userBean = userUtils.getUser();
        JSONObject object = JSONObject.parseObject(params);
        String orgId = object.getString("orgId");
        JSONArray list = JSONArray.parseArray(object.getString("list"));
        List<OrgMenuDomain> insertList = new ArrayList<OrgMenuDomain>();
        OrgMenuDomain domain = null;
        for(int i=0;i<list.size();i++){
            domain = new OrgMenuDomain();
            String menuId = list.get(i).toString();
            domain.setMenuId(menuId);
            domain.setOrgId(orgId);
            domain.setCreater(userBean.getUserCode());
            domain.setCreateTime(DateUtils.getSystemDate());
            domain.setUpdater(userBean.getUserCode());
            domain.setUpdateTime(DateUtils.getSystemDate());
            insertList.add(domain);
        }
        OrgMenuDomain orgMenuDomain = new OrgMenuDomain();
        orgMenuDomain.setOrgId(orgId);
        orgMenuDao.delete(orgMenuDomain);
        orgMenuDao.insert(insertList);
    }

    /**
     * 获取子节点
     * @param parentId
     * @return
     * @throws TransformException
     */
    private List<OrgModel> getChildren(String parentId){
        OrgDomain orgDomain = new OrgDomain();
        orgDomain.setParentId(parentId);
        List<OrgDomain> list = orgDao.findListCondition(orgDomain);
        List<OrgModel> resultList = new ArrayList<OrgModel>();
        OrgModel orgModel = null;
        if(list.size() == 0){ return null;}
        for(OrgDomain domain : list){
            orgModel = new OrgModel();
            orgModel.setId(domain.getOrgId());
            orgModel.setText(domain.getOrgName());
            orgModel.setChildren(getChildren(domain.getOrgId()));
            resultList.add(orgModel);
        }
        return resultList;
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
}
