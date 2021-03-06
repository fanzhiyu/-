package com.easy.work.obj.domain;

import com.easy.core.annotation.Column;
import com.easy.core.annotation.Table;
import com.easy.work.obj.dto.ObjTabDto;

import java.util.Date;

@Table(name = "SYS_OBJ_TAB")
public class ObjTabDomain extends ObjTabDto{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.OBJ_TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    @Column(id = "OBJ_TAB_ID",length = 12,increment = true,rule = "OT")
    private String objTabId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.OBJ_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private String objId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private String tabId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.CREATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private String creater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.CREATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.UPDATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private String updater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_OBJ_TAB.UPDATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.OBJ_TAB_ID
     *
     * @return the value of SYS_OBJ_TAB.OBJ_TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public String getObjTabId() {
        return objTabId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.OBJ_TAB_ID
     *
     * @param objTabId the value for SYS_OBJ_TAB.OBJ_TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setObjTabId(String objTabId) {
        this.objTabId = objTabId == null ? null : objTabId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.OBJ_ID
     *
     * @return the value of SYS_OBJ_TAB.OBJ_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public String getObjId() {
        return objId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.OBJ_ID
     *
     * @param objId the value for SYS_OBJ_TAB.OBJ_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.TAB_ID
     *
     * @return the value of SYS_OBJ_TAB.TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public String getTabId() {
        return tabId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.TAB_ID
     *
     * @param tabId the value for SYS_OBJ_TAB.TAB_ID
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setTabId(String tabId) {
        this.tabId = tabId == null ? null : tabId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.CREATER
     *
     * @return the value of SYS_OBJ_TAB.CREATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.CREATER
     *
     * @param creater the value for SYS_OBJ_TAB.CREATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.CREATE_TIME
     *
     * @return the value of SYS_OBJ_TAB.CREATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.CREATE_TIME
     *
     * @param createTime the value for SYS_OBJ_TAB.CREATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.UPDATER
     *
     * @return the value of SYS_OBJ_TAB.UPDATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.UPDATER
     *
     * @param updater the value for SYS_OBJ_TAB.UPDATER
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_OBJ_TAB.UPDATE_TIME
     *
     * @return the value of SYS_OBJ_TAB.UPDATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_OBJ_TAB.UPDATE_TIME
     *
     * @param updateTime the value for SYS_OBJ_TAB.UPDATE_TIME
     *
     * @mbg.generated Wed May 30 08:57:30 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}