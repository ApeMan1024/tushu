$(function () {
	$("#username").blur(function () {
		var str = $("#username").val();
		isUsername(str);

	})
	$("#userid").blur(function () {
		var str = $("#userid").val();
		isuserid(str);
	})
	$("#usersex").blur(function () {
		var str = $("#usersex").val();
		isusersex(str);
	})
	$("#major").blur(function () {
		var str = $("#major").val();
		ismajor(str);
	})
	$("#department").blur(function () {
		var str = $("#userphone").val();
		isdepartment(str);
	})
	$("#userphone").blur(function () {
		var str = $("#userphone").val();
		isuserphone(str);
	})
	$("#aplication").click(function(){
		var t = "";
		var username_str = $("#username").val();
		var isuserid_str = $("#userid").val();
		var isusersex_str = $("#usersex").val();
		var ismajor_str = $("#major").val();
		var isdepartment_str = $("#department").val();
		var isuserphone_str = $("#userphone").val();
		var bool=true;
		if(!isUsername(username_str)){
			bool = false;
			t += "用户名输入不正确！\n";
		}

		if(!isuserid(isuserid_str)){
			bool = false;
			t += "借书证号输入不正确！\n";
		}
		if(!isusersex(isusersex_str)){
			bool = false;
			t += "性别输入不正确！\n";}
		if(!ismajor(ismajor_str))
			{
				bool = false;
			t += "专业输入不正确！\n";
			}
		if(!isdepartment(isdepartment_str))
			{
				bool = false;
				t += "系别输入不正确！\n";
			}
		if(!isuserphone(isuserphone_str)){
			{
				bool = false;
				t += "联系电话号码格式不正确！\n";}
		}
		if (t != "") {
			alert(t);
			
		}
		if(bool){
			var s={
				username_str:username_str,
				isuserid_str:isuserid_str,
				isusersex_str:isusersex_str,
				ismajor_str:ismajor_str,
				isdepartment_str:isdepartment_str,
				isuserphone_str:isuserphone_str
			}
			var obj=JSON.stringify(s);
			$.post("../Borrow",{bo:obj},function(data,st){
					if(data=='true'){
        	        	window.alert("申请成功");
        	        	window.location.href="../jsp/pipe.html";
					}
					else{
						alert("申请失败");
					}
				})
		}
	})
})
// 用户名
function isUsername(str) {
	var re = /(^[\u4e00-\u9fa5]{2,6}$)|(^[A-Za-z]{2,20})/;
	 if (re.test(str)) {
		
		return true;
	}
	else{
		$("#lab1").css('color','red');
		return false;
	}
}

//电子邮箱
function isuserid(str) {
	var re = /^\w{4,20}/;
	 if (re.test(str)) {
		return true;
	}
	else{
		$("#lab2").css('color','red');
		return false;
	}
}
//密码
function isusersex(str) {
	var re = /^[\u4e00-\u9fa5]$/;
	if (re.test(str)) {
		return true;
	}
	else{
		$("#lab3").css('color','red');
		return false;
	}
}
//重复密码
function ismajor(str) {
	var re = /^[\u4e00-\u9fa5]{2,12}$/;
	if (re.test(str)) {
		
		return true;
	}
	else{
		$("#lab4").css('color','red');
		return false;
	}
}
//身份证
function isdepartment(str) {
	var re = /^[\u4e00-\u9fa5]{2,12}$/;
	if (re.test(str)) {
		
		return true;
	}
	else{
		$("#lab5").css('color','red');
		return false;
	}
}

//联系电话
function isuserphone(str) {
	var re = /^\d{10}\d$/;
	if (re.test(str)) {
		return true;
	}
	else{
		$("#lab6").css('color','red');
		return false;
	}
}