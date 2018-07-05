/**
 * Created by majun on 2017/3/31.
 */

import http from "../util/http";
import {API} from "./appConfig";


export default {

  /**
   * 保存表单
   * @param param
   */
  saveForms: function(param){
    return POST(API.SAVEFORMS,param);
  },

  sign: function (param) {
    return GET(API.SIGN,param)
  },

  getSign: function(param){
    return GET(API.GETSIGN,param)
  },

  /**
   * 查找表单分页
   * @param param
   */
  getFormsPager: function(param){
    return GET(API.GETFORMSPAGER,param)
  },

  /**
   * 查找表单详细
   * @param param
   */
  getFormsDetails: function(param){
    return GET(API.GETFORMSDETAILS,param);
  },

  /**
   * 用户登录
   * @param param
   */
  login: function (param) {
    return POST(API.LOGIN,param);
  },

  /**
   * 获取天气信息
   * @param param
   */
  getWeather: function(param){
    return GET(API.GETWEATHER,param);
  },

  /**
   * 查找数据表列表
   * @param param
   */
  getTabPager: function (param) {
    return GET(API.GETTABPAGERT,param);
  },

  /**
   * 获取表的类型
   */
  getTabType: function(){
    return GET(API.GETTABTYPE)
  },

  /**
   * 获取字段类型
   */
  getTabFieldType: function(){
    return GET(API.GETTABFIELDTYPE)
  },

  /**
   * 保存数据表信息
   * @param param
   */
  saveTab: function(param){
    return POST(API.SAVETAB, param)
  },

  /**
   * 获取字段列表
   * @param param
   */
  getTabFieldList: function(param){
    return GET(API.GETTABFIELDLIST,param)
  },

  /**
   * 查找表详细
   * @param param
   */
  getTabDetails: function (param) {
    return GET(API.GETTABDEATILS, param)
  },

  /**
   * 查找表对应的数据分页
   * @param param
   */
  getTabDataPager: function (param) {
    return GET(API.GETTABDATAPAGER,param)
  },

  /**
   * 获取所有的表
   */
  getTabAll: function () {
    return GET(API.GETTABALL)
  },

  /**
   * 删除表
   * @param param
   */
  removeTab: function (param){
    return POST(API.REMOVETAB,param)
  },

  /**
   * 删除对象
   * @param param
   */
  removeObj: function (param){
    return POST(API.REMOVEOBJ,param)
  },

  /**
   * 获取对象详细
   * @param param
   */
  getObjDetails: function(param){
    return GET(API.GETOBJDETAILS,param)
  },

  /**
   * 获取对象列表
   * @param param
   */
  getObjPager: function (param) {
    return GET(API.GETOBJPAGER,param)
  },

  /**
   * 保存对象
   * @param param
   */
  saveObj: function (param) {
    return POST(API.SAVEOBJ,param)
  },

  /**
   * 获取所有的对象
   */
  getObjAll: function () {
    return GET(API.GETOBJALL)
  },

  /**
   * 获取对象字段列表
   * @param param
   */
  getObjTabFieldList: function (param) {
    return GET(API.GETOBJTABFIELDLIST,param)
  },

  /**
   * 保存事件
   * @param param
   */
  saveEvent: function (param) {
    return POST(API.SAVEEVNET,param)
  },

  /**
   * 获取组织架构树形
   */
  getOrgTree: function () {
    return GET(API.GETORGTREE)
  },

  /**
   * 根据组织查找用户
   * @param param
   */
  getUserByOrg: function (param) {
    return GET(API.GETUSERBYORG,param)
  },

  /**
   * 查找所有省份
   */
  getProvinceList: function () {
    return GET(API.GETPROVINCELIST)
  },

  /**
   * 根据省份id查找市区
   * @param param
   */
  getCityList: function (param) {
    return GET(API.GETCITYLIST,param)
  },

  /**
   * 保存用户
   * @param param
   */
  saveUser: function (param) {
    return POST(API.SAVEUSER,param)
  },

  /**
   * 获取组织名称
   * @param param
   */
  getOrgByOrgId: function (param) {
    return GET(API.GETORGBYORGID,param)
  },

  /**
   * 保存组织
   * @param param
   */
  saveOrg: function(param){
    return POST(API.SAVEORG,param)
  },

  /**
   * 根据父节点id查找组织
   * @param param
   */
  getOrgByParentId: function (param) {
    return GET(API.GETORGBYPARENTID,param)
  },

  /**
   * 获取组织编号
   * @param param
   */
  getOrgNo: function (param) {
    return GET(API.GETORGNO,param)
  },

  /**
   * 删除组织
   * @param param
   */
  removeOrg: function (param) {
    return POST(API.REMOVEORG,param)
  },

  /**
   * 查找部门
   * @param param
   */
  getDepByParentId: function (param) {
    return GET(API.GETDEPBYPARENTID,param)
  },

  /**
   * 保存角色
   * @param param
   */
  saveRole: function (param) {
    return POST(API.SAVEROLE,param)
  },

  /**
   * 获取角色列表
   * @param param
   */
  getRolePager: function (param) {
    return GET(API.GETROLEPAGE,param)
  },

  /**
   * 保存用户角色
   * @param param
   */
  saveUserRole: function (param) {
    return POST(API.SAVEUSERROLE,param)
  },

  /**
   * 获取用户角色
   * @param param
   */
  getUserRole: function (param) {
    return GET(API.GETUSERROLE,param)
  },

  /**
   * 添加用户账号
   * @param param
   */
  saveUserAccount: function (param) {
    return POST(API.SAVEUSERACCOUNT,param)
  },

  /**
   * 获取用户账号
   * @param param
   */
  getUserAccount: function (param) {
    return GET(API.GETUSERACCOUNT,param)
  },

  /**
   * 删除用户
   * @param param
   */
  removeUser: function (param) {
    return POST(API.REMOVEUSER,param)
  },

  /**
   * 获取用户信息
   * @param param
   */
  getUserByUserId: function (param) {
    return GET(API.GETUSERBYUSERID,param)
  },

  /**
   * 查找可见范围
   */
  getRangeList: function () {
    return GET(API.GETRANGELIST)
  },

  /**
   * 删除角色
   * @param param
   */
  removeRole: function (param) {
    return GET(API.REMOVEROLE,param)
  },

  /**
   * 根据角色id查找角色
   * @param param
   */
  getRoleById: function (param) {
    return GET(API.GETROLEBYID,param)
  },

  /**
   * 获取菜单列表
   */
  getMenuList: function () {
    return GET(API.GETMENULIST)
  },

  /**
   * 根据id查找下级菜单
   * @param param
   */
  getMenuById: function (param) {
    return GET(API.GETMENUBYID,param)
  },

  /**
   * 获取父节点菜单
   */
  getMenuParent: function () {
    return GET(API.GETMENUPARENT)
  },

  /**
   * 保存菜单
   * @param param
   */
  saveMenu: function (param) {
    return POST(API.SAVEMENU,param)
  },

  /**
   * 获取菜单详细
   * @param param
   */
  getMenu: function (param) {
    return GET(API.GETMENU,param)
  },

  /**
   * 删除菜单
   * @param param
   */
  removeMenu: function (param) {
    return GET(API.REMOVEMENU,param)
  },

  /**
   * 获取所有菜单
   */
  getMenuAllList: function () {
    return GET(API.GETMENUALLLIST)
  },

  /**
   * 查找选中的menu
   * @param param
   */
  getCheckMenu: function (param) {
    return GET(API.GETCHECKMENU,param)
  },

  /**
   * 保存角色和菜单关联关系
   * @param param
   */
  saveRoleMenu: function (param) {
    return POST(API.SAVEROLEMENU,param)
  },

  /**
   * 根据组织id获取菜单id
   * @param param
   */
  getMenuByOrgId: function (param) {
    return GET(API.GETMENUBYORGID,param)
  },

  /**
   * 保存组织和菜单关联
   * @param param
   */
  saveOrgMenu: function (param) {
    return POST(API.SAVEORGMENU,param)
  },

  /**
   * 获取用户code
   * @param param
   */
  getUserCode: function(param){
    return GET(API.GETUSERCODE,param)
  },
}

const GET = (url, param) => {
  return http.get(url, param)
};

const POST = (url, param) => {
  if (typeof(param) != undefined) {
    return http.post(url, param)
  }
  else {
    return http.post(url);
  }
};

const PUT = (url, param) => {
  return http.post(url, param)
};

const DELETE = (url, param) => {
  return http.delete(url, param)
};
