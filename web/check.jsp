<%@ page import="com.mongodb.MongoClient" %>
<%@ page import="com.mongodb.client.MongoDatabase" %>
<%@ page import="com.mongodb.client.MongoCollection" %>
<%@ page import="com.mongodb.client.MongoCursor" %>
<%@ page import="org.bson.Document" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: cjh
  Date: 2017-06-07
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查登录是否正确</title>
</head>
<body>
<%
    String mid=request.getParameter("mid");
    String password=request.getParameter("password");
%>
<%!
    MongoClient mongoClient=new MongoClient("localhost",27017);
    MongoDatabase mongoDatabase=mongoClient.getDatabase("jsptest");
    MongoCollection collection=mongoDatabase.getCollection("usecol");
    MongoCursor<Document> cursor=collection.find().iterator();
    String mid_db="";
    String psw_db="";
    Boolean flag=false;
%>
<%
    while (cursor.hasNext()){
        Document document=cursor.next();
        mid_db=document.getString("mid");
        psw_db=document.getString("password");
        System.out.println("mid_db:::::"+mid_db);
        System.out.println("psw_db:::::"+psw_db);
        if (mid.equals(mid_db)&&password.equals(psw_db)){
%>
<h1>用户登录成功，WELCOME&nbsp;&nbsp;&nbsp;<%=mid%></h1>
<% return;}else{
            if (cursor.hasNext()){
                break;
            }else {
%>
<h1>用户登录失败，请返回 <a href="login.jsp">登录</a></h1>
<%
            }
        }}
%>
<%--<% return;}else{ %>--%>
<%--<h1>用户登录失败，请返回 <a href="login.jsp">登录</a></h1>--%>
<%--<%--%>
        <%--}--%>
        <%--System.out.println("--------------------");--%>
    <%--}--%>
<%--%>--%>

            <%--flag=true;--%>
            <%--return;--%>
        <%--}else {--%>
            <%--flag=false;--%>
        <%--}--%>
    <%--}--%>

<%--%>--%>
<%--<%--%>
    <%--System.out.println(flag);--%>
    <%--if (flag){--%>
<%--%>--%>
<%--<h1>用户登录成功，WELCOME<%=mid%></h1>--%>
<%--<%--%>
    <%--}else {--%>

<%--%>--%>
<%--<h1>用户登录失败，请返回 <a href="login.jsp">登录</a></h1>--%>
<%--<%--%>
    <%--}--%>

<%--%>--%>

</body>
</html>
