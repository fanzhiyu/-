package com.easy.work.org.domain;

import com.easy.core.annotation.Column;
import com.easy.core.annotation.Table;
import com.easy.work.org.dto.OrgDto;
import com.sun.mail.imap.protocol.ID;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "sys_org")
public class OrgDomain extends OrgDto{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    @Column(id = "ORG_ID",length = 12,rule = "RG",increment = true)
    private String orgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.PRAENT_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_NO
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_NAME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_NAMEAB
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgNameab;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_TYPE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_PROVINCE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgProvince;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_CITY
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgCity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_AREA
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgArea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_ADDRESS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_REGION
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgRegion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_LONGITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private BigDecimal orgLongitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_LATITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private BigDecimal orgLatitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_CONTACTS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgContacts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_STATUS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.ORG_PHONE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String orgPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.CREATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.CREATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String creater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ORG.UPDATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    private String updater;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_ID
     *
     * @return the value of SYS_ORG.ORG_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_ID
     *
     * @param orgId the value for SYS_ORG.ORG_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.PRAENT_ID
     *
     * @return the value of SYS_ORG.PRAENT_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.PRAENT_ID
     *
     * @param parentId the value for SYS_ORG.PRAENT_ID
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_NO
     *
     * @return the value of SYS_ORG.ORG_NO
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_NO
     *
     * @param orgNo the value for SYS_ORG.ORG_NO
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_NAME
     *
     * @return the value of SYS_ORG.ORG_NAME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_NAME
     *
     * @param orgName the value for SYS_ORG.ORG_NAME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_NAMEAB
     *
     * @return the value of SYS_ORG.ORG_NAMEAB
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgNameab() {
        return orgNameab;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_NAMEAB
     *
     * @param orgNameab the value for SYS_ORG.ORG_NAMEAB
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgNameab(String orgNameab) {
        this.orgNameab = orgNameab == null ? null : orgNameab.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_TYPE
     *
     * @return the value of SYS_ORG.ORG_TYPE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_TYPE
     *
     * @param orgType the value for SYS_ORG.ORG_TYPE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_PROVINCE
     *
     * @return the value of SYS_ORG.ORG_PROVINCE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgProvince() {
        return orgProvince;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_PROVINCE
     *
     * @param orgProvince the value for SYS_ORG.ORG_PROVINCE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgProvince(String orgProvince) {
        this.orgProvince = orgProvince == null ? null : orgProvince.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_CITY
     *
     * @return the value of SYS_ORG.ORG_CITY
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgCity() {
        return orgCity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_CITY
     *
     * @param orgCity the value for SYS_ORG.ORG_CITY
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity == null ? null : orgCity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_AREA
     *
     * @return the value of SYS_ORG.ORG_AREA
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgArea() {
        return orgArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_AREA
     *
     * @param orgArea the value for SYS_ORG.ORG_AREA
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgArea(String orgArea) {
        this.orgArea = orgArea == null ? null : orgArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_ADDRESS
     *
     * @return the value of SYS_ORG.ORG_ADDRESS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgAddress() {
        return orgAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_ADDRESS
     *
     * @param orgAddress the value for SYS_ORG.ORG_ADDRESS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_REGION
     *
     * @return the value of SYS_ORG.ORG_REGION
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgRegion() {
        return orgRegion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_REGION
     *
     * @param orgRegion the value for SYS_ORG.ORG_REGION
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgRegion(String orgRegion) {
        this.orgRegion = orgRegion == null ? null : orgRegion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_LONGITUDE
     *
     * @return the value of SYS_ORG.ORG_LONGITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public BigDecimal getOrgLongitude() {
        return orgLongitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_LONGITUDE
     *
     * @param orgLongitude the value for SYS_ORG.ORG_LONGITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgLongitude(BigDecimal orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_LATITUDE
     *
     * @return the value of SYS_ORG.ORG_LATITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public BigDecimal getOrgLatitude() {
        return orgLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_LATITUDE
     *
     * @param orgLatitude the value for SYS_ORG.ORG_LATITUDE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgLatitude(BigDecimal orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_CONTACTS
     *
     * @return the value of SYS_ORG.ORG_CONTACTS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgContacts() {
        return orgContacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_CONTACTS
     *
     * @param orgContacts the value for SYS_ORG.ORG_CONTACTS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgContacts(String orgContacts) {
        this.orgContacts = orgContacts == null ? null : orgContacts.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_STATUS
     *
     * @return the value of SYS_ORG.ORG_STATUS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgStatus() {
        return orgStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_STATUS
     *
     * @param orgStatus the value for SYS_ORG.ORG_STATUS
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus == null ? null : orgStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.ORG_PHONE
     *
     * @return the value of SYS_ORG.ORG_PHONE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getOrgPhone() {
        return orgPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.ORG_PHONE
     *
     * @param orgPhone the value for SYS_ORG.ORG_PHONE
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.CREATE_TIME
     *
     * @return the value of SYS_ORG.CREATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.CREATE_TIME
     *
     * @param createTime the value for SYS_ORG.CREATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.CREATER
     *
     * @return the value of SYS_ORG.CREATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.CREATER
     *
     * @param creater the value for SYS_ORG.CREATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.UPDATE_TIME
     *
     * @return the value of SYS_ORG.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.UPDATE_TIME
     *
     * @param updateTime the value for SYS_ORG.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ORG.UPDATER
     *
     * @return the value of SYS_ORG.UPDATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ORG.UPDATER
     *
     * @param updater the value for SYS_ORG.UPDATER
     *
     * @mbg.generated Mon Apr 16 09:22:07 CST 2018
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}