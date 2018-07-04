package com.easy.work.menu.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.menu.dao.MenuDao;
import com.easy.work.menu.domain.MenuDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/28.
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseRepositoryServiceImpl<MenuDomain> implements MenuDao{
}
