package com.easy.work.forms.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.forms.model.FormsModel;
import com.easy.work.forms.model.FormsPager;
import com.easy.work.forms.model.FormsSearch;

import java.util.List;

/**
 * Created by apple on 18/4/12.
 */
public interface FormsService {

    /**
     * 保存
     * @param formModel
     * @throws MessageException
     */
    public void saveForms(FormsModel formModel) throws MessageException,TransformException;

    /**
     * 表单分页
     * @param formsSearch
     * @return
     * @throws MessageException
     */
    public FormsPager searchFormsPager(FormsSearch formsSearch) throws MessageException,TransformException;

    /**
     * 查找表单详细
     * @param formsModel
     * @return
     * @throws MessageException
     */
    public FormsModel searchFormsDetails(FormsModel formsModel) throws MessageException,TransformException;

    /**
     * 查询出所有表单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<FormsModel> searchForms() throws MessageException,TransformException;
}
