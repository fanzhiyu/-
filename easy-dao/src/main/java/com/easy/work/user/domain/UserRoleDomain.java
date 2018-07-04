package com.easy.work.user.domain;

import com.easy.core.annotation.Column;
import com.easy.core.annotation.Table;
import com.easy.work.user.dto.UserRoleDto;

import java.util.Date;

@Table(name = "sys_user_role")
public class UserRoleDomain extends UserRoleDto{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.USER_ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    @Column(id = "user_role_id",length = 12,rule = "UR",increment = true)
    private String userRoleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.USER_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private String roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.CREATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.CREATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private String creater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER_ROLE.UPDATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    private String updater;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.USER_ROLE_ID
     *
     * @return the value of SYS_USER_ROLE.USER_ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public String getUserRoleId() {
        return userRoleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.USER_ROLE_ID
     *
     * @param userRoleId the value for SYS_USER_ROLE.USER_ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId == null ? null : userRoleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.USER_ID
     *
     * @return the value of SYS_USER_ROLE.USER_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.USER_ID
     *
     * @param userId the value for SYS_USER_ROLE.USER_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.ROLE_ID
     *
     * @return the value of SYS_USER_ROLE.ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.ROLE_ID
     *
     * @param roleId the value for SYS_USER_ROLE.ROLE_ID
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.CREATE_TIME
     *
     * @return the value of SYS_USER_ROLE.CREATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.CREATE_TIME
     *
     * @param createTime the value for SYS_USER_ROLE.CREATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.CREATER
     *
     * @return the value of SYS_USER_ROLE.CREATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.CREATER
     *
     * @param creater the value for SYS_USER_ROLE.CREATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.UPDATE_TIME
     *
     * @return the value of SYS_USER_ROLE.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.UPDATE_TIME
     *
     * @param updateTime the value for SYS_USER_ROLE.UPDATE_TIME
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER_ROLE.UPDATER
     *
     * @return the value of SYS_USER_ROLE.UPDATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER_ROLE.UPDATER
     *
     * @param updater the value for SYS_USER_ROLE.UPDATER
     *
     * @mbg.generated Thu Jun 21 13:38:43 CST 2018
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}