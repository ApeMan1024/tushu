$(function () {
    if($.cookie('user')){
        $(".tao .tao-right .name1").html($.cookie('user'));
        $(".tao .tao-right .opa").html($.cookie('bool'));
        $(".tao .tao-right .phona").html($.cookie('phone'));
        if($.cookie('bool')=='普通管理员'){
        	$(".heard ul li:nth-child(2)").hide();
        }
    }
    tab = $('.right-1 .shou-1 #Tab tbody');
    $.post("../Pipe", { bo: "card" }, function (data, st) {
        json = JSON.parse(data);
        for (i = 0; i < json.length; i++) {
            var tr = $("<tr></tr>");
            var td1 = $("<td></td>").html(json[i]["name"]);
            var td2 = $("<td></td>").html(json[i]["dpt"]);
            var td3 = $("<td></td>").html(json[i]["amount"]);
            var td4 = $("<td></td>").html(json[i]["number"]);
            var td5 = $("<td></td>").html(json[i]["total"]);
            var td6 = $("<td></td>").html(json[i]["owe"]);
            var td7=$("<td></td>").html(json[i]["gender"]);
            var td8=$("<td></td>").html(json[i]["phone"]);
            tr.append(td1, td2,td8,td7, td3, td4, td5, td6);
            tab.append(tr);
        }
    });
    $("#sub1").click(function () {
        var book = $("#book").val();
        var table = $('<table border="1" cellspacing="0" class="lin"></table>');
        table.html("<thead>< tr><td>姓名</td><td>系别</td><td>号码</td><td>性别</td><td>允许借阅量</td><td>借书证号</td><td>总分</td><td>欠分</td></tr ></thead > ");
        if (book != "") {
            var tr1 = document.getElementById("Tab").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
            tab = $('.right-1 .shou-1 #Tab tbody');
            var tr2 = tab.children();
            bool = true;
            for (i = 0; i < tr1.length; i++) {
                var td1 = tr2.eq(i).children();
                for (j = 0; j < 6; j++) {
                    var s = td1.eq(j).html();
                    if (s.search(book) != -1) {
                        bool = false;
                        var tr0 = $("<tr></tr>").html(tr2.eq(i).html());
                        table.append(tr0);
                        break;
                    }
                }

            }
            if (bool) alert("没有这个学生");
            else {
                $(".right-1 .shou-1 table").remove(".lin");
                $(".right-1 .shou-1 table").hide();
                $(".right-1 .shou-1").append(table);
            }

        }
    });
    $("#book").bind("input propertychange", function (event) {
        var d = $(this).val();
        if (d == "") {
            $(".right-1 .shou-1 table").remove(".lin");
            $(".right-1 .shou-1 table").show();
        }
    });
    $(".heard ul li:nth-child(1)").click(function(){
        window.location.href="../jsp/borrow.html";
    });
    $(".heard ul li:nth-child(6)").click(function(){
        $.cookie('user','',{expires:-1});
        $.cookie('phone','',{expires:-1});
        $.cookie('bool','',{expires:-1});
//        window.location.href="../jsp/index.html";
        window.location.href="../";
    })
    $(".heard ul li:nth-child(5)").click(function () {
        $(".right-1").show();
        $(".right-2,.right-3,.right-4").hide();
        var tab = $('.right-1 .shou-1 #Tab tbody');
        tab.empty();
        $.post("../Pipe", { bo: "card" }, function (data, st) {
            json = JSON.parse(data);
            for (i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").html(json[i]["name"]);
                var td2 = $("<td></td>").html(json[i]["dpt"]);
                var td3 = $("<td></td>").html(json[i]["amount"]);
                var td4 = $("<td></td>").html(json[i]["number"]);
                var td5 = $("<td></td>").html(json[i]["total"]);
                var td6 = $("<td></td>").html(json[i]["owe"]);
                var td7=$("<td></td>").html(json[i]["gender"]);
                var td8=$("<td></td>").html(json[i]["phone"]);
                tr.append(td1, td2,td8,td7, td3, td4, td5, td6);
                tab.append(tr);
            }
        });
    });
    $(".heard ul li:nth-child(3)").click(function () {
        $(".right-2").show();
        $(".right-1,.right-3,.right-4").hide();
        var tab = $('.right-2 .shou-1 #Tab1 tbody');
        tab.empty();
        $.post("../Pipe", { bo: "book" }, function (data, st) {
            json = JSON.parse(data);
            for (i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").html(json[i]["booknumber"]);
                var td2 = $("<td></td>").html(json[i]["bookname"]);
                var td3 = $("<td></td>").html(json[i]["author"]);
                var td4 = $("<td></td>").html(json[i]["data"]);
                var td5 = $("<td></td>").html(json[i]["appear"]);
                var td6 = $("<td></td>").html(json[i]["bookshelf"]);
                var td7 = $("<td></td>").html(json[i]["amount"]);
                var td8 = $("<td></td>").html(json[i]["total"]);
                tr.append(td1, td2, td3, td4, td5, td6, td7, td8);
                tab.append(tr);
            }
        });
    });
    $(".right-2 .su1 input").click(function () {
        var s1 = $("#booknumber");
        var s2 = $("#bookname");
        var s3 = $("#zuo");
        var s4 = $("#chu");
        var s5 = $("#ban");
        var s6 = $("#hao");
        var s7 = $("#liang");

        if (s1.val() != "" && s2.val() != "" && s3.val() != "" && s4.val() != "" && s5.val() != "" && s6.val() != "" && s7.val() != "") {
            
            s = {
                booknumber: s1.val(),
                bookname: s2.val(),
                zuo: s3.val(),
                chu: s4.val(),
                ban: s5.val(),
                hao: s6.val(),
                liang: s7.val()
            };
            s1.val(""); s2.val(""); s3.val(""); s4.val(""); s5.val(""); s6.val(""); s7.val("");
            json = JSON.stringify(s);
            var tab = $('.right-2 .shou-1 #Tab1 tbody');
            tab.empty();
            $.post("../Pipe", { bo: "book1", json: json }, function (data, st) {
                json = JSON.parse(data);
                for (i = 0; i < json.length; i++) {
                    var tr = $("<tr></tr>");
                    var td1 = $("<td></td>").html(json[i]["booknumber"]);
                    var td2 = $("<td></td>").html(json[i]["bookname"]);
                    var td3 = $("<td></td>").html(json[i]["author"]);
                    var td4 = $("<td></td>").html(json[i]["data"]);
                    var td5 = $("<td></td>").html(json[i]["appear"]);
                    var td6 = $("<td></td>").html(json[i]["bookshelf"]);
                    var td7 = $("<td></td>").html(json[i]["amount"]);
                    var td8 = $("<td></td>").html(json[i]["total"]);
                    tr.append(td1, td2, td3, td4, td5, td6, td7, td8);
                    tab.append(tr);
                }
            });
        }
        else {
            alert("请按规定输入信息");
        }
    });
    $(".heard ul li:nth-child(4)").click(function () {
        $(".right-3").show();
        $(".right-1,.right-2,.right-4").hide();
        var tab = $('.right-3 .shou-1 #Tab2 tbody');
        tab.empty();
        $.post("../Pipe", { bo: "book" }, function (data, st) {
            json = JSON.parse(data);
            for (i = 0; i < json.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").html(json[i]["booknumber"]);
                var td2 = $("<td></td>").html(json[i]["bookname"]);
                var td3 = $("<td></td>").html(json[i]["author"]);
                var td4 = $("<td></td>").html(json[i]["data"]);
                var td5 = $("<td></td>").html(json[i]["appear"]);
                var td6 = $("<td></td>").html(json[i]["bookshelf"]);
                var td7 = $("<td></td>").html(json[i]["amount"]);
                var td8 = $("<td></td>").html(json[i]["total"]);
                var inp=$("<input type='button' class='btn' value='出库'>");
                var td9 = $("<td></td>").append(inp);
                tr.append(td1, td2, td3, td4, td5, td6, td7, td8, td9);
                tab.append(tr);
            }
        });
    });

    $(".right-3 .sub-3 input").click(function () {
        var s1 = $("#bookname1");
        var s2 = $("#booknumber1");
        var s3 = $("#shuliang");
        var s4=$("#shujia");
        if (s1.val() != "" && s2.val() != "" && s3.val() != ""&&s4.val()) {
            var s = {
                bookname1: s1.val(),
                booknumber1: s2.val(),
                shuliang: s3.val(),
                shujia:s4.val()
            }
            s1.val("");s2.val("");s3.val("");s4.val("");
            var json = JSON.stringify(s);
            $.post("../Pipe", { bo: "chu", json: json }, function (data, st) {
                if (data == 'false') {
                    alert("图书出库失败");
                }
                else {
                    var tab = $('.right-3 .shou-1 #Tab2 tbody');
                    tab.empty();
                    json = JSON.parse(data);
                    for (i = 0; i < json.length; i++) {
                        var tr = $("<tr></tr>");
                        var td1 = $("<td></td>").html(json[i]["booknumber"]);
                        var td2 = $("<td></td>").html(json[i]["bookname"]);
                        var td3 = $("<td></td>").html(json[i]["author"]);
                        var td4 = $("<td></td>").html(json[i]["data"]);
                        var td5 = $("<td></td>").html(json[i]["appear"]);
                        var td6 = $("<td></td>").html(json[i]["bookshelf"]);
                        var td7 = $("<td></td>").html(json[i]["amount"]);
                        var td8 = $("<td></td>").html(json[i]["total"]);
                        var inp=$("<input type='button' class='btn' value='出库'>");
                        var td9 = $("<td></td>").append(inp);
                        tr.append(td1, td2, td3, td4, td5, td6, td7, td8, td9);
                        tab.append(tr);
                    }
                }
            });
        }
        else{
            alert("请按规定输入信息")
        }
    });
$(document).on('click','.btn',function(){
	var tr=$(this).parent().parent();
	var td=tr.children();
	show(td);
})
});

