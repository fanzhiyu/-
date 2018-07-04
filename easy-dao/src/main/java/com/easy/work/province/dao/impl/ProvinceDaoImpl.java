package com.easy.work.province.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.province.dao.ProvinceDao;
import com.easy.work.province.domain.ProvinceDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/15.
 */
@Repository("provinceDao")
public class ProvinceDaoImpl extends BaseRepositoryServiceImpl<ProvinceDomain> implements ProvinceDao {
}
