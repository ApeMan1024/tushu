$(function(){
    var re1=/^\w{4,20}/;
    var re2=/^[A-Za-z0-9]{4,20}/;
    var username=$("#username");
    var pass=$("#pass");
    var pass1=$("#pass1");
    $("form").submit(function(){
        var boo=true;
        var s="";
        if(username.val().length>20||pass.val().length>20||pass1.val().length>20){
            alert("用户名或密码不能超过20位");
            event.preventDefault();
            return;
        }
        if(!bool(re1,username.val())){
            boo=false;
            s+="用名名只能由字母数字下划线组成\n";
        }
        if(!bool(re2,pass.val())){
            boo=false;
            s+="密码只能由字母数字组成\n";
        }
        if(pass.val()!==pass1.val()){
            s+="两次输入的密码不一致\n";
            boo=false
        }
        if(boo){
            var obj={username:username.val(),pass:pass.val()};
            var json=JSON.stringify(obj);
            $.post("../Password",{json:json},function(data,sta){
                if(data==1){
                    alert("密码修改成功");
                    window.location.href="../jsp/register.html";
                }
                else{
                    alert("用户名不存在");
                }
            });
        }
        else{
            alert(s);
            event.preventDefault();
            return;
        }

    });

})



function bool(te,s){

    //正则匹配
    return te.test(s);
}