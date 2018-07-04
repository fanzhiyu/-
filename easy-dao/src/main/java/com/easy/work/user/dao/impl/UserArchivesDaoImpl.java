package com.easy.work.user.dao.impl;

import com.easy.core.repository.service.impl.BaseRepositoryServiceImpl;
import com.easy.work.user.dao.UserArchivesDao;
import com.easy.work.user.domain.UserArchivesDomain;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 18/6/21.
 */
@Repository("userArchivesDao")
public class UserArchivesDaoImpl extends BaseRepositoryServiceImpl<UserArchivesDomain> implements UserArchivesDao{
}
