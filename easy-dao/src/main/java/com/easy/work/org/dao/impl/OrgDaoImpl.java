package com.easy.work.org.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.org.dao.OrgDao;
import com.easy.work.org.domain.OrgDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/4/16.
 */
@Repository("orgDao")
public class OrgDaoImpl extends BaseRepositoryPagingServiceImpl<OrgDomain> implements OrgDao{

    /**
     * 查找最大的组织号
     * @param orgDomain
     * @return
     */
    @Override
    public String findMaxOrgNo(OrgDomain orgDomain) {
        return this.getSqlSession().selectOne(orgDomain.getSqlId()+"findMaxOrgNo",orgDomain);
    }
}
