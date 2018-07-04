package com.easy.work.forms.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryPagingServiceImpl;
import com.easy.work.forms.dao.FormsDao;
import com.easy.work.forms.domain.FormsDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/4/16.
 */
@Repository("formsDao")
public class FormsDaoImpl extends BaseRepositoryPagingServiceImpl<FormsDomain> implements FormsDao {
}
