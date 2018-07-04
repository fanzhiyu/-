package com.easy.work.role.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.role.dao.RoleMenuDao;
import com.easy.work.role.domain.RoleMenuDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/7/2.
 */
@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseRepositoryServiceImpl<RoleMenuDomain> implements RoleMenuDao{
}
