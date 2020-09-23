<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>哈利路亚会员注册页面</title>
    <%--	静态包含头 base标签，css样式，jqery文件--%>
    <%@include file="/pages/common/header.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("#username").blur(function () {
                var username = this.value;
                $.ajax({
                    url: "http://localhost:8080/demo09_ssm/user/existUsernameAjax.do",
                    data: {
                        username:username
                    },
                    type: "post",
                    dataType: "text",
                    success: function (exist) {
                                $("span.errorMsg").text(exist)
                               /* if ("true" == exist){
                                    $("span.errorMsg").text("用户名已存在！")
                                }else {
                                    $("span.errorMsg").text("用户名可用")
                                }*/
                    }
                })
                /*$.getJSON(
                    "http://localhost:8080/d0 v5_book/userServlet",
                    "action=ajaxExistsUsername&username=" + username,
                    function (resultMap) {
                        if (resultMap.existsUsername) {
                            $("span.errorMsg").text("用户名已存在")
                        } else {
                            $("span.errorMsg").text("用户名可用")
                        }
                    }
                );*/
            });


            $("#sub_btn").click(function () {

                //5.验证验证码是否不为空或者空字符串。
                var codeText = $("#code").val();
                var codetrimText = $.trim(codeText);

                //5.1 if判断用户验证码格式是否正确。
                if (codetrimText == null || codetrimText == "") {
                    $("span.errorMsg").text("验证码为空或格式不正确");
                    return false;
                }

                /*//1.验证用户名： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
                var usernameText = $("#username").val();

                //1.1 正则表达判断
                var usernamePatt = /^\w{5,12}$/;

                //1.2 if判断用户输入是否和正则匹配
                //(usernamePatt.test(usernameText))== false
                if (!usernamePatt.test(usernameText)) {
                    $("span.errorMsg").text("用户账号输入格式不正确");
                    return false;
                }*/
                var usernameText = $("#username").val();
                if(usernameText == null || usernameText ==""){
                    $("span.errorMsg").text("用户名不能为空！");
                    return false
                }

                //2.验证密码： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
                var passwordText = $("#password").val();

                //2.1 正则表达判断
                var passwordPatt = /^\w{5,12}$/;

                //2.2 if判断用户输入是否和正则匹配
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("用户密码输入格式不正确");
                    return false;
                }

                //3.验证密码和确认密码是否一致：
                var repwd = $("#repwd").val();

                //3.1 if判断用户输入是否和正则匹配
                if (repwd != passwordText) {
                    $("span.errorMsg").text("两次密码输入不一致");
                    return false;
                }

                //4.验证邮箱
                var emailText = $("#email").val();

                //4.1 正则判断邮箱格式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

                //4.2 if判断用户输入是否和正则匹配
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("用户邮箱输入格式不正确");
                    return false;
                }

                //5.验证验证码是否不为空或者空字符串。
                var codeText = $("#code").val();
                var codetrimText = $.trim(codeText);

                //5.1 if判断用户验证码是否正确。
                if (codetrimText == null || codetrimText == "") {
                    $("span.errorMsg").text("验证码输入格式不正确");
                    return false;
                }
                $("span.errorMsg").text("");
            });


            // 给验证码的图片， 绑定单击事件
            $("#code_img").click(function () {
                // 在事件响应的 function 函数中有一个 this 对象。 这个 this 对象， 是当前正在响应事件的 dom 对象
                // src 属性表示验证码 img 标签的 图片路径。 它可读， 可写
                // alert(this.src);
                this.src = "${basePath}getVerifyCode.do?" + Math.floor(Math.random() * 100);
            });


        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <a href="index.jsp"><img class="logo_img" alt="" src="static/img/logo.gif" ></a>
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg == null ? "请输入相关信息" : requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="user/regist.do" method="post">
                        <input type="hidden" name="action" value="regist">

                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"
                        value="${requestScope.password}"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 120px;" id="code"/>
                        <img id="code_img" alt="" src="getVerifyCode.do"
                             style="float: right; margin-right: 40px;width: 100px;height: 32px"><br/>
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>

