package com.easy.work.tab.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.easy.core.bean.UserBean;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.DateUtils;
import com.easy.core.util.StringUtils;
import com.easy.core.util.TransUtils;
import com.easy.core.util.UserUtils;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.obj.domain.ObjDomain;
import com.easy.work.obj.model.ObjModel;
import com.easy.work.obj.model.ObjTabModel;
import com.easy.work.tab.dao.TabDao;
import com.easy.work.tab.dao.TabFieldDao;
import com.easy.work.tab.dao.TabFieldTypeDao;
import com.easy.work.tab.dao.TabTypeDao;
import com.easy.work.tab.domain.TabDomain;
import com.easy.work.tab.domain.TabFieldDomain;
import com.easy.work.tab.domain.TabFieldTypeDomain;
import com.easy.work.tab.domain.TabTypeDomain;
import com.easy.work.tab.model.*;
import com.easy.work.tab.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by apple on 18/5/17.
 */
@Service("tabService")
@Transactional
public class TabServiceImpl implements TabService {

    @Autowired
    private TabTypeDao tabTypeDao;

    @Autowired
    private TabFieldTypeDao tabFieldTypeDao;

    @Autowired
    private TabDao tabDao;

    @Autowired
    private TabFieldDao tabFieldDao;

    @Autowired
    private UserUtils userUtils;

    private static final String[] FIELD_TYPE_ARRAY = {"text","date","datetime","time","timestamp"};

    /**
     * 查找数据库表类型
     * @return
     */
    @Override
    public List<TabTypeModel> searchTabType() throws TransformException  {
        TabTypeDomain tabTypeDomain = new TabTypeDomain();
        List<TabTypeDomain> list = tabTypeDao.findListCondition(tabTypeDomain);
        return  TransUtils.formatList(TabTypeModel.class,list);
    }

    /**
     * 查找数据库字段类型
     * @return
     */
    @Override
    public List<TabFieldTypeModel> searchTabFieldType() throws TransformException{
        TabFieldTypeDomain tabFieldTypeDomain = new TabFieldTypeDomain();
        List<TabFieldTypeDomain> list = tabFieldTypeDao.findList(tabFieldTypeDomain);
        return TransUtils.formatList(TabFieldTypeModel.class,list);
    }

    /**
     * 查找数据库表列表
     * @param tabPager
     * @return
     * @throws MessageException
     */
    @Override
    public TabPager searchTabPager(TabPager tabPager) throws MessageException, TransformException {
        UserBean userBean = userUtils.getUser();
        TabDomain tabDomain = TransUtils.formatObject(TabDomain.class, tabPager);
        tabDomain.setOrgId(userBean.getOrgNo());
        TabDomain resultDomain = tabDao.findPaging(tabDomain);
        tabPager = TransUtils.formatObject(TabPager.class, resultDomain);
        List<TabPager> list = TransUtils.formatList(TabPager.class, resultDomain.getResultList());
        tabPager.setResultList(list);
        return tabPager;
    }

    /**
     * 查找表字段列表
     * @param tabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public TabFieldModel searchTabFieldList(TabFieldModel tabFieldModel) throws MessageException, TransformException {
        checkTabId(tabFieldModel.getTabId(), tabFieldModel.getTabIds());
        TabFieldDomain tabFieldDomain = TransUtils.formatObject(TabFieldDomain.class,tabFieldModel);
        List<TabFieldDomain> resultList = tabFieldDao.findListCondition(tabFieldDomain);
        List<TabFieldInfoModel> list = TransUtils.formatList(TabFieldInfoModel.class, resultList);
        tabFieldModel.setFields(list);
        return tabFieldModel;
    }

    /**
     * 保存数据库表信息
     * @param tabModel
     * @throws MessageException
     */
    @Override
    public void saveTab(TabModel tabModel) throws MessageException, TransformException {
        TabDomain tabDomain = StringUtils.stringToBena(tabModel.getTabFiles(),TabDomain.class);
        TabDomain domain = TransUtils.formatObject(TabDomain.class,tabModel);
        domain.setFieldList(tabDomain.getFieldList());
        if(StringUtils.isNull(domain.getTabId())){
            addTab(domain);
            saveTabField(domain);
        }else{
            updateTab(domain);
        }
    }

