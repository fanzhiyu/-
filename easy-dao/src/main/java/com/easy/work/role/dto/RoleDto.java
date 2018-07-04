package com.easy.work.role.dto;

import com.easy.core.annotation.Fields;
import com.easy.core.domain.PagingDomain;
import com.easy.work.role.domain.RoleDomain;

/**
 * Created by apple on 18/6/21.
 */
public class RoleDto extends PagingDomain<RoleDomain> {

    private static final String SQL_ID = "com.easy.work.role.dao.RoleDao.";

    private String createUser;
    private String rangeName;
    private String startDate;
    private String endDate;
    private String orgId;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

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


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
