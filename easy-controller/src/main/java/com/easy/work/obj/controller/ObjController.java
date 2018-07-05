package com.easy.work.obj.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.core.util.StringUtils;
import com.easy.work.obj.model.ObjModel;
import com.easy.work.obj.model.ObjPager;
import com.easy.work.obj.model.ObjTabFieldModel;
import com.easy.work.obj.service.ObjService;
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
        ObjModel paramModel = StringUtils.stringToBena(params, ObjModel.class);
        objModel.setTabIds(paramModel.getTabIds());
        objService.saveObj(objModel);
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
}
