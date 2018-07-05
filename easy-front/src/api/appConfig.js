/**
 * Created by Admin on 2017/4/5.
 */

import constants from "../constants/index";

var API_PREFIX = constants.apiHost;
var API = {

  LOGIN: [
    API_PREFIX + '/user/login'
  ],

  SIGN: [
    API_PREFIX + '/user/sign'
  ],

  GETSIGN: [
    API_PREFIX + '/user/getSign'
  ],

  SAVEFORMS: [
    API_PREFIX + '/forms/save'
  ],

  GETFORMSPAGER: [
    API_PREFIX + '/forms/getFormsPager'
  ],

  GETFORMSDETAILS: [
    API_PREFIX + '/forms/getFormsDetails'
  ],

  GETWEATHER:[
    API_PREFIX + '/weather/getWeather'
  ],

  GETTABPAGERT: [
    API_PREFIX + '/tab/getTabPager'
  ],

  GETTABTYPE: [
    API_PREFIX + '/tab/getTabType'
  ],

  GETTABFIELDTYPE: [
    API_PREFIX + '/tab/getTabFieldType'
  ],

  SAVETAB: [
    API_PREFIX + '/tab/saveTab',
  ],

  GETTABFIELDLIST: [
    API_PREFIX +'/tab/getTabFieldList'
  ],

  GETTABDEATILS: [
    API_PREFIX + '/tab/getTabDetails'
  ],

  GETTABDATAPAGER: [
    API_PREFIX + '/tab/getTabDataPager'
  ],

  GETTABALL: [
    API_PREFIX + '/tab/getTabAll'
  ],

  REMOVETAB: [
    API_PREFIX + '/tab/removeTab'
  ],

  GETOBJPAGER: [
    API_PREFIX + '/obj/getObjPager'
  ],

  SAVEOBJ: [
    API_PREFIX + '/obj/saveObj'
  ],

  GETOBJALL: [
    API_PREFIX + '/obj/getObjAll'
  ],

  GETOBJTABFIELDLIST: [
    API_PREFIX + '/obj/getObjTabFieldList'
  ],

  REMOVEOBJ: [
    API_PREFIX + '/obj/removeObj'
  ],

  GETOBJDETAILS: [
    API_PREFIX + '/obj/getObjDetails'
  ],

  SAVEEVNET: [
    API_PREFIX + '/event/save'
  ],

  GETORGTREE: [
    API_PREFIX + '/org/getOrgTree'
  ],

  GETUSERBYORG: [
    API_PREFIX + '/user/getUserByOrg'
  ],

  GETPROVINCELIST: [
    API_PREFIX + '/address/getProvinceList'
  ],

  GETCITYLIST: [
    API_PREFIX + '/address/getCityList'
  ],

  SAVEUSER: [
    API_PREFIX + '/user/saveUser'
  ],

  GETORGBYORGID: [
    API_PREFIX + '/org/getOrgByOrgId'
  ],

  SAVEORG: [
    API_PREFIX + '/org/saveOrg'
  ],

  GETORGBYPARENTID: [
    API_PREFIX + '/org/getOrgByParentId'
  ],

  GETDEPBYPARENTID: [
    API_PREFIX + '/org/getDepByParentId'
  ],

  GETORGNO: [
    API_PREFIX + '/org/getOrgNo'
  ],

  REMOVEORG: [
    API_PREFIX + '/org/removeOrg'
  ],

  SAVEROLE: [
    API_PREFIX + '/role/saveRole'
  ],

  GETROLEPAGE: [
    API_PREFIX + '/role/getRolePager'
  ],

  SAVEUSERROLE: [
    API_PREFIX + '/user/saveUserRole'
  ],

  GETUSERROLE: [
    API_PREFIX + '/user/getUserRole'
  ],

  SAVEUSERACCOUNT: [
    API_PREFIX + '/user/saveUserAccount'
  ],

  GETUSERACCOUNT: [
    API_PREFIX + '/user/getUserAccount'
  ],

  REMOVEUSER: [
    API_PREFIX +'/user/removeUser'
  ],

  GETUSERBYUSERID: [
    API_PREFIX + '/user/getUserByUserId'
  ],

  GETRANGELIST: [
    API_PREFIX + '/role/getRangeList'
  ],

  REMOVEROLE: [
    API_PREFIX + '/role/removeRole'
  ],

  GETROLEBYID: [
    API_PREFIX + '/role/getRoleById'
  ],

  GETCHECKMENU: [
    API_PREFIX + '/role/getCheckedMenu'
  ],

  SAVEROLEMENU: [
    API_PREFIX + '/role/saveRoleMenu'
  ],

  GETMENULIST: [
    API_PREFIX + '/menu/getMenuList'
  ],

  GETMENUBYID: [
    API_PREFIX + '/menu/getMenuById'
  ],

  GETMENUPARENT: [
    API_PREFIX + '/menu/getMenuParent'
  ],

  SAVEMENU: [
    API_PREFIX + '/menu/saveMenu'
  ],

  GETMENU: [
    API_PREFIX +'/menu/getMenu'
  ],

  REMOVEMENU: [
    API_PREFIX + '/menu/removeMenu'
  ],

  GETMENUALLLIST: [
    API_PREFIX + '/menu/getMenuAllList'
  ],

  GETMENUBYORGID: [
    API_PREFIX+'/org/getMenuByOrgId'
  ],

  SAVEORGMENU: [
    API_PREFIX + '/org/saveOrgMenu'
  ],

  GETUSERCODE: [
    API_PREFIX + '/user/getUserCode'
  ]
}
export {
  API,
  API_PREFIX
};
