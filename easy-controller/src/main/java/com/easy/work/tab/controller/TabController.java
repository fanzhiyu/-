package com.easy.work.tab.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.core.util.StringUtils;
import com.easy.work.tab.model.*;
import com.easy.work.tab.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/18.
 */
@RequestMapping("/tab")
@RestController
public class TabController {

    @Autowired
    private TabService tabService;

    /**
     * 获取数据库表类型
     * @return
     */
    @RequestMapping(value="/getTabType", method = RequestMethod.GET)
    public ResponseEntity getTabType() throws TransformException{
        List<TabTypeModel> resultList = tabService.searchTabType();
        return ResponseUtil.success(resultList);
    }

    /**
     * 获取数据库表字段类型
     * @return
     * @throws TransformException
     */
    @RequestMapping(value = "/getTabFieldType", method = RequestMethod.GET)
    public ResponseEntity getTabFieldType() throws TransformException{
        List<TabFieldTypeModel> resultList = tabService.searchTabFieldType();
        return ResponseUtil.success(resultList);
    }

    /**
     * 查找所有的数据表
     * @return
     * @throws TransformException
     */
    @RequestMapping(value = "/getTabAll", method = RequestMethod.GET)
    public ResponseEntity getTabAll() throws TransformException{
        List<TabModel> result = tabService.searchAll();
        return ResponseUtil.success(result);
    }

    /**
     * 查找数据库表分页
     * @return
     * @throws TransformException
     * @throws MessageException
     */
    @RequestMapping(value = "/getTabPager", method = RequestMethod.GET)
    public ResponseEntity getTabPager(@ModelAttribute TabPager tabPager) throws TransformException,MessageException{
        TabPager resultModel = tabService.searchTabPager(tabPager);
        return ResponseUtil.pageSuccess(resultModel.getCount(),resultModel.getResultList());
    }

    /**
     * 保存数据库表信息
     * @param tabModel
     * @return
     */
    @RequestMapping(value = "/saveTab", method = RequestMethod.POST)
    public ResponseEntity saveTab(@ModelAttribute TabModel tabModel) throws MessageException, TransformException{
        tabService.saveTab(tabModel);
        return ResponseUtil.success();
    }

    /**
     * 删除数据表
     * @param tabModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "/removeTab", method = RequestMethod.POST)
    public ResponseEntity removeTab(@ModelAttribute TabModel tabModel) throws MessageException{
        tabService.deleteTab(tabModel);
        return ResponseUtil.success();
    }

    /**
     * 获取数据表详细信息
     * @param tabModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getTabDetails", method = RequestMethod.GET)
    public ResponseEntity getTabDetails(@ModelAttribute TabModel tabModel) throws MessageException,TransformException{
        TabModel resultModel = tabService.searchTabDetails(tabModel);
        return ResponseUtil.success(resultModel);
    }

    /**
     * 获取表字段信息列表
     * @param tabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getTabFieldList", method = RequestMethod.GET)
    public ResponseEntity getTabFieldList(@ModelAttribute TabFieldModel tabFieldModel, String params) throws MessageException,
            TransformException{
        if(StringUtils.isNotNull(params)){
            tabFieldModel = StringUtils.stringToBena(params,TabFieldModel.class);
        }
        TabFieldModel resultModel = tabService.searchTabFieldList(tabFieldModel);
        return ResponseUtil.success(resultModel);
    }

    /**
     * 查找表所对应的数据分页
     * @param tabDataPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getTabDataPager", method = RequestMethod.GET)
    public ResponseEntity getTabDataPager(@ModelAttribute TabDataPager tabDataPager) throws MessageException,TransformException{
        TabDataPager resultModel = tabService.searchTabDataPager(tabDataPager);
        return ResponseUtil.success(resultModel);
    }

    /**
     * 查找表所对应的数据
     * @param tabDataModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getTabData", method = RequestMethod.GET)
    public ResponseEntity getTabData(@ModelAttribute TabDataModel tabDataModel) throws MessageException,TransformException{
        Map<String,Object> result = tabService.searchTabData(tabDataModel);
        return ResponseUtil.success(result.get("data"));
    }
}
