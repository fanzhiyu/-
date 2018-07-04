package com.easy.work.org.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.org.dao.OrgMenuDao;
import com.easy.work.org.domain.OrgMenuDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/28.
 */
@Repository("orgMenuDao")
public class OrgMenuDaoImpl extends BaseRepositoryServiceImpl<OrgMenuDomain> implements OrgMenuDao{
}
