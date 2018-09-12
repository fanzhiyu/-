package com.easy.work.form.controller;


import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.forms.model.FormsModel;
import com.easy.work.forms.model.FormsPager;
import com.easy.work.forms.model.FormsSearch;
import com.easy.work.forms.service.FormsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by apple on 18/4/12.
 */
@RestController
@RequestMapping("/forms")
public class FormsController {

    @Resource
    private FormsService formsService;

    /**
     * 表单保存
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity save(@ModelAttribute FormsModel formModel) throws MessageException, TransformException{
        formsService.saveForms(formModel);
        return ResponseUtil.success();
    }

    /**
     * 查找表单分页
     * @param formsSearch
     * @return
     * @throws MessageException
     */
    @RequestMapping(value="/getFormsPager", method = RequestMethod.GET)
    public ResponseEntity getFormsPager(@ModelAttribute FormsSearch formsSearch) throws MessageException,TransformException{
        FormsPager resultModel = formsService.searchFormsPager(formsSearch);
        return ResponseUtil.pageSuccess(resultModel.getCount(),resultModel.getResultList());
    }

    /**
     * 查找表单详细
     * @param formsModel
     * @return
     */
    @RequestMapping(value="/getFormsDetails", method = RequestMethod.GET)
    public ResponseEntity getFormsDetails(@ModelAttribute FormsModel formsModel) throws MessageException,TransformException{
        FormsModel resultModel = formsService.searchFormsDetails(formsModel);
        return ResponseUtil.success(resultModel);
    }

    /**
     * 查出所有表单
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value="/getAllForms", method = RequestMethod.GET)
    public ResponseEntity getAllForms() throws MessageException,TransformException{
        List<FormsModel> result = formsService.searchForms();
        return ResponseUtil.success(result);
    }
}
