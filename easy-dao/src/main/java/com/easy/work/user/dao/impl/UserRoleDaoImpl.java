package com.easy.work.user.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.user.dao.UserRoleDao;
import com.easy.work.user.domain.UserRoleDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/21.
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseRepositoryServiceImpl<UserRoleDomain> implements UserRoleDao{
}
