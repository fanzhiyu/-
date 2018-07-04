package com.easy.work.org.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.org.domain.OrgDomain;
import com.easy.work.org.model.OrgInfoModel;
import com.easy.work.org.model.OrgModel;
import com.easy.work.org.model.OrgsModel;
import com.easy.work.org.service.OrgService;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by apple on 18/6/14.
 */
@RequestMapping("/org")
@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;

    /**
     * 获取组织架构
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getOrgTree", method = RequestMethod.GET)
    public ResponseEntity getOrgTree() throws MessageException{
        OrgModel result = orgService.searchOrgList();
        return ResponseUtil.success(result);
    }

    /**
     * 根据orgid查找组织
     * @param orgId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getOrgByOrgId", method = RequestMethod.GET)
    public ResponseEntity getOrgByOrgId(String orgId) throws MessageException,TransformException{
        OrgInfoModel result = orgService.searchOrgByOrgId(orgId);
        return ResponseUtil.success(result);
    }

    /**
     * 保存组织
     * @param orgInfoModel
     * @return
     */
    @RequestMapping(value = "saveOrg", method = RequestMethod.POST)
    public ResponseEntity saveOrg(@ModelAttribute OrgInfoModel orgInfoModel) throws MessageException,TransformException{
        orgService.saveOrg(orgInfoModel);
        return ResponseUtil.success();
    }

    /**
     * 根据父节点查找所有的组织
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getOrgByParentId", method = RequestMethod.GET)
    public ResponseEntity getOrgByParentId(String parentId) throws MessageException,TransformException{
        List<OrgsModel> result = orgService.searchOrgByParentId(parentId);
        return ResponseUtil.success(result);
    }

    /**
     * 查找部门
     * @param parentId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "getDepByParentId", method = RequestMethod.GET)
    public ResponseEntity getDepByParentId(String parentId) throws MessageException,TransformException{
        List<OrgModel> result = orgService.searchDepByParentId(parentId);
        return ResponseUtil.success(result);
    }

    /**
     * 查找组织编号
     * @param parentId
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getOrgNo", method = RequestMethod.GET)
    public ResponseEntity getOrgNo(String parentId) throws MessageException{
        Object result = orgService.searchOrgNo(parentId);
        return ResponseUtil.success(result);
    }

    /**
     * 删除组织
     * @param orgInfoModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "removeOrg", method = RequestMethod.POST)
    public ResponseEntity removeOrg(@ModelAttribute OrgInfoModel orgInfoModel) throws MessageException{
        orgService.deleteOrg(orgInfoModel);
        return ResponseUtil.success();
    }

    /**
     * 根据组织编号查找菜单id
     * @param orgInfoModel
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "getMenuByOrgId", method = RequestMethod.GET)
    public ResponseEntity getMenuByOrgId(@ModelAttribute OrgInfoModel orgInfoModel) throws MessageException{
        List<String> list =orgService.searchMenuByOrgId(orgInfoModel);
        return ResponseUtil.success(list);
    }

    /**
     * 保存组织和菜单关联关系
     * @param params
     * @return
     * @throws MessageException
     */
    @RequestMapping(value = "saveOrgMenu", method = RequestMethod.POST)
    public ResponseEntity saveOrgMenu(String params) throws MessageException{
        orgService.saveOrgMenu(params);
        return ResponseUtil.success();
    }
}
