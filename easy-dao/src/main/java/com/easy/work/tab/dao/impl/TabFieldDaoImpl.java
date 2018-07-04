package com.easy.work.tab.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.tab.dao.TabFieldDao;
import com.easy.work.tab.domain.TabFieldDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/17.
 */
@Repository("tabFieldDao")
public class TabFieldDaoImpl extends BaseRepositoryServiceImpl<TabFieldDomain> implements TabFieldDao{

    /**
     * 根据table主表删除数据
     * @param tabFieldDomain
     */
    @Override
    public void deleteByTabId(TabFieldDomain tabFieldDomain) {
        this.getSqlSession().delete(tabFieldDomain.getSqlId()+"deleteByTabId",tabFieldDomain);
    }
}
