package com.easy.work.obj.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.obj.domain.ObjDomain;

/**
 * Created by apple on 18/5/30.
 */
public class ObjDto extends PagingDomain<ObjDomain> {

    private static final String SQL_ID = "com.easy.work.obj.dao.ObjDao.";

    private String startDate;
    private String endDate;
    private String createUser;
    private String updateUser;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
