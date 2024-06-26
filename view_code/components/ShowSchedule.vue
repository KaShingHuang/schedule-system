<script setup>
import request from '../utils/request.js'
import {defineSchedule} from '../store/scheduleStore.js'
import {defineUser} from '../store/userStore.js'
let schedule = defineSchedule();
let sysUser = defineUser()
import {reactive,ref,onMounted,onUpdated} from 'vue'

onMounted(async function (){
        // 加载完毕后,立刻调用查询数据的方法
        showSchedule()
    })
    async function  showSchedule(){
        let {data} = await request.get("/schedule/findAllSchedule",{params:{"uid":sysUser.uid}})
        schedule.itemList =data.data.itemList
    }

async function addItem(){
        // 立刻向后端发送一个请求,让后端先为当前用户在数据库中增加一个默认格式的空数据
        let {data} = await request.get("/schedule/addDefaultSchedule",{params:{"uid":sysUser.uid}})
        if(data.code == 200){
            // 然后调用刷新页面数据方法,立刻获取最新数据
            showSchedule()
        }else{
            alert("添加异常")
        }
    }


async function updateItem(index){
        // 根据索引获取元素
        
        // 将元素通过 JSON串的形式 发送给服务端
        let {data} =await request.post("/schedule/updateSchedule",schedule.itemList[index])
        if(data.code == 200){
            alert("更新成功")
            // 服务端修改完毕后,刷新页面数据
            showSchedule()
        }else{
            alert("更新异常")
        }

    }

    async function removeItem(index){
        // 弹窗提示是否删除
        if(confirm("确定要删除该条数据")){
            // 根据索引获取要删除的item的id
            let sid = schedule.itemList[index].sid
            // 向服务端发送请求删除元素
            let{data} = await request.get("/schedule/removeSchedule",{params:{"sid":sid}})
            //根据业务码判断删除是否成功
            if(data.code == 200){
                // 删除成功,更新数据
                showSchedule()
            }else{
                // 删除失败,提示失败
                alert("删除失败")
            }
        }
    }


</script>

<template>
  <div>
    <h3 class="ht">您的日程如下</h3>
<table class="tab" cellspacing="0px">
    <tr class="ltr">
        <th>编号</th>
        <th>内容</th>
        <th>进度</th>
        <th>操作</th>
    </tr>
    <tr class="ltr" v-for="item,index in schedule.itemList" :key="index">
        <td v-text="index+1">
        </td>
        <td>
            <input type="input" v-model="item.title">
        </td>
        <td>
            <input type="radio"   value="1" v-model="item.completed"> 已完成
            <input type="radio"   value="0" v-model="item.completed"> 未完成
        </td>
        <td class="buttonContainer">
             <button class="btn1"  @click="removeItem(index)">删除</button>
            <button class="btn1" @click="updateItem(index)">保存修改</button>
        </td>
    </tr>
    <tr class="ltr buttonContainer" >
        <td colspan="4">
            <button class="btn1" @click="addItem()">新增日程</button>
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
            width: 80%;
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
            width:100px;
            background-color: antiquewhite;

        }
        #usernameMsg , #userPwdMsg {
            color: gold;
        }

        .buttonContainer{
            text-align: center;
        }

</style>