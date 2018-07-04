package com.easy.work.tab.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.tab.dao.TabFieldTypeDao;
import com.easy.work.tab.domain.TabFieldTypeDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/17.
 */
@Repository("tabFieldTypeDao")
public class TabFieldTypeDaoImpl extends BaseRepositoryServiceImpl<TabFieldTypeDomain> implements TabFieldTypeDao {
}
