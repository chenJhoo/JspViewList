<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: cjh
  Date: 2017-06-07
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录界面</title>
    <meta charset="utf-8">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  </head>
  <body>
  <h1>测试</h1>
  <%
    request.setCharacterEncoding("utf-8");
    String mid="";
    String password="";
    Cookie [] cookies =request.getCookies();
    if (cookies!=null&&cookies.length>0){
        for (Cookie c:cookies){
            if (c.getName().equals("mid")){
              mid= URLDecoder.decode(c.getValue(),"utf-8") ;
            }
            if (c.getName().equals("password")){
              password=URLDecoder.decode(c.getValue(),"utf-8");
            }
        }
    }
  %>
    <div class="container">
      <form id="loginForm" action="check.jsp" method="post" class="form-horizontal">
        <fieldset>
          <legend><label><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</label></legend>
        </fieldset>
        <div class="form-group" id="midDiv">
          <label class="col-md-3 control-label" for="mid" >用户名</label>
          <div class="col-md-5">
            <input type="text" class="form-control" id="mid" name="mid" placeholder="请输入用户名" value="<%=mid%>">
          </div>
          <div class="col-md-4" id="midspan"></div>
        </div>
        <div class="form-group" id="passwordDiv">
          <label class="col-md-3 control-label" for="password">密码</label>
          <div class="col-md-5">
            <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" value="<%=password%>">
          </div>
          <div class="col-md-4" id="passwordspan"></div>
        </div>
        <div class="col-md-6 col-md-offset-3"><input type="checkbox" name="isUseCookie" checked="checked">记住登录状态10天</div>
        <div class="form-group" id="btnDiv">
          <div class="col-md-5 col-md-offset-6"><button type="submit" id="submitBtn" class="btn btn-success">登录</button>
          <button type="reset" id="resetBtn"   class="btn btn-warning">重置</button></div>
        </div>
      </form>
    </div>
  </body>
</html>










