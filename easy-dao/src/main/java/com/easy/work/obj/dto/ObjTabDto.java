package com.easy.work.obj.dto;

import com.easy.core.domain.Domain;
import com.easy.core.domain.PagingDomain;
import com.easy.work.obj.domain.ObjTabDomain;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public class ObjTabDto extends Domain {

    private static final String SQL_ID = "com.easy.work.obj.dao.ObjTabDao.";

    private List<String> fieldIds;

    private String tabName;

    public List<String> getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(List<String> fieldIds) {
        this.fieldIds = fieldIds;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
