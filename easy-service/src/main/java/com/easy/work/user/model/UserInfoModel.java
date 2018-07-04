package com.easy.work.user.model;

import com.easy.core.annotation.Fields;

/**
 * Created by apple on 18/6/15.
 */
public class UserInfoModel {

    private String userId;
    private String userCode;
    private String userTel;
    private String userName;
    private String userEmail;
    private String userSex;
    private String userAge;
    private String userAddress;
    @Fields(format = "yyyy-MM-dd")
    private String userBirthday;
    private String userPlaceProvince;
    private String userPlaceCity;
    private String userDep;
    private String userPost;
    private String userHeaderImg;
    @Fields(format = "yyyy-MM-dd")
    private String userEntryDate;
    @Fields(format = "yyyy-MM-dd")
    private String userQuitDate;
    @Fields(format = "yyyy-MM-dd")
    private String userCorrDate;
    private String userCid;
    private String orgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getUserDep() {
        return userDep;
    }

    public void setUserDep(String userDep) {
        this.userDep = userDep;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
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

    public String getUserCid() {
        return userCid;
    }

    public void setUserCid(String userCid) {
        this.userCid = userCid;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
