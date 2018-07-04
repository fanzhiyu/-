package com.easy.work.user.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.user.dao.UserDao;
import com.easy.work.user.domain.UserDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/4/15.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseRepositoryPagingServiceImpl<UserDomain> implements UserDao {

    /**
     * 查找人员编号
     * @param userDomain
     * @return
     */
    @Override
    public String findMaxUserCode(UserDomain userDomain) {
        return this.getSqlSession().selectOne(userDomain.getSqlId()+"findMaxUserCode",userDomain);
    }
}
