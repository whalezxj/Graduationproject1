$(function () {
    toSomePage(1);
});

//每次调用此函数都发送一次请求，变量pn是默认开始页面
function toSomePage(pn) {
    $.ajax({
        url:"/department/querydept", type: "post",
        data: "pn=" + pn,
        success: function (result) {
            build_user_table(result);//构建用户表格
            build_pagination_info(result);//构建分页信息
            build_pagination_nav(result);//构建分页导航
            dat();//根据id激活
            dde();//根据id删除
        }
    })
};
var array = new Array();
function build_user_table(result) {
    //先清空表格，不然会直接回追加到上次查询结果上
    $("dept").empty();
    //获取服务器返回的json数据
    var departments = result.info.pageInfo.list;
    dept = "";
    //$.each()是jquery的遍历方法
    $.each(departments, function (index, item) {
        array.push(item.departmentId);
        dept += "<tr><td id='deptId'>" + item.departmentId + "</td>";//
        dept += "<td id='deptName'>" + item.departmentName + "</td>";//
        dept += "<td id='deptAdmin'>" + item.departmentAdmin + "</td>";//
        dept += "<td id='deptStatus'>" + item.departmentStatus + "</td>";//
        dept += "<td>"  + '<button type="button" id="pbtn"><i class="fa fa-pencil"></i></button>\n' +
            '<button type="button"  id="btn"   role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></button>' + "</td></tr>";
    });
    $("#dept").html(dept);
}


function toSomePage1() {
    var value = document.getElementById("input1").value;
    var pnu = 1;
    $.ajax({
        url: "/department/select", type: "post",
        data: {"pn": pnu, "deptName": value},
        success: function (result) {
            build_user_table(result);//构建用户表格
            build_pagination_info(result);//构建分页信息
            build_pagination_nav(result);//构建分页导航
            xh()
        }
    })
};


/**
 创建分页信息
 */
function build_pagination_info(result) {
    $("#dataTableExample_info").empty();
    //得到服务器返回的jason数据里的分页信息，然后拼接
    var pageNum = result.info.pageInfo.pageNum;
    var pageSize = result.info.pageInfo.pages;
    var total = result.info.pageInfo.total;
    $("#dataTableExample_info").append("当前" + pageNum + "页,共" + pageSize + "页,总" + total + "条记录")
}

/**
 创建分页导航条：
 根据bootstrap文档里的分页说明，使用jquery创建元素。
 */

function build_pagination_nav(result) {
    $("#dataTableExample_paginate").empty();
    //首页
    var fristPageLi = $("<li></li>").append($("<a></a>").attr("href", "javacript:void(0);").append("首页"));
    //前一页
    var prePageLi = $("<li></li>").append($("<a></a>").attr("href", "javacript:void(0);").append($("<span></span>").append("&laquo;")));
    //如果没有前一页，就让按钮不能被点击,否则绑定点击事件，重新发送ajax请求，访问相应页面
    if (result.info.pageInfo.hasPreviousPage == false) {
        fristPageLi.addClass("disable");
        prePageLi.addClass("disable");
    } else {
        fristPageLi.click(function () {
            toSomePage(1);
        });
        prePageLi.click(function () {
            toSomePage(result.info.pageInfo.pageNum - 1);
        });
    }
    var ul = $("<ul class=\"pagination\" style=\"float: right\"></ul>").addClass("pagination").append(fristPageLi).append(prePageLi);
    $.each(result.info.pageInfo.navigatepageNums, function (index, element) {

        var numLi = $("<li></li>").append($("<a></a>").attr("href", "javacript:void(0);").append(element));

        //如果遍历的页码等于当前页面，就高亮显示，然后添加点击事件，如果有点击，就重新发送ajax请求，访问当前页面（pn=element）
        if (result.info.pageInfo.pageNum == element) {
            numLi.addClass("active");
        }

        numLi.click(function () {
            toSomePage(element);
        })
        ul.append(numLi);
    })

    //下一页
    var nextPageLi = $("<li></li>").append($("<a></a>").attr("href", "javacript:void(0);").append($("<span></span>").append("&raquo;")));
    //末页
    var lastPageLi = $("<li></li>").append($("<a></a>").attr("href", "javacript:void(0);").append("末页"));
    //如果没有后一页，就让按钮不能被点击,否则绑定点击事件，重新发送ajax请求，访问相应页面
    if (result.info.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disable");
        lastPageLi.addClass("disable");
    } else {
        nextPageLi.click(function () {
            toSomePage(result.info.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            toSomePage(result.info.pageInfo.pages);
        });
    }
    ul.append(nextPageLi).append(lastPageLi);
    $("<nav></nav>").append(ul).appendTo("#dataTableExample_paginate");
}

/*根据ID删除
* 思路：
* 在上面遍历的时候将所有的 ID 存进Array里面
* 获取所有button
* 排除掉当前页面的其他button 下标0开始
* 遍历每个 button 绑定事件
* */
function dde() {
    var btns = document.getElementsByTagName("button");//shangchu
    for (let i = 6; i < btns.length; i += 2) {
        btns[i].onclick = function () {
            //alert(((i - 3) / 2) - 1.5);
            let id = array[((i-3)/2)-1.5];
            $.ajax({
                url: "/department/delete",
                type: "post",
                data: "id=" + id,
                success: function (result) {
                    alert(result.info)
                    window.location.reload();
                }
            })
        }
    }
}
function dat() {
    var btns = document.getElementsByTagName("button");//shangchu
    for (let i = 5; i < btns.length; i += 2) {
        btns[i].onclick = function () {
            //alert(((i - 4) / 2) - 0.5);
            let id = array[((i-4)/2)-0.5];
            $.ajax({
                url: "/department/activite",
                type: "post",
                data: "id=" + id,
                success: function (result) {
                    alert(result.info)
                    window.location.reload();
                }
            })
        }
    }
}

function adddept() {
    var str = {"departmentName":$('#deptname').val(),"departmentAdmin":$('#deptadmin').val()};
    if ($('#deptname').val().length==""||$('#deptadmin').val().length==""){
        alert("信息不能为空")
    }
    $.ajax({
        url: "/department/addddept",
        type: "post",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(str),
        success: function (data) {
            if (data.info == "添加成功") {
                alert("添加成功");
                window.setTimeout(1000);
                location.reload();
            } else
                alert("添加失败")
        }
    });
}

