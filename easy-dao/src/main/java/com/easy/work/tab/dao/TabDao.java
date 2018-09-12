package com.easy.work.tab.dao;

import com.easy.core.repository.service.BaseRepositoryPagingService;
import com.easy.work.tab.domain.TabDomain;

import java.util.Map;

public interface TabDao extends BaseRepositoryPagingService<TabDomain> {

    /**
     * 创建表
     * @param tabDomain
     */
    public void createTable(TabDomain tabDomain);

    /**
     * 删除表
     * @param tabDomain
     */
    public void dropTable(TabDomain tabDomain);

    /**
     * 查找表
     * @param tabDomain
     * @return
     */
    public Integer findTableCount(TabDomain tabDomain);

    /**
     * 查找表所对应的数据分页
     * @param tabDomain
     * @return
     */
    public Map<String, Object>  findTabDataPaging(TabDomain tabDomain);

    /**
     * 查找表对象的数据
     * @param tabDomain
     * @return
     */
    public Map<String, Object> findTabData(TabDomain tabDomain);

    /**
     * 添加对应表的数据
     * @param tabDomain
     */
    public void insertData(TabDomain tabDomain);

    /**
     * 修改对应的表数据
     * @param tabDomain
     */
    public void updateData(TabDomain tabDomain);

    /**
     * 删除对应表的数据
     * @param tabDomain
     */
    public void deleteData(TabDomain tabDomain);
}