    /**
     * 删除表
     * @param tabModel
     * @throws MessageException
     */
    @Override
    public void deleteTab(TabModel tabModel) throws MessageException {
        checkTabId(tabModel.getTabId());
        TabDomain tabDomain = new TabDomain();
        tabDomain.setTabId(tabModel.getTabId());
        TabDomain result = tabDao.findCondition(tabDomain);
        if(StringUtils.isNotNull(result)){
            tabDao.delete(result);
            TabFieldDomain tabFieldDomain = new TabFieldDomain();
            tabFieldDomain.setTabId(result.getTabId());
            tabFieldDao.deleteByTabId(tabFieldDomain);
            tabDao.dropTable(result);
        }
    }

    /**
     * 查找所有数据表
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<TabModel> searchAll() throws TransformException {
        TabDomain tabDomain = new TabDomain();
        List<TabDomain> reslutList = tabDao.findListCondition(tabDomain);
        return TransUtils.formatList(TabModel.class,reslutList);
    }


    /**
     * 查找表详细
     * @param tabModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public TabModel searchTabDetails(TabModel tabModel) throws MessageException, TransformException {
        checkTabId(tabModel.getTabId());
        TabDomain tabDomain = new TabDomain();
        tabDomain.setTabId(tabModel.getTabId());
        TabDomain resultDomain = tabDao.findCondition(tabDomain);
        if(StringUtils.isNull(resultDomain)){
            throw new MessageException("表编号不正确");
        }
        TabFieldDomain tabFieldDomain = new TabFieldDomain();
        tabFieldDomain.setTabId(resultDomain.getTabId());
        List<TabFieldDomain> list = tabFieldDao.findListCondition(tabFieldDomain);
        List<TabFieldInfoModel> resultList = TransUtils.formatList(TabFieldInfoModel.class,list);
        tabModel = TransUtils.formatObject(TabModel.class, resultDomain);
        tabModel.setFields(resultList);
        return tabModel;
    }

    /**
     * 查找表数据
     * @param tabDataPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public TabDataPager searchTabDataPager(TabDataPager tabDataPager) throws MessageException, TransformException {
        TabDomain tabDomain = null;
        if(StringUtils.isNull(tabDataPager.getObjModel())){
            checkTabId(tabDataPager.getTabId());
            tabDomain = searchTabDomain(tabDataPager.getTabId());
        }else{
            tabDomain = searchTabDomain(tabDataPager.getObjModel());
        }
        tabDomain.setPageNo(tabDataPager.getPageNo());
        tabDomain.setPageSize(tabDataPager.getPageSize());
        Map<String,Object> json = StringUtils.isNotNull(tabDataPager.getParams()) ? tabDataPager.getParams() : null;
        tabDomain.setParamList(json);
        Map<String,Object> res = tabDao.findTabDataPaging(tabDomain);
        tabDataPager.setTitles((List<String>) res.get("titles"));
        tabDataPager.setCount(Integer.parseInt(res.get("count").toString()));
        tabDataPager.setResultList((List<Map<String, Object>>) res.get("resultList"));
        return tabDataPager;
    }

    /**
     * 查找数据详细
     * @param tabDataModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public Map<String, Object> searchTabData(TabDataModel tabDataModel) throws MessageException, TransformException {
        checkTabId(tabDataModel.getTabId());
        TabDomain tabDomain = searchTabDomain(tabDataModel.getTabId());
        JSONObject json = StringUtils.isNotNull(tabDataModel.getParams()) ? JSONObject.parseObject(tabDataModel.getParams()) : null;
        tabDomain.setParamList(json);
        return tabDao.findTabData(tabDomain);
    }

    /**
     * 删除对应表的数据
     * @param tabModel
     */
    @Override
    public void deleteTabData(TabModel tabModel) throws MessageException {
        checkTabId(tabModel.getTabId());
        if(StringUtils.isNotNull(tabModel.getKeyMap()) && tabModel.getKeyMap().size()  == 0){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1025));
        }
        TabDomain tabDomain = new TabDomain();
        tabDomain.setTabId(tabModel.getTabId());
        TabDomain resDomain = tabDao.findCondition(tabDomain);
        if(StringUtils.isNotNull(resDomain)){
            resDomain.setKeyMap(tabModel.getKeyMap());
            tabDao.deleteData(resDomain);
        }
    }

    /**
     * 添加数据库表信息
     * @param tabDomain
     */
    private void addTab(TabDomain tabDomain){
        UserBean userBean = userUtils.getUser();
        tabDomain.setCreater(userBean.getUserCode());
        tabDomain.setCreateTime(DateUtils.getSystemDate());
        tabDomain.setUpdater(userBean.getUserCode());
        tabDomain.setUpdateTime(DateUtils.getSystemDate());
        tabDomain.setRuleNumber(DateUtils.getYYmm());
        tabDomain.setOrgId(userBean.getOrgNo());
        tabDomain.setTabSourceName(tabDomain.getTabName());
        tabDomain.setTabName(getTabName(tabDomain));
        tabDao.insert(tabDomain);
    }

    /**
     * 保存数据库表字段信息
     * @param tabDomain
     * @throws MessageException
     */
    private void saveTabField(TabDomain tabDomain) throws MessageException {
        checkTabId(tabDomain.getTabId());
        UserBean userBean = userUtils.getUser();
        List<TabFieldDomain> insertList = new ArrayList<TabFieldDomain>();
        for(TabFieldDomain domain : tabDomain.getFieldList()){
            if(Arrays.binarySearch(FIELD_TYPE_ARRAY,domain.getTabFieldType()) < 0
                    && StringUtils.isNull(domain.getTabFieldLength())){
                throw new MessageException(Manager.getMessage(EasyMessage.EASY1026,domain.getTabFieldType()));
            }
            domain.setTabId(tabDomain.getTabId());
            domain.setUpdater(userBean.getUserCode());
            domain.setUpdateTime(DateUtils.getSystemDate());
            domain.setTabIsKey(domain.getTabIsKey() == "true" ? "1" : "0");
            domain.setTabIsNull(domain.getTabIsNull() == "true" ? "1" : "0");
            domain.setCreater(userBean.getUserCode());
            domain.setCreateTime(DateUtils.getSystemDate());
            domain.setRuleNumber(DateUtils.getYYmm());
            insertList.add(domain);
        }
        TabFieldDomain tabFieldDomain = new TabFieldDomain();
        tabFieldDomain.setTabId(tabDomain.getTabId());
        tabFieldDao.deleteByTabId(tabFieldDomain);
        tabFieldDao.insert(insertList);
        tabDao.createTable(tabDomain);
    }

    /**
     * 修改表信息
     * @param tabDomain
     */
    private void updateTab(TabDomain tabDomain) throws MessageException{
        checkTabId(tabDomain.getTabId());
        UserBean userBean = userUtils.getUser();
        TabDomain where = new TabDomain();
        where.setTabId(tabDomain.getTabId());
        TabDomain updateDomain = tabDao.findCondition(where);
        if(StringUtils.isNull(updateDomain)){
            // 数据库表编号没有查到匹配的数据库请确认编号是否正确
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1012));
        }
        // 修改之前先把原表删除
        tabDao.dropTable(updateDomain);
        updateDomain.setTabSourceName(tabDomain.getTabName());
        updateDomain.setTabName(getTabName(tabDomain));
        updateDomain.setUpdater(userBean.getUserCode());
        updateDomain.setUpdateTime(DateUtils.getSystemDate());
        updateDomain.setFieldList(tabDomain.getFieldList());
        // 修改新表
        tabDao.update(updateDomain);
        // 修改字段
        saveTabField(updateDomain);
    }

    /**
     * 查找出表所对应的字段
     * @param objModels
     * @return
     * @throws MessageException
     */
    private TabDomain searchTabDomain(ObjModel objModels) throws MessageException{
        TabDomain resultDomain = new TabDomain();
        List<TabFieldDomain> fieldList = new ArrayList<TabFieldDomain>();
        List<String> tabNames = new ArrayList<String>();
        for(ObjTabModel objTabModel : objModels.getTabIds()){
            TabFieldDomain tabFieldDomain = new TabFieldDomain();
            if(StringUtils.isNotNull(objTabModel.getFieldIds()) && objTabModel.getFieldIds().size() > 0){
                tabFieldDomain.setTabFieldIdList(objTabModel.getFieldIds());
            }else{
                tabFieldDomain.setTabId(objTabModel.getTabId());
            }
            TabDomain tabDomain = new TabDomain();
            tabDomain.setTabId(objTabModel.getTabId());
            TabDomain createDomain = tabDao.findCondition(tabDomain);
            if(StringUtils.isNull(createDomain)){
                throw new MessageException(Manager.getMessage(EasyMessage.EASY1010));
            }

            List<TabFieldDomain> resultFields = tabFieldDao.findListCondition(tabFieldDomain);
            for(TabFieldDomain domain : resultFields){
                domain.setAlias(createDomain.getTabName()+"_"+domain.getTabFieldName());
                domain.setTabFieldName(createDomain.getTabName()+"."+domain.getTabFieldName());
                fieldList.add(domain);
            }
            if(StringUtils.isNull(fieldList)){
                throw new MessageException(Manager.getMessage(EasyMessage.EASY1013));
            }
            tabNames.add(createDomain.getTabName());
        }
        resultDomain.setFieldList(fieldList);
        resultDomain.setTabNameList(tabNames);
        return resultDomain;
    }

    /**
     * 查找出表所对应的字段
     * @param tabId
     * @return
     * @throws MessageException
     */
    private TabDomain searchTabDomain(String tabId) throws MessageException{
        List<ObjTabModel> tabIds = new ArrayList<ObjTabModel>();
        ObjTabModel model = new ObjTabModel();
        model.setTabId(tabId);
        tabIds.add(model);
        ObjModel objModel = new ObjModel();
        objModel.setTabIds(tabIds);
        return searchTabDomain(objModel);
    }

    /**
     * 验证tabid是否填写
     * @param tabId
     * @throws MessageException
     */
    private void checkTabId(String tabId) throws MessageException{
        if(StringUtils.isNull(tabId)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1011));
        }
    }

    /**
     * 验证tabid是否填写
     * @param tabIds
     * @throws MessageException
     */
    private void checkTabId(String tabId, List<String> tabIds) throws MessageException{
        if(StringUtils.isNull(tabId) && (StringUtils.isNull(tabIds) || tabIds.size() == 0)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1011));
        }
    }

    /**
     * 修改表名
     * @param tabDomain
     */
    private String getTabName(TabDomain tabDomain){
        UserBean userBean = userUtils.getUser();
        // 查找表的前缀
        TabTypeDomain tabTypeDomain = new TabTypeDomain();
        tabTypeDomain.setTabTypeId(tabDomain.getTabType());
        TabTypeDomain result = tabTypeDao.findCondition(tabTypeDomain);
        StringBuffer tabName = new StringBuffer(result.getTabTypeCode());
        if(StringUtils.isNotNull(result)){
            tabName.append(StringUtils.isNotNull(userBean.getOrgNo()) ? "_"+userBean.getOrgNo() : "");
            tabName.append("_"+tabDomain.getTabName());
        }
        return tabName.toString();
    }
}