function show(td){
    var s={
        booknumber:td.eq(0).html(),
        bookname:td.eq(1).html(),
        author:td.eq(2).html(),
        data:td.eq(3).html(),
        appear:td.eq(4).html(),
        bookshelf:td.eq(5).html(),
        amount:td.eq(6).html(),
        total:td.eq(7).html()
    };
    var s1=JSON.stringify(s);
    $.post("../Pipe",{bo:"chu1",json:s1},function(data,st){
        var s2=JSON.parse(data);
        if(s2[0]=='true'){
            var tab = $('.right-3 .shou-1 #Tab2 tbody');
            tab.empty();
            json1 = JSON.parse(s2[1]);
            for (i = 0; i < json1.length; i++) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").html(json1[i]["booknumber"]);
                var td2 = $("<td></td>").html(json1[i]["bookname"]);
                var td3 = $("<td></td>").html(json1[i]["author"]);
                var td4 = $("<td></td>").html(json1[i]["data"]);
                var td5 = $("<td></td>").html(json1[i]["appear"]);
                var td6 = $("<td></td>").html(json1[i]["bookshelf"]);
                var td7 = $("<td></td>").html(json1[i]["amount"]);
                var td8 = $("<td></td>").html(json1[i]["total"]);
                var inp=$("<input type='button' class='btn' value='出库'>");
                var td9 = $("<td></td>").append(inp);
                tr.append(td1, td2, td3, td4, td5, td6, td7, td8, td9);
                tab.append(tr);
            }
            
        }
        else{
            alert("图书出库失败");
        }
    })
}