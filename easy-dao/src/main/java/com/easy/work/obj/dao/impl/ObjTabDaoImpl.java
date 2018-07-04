package com.easy.work.obj.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.obj.dao.ObjTabDao;
import com.easy.work.obj.domain.ObjTabDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/5/30.
 */
@Repository("objTabDao")
public class ObjTabDaoImpl extends BaseRepositoryServiceImpl<ObjTabDomain> implements ObjTabDao{
}
