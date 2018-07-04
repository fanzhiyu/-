package com.easy.work.dictionary.dto;

import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/6/27.
 */
public class DictionaryDto extends Domain {

    private static final String SQL_ID = "com.easy.work.dictionary.dao.DictionaryDao.";

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
