package com.easy.work.city.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.city.dao.CityDao;
import com.easy.work.city.domain.CityDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/15.
 */
@Repository("cityDao")
public class CityDaoImpl extends BaseRepositoryServiceImpl<CityDomain> implements CityDao {
}
