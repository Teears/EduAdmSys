import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/LoginUser'
import Forgot from '@/components/login/Forgot'
import LoginAdmin from '@/components/login/LoginAdmin'
import Student from '@/components/student/Student'
import Header from '@/components/common/Header'
import Stu1_1 from '@/components/student/Stu1_1'
import Stu1_2 from '@/components/student/Stu1_2'
import Stu2 from '@/components/student/Stu2'
import Stu3 from '@/components/student/Stu3'
import Stu4_1 from '@/components/student/Stu4_1'
import Stu4_2 from '@/components/student/Stu4_2'
import Stu4_3 from '@/components/student/Stu4_3'

import Tea4_3 from '@/components/teacher/Tea4_3'
import Tea4_2 from '@/components/teacher/Tea4_2'
import Tea4_1 from '@/components/teacher/Tea4_1'
import Tea3 from '@/components/teacher/Tea3'
import Tea2 from '@/components/teacher/Tea2'
import Tea1_2 from '@/components/teacher/Tea1_2'
import Tea1_1 from '@/components/teacher/Tea1_1'
import Teacher from '@/components/teacher/Teacher.vue'

import Adm1_1 from '@/components/admin/Adm1_1'
import Adm1_2 from '@/components/admin/Adm1_2'
import Adm1_3 from '@/components/admin/Adm1_3'
import Adm2_1 from '@/components/admin/Adm2_1'
import Adm3_1 from '@/components/admin/Adm3_1'
import Adm3_2 from '@/components/admin/Adm3_2'
import Adm4 from '@/components/admin/Adm4'
import Admin from '@/components/admin/Admin.vue'

Vue.use(Router)
 
export default new Router({
  mode:'history',
    routes: [
      {
        path: '/',
        redirect: '/login'
      },
        {
          path: '/login',
          component: Login,
          meta:{
            keepalive:false
          }
        },
        {
          path:'/loginAdmin',
          component:LoginAdmin,
        },{
          path:'/header',
          component:Header,
          meta:{
            keepalive:false
          }
        },
        {
          path:'/forgot',
          name:'forgot',
          component:Forgot
        },
        {
          path: '/Student',
          component: Student,
          meta:{
            keepalive: false,
            requireAuth: true
          },
          children:[
            {
              path:'/Stu1_1',
              component:Stu1_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu1_2',
              component:Stu1_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu2',
              component:Stu2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu3',
              component:Stu3,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu4_1',
              component:Stu4_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu4_2',
              component:Stu4_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Stu4_3',
              component:Stu4_3,
              meta:{
                requireAuth: true
              }
            },
          ]
          
        },{
          path: '/Teacher',
          component: Teacher,
          meta:{
            keepalive: false,
            requireAuth: true
          },
          children:[
            {
              path:'/Tea1_1',
              component:Tea1_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea1_2',
              component:Tea1_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea2',
              component:Tea2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea3',
              component:Tea3,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea4_1',
              component:Tea4_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea4_2',
              component:Tea4_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Tea4_3',
              component:Tea4_3,
              meta:{
                requireAuth: true
              }
            },
          ]
          
        },{
          path: '/Admin',
          name:'/Admin',
          component: Admin,
          meta:{
            keepalive: false,
            requireAuth: true
          },
          children:[
            {
              path:'/Adm1_1',
              name:'/Adm1_1',
              component:Adm1_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm1_2',
              component:Adm1_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm1_3',
              component:Adm1_3,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm2_1',
              component:Adm2_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm3_1',
              component:Adm3_1,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm3_2',
              component:Adm3_2,
              meta:{
                requireAuth: true
              }
            },
            {
              path:'/Adm4',
              component:Adm4,
              meta:{
                requireAuth: true
              }
            }
          ]
          
        }
      ]
});