package com.easy.work.org.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.org.domain.OrgDomain;

/**
 * Created by apple on 18/4/16.
 */
public class OrgDto extends PagingDomain<OrgDomain> {

    private static final String SQL_ID = "com.easy.work.org.dao.OrgDao.";

    private String parentName;

    private String provinceName;
    private String cityName;
    private String depId;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
