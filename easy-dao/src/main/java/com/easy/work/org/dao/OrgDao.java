package com.easy.work.org.dao;

import com.easy.core.repository.service.BaseRepositoryPagingService;
import com.easy.work.org.domain.OrgDomain;

public interface OrgDao extends BaseRepositoryPagingService<OrgDomain> {

    /**
     * 查找最大的组织号
     * @param orgDomain
     * @return
     */
    public String findMaxOrgNo(OrgDomain orgDomain);

}