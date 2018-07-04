package com.easy.work.tab.dao;

import com.easy.core.repository.service.BaseRepositoryService;
import com.easy.work.tab.domain.TabFieldDomain;

public interface TabFieldDao extends BaseRepositoryService<TabFieldDomain> {


    /**
     * 根据table主表删除数据
     * @param tabFieldDomain
     */
    public void deleteByTabId(TabFieldDomain tabFieldDomain);
}