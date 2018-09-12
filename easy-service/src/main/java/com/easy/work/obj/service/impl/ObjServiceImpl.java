package com.easy.work.obj.service.impl;

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
import com.easy.work.forms.model.FormsModel;
import com.easy.work.forms.service.FormsService;
import com.easy.work.obj.dao.ObjDao;
import com.easy.work.obj.dao.ObjTabDao;
import com.easy.work.obj.dao.ObjTabFieldDao;
import com.easy.work.obj.domain.ObjDomain;
import com.easy.work.obj.domain.ObjTabDomain;
import com.easy.work.obj.domain.ObjTabFieldDomain;
import com.easy.work.obj.model.*;
import com.easy.work.obj.service.ObjService;
import com.easy.work.tab.model.TabDataPager;
import com.easy.work.tab.model.TabFieldInfoModel;
import com.easy.work.tab.model.TabModel;
import com.easy.work.tab.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by apple on 18/5/30.
 */
@Service("objService")
@Transactional
public class ObjServiceImpl implements ObjService{

    @Autowired
    private ObjDao objDao;

    @Autowired
    private ObjTabDao objTabDao;

    @Autowired
    private ObjTabFieldDao objTabFieldDao;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TabService tabService;

    @Autowired
    private FormsService formsService;

    /**
     * 查找数据对象分页
     * @param objPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public ObjPager searchObjPager(ObjPager objPager) throws MessageException, TransformException {
        ObjDomain objDomain = TransUtils.formatObject(ObjDomain.class, objPager);
        ObjDomain resultDomain = objDao.findPaging(objDomain);
        List<ObjPager> list = TransUtils.formatList(ObjPager.class,resultDomain.getResultList());
        objPager.setResultList(list);
        objPager.setCount(resultDomain.getCount());
        return objPager;
    }

    /**
     * 查找所有对象
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<ObjModel> searchObjAll() throws MessageException, TransformException {
        ObjDomain objDomain = new ObjDomain();
        List<ObjDomain> result = objDao.findListCondition(objDomain);
        return TransUtils.formatList(ObjModel.class,result);
    }

    /**
     * 查找对象字段
     * @param objTabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<ObjTabFieldModel> searchObjTabFieldList(ObjTabFieldModel objTabFieldModel) throws MessageException, TransformException {
        ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
        objTabFieldDomain.setObjId(objTabFieldModel.getObjId());
        List<ObjTabFieldDomain> resultDomain = objTabFieldDao.findListCondition(objTabFieldDomain);
        return TransUtils.formatList(ObjTabFieldModel.class, resultDomain);
    }

    /**
     * 保存数据对象
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void saveObj(ObjModel objModel,String params) throws MessageException, TransformException {
        if(StringUtils.isNull(objModel.getObjId())){
            addObj(objModel,params);
        }else{
            updateObj(objModel,params);
        }
    }

    /**
     * 查找详细
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public ObjModel searchObjDetails(ObjModel objModel) throws MessageException, TransformException {
        checkObjId(objModel);
        ObjDomain objDomain = new ObjDomain();
        objDomain.setObjId(objModel.getObjId());
        ObjDomain resObj = objDao.findCondition(objDomain);
        ObjTabDomain objTabDomain = new ObjTabDomain();
        objTabDomain.setObjId(objModel.getObjId());
        List<ObjTabDomain> resObjTabList = objTabDao.findListCondition(objTabDomain);
        ObjModel res = TransUtils.formatObject(ObjModel.class,resObj);
        res.setTabIds(getObjTabModel(resObjTabList));
        return res;
    }

    /**
     * 根对象id查找表数据
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public TabDataPager searchTabDataByObjId(ObjModel objModel) throws MessageException, TransformException {
        checkObjId(objModel);
        ObjDomain objDomain = new ObjDomain();
        objDomain.setObjId(objModel.getObjId());
        ObjDomain resultObjDomain = objDao.findCondition(objDomain);
        FormsModel formsModel = new FormsModel();
        formsModel.setFormId(resultObjDomain.getSearchFormId());
        FormsModel resForm = formsService.searchFormsDetails(formsModel);
        Map<String,Object> paramsList = new HashMap<>();
        if(StringUtils.isNotNull(resForm)){
            ObjTabDomain search = new ObjTabDomain();
            search.setObjId(resForm.getObjId());
            List<ObjTabDomain> resList = objTabDao.findListCondition(search);
            for(ObjTabDomain domain : resList){
                JSONArray list = JSONArray.parseArray(objModel.getParamList());
                if(StringUtils.isNotNull(list)){
                    for(int i=0;i<list.size();i++){
                        JSONObject object = JSONObject.parseObject(list.get(i).toString());
                        Iterator<String> sIterator = object.keySet().iterator();
                        while (sIterator.hasNext()) {
                            String key = sIterator.next();
                            ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
                            objTabFieldDomain.setObjTabFieldId(key);
                            objTabFieldDomain.setObjTabId(domain.getObjTabId());
                            ObjTabFieldDomain result = objTabFieldDao.findCondition(objTabFieldDomain);
                            if(StringUtils.isNotNull(result)){
                                Object val  =object.get(key);
                                if(StringUtils.isNotNull(val)){
                                    String filedName =domain.getTabName()+"."+result.getFieldName();
                                    if (StringUtils.isEquals("like",result.getObjCondition())){
                                        val = "like '%"+ val+"%'";
                                    }else{
                                        val = result.getObjCondition() + " '" + val+"'";
                                    }
                                    paramsList.put(filedName.toLowerCase(),val);
                                }
                            }
                        }
                    }
                }
            }
        }
        ObjTabDomain objTabDomain = new ObjTabDomain();
        objTabDomain.setObjId(objModel.getObjId());
        List<ObjTabDomain> resultList = objTabDao.findListCondition(objTabDomain);
        ObjModel mod = new ObjModel();
        mod.setTabIds(getObjTabModel(resultList));
        TabDataPager tabDataPager = new TabDataPager();
        tabDataPager.setObjModel(mod);
        tabDataPager.setParams(paramsList);
        return tabService.searchTabDataPager(tabDataPager);
    }

    /**
     * 查找表数据详细
     * @param params
     * @return
     * @throws MessageException
     */
    @Override
    public JSONObject searchTabDataDetails(String params) throws MessageException,TransformException {
        JSONObject jsonObject = new JSONObject();
        JSONObject object = JSONObject.parseObject(params);
        if(StringUtils.isNotNull(object)){
            List<ObjTabDomain> resList = getObjTab(params);
            for(ObjTabDomain domain : resList){
                JSONObject list = JSONObject.parseObject(object.getString("list"));
                if(StringUtils.isNotNull(list)){
                    Iterator<String> sIterator = list.keySet().iterator();
                    while (sIterator.hasNext()) {
                        String key = sIterator.next();
                        ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
                        objTabFieldDomain.setObjTabId(domain.getObjTabId());
                        objTabFieldDomain.setObjFieldId(key);
                        ObjTabFieldDomain res = objTabFieldDao.findCondition(objTabFieldDomain);
                        if(StringUtils.isNotNull(res)){
                            jsonObject.put(res.getObjTabFieldId(), list.get(key));
                        }
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     * 查找obj对应的table
     * @param params
     * @return
     */
    private List<ObjTabDomain> getObjTab(String params) throws MessageException,TransformException{
        JSONObject object = JSONObject.parseObject(params);
        List<ObjTabDomain> resList = null;
        if(StringUtils.isNotNull(object)) {
            String objId = object.getString("objId");
            checkObjId(objId);
            ObjDomain objDomain = new ObjDomain();
            objDomain.setObjId(objId);
            ObjDomain resObjDomain = objDao.findCondition(objDomain);
            FormsModel formsModel = new FormsModel();
            formsModel.setFormId(resObjDomain.getAddFormId());
            FormsModel resForms = formsService.searchFormsDetails(formsModel);
            ObjTabDomain objTabDomain = new ObjTabDomain();
            objTabDomain.setObjId(resForms.getObjId());
            resList = objTabDao.findListCondition(objTabDomain);
        }
        return resList;
    }


    /**
     * 删除数据对象
     * @param objModel
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void deleteObj(ObjModel objModel) throws MessageException {
        checkObjId(objModel);
        ObjTabDomain objTabDomain = new ObjTabDomain();
        objTabDomain.setObjId(objModel.getObjId());
        List<ObjTabDomain> resList = objTabDao.findListCondition(objTabDomain);
        List<ObjTabFieldDomain> deleteObjTabField = new ArrayList<ObjTabFieldDomain>();
        ObjTabFieldDomain objTabFieldDomain = null;
        for (ObjTabDomain domain : resList){
            objTabFieldDomain = new ObjTabFieldDomain();
            objTabFieldDomain.setObjTabId(domain.getObjTabId());
            deleteObjTabField.add(objTabFieldDomain);
        }
        objTabFieldDao.delete(deleteObjTabField);
        objTabDao.delete(resList);
        ObjDomain objDomain = new ObjDomain();
        objDomain.setObjId(objModel.getObjId());
        objDao.delete(objDomain);
    }

    /**
     * 根据objId查找表单
     * @param objModel
     * @return
     * @throws MessageException
     */
    @Override
    public FormsModel searchFormByObjId(ObjModel objModel) throws MessageException,TransformException{
        checkObjId(objModel);
        ObjDomain objDomain = new ObjDomain();
        objDomain.setObjId(objModel.getObjId());
        ObjDomain result = objDao.findCondition(objDomain);
        FormsModel formsModel = new FormsModel();
        formsModel.setFormId(result.getAddFormId());
        return formsService.searchFormsDetails(formsModel);
    }

    /**
     * 根据objId查找出字段
     * @param objModel
     * @return
     * @throws MessageException
     */
    @Override
    public FormsModel searchFieldByObjId(ObjModel objModel) throws MessageException,TransformException {
        checkObjId(objModel);
        ObjDomain objDomain = new ObjDomain();
        objDomain.setObjId(objModel.getObjId());
        ObjDomain result = objDao.findCondition(objDomain);
        FormsModel formsModel = new FormsModel();
        formsModel.setFormId(result.getSearchFormId());
        return formsService.searchFormsDetails(formsModel);
    }

    /**
     * 删除表数据
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public void deleteTabData(String params) throws MessageException, TransformException {
        JSONObject object = JSONObject.parseObject(params);
        if(StringUtils.isNotNull(object)){
            List<ObjTabDomain> resList = getObjTab(params);
            for(ObjTabDomain domain : resList){
                JSONObject list = JSONObject.parseObject(object.getString("list"));
                if(StringUtils.isNotNull(list)){
                    Iterator<String> sIterator = list.keySet().iterator();
                    while (sIterator.hasNext()) {
                        String key = sIterator.next();
                        ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
                        objTabFieldDomain.setObjTabId(domain.getObjTabId());
                        objTabFieldDomain.setObjFieldId(key);
                        objTabFieldDomain.setObjKey(EasyConstant.IS_KEY);
                        ObjTabFieldDomain res = objTabFieldDao.findCondition(objTabFieldDomain);
                        if(StringUtils.isNotNull(res)){
                            Map<String,Object> keyMap = new HashMap<>();
                            keyMap.put(res.getFieldName(),list.get(key));
                            TabModel tabModel = new TabModel();
                            tabModel.setTabId(domain.getTabId());
                            tabModel.setKeyMap(keyMap);
                            tabService.deleteTabData(tabModel);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取对象和数据表关联关系
     * @param result
     * @return
     */
    private List<ObjTabModel> getObjTabModel(List<ObjTabDomain>  result){
        List<ObjTabModel> modelList = new ArrayList<ObjTabModel>();
        ObjTabModel objTabModel = null;
        for(ObjTabDomain domain : result){
            objTabModel = new ObjTabModel();
            ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
            objTabFieldDomain.setObjTabId(domain.getObjTabId());
            List<ObjTabFieldDomain> list = objTabFieldDao.findListCondition(objTabFieldDomain);
            if(StringUtils.isNull(list) || list.size() == 0){
                break;
            }
            List<String> tabFieldIdList = new ArrayList<String>();
            for(ObjTabFieldDomain d : list){
                tabFieldIdList.add(d.getObjFieldId());
            }
            objTabModel.setFieldIds(tabFieldIdList);
            objTabModel.setTabId(domain.getTabId());
            modelList.add(objTabModel);
        }
        return modelList;
    }

    /**
     * 添加数据对象
     * @param objModel
     */
    private void addObj(ObjModel objModel,String params) throws TransformException{
        UserBean userBean = userUtils.getUser();
        ObjDomain objDomain = TransUtils.formatObject(ObjDomain.class,objModel);
        objDomain.setCreater(userBean.getUserCode());
        objDomain.setCreateTime(DateUtils.getSystemDate());
        objDomain.setUpdater(userBean.getUserCode());
        objDomain.setUpdateTime(DateUtils.getSystemDate());
        objDomain.setRuleNumber(DateUtils.getYYmm());
        objDao.insert(objDomain);
        insertObjTab(objDomain.getObjId(), params);

    }

    /**
     * 添加对象关联表
     * @param objId
     * @param params
     */
    private void insertObjTab(String objId,String params){
        UserBean userBean = userUtils.getUser();
        //**************>> start 添加对象对应的数据表 <<**************
        List<ObjTabFieldDomain> insertTabField = new ArrayList<ObjTabFieldDomain>();
        JSONArray list = JSONArray.parseArray(params);
        if(StringUtils.isNotNull(list)){
            ObjTabDomain objTabDomain = null;
            for(int i=0;i<list.size();i++){
                JSONObject object = JSONObject.parseObject(list.get(i).toString());
                String tabId = object.getString("tabId");
                objTabDomain = new ObjTabDomain();
                objTabDomain.setObjId(objId);
                objTabDomain.setTabId(tabId);
                objTabDomain.setRuleNumber(DateUtils.getYYmm());
                objTabDomain.setCreater(userBean.getUserCode());
                objTabDomain.setCreateTime(DateUtils.getSystemDate());
                objTabDomain.setUpdater(userBean.getUserCode());
                objTabDomain.setUpdateTime(DateUtils.getSystemDate());
                objTabDao.insert(objTabDomain);
                JSONArray fieldList = JSONArray.parseArray(object.getString("fields"));
                if(StringUtils.isNotNull(fieldList)){
                    ObjTabFieldDomain objTabFieldDomain = null;
                    for(int j=0;j<fieldList.size();j++){
                        JSONObject field = JSONObject.parseObject(fieldList.get(j).toString());
                        String fieldId = field.getString("fieldId");
                        String condition = field.getString("condition");
                        String key = field.getString("objKey") == "true" ? "1" : "0";
                        objTabFieldDomain = new ObjTabFieldDomain();
                        objTabFieldDomain.setObjFieldId(fieldId);
                        objTabFieldDomain.setObjTabId(objTabDomain.getObjTabId());
                        objTabFieldDomain.setObjCondition(condition);
                        objTabFieldDomain.setObjKey(key);
                        objTabFieldDomain.setCreater(userBean.getUserCode());
                        objTabFieldDomain.setCreateTime(DateUtils.getSystemDate());
                        objTabFieldDomain.setUpdater(userBean.getUserCode());
                        objTabFieldDomain.setUpdateTime(DateUtils.getSystemDate());
                        objTabFieldDomain.setRuleNumber(DateUtils.getYYmm());
                        insertTabField.add(objTabFieldDomain);
                    }
                }
            }
        }
        objTabFieldDao.insert(insertTabField);
        //**************>> end 添加对象对应的数据表 <<************
    }

    /**
     * 更新数据对象
     * @param objModel
     */
    private void updateObj(ObjModel objModel,String params) throws MessageException,TransformException{
        checkObjId(objModel);
        UserBean userBean = userUtils.getUser();
        ObjDomain objDomain = TransUtils.formatObject(ObjDomain.class,objModel);
        objDomain.setUpdater(userBean.getUserCode());
        objDomain.setUpdateTime(DateUtils.getSystemDate());
        ObjTabDomain objTabDomain = new ObjTabDomain();
        objTabDomain.setObjId(objModel.getObjId());
        List<ObjTabDomain> resultList = objTabDao.findListCondition(objTabDomain);
        List<ObjTabFieldDomain> delObjTabField = new ArrayList<ObjTabFieldDomain>();
        ObjTabFieldDomain objTabFieldDomain = null;
        for(ObjTabDomain domain : resultList){
            objTabFieldDomain = new ObjTabFieldDomain();
            objTabFieldDomain.setObjTabId(domain.getObjTabId());
            delObjTabField.add(objTabFieldDomain);
        }
        objDao.update(objDomain);
        objTabDao.delete(objTabDomain);
        objTabFieldDao.delete(delObjTabField);
        insertObjTab(objModel.getObjId(),params);
    }

    /**
     * 验证对象编号
     * @param objModel
     * @throws MessageException
     */
    private void checkObjId(ObjModel objModel) throws MessageException{
        if(StringUtils.isNull(objModel.getObjId())){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1024)));
        }
    }

    /**
     * 验证对象编号
     * @param objId
     * @throws MessageException
     */
    public void checkObjId(String objId) throws MessageException{
        if(StringUtils.isNull(objId)){
            throw new MessageException(Manager.getMessage(EasyMessage.EASY1001,Manager.getMessage(EasyMessage.EASY1024)));
        }
    }
}
