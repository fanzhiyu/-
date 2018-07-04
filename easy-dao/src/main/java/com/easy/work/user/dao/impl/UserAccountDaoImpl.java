package com.easy.work.user.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.user.dao.UserAccountDao;
import com.easy.work.user.domain.UserAccountDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/25.
 */
@Repository("userAccountDao")
public class UserAccountDaoImpl extends BaseRepositoryServiceImpl<UserAccountDomain> implements UserAccountDao{
}
