$(function () {
    var arr = ["../images/tushu-1.jpg",
        "../images/tushu-2.jpg",
        "../images/tushu-3.jpg", "../images/tushu-4.jpg", "../images/tushu-5.jpg"];

    $(".cha").click(function () {
        $(".right-1").show();
        $(".right-2").hide();
        $(".right-1 .heard2 ul").hide();
        $(".right-1 .heard2 #Tab1").show();
        $('#te1').val('');
        var tab = $(".right-1 .heard2 #Tab1 tbody");
        tab.empty();
        $.post("../Reader", { bo: "book" }, function (data, st) {
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




        $(".right-1 .heard1 form input[type='submit']").click(function () {
            var tr = $(".right-1 .heard2 #Tab1 tbody").children();
            var tr1 = document.getElementById("Tab1").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            var te1 = $('#te1');
            var s1 = te1.val();
            var arr1 = []
            if (s1 != "") {
                for (i = 0; i < tr1.length; i++) {
                    var td = tr.eq(i).children();
                    for (j = 0; j < 8; j++) {
                        if (td.eq(j).html().search(s1) != -1) {
                            arr1.push(tr.eq(i));
                            break;
                        }
                    }
                }

                if (arr1.length != 0) {
                    $(".right-1 .heard2 #Tab1").hide();
                    $(".right-1 .heard2 ul").show();
                    $(".right-1 .heard2 ul li").show();
                    if (arr1.length < 6) {

                        for (i = arr1.length; i < 6; i++)$(".right-1 .heard2 ul li").eq(i).hide();
                        for (i = 0; i < arr1.length; i++) {
                            var book = arr1[i].children();
                            var box = $(".right-1 .heard2 ul li:nth-child(" + (i + 1) + ")" + " .li-right");

                            for (j = 0; j < 8; j++) {
                                box.find('.name' + (j + 1)).html(book.eq(j).html());
                            }
                        }
                    }
                    else {
                        for (i = 1; i <= arr1.length - 6; i++)$(".right-1 .heard2 ul li:nth-child(1)").clone().appendTo(".right-1 .heard2 ul");
                        index = 1;
                        for (i = 0; i < arr1.length; i++) {
                            var book = arr1[i].children();

                            var box = $(".right-1 .heard2 ul li:nth-child(" + (i + 1) + ")" + " .li-right");
                            if (i >= 6) {
                                $(".right-1 .heard2 ul li:nth-child(" + (i + 1) + ")" + " .li-left").css({
                                    'background': 'url(' + arr[index++] + ') no-repeat scroll',
                                    'backgroundSize': "100% 100%"
                                });
                                if (index >= arr.length) index = 0;
                            }
                            for (j = 0; j < 8; j++) {
                                box.find('.name' + (j + 1)).html(book.eq(j).html());
                            }
                        }
                    }
                }
                else {
                    alert("图书不存在");
                }
            }
            else {
                alert("请按规定输入数据");
            }

        });

        $(document).on('click', '.right-1 .heard2 ul li .li-right p:nth-child(3)', function () {
            var num = parseInt($(this).prev().children(".name7").html());
            var span = $(this).parent().find("span");
            if (num > 1) {
                var p = prompt('输入你的借书证号', '');
                if (p != '') {
                    $.post('../Reader', { bo: "num", numbre: p }, function (data, st) {
                        if (data == 'true') {
                            var s = {
                                booknumber: span.eq(0).html(),
                                bookname: span.eq(1).html(),
                                author: span.eq(2).html(),
                                data: span.eq(3).html(),
                                appear: span.eq(4).html(),
                                bookshelf: span.eq(5).html(),
                                amount: span.eq(6).html(),
                                total: span.eq(7).html()
                            };

                            var str = JSON.stringify(s);
                            $.post("../Reader", { bo: "re", json2: str, k: p }, function (data1, st1) {
                                var json = JSON.parse(data1);
                                var k = json[0];
                                if (k == 'true') {
                                    alert("借阅成功");
                                    var json1 = JSON.parse(json[1]);
                                    var tab = $(".right-1 .heard2 #Tab1 tbody");
                                    tab.empty();
                                    $(".right-1 .heard2 #Tab1").show();
                                    $(".right-1 .heard2 ul").hide();
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
                                        tr.append(td1, td2, td3, td4, td5, td6, td7, td8);
                                        tab.append(tr);
                                    }
                                }
                                else {
                                    alert("有超期图书没有归还或借阅图书数量上限");
                                }
                            })
                        }
                        else {
                            alert('请输入正确的借书证信息');
                        }
                    })
                }
                else {
                    alert('请按规定输入信息');
                }

            }
            else {
                alert('不允许借出');
            }
        })
    })



})