package com.easy.work.user.dto;

import com.easy.core.domain.PagingDomain;
import com.easy.work.user.domain.UserDomain;

/**
 * Created by apple on 18/4/15.
 */
public class UserDto extends PagingDomain<UserDomain> {

    private static final String SQL_ID = "com.easy.work.user.dao.UserDao.";

    private String orgId;

    private String orgName;

    private String userSex;
    private String userAge;
    private String userPlaceProvince;
    private String userPlaceCity;
    private String userAddress;
    private String userBirthday;
    private String userHeaderImg;
    private String userEntryDate;
    private String userQuitDate;
    private String userCorrDate;
    private String userPost;
    private String userDep;
    private String userCid;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserPlaceProvince() {
        return userPlaceProvince;
    }

    public void setUserPlaceProvince(String userPlaceProvince) {
        this.userPlaceProvince = userPlaceProvince;
    }

    public String getUserPlaceCity() {
        return userPlaceCity;
    }

    public void setUserPlaceCity(String userPlaceCity) {
        this.userPlaceCity = userPlaceCity;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserHeaderImg() {
        return userHeaderImg;
    }

    public void setUserHeaderImg(String userHeaderImg) {
        this.userHeaderImg = userHeaderImg;
    }

    public String getUserEntryDate() {
        return userEntryDate;
    }

    public void setUserEntryDate(String userEntryDate) {
        this.userEntryDate = userEntryDate;
    }

    public String getUserQuitDate() {
        return userQuitDate;
    }

    public void setUserQuitDate(String userQuitDate) {
        this.userQuitDate = userQuitDate;
    }

    public String getUserCorrDate() {
        return userCorrDate;
    }

    public void setUserCorrDate(String userCorrDate) {
        this.userCorrDate = userCorrDate;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getUserDep() {
        return userDep;
    }

    public void setUserDep(String userDep) {
        this.userDep = userDep;
    }

    public String getUserCid() {
        return userCid;
    }

    public void setUserCid(String userCid) {
        this.userCid = userCid;
    }

    @Override
    public String getSqlId() {
        return SQL_ID;
    }
}
