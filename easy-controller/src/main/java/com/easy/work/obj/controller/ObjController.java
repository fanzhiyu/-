package com.easy.work.obj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.core.util.StringUtils;
import com.easy.work.forms.model.FormsModel;
import com.easy.work.obj.domain.ObjDomain;
import com.easy.work.obj.model.ObjFieldModel;
import com.easy.work.obj.model.ObjModel;
import com.easy.work.obj.model.ObjPager;
import com.easy.work.obj.model.ObjTabFieldModel;
import com.easy.work.obj.service.ObjService;
import com.easy.work.tab.model.TabDataPager;
import com.easy.work.tab.model.TabFieldInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
@RequestMapping("/obj")
@RestController
public class ObjController {

    @Autowired
    private ObjService objService;

    /**
     * 查找数据对象分页
     * @param objPager
     * @return
     */
    @RequestMapping(value = "getObjPager", method = RequestMethod.GET)
    public ResponseEntity getObjPager(@ModelAttribute ObjPager objPager) throws MessageException,TransformException{
        ObjPager result = objService.searchObjPager(objPager);
        return ResponseUtil.pageSuccess(result.getCount(),result.getResultList());
    }

    /**
     * 保存
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "saveObj", method = RequestMethod.POST)
    public ResponseEntity saveObj(@ModelAttribute ObjModel objModel,String params) throws MessageException,TransformException{
        objService.saveObj(objModel,params);
        return ResponseUtil.success();
    }

    /**
     * 获取所有对象
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getObjAll", method = RequestMethod.GET)
    public ResponseEntity getObjAll(@ModelAttribute ObjModel objModel) throws MessageException,TransformException{
        List<ObjModel> resultModel = objService.searchObjAll();
        return ResponseUtil.success(resultModel);
    }

    /**
     * 查找对象字段
     * @param objTabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getObjTabFieldList", method = RequestMethod.GET)
    public ResponseEntity getObjTabFieldList(@ModelAttribute ObjTabFieldModel objTabFieldModel) throws MessageException,TransformException{
        List<ObjTabFieldModel> result = objService.searchObjTabFieldList(objTabFieldModel);
        return ResponseUtil.success(result);
    }

    /**
     * 获取对象详细
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getObjDetails", method = RequestMethod.GET)
    public ResponseEntity getObjDetails(@ModelAttribute ObjModel objModel) throws MessageException,TransformException{
        ObjModel result = objService.searchObjDetails(objModel);
        return ResponseUtil.success(result);
    }

    /**
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getObjDataPager", method = RequestMethod.GET)
    public ResponseEntity getObjDataPager(@ModelAttribute ObjModel objModel) throws MessageException,TransformException{
        TabDataPager result = objService.searchTabDataByObjId(objModel);
        return ResponseUtil.success(result);
    }

    /**
     * 删除对象
     * @param objModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "removeObj", method = RequestMethod.POST)
    public ResponseEntity removeObj(@ModelAttribute ObjModel objModel) throws MessageException{
        objService.deleteObj(objModel);
        return ResponseUtil.success();
    }

    /**
     * 查找表单
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getFormsByObjId", method = RequestMethod.GET)
    public ResponseEntity getFormsByObjId(@ModelAttribute ObjModel objModel) throws MessageException,TransformException{
        FormsModel result = objService.searchFormByObjId(objModel);
        return ResponseUtil.success(result);
    }

    /**
     * 根据objid查找字段
     * @param objModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getFieldByObjId", method = RequestMethod.GET)
    public ResponseEntity getFieldByObjId(@ModelAttribute ObjModel objModel) throws MessageException,TransformException{
        FormsModel result = objService.searchFieldByObjId(objModel);
        return ResponseUtil.success(result);
    }

    /**
     * 查找数据详细
     * @param params
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getTabDataDetails", method = RequestMethod.GET)
    public ResponseEntity getTabDataDetails(String params) throws MessageException,TransformException{
        JSONObject result = objService.searchTabDataDetails(params);
        return ResponseUtil.success(result);
    }

    /**
     * 删除表数据
     * @param params
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "removeTabData", method = RequestMethod.POST)
    public ResponseEntity removeTabData(String params) throws MessageException,TransformException{
        objService.deleteTabData(params);
        return ResponseUtil.success();
    }
}
