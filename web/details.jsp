<%@ page import="dao.ItemsDao" %>
<%@ page import="entity.Items" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: cjh
  Date: 2017-06-14
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath %>">
    <title>商品详情</title>
    <style>
        hr{

            border-color:FF7F00;
        }

        div{
            float:left;
            margin-left: 30px;
            margin-right:30px;
            margin-top: 5px;
            margin-bottom: 5px;

        }
        div dd{
            margin:0px;
            font-size:10pt;
        }
        div dd.dd_name
        {
            color:blue;
        }
        div dd.dd_city
        {
            color:#000;
        }
        div #cart
        {
            margin:0px auto;
            text-align:right;
        }
        span{
            padding:0 2px;border:1px #c0c0c0 solid;cursor:pointer;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>商品详情</h1>
<a href="index.jsp">首页</a> >> <a href="index.jsp"></a>
<hr>
            <%--程序思路：
            每一次将商品编号保存到字符串当中，字符串用","来分割，然后再保存到Cookie当中，
            每次从Cookie中去读取这个字符串，然后每次在它后面加上一个商品编号，再加一个逗号，
            再判断浏览的记录是否超过1000条，超过1000就把这个字符串清空，再把他保存在Cookie当中，
            在details.jsp中调用getViewList把字符串传到ItemsDao，
            在业务逻辑类，在将字符串用“，”分割到一个数组，再遍历循环这个数组，将最近五条记录放到一个集合中，返回这个集合
            再把这个集合在页面上显示就实现了商品浏览记录
            --%>
<center> <table width="750" height="60" cellpadding="0" border="0">
    <tr> <%--商品详情--%>
        <%
            ItemsDao itemsDao=new ItemsDao();
            Items item=itemsDao.getItemsById(Integer.parseInt(request.getParameter("id")));
            if (item!=null){
                System.out.println(item.getId());
        %>
        <td width="70%" valign="top">
            <table>
                <tr>
                    <td rowspan="4"><img src="images/<%=item.getPicture()%>" alt="no" width="200" height="160"></td>
                </tr>
                <tr>
                    <td><b><%=item.getName()%></b></td>
                </tr>
                <tr>
                    <td>产地:<%=item.getCity()%></td>
                </tr>
                <tr>
                    <td>价格:￥<%=item.getPrice()%></td>
                </tr>
                <tr>
                    <td>购买数量：<span>-</span><input type="text" id="number" name="number" value="1" size="2"><span>+</span></td>
                </tr>
            </table>
            <div id="cart">
                <img src="images/buy_now.png">
                <a href="javascript:selflog_show(<%=item.getId()%>)"><img src="images/in_cart.png"></a>
                <a href="servlet/CartServlet?action=show"><img src="images/view_cart.jpg"/></a>
            </div>
        </td>
        <%

                }
        %>

        <%
            String list="";
            //从客户端获得Cookie集合
            Cookie []cookies=request.getCookies();
            //遍历集合
            for (Cookie c:cookies){
                if (c.getName().equals("ListViewCookie")){
                    list=c.getValue();
                }
            }
            list=list+request.getParameter("id")+",";
            //如果浏览记录超过1000条，则清零
            String[]arr=list.split(",");
            if (arr!=null&&arr.length>0){
                if (arr.length>=1000){
                    list="";
                }
            }
            Cookie cookie=new Cookie("ListViewCookie",list);
            response.addCookie(cookie);
        %>
        <td width="30%" bgcolor="#EEE" align="center">
            <br>
            <b>您浏览过的商品</b>
            <br>
            <%--循环开始--%>
            <%
                ArrayList<Items> itemlist =itemsDao.getViewList(list);
                if (itemlist!=null&&itemlist.size()>0){
                    System.out.println("itemlist.size="+itemlist);
                    for (Items i:itemlist){
                        
                
            %>
            <div>
                <dl>
                    <dt>
                        <a href="details.jsp?id=<%=i.getId()%>">
                            <img src="images/<%=i.getPicture()%>" width="120" height="90" border="1">
                        </a>
                    </dt>
                </dl>
                <dd class="dd_name"><%=i.getName()%></dd>
                <dd class="dd_city">产地：<%=i.getCity()%>&nbsp;&nbsp;
                价格：<%=i.getPrice()%>￥
                </dd>
            </div>



<%
    }
    }
%>

        </td>
    </tr>
</table>
</center>
<script>
    function selflog_show(id) {
        var num=document.getElementById("number").value;
        J.dialog.get({id:'haoyue_creat',title:'购物成功',width:600,height:400,link:'<%=path%>/servlet/CartServlet?id='+id+'&num='+num+'&action=add',cover:true});
    }
</script>
<script src="js/lhgcore.js"></script>
<script src="js/lhgdialog.js"></script>
</body>
</html>
