package com.easy.work.dictionary.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.dictionary.dao.DictionaryDao;
import com.easy.work.dictionary.domain.DictionaryDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/27.
 */
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseRepositoryServiceImpl<DictionaryDomain> implements DictionaryDao{
}
