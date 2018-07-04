package com.easy.work.tab.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.tab.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/17.
 */
public interface TabService {

    /**
     * 查找数据库表类型
     * @return
     */
    public List<TabTypeModel> searchTabType() throws TransformException;

    /**
     * 查找数据库字段类型
     * @return
     */
    public List<TabFieldTypeModel> searchTabFieldType() throws TransformException;

    /**
     * 查找数据库表列表
     * @param tabPager
     * @return
     * @throws MessageException
     */
    public TabPager searchTabPager(TabPager tabPager) throws MessageException,TransformException;

    /**
     * 查找表字段列表
     * @param tabFieldModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public TabFieldModel searchTabFieldList(TabFieldModel tabFieldModel) throws MessageException, TransformException;

    /**
     * 保存数据库表信息
     * @param tabModel
     * @throws MessageException
     */
    public void saveTab(TabModel tabModel) throws MessageException,TransformException;

    /**
     * 删除表
     * @param tabModel
     * @throws MessageException
     */
    public void deleteTab(TabModel tabModel) throws MessageException;

    /**
     * 查找所有数据表
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<TabModel> searchAll() throws TransformException;

    /**
     * 查找表详细
     * @param tabModel
     * @throws MessageException
     * @throws TransformException
     */
    public TabModel searchTabDetails(TabModel tabModel) throws MessageException,TransformException;

    /**
     * 查找表数据分页
     * @param tabDataPager
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public TabDataPager searchTabDataPager(TabDataPager tabDataPager) throws MessageException, TransformException;

    /**
     * 查找数据详细
     * @param tabDataModel
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public Map<String,Object> searchTabData(TabDataModel tabDataModel) throws MessageException,TransformException;

}
