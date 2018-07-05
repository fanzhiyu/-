package com.easy.work.obj.service.impl;

import com.easy.core.bean.UserBean;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.manager.Manager;
import com.easy.core.util.DateUtils;
import com.easy.core.util.StringUtils;
import com.easy.core.util.TransUtils;
import com.easy.core.util.UserUtils;
import com.easy.work.core.constant.EasyMessage;
import com.easy.work.core.util.DateUtil;
import com.easy.work.obj.dao.ObjDao;
import com.easy.work.obj.dao.ObjTabDao;
import com.easy.work.obj.dao.ObjTabFieldDao;
import com.easy.work.obj.domain.ObjDomain;
import com.easy.work.obj.domain.ObjTabDomain;
import com.easy.work.obj.domain.ObjTabFieldDomain;
import com.easy.work.obj.model.ObjModel;
import com.easy.work.obj.model.ObjPager;
import com.easy.work.obj.model.ObjTabFieldModel;
import com.easy.work.obj.model.ObjTabModel;
import com.easy.work.obj.service.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public void saveObj(ObjModel objModel) throws MessageException, TransformException {
        if(StringUtils.isNull(objModel.getObjId())){
            addObj(objModel);
        }else{
            updateObj(objModel);
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
        List<ObjTabModel> objTabModels = new ArrayList<ObjTabModel>();
        ObjTabModel model = null;
        for(ObjTabDomain domain : resObjTabList){
            model = new ObjTabModel();
            model.setTabId(domain.getTabId());
            ObjTabFieldDomain objTabFieldDomain = new ObjTabFieldDomain();
            objTabFieldDomain.setObjTabId(domain.getObjTabId());
            List<ObjTabFieldDomain> list = objTabFieldDao.findListCondition(objTabFieldDomain);
            List<String> fieldIds = new ArrayList<String>();
            for(ObjTabFieldDomain d : list){
                fieldIds.add(d.getObjFieldId());
            }
            model.setFieldIds(fieldIds);
            objTabModels.add(model);
        }
        ObjModel res = TransUtils.formatObject(ObjModel.class,resObj);
        res.setTabIds(objTabModels);
        return res;
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
     * 添加数据对象
     * @param objModel
     */
    private void addObj(ObjModel objModel) throws TransformException{
        UserBean userBean = userUtils.getUser();
        ObjDomain objDomain = TransUtils.formatObject(ObjDomain.class,objModel);
        objDomain.setCreater(userBean.getUserCode());
        objDomain.setCreateTime(DateUtils.getSystemDate());
        objDomain.setUpdater(userBean.getUserCode());
        objDomain.setUpdateTime(DateUtils.getSystemDate());
        objDomain.setRuleNumber(DateUtils.getYYmm());
        objDao.insert(objDomain);
        insertObjTab(objModel,objDomain.getObjId());

    }

    /**
     * 添加对象关联表
     * @param objModel
     * @param objId
     */
    private void insertObjTab(ObjModel objModel,String objId){
        UserBean userBean = userUtils.getUser();
        //**************>> start 添加对象对应的数据表 <<**************
        List<ObjTabDomain> insertTab = new ArrayList<ObjTabDomain>();
        ObjTabDomain objTabDomain = null;
        for(ObjTabModel objTabModel : objModel.getTabIds()){
            objTabDomain = new ObjTabDomain();
            objTabDomain.setObjId(objId);
            objTabDomain.setTabId(objTabModel.getTabId());
            objTabDomain.setObjTabId(objTabModel.getTabId());
            objTabDomain.setRuleNumber(DateUtils.getYYmm());
            objTabDomain.setCreater(userBean.getUserCode());
            objTabDomain.setCreateTime(DateUtils.getSystemDate());
            objTabDomain.setUpdater(userBean.getUserCode());
            objTabDomain.setUpdateTime(DateUtils.getSystemDate());
            objTabDomain.setFieldIds(objTabModel.getFieldIds());
            insertTab.add(objTabDomain);
        }
        objTabDao.insert(insertTab);
        //**************>> end 添加对象对应的数据表 <<**************
        //**************>> start 添加对象字段对象的数据表 <<************
        List<ObjTabFieldDomain> insertTabField = new ArrayList<ObjTabFieldDomain>();
        for(ObjTabDomain tabTab : insertTab){
            ObjTabFieldDomain objTabFieldDomain = null;
            for(String fieldId : tabTab.getFieldIds()){
                objTabFieldDomain = new ObjTabFieldDomain();
                objTabFieldDomain.setObjTabId(tabTab.getObjTabId());
                objTabFieldDomain.setObjFieldId(fieldId);
                objTabFieldDomain.setCreater(userBean.getUserCode());
                objTabFieldDomain.setCreateTime(DateUtils.getSystemDate());
                objTabFieldDomain.setUpdater(userBean.getUserCode());
                objTabFieldDomain.setUpdateTime(DateUtils.getSystemDate());
                objTabFieldDomain.setRuleNumber(DateUtils.getYYmm());
                insertTabField.add(objTabFieldDomain);
            }
        }
        objTabFieldDao.insert(insertTabField);
        //**************>> end 添加对象字段对象的数据表 <<************
    }

    /**
     * 更新数据对象
     * @param objModel
     */
    private void updateObj(ObjModel objModel) throws MessageException,TransformException{
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
        objTabDao.delete(resultList);
        objTabFieldDao.delete(delObjTabField);
        insertObjTab(objModel, objModel.getObjId());
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
}
