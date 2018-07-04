package com.easy.work.role.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.role.dao.RoleDao;
import com.easy.work.role.domain.RoleDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/21.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseRepositoryPagingServiceImpl<RoleDomain> implements RoleDao {
}
