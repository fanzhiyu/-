package com.easy.work.role.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.role.dao.RoleOrgDao;
import com.easy.work.role.domain.RoleOrgDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/28.
 */
@Repository("roleOrgDao")
public class RoleOrgDaoImpl extends BaseRepositoryServiceImpl<RoleOrgDomain> implements RoleOrgDao {
}
