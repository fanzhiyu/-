package com.easy.work.forms.service.impl;

import com.easy.core.bean.UserBean;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.DateUtils;
import com.easy.core.util.StringUtils;
import com.easy.core.util.TransUtils;
import com.easy.core.util.UserUtils;
import com.easy.work.forms.dao.FormsDao;
import com.easy.work.forms.domain.FormsDomain;
import com.easy.work.forms.model.FormsModel;
import com.easy.work.forms.model.FormsPager;
import com.easy.work.forms.model.FormsSearch;
import com.easy.work.forms.service.FormsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 18/4/12.
 */
@Repository("formsService")
@Transactional
public class FormsServiceImpl implements FormsService {

    @Resource
    private FormsDao formsDao;

    @Resource
    private UserUtils userUtils;
    /**
     * 保存
     * @param formModel
     * @throws MessageException
     */
    @Override
    public void saveForms(FormsModel formModel) throws MessageException,TransformException {
        if(StringUtils.isNotNull(formModel.getFormId())){
            updateForms(formModel);
        }else{
            addForms(formModel);
        }
    }

    /**
     * 表单分页
     * @param formsSearch
     * @return
     * @throws MessageException
     */
    @Override
    public FormsPager searchFormsPager(FormsSearch formsSearch) throws MessageException,TransformException {
        FormsDomain formsDomain = TransUtils.formatObject(FormsDomain.class,formsSearch);
        FormsDomain resultDomain = formsDao.findPaging(formsDomain);
        List<FormsPager> list = TransUtils.formatList(FormsPager.class,resultDomain.getResultList());
        FormsPager formsPager = new FormsPager();
        formsPager.setCount(resultDomain.getCount());
        formsPager.setResultList(list);
        return formsPager;
    }

    /**
     * 查找表单详细
     * @param formsModel
     * @return
     * @throws MessageException
     */
    @Override
    public FormsModel searchFormsDetails(FormsModel formsModel) throws MessageException,TransformException {
        FormsDomain formsDomain = new FormsDomain();
        formsDomain.setFormId(formsModel.getFormId());
        FormsDomain resultDomain = formsDao.findCondition(formsDomain);
        return TransUtils.formatObject(FormsModel.class,resultDomain);
    }

    /**
     * 添加表单
     * @param formModel
     */
    private void addForms(FormsModel formModel) throws TransformException{
        UserBean userBean = userUtils.getUser();
        FormsDomain formsDomain = TransUtils.formatObject(FormsDomain.class,formModel);
        formsDomain.setRuleNumber(DateUtils.getYYmm());
        formsDomain.setCreater(userBean.getUserCode());
        formsDomain.setCreateTime(DateUtils.getSystemDate());
        formsDomain.setUpdater(userBean.getUserCode());
        formsDomain.setUpdateTime(DateUtils.getSystemDate());
        formsDao.insert(formsDomain);
    }

    /**
     * 修改表单
     * @param formModel
     */
    private void updateForms(FormsModel formModel) throws TransformException{
        UserBean userBean = userUtils.getUser();
        FormsDomain formsDomain = TransUtils.formatObject(FormsDomain.class,formModel);
        formsDomain.setFormId(formModel.getFormId());
        formsDomain.setUpdater(userBean.getUserCode());
        formsDomain.setUpdateTime(DateUtils.getSystemDate());
        formsDao.update(formsDomain);
    }

    /**
     * 设置domain
     * @param formModel
     * @return
     */
//    private FormsDomain getFormsDomain(FormsModel formModel){
//        FormsDomain formsDomain = new FormsDomain();
//        formsDomain.setFormHtml(formModel.getHtml());
//        formsDomain.setFormTitle(formModel.getTitle());
//        formsDomain.setFormAction(formModel.getAction());
//        formsDomain.setProcessId(formModel.getProccessId());
//        formsDomain.setFormRemark(formModel.getRemark());
//        formsDomain.setRuleNumber(DateUtils.getYYmm());
//        return formsDomain;
//    }
}
