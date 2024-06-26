<script setup>
      import{ ref,reactive} from 'vue'
      import request from '../utils/request'
      import {useRouter} from 'vue-router'
      const router = useRouter()
       // 响应式数据,保存用户输入的表单信息
       let registUser =reactive({
        username:'',
        userPwd:''
       })

       // 响应式数据,保存校验的提示信息
       let reUserPwd =ref('')
       let reUserPwdMsg =ref('')
       let usernameMsg =ref('')
       let userPwdMsg = ref('')

          // 校验用户名的方法
        function checkUsername(){
            // 定义正则
            let usernameReg=/^[a-zA-Z0-9]{5,10}$/
            // 校验
            if(!usernameReg.test(registUser.username)){
              // 提示
              usernameMsg.value = "不合法"
              return false
            }
            // 通过校验
            usernameMsg.value="OK"
            return true
        }
        // 校验密码的方法
        function checkUserPwd(){
            // 定义正则
            let passwordReg=/^[0-9]{6}$/
             // 校验
             if(!passwordReg.test(registUser.userPwd)){
              // 提示
              userPwdMsg.value = "不合法"
              return false
            }
            // 通过校验
            userPwdMsg.value="OK"
            return true
        }
        // 校验密码的方法
        function checkReUserPwd(){
            // 定义正则
            let passwordReg=/^[0-9]{6}$/
            // 校验
            if(!passwordReg.test(reUserPwd.value)){
              // 提示
              reUserPwdMsg.value = "不合法"
              return false
            }
            console.log(registUser.userPwd,reUserPwd.value)
            // 校验
            if(!(registUser.userPwd==reUserPwd.value)){
              // 提示
              reUserPwdMsg.value = "不一致"
              return false
            }


            // 通过校验
            reUserPwdMsg.value="OK"
            return true
        }

        async function regist(){
            //校验所有的输入是否合法
            let flag1= await checkUsername()
            let flag2= await checkUserPwd()
            let flag3= await checkReUserPwd()
            if(flag1&&flag2&&flag3){
                let {data}=await request.post("user/regist",registUser)
                if(data.code==200){
                    alert("注册成功，快去登录吧")
                    router.push("/login")
                }
                else{
                    alert("抱歉，已经被抢先注册了")
                }
            }
            else{
                alert("校验不通过，请求再次检查数据")
            }
        }
</script>

<template>
  <div>
    <h3 class="ht">请注册</h3>

    <table class="tab" cellspacing="0px">
        <tr class="ltr">
            <td>请输入账号</td>
            <td>
                <input class="ipt" 
                  id="usernameInput" 
                  type="text" 
                  name="username" 
                  v-model="registUser.username"
                  @blur="checkUsername()">

                <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>请输入密码</td>
            <td>
                <input class="ipt" 
                id="userPwdInput" 
                type="password" 
                name="userPwd" 
                v-model="registUser.userPwd"
                @blur="checkUserPwd()">
                <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>确认密码</td>
            <td>
                <input class="ipt" 
                id="reUserPwdInput" 
                type="password" 
                v-model="reUserPwd"
                @blur="checkReUserPwd()">
                <span id="reUserPwdMsg" class="msg" v-text="reUserPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td colspan="2" class="buttonContainer">
                <input class="btn1" type="button" @click="regist()" value="注册">
                <input class="btn1" type="button" value="重置">
                <router-link to="/login">
                  <button class="btn1">去登录</button>
                </router-link>
            </td>
        </tr>
    </table>


  </div>
</template>

<style scoped>
       .ht{
            text-align: center;
            color: cadetblue;
            font-family: 幼圆;
        }
        .tab{
            width: 500px;
            border: 5px solid cadetblue;
            margin: 0px auto;
            border-radius: 5px;
            font-family: 幼圆;
        }
        .ltr td{
            border: 1px solid  powderblue;

        }
        .ipt{
            border: 0px;
            width: 50%;

        }
        .btn1{
            border: 2px solid powderblue;
            border-radius: 4px;
            width:60px;
            background-color: antiquewhite;

        }
        .msg {
            color: gold;
        }
        .buttonContainer{
            text-align: center;
        }
</style>
