package com.easy.work.obj.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.forms.model.FormsModel;
import com.easy.work.obj.model.*;
import com.easy.work.tab.model.TabDataPager;
import com.easy.work.tab.model.TabFieldInfoModel;
import com.easy.work.tab.model.TabModel;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public interface ObjService {

    /**
     * 查找数据对象分页
     * @param objPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public ObjPager searchObjPager(ObjPager objPager) throws MessageException,TransformException;

    /**
     * 查找所有对象
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<ObjModel> searchObjAll() throws MessageException,TransformException;

    /**
     * 查找对象字段
     * @param objTabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<ObjTabFieldModel> searchObjTabFieldList(ObjTabFieldModel objTabFieldModel) throws MessageException,TransformException;

    /**
     * 保存数据对象
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public void saveObj(ObjModel objModel,String params) throws MessageException,TransformException;

    /**
     * 查找详细
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public ObjModel searchObjDetails(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 根对象id查找表数据
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public TabDataPager searchTabDataByObjId(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 查找表数据详细
     * @param params
     * @return
     * @throws MessageException
     */
    public JSONObject searchTabDataDetails(String params) throws MessageException,TransformException;

    /**
     * 删除数据对象
     * @param objModel
     * @throws MessageException
     * @throws TransformException
     */
    public void deleteObj(ObjModel objModel) throws MessageException;

    /**
     * 根据objId查找表单
     * @param objModel
     * @return
     * @throws MessageException
     */
    public FormsModel searchFormByObjId(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 根据objId查找出字段
     * @param objModel
     * @return
     * @throws MessageException
     */
    public FormsModel searchFieldByObjId(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 删除表数据
     * @param params
     * @throws MessageException
     * @throws TransformException
     */
    public void deleteTabData(String params) throws MessageException,TransformException;
}
