$(function(){
    var username=$('#username');
    var pword=$('#pword');
    var pword1=$('#pword1');
    var phone=$('#phone');
    var sex=$('#sex');
    var emild=$('#emild');
    re1=/^\w{5,20}/;
    re2=/^[A-Za-z0-9]{8,20}/;
    re3=/^[1]\d{10}/;
    var str="";
    $('form').submit(function(){
        var s=username.val();
        var s1=pword.val();
        var s2=phone.val();
        
        if(!pd(re1,s)||s.legth>20){
            str+="用户名格式不正确\n";
        }
        if(!pd(re2,s1)||s1.length>20){
            str+="密码格式不正确\n";
        }else{
            if(pword1.val()!==pword1.val()){
                str+="两次输入的密码不一致\n";
            }
        }
        if(!pd(re3,s2)||s2.length>11){
            str+="手机号码格式不正确\n";
        }
        if(str!=""){
            alert(str);
	str="";
            event.preventDefault();
        }
        else{
        	var json=JSON.stringify({username:username.val(),pword:pword.val(),phone:phone.val(),sex:sex.val(),emild:emild.val()});
        	 $.post("../Login",{user:json},function(data,st){
        	        if(data=='true'){
        	        	window.alert("注册成功");
        	        	window.location.href="../jsp/pipe.html";
        	        }
        	        else{
        	        	window.alert("注册失败");
        	        }
        	    });
        }
    });
    pword.change(function(){
        if(pword.val()!=""){
            $('.show').show();
        }else{
            $('.show').hide();
        }
    });
    document.getElementById('pword').oninput=document.getElementById('pword1').oninput=function(){
        if(pword1.val()!=""){
            $('.show1').show();
        }else{
            $('.show1').hide();
        }
        if(pword.val()!=""){
            $('.show').show();
        }else{
            $('.show').hide();
        }
    }
    // pword.on('input propertychange',function(){
    //     if(pword.val()!=""){
    //         $('.show').show();
    //     }else{
    //         $('.show').hide();
    //     }

    // });
    $('.show').click(function(){
        var type1=pword.attr('type');
        if(type1=='password'){
            pword.attr('type','text');
            $(this).attr('title','隐藏');
        }else{
            pword.attr('type','password');
            $(this).attr('title','显示');
        }
    });
    $('.show1').click(function(){
        var type1=pword1.attr('type');
        if(type1=='password'){
            pword1.attr('type','text');
            $(this).attr('title','隐藏');
        }else{
            pword1.attr('type','password');
            $(this).attr('title','显示');
        }
    });
    
    
   
});
function pd(re,s){
    return re.test(s);
}