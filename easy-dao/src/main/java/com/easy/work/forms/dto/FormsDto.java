package com.easy.work.forms.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.forms.domain.FormsDomain;

/**
 * Created by apple on 18/4/16.
 */
public class FormsDto extends PagingDomain<FormsDomain> {

    private static final String SQL_ID = "com.easy.work.forms.dao.FormsDao.";

    private String createUser;

    private String updateUser;

    private String objName;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
