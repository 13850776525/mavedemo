layui.use(['layer','form'], function () {
    var form = layui.form;
    var layer = layui.layer;
    $ = layui.jquery;


    //对form数据验证
    form.verify({
        UserAccount: [
            /^[0-9]*$/,
            '请输入正确的账号'
        ],
        UserPassword: [
            /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
            '请输入正确格式的密码'
        ],
    });


    //添加申请单
    form.on('submit(login)',function(data){
        var $path = $("#path").val();
        $.ajax({
            url:$("#path").val()+"/adminControl/login",
            method:'post',
            data:data.field,
            dataType:'text',
            beforeSend:function () {
                $("[value=登陆]").prop("disabled","disabled");
            },
            success:function (data) {
                if (data == "登陆成功") {
                    layer.msg('登陆成功！');
                    setTimeout(function(){
                        location.href = $path + "/adminControl/FindMenuUser";
                    }, 1000);
                }else{
                    layer.msg(data);
                }
                changeImg();
            },error:function () {
                changeImg();
            },complete:function () {
                $("[value=登陆]").prop("disabled","false");
            }
        });
        return false;
    });
});

layui.use(['layer','form'], function () {
    var form = layui.form;
    var layer = layui.layer;
    $ = layui.jquery;
    //对form数据验证
    form.verify({
        AdminAccount: [
            /^[0-9]*$/,
            '请输入正确的账号'
        ],
        AdminPassword: [
            /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
            '请输入正确格式的密码'
        ],
    });
    form.on('submit(loginManger)',function(data){
        var $path = $("#path").val();
        $.ajax({
            url:$("#path").val()+"/adminControl/loginManger",
            method:'post',
            data:data.field,
            dataType:'text',
            beforeSend:function () {
                $("[value=登陆]").prop("disabled","disabled");
            },
            success:function (data) {
                if (data == "登陆成功") {
                    layer.msg('登陆成功！');
                    setTimeout(function(){
                        location.href = $path + "/adminControl/FindMenu";
                    }, 500);
                }else{
                    layer.msg(data);
                }
                changeImg();
            },error:function () {
                changeImg();
            },complete:function () {
                $("[value=登陆]").prop("disabled","false");
            }
        });
        return false;
    });

});

//改变验证码
function changeImg() {
    var path = $("#path").val();
    $("#vCodeImg").attr("src", path + "/verifyCodeServlet?date=" + new Date());
    // alert($("#vCodeImg").val())
}


layui.use(['layer','form'], function () {
    var form = layui.form;
    var layer = layui.layer;
    $ = layui.jquery;

    //对form数据验证
    form.verify({
        UserName: [
            /^[\u4e00-\u9fa5]{1,10}$/,
            '名称必须为中文'
        ],
        UserPassword: [
            /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
            '密码至少包含数字和英文，长度6-12'
        ],
        UserPassword1: [
            /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
            '密码至少包含数字和英文，长度6-12'
        ],
        UserPhone:[
            /^1[3|4|5|7|8][0-9]{9}$/,
            '请输入有效的11位手机号码！'
        ],
        UserEmall:[
            /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,
            '请输入正确的邮箱地址！'
        ],
    });


    //添加申请单
    form.on('submit(RegUser)',function(data){
        $.ajax({
            url:$("#path").val()+"/loginServlet?methodName=RegUser",
            method:'post',
            data:data.field,
            dataType:'text',
            success:function(data){
                if(data=="注册成功"){
                    layer.msg('注册成功！');
                }else if(data=="添加失败"){
                    layer.msg('添加失败！');
                }
            },
        });
        return false;
    });
})
function Admintable() {
    layer.open({
        type: 2,
        title: '',
        shadeClose: false,//点击遮罩关闭为no
        anim: 1,//弹窗动画
        fixed: true,//---固定
        closeBtn: 2,
        resize: false,//设置不可拉伸
        shade: true,
        shade: [0.3, '#99CCFF'],
        maxmin: true, //开启最大化最小化按钮
        area: ['1620px', '600px'],
        content: ['/Document/jsp/AdminTable.jsp', 'no'], //iframe的url，no代表不显示滚动条
        success: function (layero, index) { //成功获得加载changefile.html时，预先加载，将值从父窗口传到 子窗口
            let body = layer.getChildFrame('body', index);
            var admin = {"guaaccount": guaaccount, "guaday": a}
            $.ajax({
                url: $("#path").val() + "/loginServlet?methodName=findAdmin",
                method: 'post',
                data: admin,
                dataType: 'text',
                success: function (data) {//---------------------------画出表数据
                    $(body.find("#addokok")).html(tableStr);

                }
            })
        }
    });
    return false;
}

function Usertable() {
    layer.open({
        type: 2,
        title: '',
        shadeClose: false,//点击遮罩关闭为no
        anim: 1,//弹窗动画
        fixed: true,//---固定
        closeBtn:2,
        resize:false,//设置不可拉伸
        shade: true,
        shade: [0.3, '#99CCFF'],
        maxmin: true, //开启最大化最小化按钮
        area: ['1620px', '600px'],
        content: ['/Document/jsp/AdminTable.jsp', 'no'], //iframe的url，no代表不显示滚动条
    });
    return false;
}

var datas;
function UserTables(list) {
    layui.use('table', function () {
        var table = layui.table;
        var util = layui.util;
        table.render({
            elem: '#userTables'
            //使用layui数据表格的data属性将Ajax调用的后台数据绑定
            , data: list
            , title: '预约挂号记录'
            ,height: 230
            , cols: [[
                {type:'checkbox', title: '选择',width: 75,align:'center'}
                ,{field: 'guaday', title: '预约日期', width: 220 ,align:'center',sort: true ,templet: function (d) {
                        return util.toDateString(d.guaday, "yyyy-MM-dd")
                    }
                }
                , {field: 'guaday', title: '预约就诊日期', width: 220 ,align:'center',sort: true ,templet: function (d) {
                        return util.toDateString(d.guaday, "yyyy-MM-dd")
                    }
                }
                , {field: 'guaname', title: '预约医生',width: 220,align:'center'}
                , {field: 'guakeshi', title: '预约科室',width: 220,align:'center'}
            ]]
        })
        table.on('checkbox(userTables)', function(obj){//---------------------------获取选择数据
            var checkStatus = table.checkStatus('userTables');
            datas = checkStatus.data;
            console.log(datas);
        });
    });
}  //预约取号的数据表格