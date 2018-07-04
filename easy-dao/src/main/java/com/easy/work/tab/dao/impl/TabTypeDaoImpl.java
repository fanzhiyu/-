package com.easy.work.tab.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.tab.dao.TabTypeDao;
import com.easy.work.tab.domain.TabTypeDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/17.
 */
@Repository("tabTypeDao")
public class TabTypeDaoImpl extends BaseRepositoryServiceImpl<TabTypeDomain> implements TabTypeDao{
}
