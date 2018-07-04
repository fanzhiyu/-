package com.easy.work.user.dao;

import com.easy.core.repository.service.BaseRepositoryPagingService;
import com.easy.work.user.domain.UserDomain;

public interface UserDao extends BaseRepositoryPagingService<UserDomain> {

    /**
     * 查找人员编号
     * @param userDomain
     * @return
     */
    public String findMaxUserCode(UserDomain userDomain);
}