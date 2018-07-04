package com.easy.work.obj.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.obj.model.ObjModel;
import com.easy.work.obj.model.ObjPager;
import com.easy.work.obj.model.ObjTabFieldModel;

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
    public void saveObj(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 查找详细
     * @param objModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public ObjModel searchObjDetails(ObjModel objModel) throws MessageException,TransformException;

    /**
     * 删除数据对象
     * @param objModel
     * @throws MessageException
     * @throws TransformException
     */
    public void deleteObj(ObjModel objModel) throws MessageException;
}
