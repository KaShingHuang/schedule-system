import {createRouter,createWebHashHistory} from 'vue-router'
import pinia from '../pinia.js'
import {defineUser} from '../store/userStore.js'

let sysUser = defineUser(pinia)
import Login from '../components/Login.vue'
import Regist from '../components/Regist.vue'
import ShowSchedule from '../components/ShowSchedule.vue'

let router =createRouter(
    {
        history:createWebHashHistory(),
        routes:[
            {
                path:'/',
                component:Login
            },
            {
                path:'/login',
                component:Login
            },
            {
                path:'/regist',
                component: Regist
            },
            {
                path:'/showSchedule',
                component:ShowSchedule
            }
        ]
    }
)
router.beforeEach((to,from,next) =>{
    if(to.path=='/showSchedule'){
        if(sysUser.username==''){
            alert("尚未登录")
            next('/login')
        }
        else{
            next()
        }
    }
    else{
        next()
    }
})

export default router
