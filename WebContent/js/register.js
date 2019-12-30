$(function(){
    $("#username").focus(function(){
        show_1($(this),'placeholder',"");
    });
    $("#pword").focus(function(){
        show_1($(this),'placeholder',"");
    });
    $("#username").blur(function(){
        show_1($(this),'placeholder',"用户名/手机号码");
    });
    $("#pword").blur(function(){
        show_1($(this),'placeholder',"密码");
    });
    $('#ve-code').click(function(){
        var length=$(this).text().length;
        var s="";
        for(i=0;i<length;i++){
            s+=rand();
        }
        $(this).text(s);
    });
    $(".record input").click(function(){
        var username_val=$('#username').val();
        var pword_val=$('#pword').val();
        var ve_code=$('#ve-code').text();
        var ve=$("#code").val();
        var re1=/^\w{4,20}/;
        var re2=/^[A-Za-z0-9]{4,20}/;
        var s=""
        bool=true;
        if(username_val.length==0||pword_val.length==0){
            alert("用户名与密码不能为空");
            event.preventDefault();
            bool=false;
        }
        else{

            if(!(pd(re1,username_val)&&username_val.length<=20)){
                s+="用户名格式不正确\n";
                bool=false;
            }
            if(!(pd(re2,pword_val)&&pword_val.length<=20)){
                s+="密码不正确\n";
                bool=false;
            }
            if(ve_code!==ve){
                s+="验证码不正确";
                bool=false;
            }
            if(s!=""){
                alert(s);
                s="";
                bool=false;
            }
            if(bool){
                $.post("../Register",{username:username_val,pword:pword_val},function(data,st){
                	if(data!="#"){
                		
                		var s=JSON.parse(data);
                		var s1=JSON.parse(s[1]);
                		if(s[0]=='true'){
                			$.cookie('user',s1["name"]);
                			$.cookie('phone',s1["phone"]);
                			$.cookie('bool',"超级管理员");
                			window.location.href="../jsp/pipe.html";
                		}
                		else if(s[0]=='false'){
                			$.cookie('user',s1["name"]);
                			$.cookie('phone',s1["phone"]);
                			$.cookie('bool',"普通管理员");
                			window.location.href="../jsp/pipe.html";
                		}
                	}
                    else{
                    	alert("密码不正确");
                    }
                });
            }
        }
       
    });
    
});
function show_1(obj,name,s){
    obj.prop(name,s);
}
function rand(){
    var s="0123456789";
    var s1="qwertyuiopasdfghjklzxcvbnm";
    s=s+s1+s1.toUpperCase();
    var n=Math.floor(Math.random()*62);
    return s[n];
}
function pd(re,s){
    return re.test(s);
}