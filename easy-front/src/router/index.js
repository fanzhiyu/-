import Vue from 'vue'
import Router from 'vue-router'
import lazyLoad from '../util/lazyLoading';

Vue.use(Router)

const login = lazyLoad('login/login');
const index = lazyLoad('index/index');
const home = lazyLoad('home/home');
const org = lazyLoad('org/org');
const user = lazyLoad('org/user');
const addOrg = lazyLoad('org/addOrg');
const admin =lazyLoad('admin/admin');
const pro = lazyLoad('process/process');
const task = lazyLoad('task/task');
const make = lazyLoad('form/make');
const forms = lazyLoad('form/forms');
const sign = lazyLoad('sign/sign');
const tabs = lazyLoad('tabs/tabs');
const tabsAdd = lazyLoad('tabs/tabsAdd');
const object = lazyLoad('object/object');
const objectAdd = lazyLoad('object/objectAdd')
const formsDetails = lazyLoad('form/formsDetails')
const role = lazyLoad('role/role')
const roleAdd = lazyLoad('role/roleAdd')
const userRole = lazyLoad('org/userRole')
const userAccount = lazyLoad('org/userAccount')
const auth = lazyLoad('auth/auth')
const authAdd = lazyLoad('auth/authAdd')


export default new Router({
    trict: process.env.NODE_ENV !== 'production',
    mode: 'history',
    // linkActiveClass: 'is-active',
    scrollBehavior: () => ({y: 0}),
    routes: [
        {
            path: '/',
            name: 'login',
            component: login
        },
        {
          path: '/sign',
          name: 'sign',
          component: sign
        },
        {
          path: '/index',
          name: 'index',
          redirect: 'home',
          component: index,
          children:[
            {
              path: '/org',
              name: 'org',
              component: org,
              children:[
                {
                  path: '/user',
                  name: 'user',
                  component: user
                },
                {
                  path: '/addOrg',
                  name: 'addOrg',
                  component: addOrg
                },
                {
                  path: '/userAccount',
                  name: 'userAccount',
                  component: userAccount
                }
              ]
            },
            {
              path: '/home',
              name: 'home',
              component: home
            },
            {
              path: '/admin',
              name: 'admin',
              component: admin
            },
            {
              path: '/process',
              name: 'process',
              component: pro
            },
            {
              path: '/task',
              name: 'task',
              component: task
            },
            {
              path: '/make',
              name: 'make',
              component: make
            },
            {
              path: '/forms',
              name: 'forms',
              component: forms,
              children:[
                {
                  path: '/forms-details',
                  name: 'formsDetails',
                  component: formsDetails
                }
              ]
            },
            {
              path: '/tabs',
              name: 'tabs',
              component: tabs,
              children:[
                {
                  path: '/tabs-add',
                  name: 'tabsAdd',
                  component: tabsAdd
                }
              ]
            },
            {
              path: '/object',
              name: 'object',
              component: object,
              children: [
                {
                  path: '/object-add',
                  name: 'objectAdd',
                  component: objectAdd
                }
              ]
            },
            {
              path: '/role',
              name: 'role',
              component: role,
              children: [
                {
                  path: '/role-add',
                  name: 'roleAdd',
                  component: roleAdd,
                }
              ]
            },
            {
              path: '/auth',
              name: 'auth',
              component: auth,
              children: [
                {
                  path: '/auth-add',
                  name: 'authAdd',
                  component: authAdd
                }
              ]
            }
          ]
        },
    ]
})

