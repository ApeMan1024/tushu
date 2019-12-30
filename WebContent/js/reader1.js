$(function () {
    $(".gui").click(function () {
        $(".right-2").show();
        $(".right-1").hide();
        $("#te2").val('');
        $("#te3").val('');
        $(".right-2 .heard1 form p:nth-child(2) input").click(function () {
            var te2 = $("#te2").val();
            var te3 = $("#te3").val();
            if (te2 != "" && te3 != "") {
                $.post("../Reader1", { bo: "jie", card: te2, name: te3 }, function (data, st) {
                
                    if (data == 'false') {
                        alert('输入的借书证信息有误或当前你没有借阅图书');
                    }
                    else {
                        var tab = $(".right-2 .heard2 table tbody");
                        tab.empty();
                        var json = JSON.parse(data);
                        for (i = 0; i < json.length; i++) {
                            var tr = $("<tr></tr>");
                            var td1 = $("<td></td>").html(json[i]["card"]);
                            var td2 = $("<td></td>").html(json[i]["booknumber"]);
                            var td3 = $("<td></td>").html(json[i]["bookname"]);
                            var td4 = $("<td></td>").html(json[i]["name"]);
                            var td5 = $("<td></td>").html(json[i]["date"]);
                            var inp = $('<input type="button" class="btn" value="归还">');
                            var td6 = $("<td></td>").append(inp);
                            tr.append(td1, td2, td3, td4, td5, td6);
                            tab.append(tr);
                        }
                    }
                })
            }
            else {
                alert("请按规定输入信息");
            }
        });

        $(document).on('click', '.btn', function () {
            var td = $(this).parent().siblings();
            var obj = {
                card: td.eq(0).html(),
                booknumber: td.eq(1).html(),
                bookname: td.eq(2).html(),
                name: td.eq(3).html(),
                date: td.eq(4).html()
            }
            var s = JSON.stringify(obj);
            $.post("../Reader1", { bo: 'gui', json1: s }, function (data, st) {
                if (data == 'false') {
                    alert("系统出现异常");
                }
                else {
                    alert('图书归还成功');
                    var tab = $(".right-2 .heard2 table tbody");
                    tab.empty();
                    var json = JSON.parse(data);
                    for (i = 0; i < json.length; i++) {
                        var tr = $("<tr></tr>");
                        var td1 = $("<td></td>").html(json[i]["card"]);
                        var td2 = $("<td></td>").html(json[i]["booknumber"]);
                        var td3 = $("<td></td>").html(json[i]["bookname"]);
                        var td4 = $("<td></td>").html(json[i]["name"]);
                        var td5 = $("<td></td>").html(json[i]["date"]);
                        var inp = $('<input type="button" class="btn" value="归还">')
                        var td6 = $("<td></td>").append(inp);
                        tr.append(td1, td2, td3, td4, td5, td6);
                        tab.append(tr);
                    }
                }

            });
        })
    })


});