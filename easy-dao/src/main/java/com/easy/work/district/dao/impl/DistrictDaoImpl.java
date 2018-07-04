package com.easy.work.district.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.district.dao.DistrictDao;
import com.easy.work.district.domain.DistrictDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/15.
 */
@Repository("districtDao")
public class DistrictDaoImpl extends BaseRepositoryServiceImpl<DistrictDomain> implements DistrictDao {
}
