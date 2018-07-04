package com.easy.work.sign.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.sign.dao.SignDao;
import com.easy.work.sign.domain.SignDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/2.
 */
@Repository("signDao")
public class SignDaoImpl extends BaseRepositoryPagingServiceImpl<SignDomain> implements SignDao {
}
