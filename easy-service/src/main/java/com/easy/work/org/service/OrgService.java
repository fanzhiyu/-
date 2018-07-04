package com.easy.work.org.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.org.model.OrgInfoModel;
import com.easy.work.org.model.OrgModel;
import com.easy.work.org.model.OrgsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/6/14.
 */
public interface OrgService {

    /**
     * 查找组织架构
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public OrgModel searchOrgList() throws MessageException;

    /**
     * 根据组织id查找组织名称
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public OrgInfoModel searchOrgByOrgId(String orgId) throws MessageException,TransformException;

    /**
     * 保存组织
     * @param orgInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    public void saveOrg(OrgInfoModel orgInfoModel) throws MessageException,TransformException;

    /**
     * 根据父节点查找下面的组织
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<OrgsModel> searchOrgByParentId(String parentId) throws MessageException,TransformException;


    /**
     * 查找部门
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<OrgModel> searchDepByParentId(String parentId) throws MessageException,TransformException;

    /**
     * 查找组织编号
     * @param parentId
     * @return
     * @throws MessageException
     */
    public String searchOrgNo(String parentId) throws MessageException;

    /**
     * 删除组织
     * @param orgInfoModel
     * @throws MessageException
     * @throws TransformException
     */
    public void deleteOrg(OrgInfoModel orgInfoModel) throws MessageException;

    /**
     * 查找组织和菜单关联id
     * @param orgInfoModel
     * @return
     * @throws MessageException
     */
    public List<String> searchMenuByOrgId(OrgInfoModel orgInfoModel) throws MessageException;

    /**
     * 添加组织和菜单关系
     * @param params
     * @throws MessageException
     */
    public void saveOrgMenu(String params) throws MessageException;

}
