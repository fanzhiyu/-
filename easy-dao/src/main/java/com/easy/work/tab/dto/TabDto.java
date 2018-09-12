package com.easy.work.tab.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.tab.domain.TabDomain;
import com.easy.work.tab.domain.TabFieldDomain;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/17.
 */
public class TabDto extends PagingDomain<TabDomain> {

    private static final String SQL = "com.easy.work.tab.dao.TabDao.";

    private List<TabFieldDomain> fieldList;

    private Map<String,Object> fieldMap;

    private Map<String,Object> keyMap;

    private String database;

    private Map<String,Object> paramList;

    private String createrUser;

    private String updaterUser;

    private String startDate;

    private String endDate;

    private List<String> tabNameList;

    public List<TabFieldDomain> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<TabFieldDomain> fieldList) {
        this.fieldList = fieldList;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, Object> keyMap) {
        this.keyMap = keyMap;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Map<String, Object> getParamList() {
        return paramList;
    }

    public void setParamList(Map<String, Object> paramList) {
        this.paramList = paramList;
    }


    public String getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(String createrUser) {
        this.createrUser = createrUser;
    }

    public String getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(String updaterUser) {
        this.updaterUser = updaterUser;
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

    public List<String> getTabNameList() {
        return tabNameList;
    }

    public void setTabNameList(List<String> tabNameList) {
        this.tabNameList = tabNameList;
    }

    @Override
    public String getSqlId() {
        return SQL;
    }
}
