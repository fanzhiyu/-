package com.easy.work.menu.domain;

import com.easy.core.annotation.Column;
import com.easy.core.annotation.Table;
import com.easy.work.menu.dto.MenuDto;

import java.util.Date;

@Table(name = "SYS_MENU")
public class MenuDomain extends MenuDto{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    @Column(id = "MENU_ID",increment = true,length = 12,rule = "ME")
    private String menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.PAREMT_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_CODE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_NAME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_ICON
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuIcon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_REMARK
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuRemark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_STATUS
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_URL
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_TYPE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_RANGE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuRange;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.MENU_SEQ
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String menuSeq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.CREATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String creater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.CREATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.UPDATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private String updater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_MENU.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_ID
     *
     * @return the value of SYS_MENU.MENU_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_ID
     *
     * @param menuId the value for SYS_MENU.MENU_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.PAREMT_ID
     *
     * @return the value of SYS_MENU.PAREMT_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_CODE
     *
     * @param parentId the value for SYS_MENU.PARENT_ID
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_CODE
     *
     * @return the value of SYS_MENU.MENU_CODE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_CODE
     *
     * @param menuCode the value for SYS_MENU.MENU_CODE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_NAME
     *
     * @return the value of SYS_MENU.MENU_NAME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_NAME
     *
     * @param menuName the value for SYS_MENU.MENU_NAME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_ICON
     *
     * @return the value of SYS_MENU.MENU_ICON
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_ICON
     *
     * @param menuIcon the value for SYS_MENU.MENU_ICON
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_REMARK
     *
     * @return the value of SYS_MENU.MENU_REMARK
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuRemark() {
        return menuRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_REMARK
     *
     * @param menuRemark the value for SYS_MENU.MENU_REMARK
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark == null ? null : menuRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_STATUS
     *
     * @return the value of SYS_MENU.MENU_STATUS
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuStatus() {
        return menuStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_STATUS
     *
     * @param menuStatus the value for SYS_MENU.MENU_STATUS
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus == null ? null : menuStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_URL
     *
     * @return the value of SYS_MENU.MENU_URL
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_URL
     *
     * @param menuUrl the value for SYS_MENU.MENU_URL
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_TYPE
     *
     * @return the value of SYS_MENU.MENU_TYPE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_TYPE
     *
     * @param menuType the value for SYS_MENU.MENU_TYPE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_RANGE
     *
     * @return the value of SYS_MENU.MENU_RANGE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuRange() {
        return menuRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_RANGE
     *
     * @param menuRange the value for SYS_MENU.MENU_RANGE
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuRange(String menuRange) {
        this.menuRange = menuRange == null ? null : menuRange.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.MENU_SEQ
     *
     * @return the value of SYS_MENU.MENU_SEQ
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getMenuSeq() {
        return menuSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.MENU_SEQ
     *
     * @param menuSeq the value for SYS_MENU.MENU_SEQ
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setMenuSeq(String menuSeq) {
        this.menuSeq = menuSeq == null ? null : menuSeq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.CREATER
     *
     * @return the value of SYS_MENU.CREATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.CREATER
     *
     * @param creater the value for SYS_MENU.CREATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.CREATE_TIME
     *
     * @return the value of SYS_MENU.CREATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.CREATE_TIME
     *
     * @param createTime the value for SYS_MENU.CREATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.UPDATER
     *
     * @return the value of SYS_MENU.UPDATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.UPDATER
     *
     * @param updater the value for SYS_MENU.UPDATER
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_MENU.UPDATE_TIME
     *
     * @return the value of SYS_MENU.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_MENU.UPDATE_TIME
     *
     * @param updateTime the value for SYS_MENU.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 28 08:34:38 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}