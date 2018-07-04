package com.easy.work.obj.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.obj.dao.ObjDao;
import com.easy.work.obj.domain.ObjDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/30.
 */
@Repository("objDao")
public class ObjDaoImpl extends BaseRepositoryPagingServiceImpl<ObjDomain> implements ObjDao{
}
