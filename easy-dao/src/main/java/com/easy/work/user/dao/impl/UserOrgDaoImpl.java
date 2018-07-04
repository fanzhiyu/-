package com.easy.work.user.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.user.dao.UserOrgDao;
import com.easy.work.user.domain.UserOrgDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/4/16.
 */
@Repository("userOrgDao")
public class UserOrgDaoImpl extends BaseRepositoryPagingServiceImpl<UserOrgDomain> implements UserOrgDao {
}
