<%--

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : login
    Created on : Jan 16, 2015, 3:40:17 PM
    Author     : huynhthao

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="bootstrap-3.3.2-dist/css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                margin: 0;
            }
            header {
                width: auto;
                height: 60px;
                background-color: #a73e2d;
                cursor: default;
                margin: auto;
            }
            .container {
                max-width: 1000px;
                margin: auto;
            }
            .logo {
                background-size: 50%;
                background-position: center;
                background-repeat: no-repeat;
                display: inline-block;
                width: 200px;
                height: 60px;
                vertical-align: middle;
            }
            ul.navmenu {
                display: inline-block;
                list-style-type: none;
                margin: 0;
                padding: 0;
                vertical-align: middle;
            }
            ul.navmenu li.active {
                background-color: rgba(0,0,0,0.3);
            }
            ul.navmenu li {
                display: inline-block;
                color: white;
                font-size: 13px;
                padding: 7px 13px;
                border-radius: 5px;
                text-shadow: 0 3px 5px rgba(0, 0, 0, 0.6);
            }
            ul.navmenu li a {
                color: white;
                text-decoration: none;
            }
            .unlogin {
                float: right;
                height: 50px;
                padding: 0;
                margin-top: 5px;
                vertical-align: middle;
            }
            .login {
                float: right;
                height: 50px;
                padding: 0;
                vertical-align: middle;
            }
            .search {
                width: 150px;
                height: 5px;
                padding: 0;
                vertical-align: middle;
            }
        </style>
        <title>Login Page</title>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="logo"><img src="img/logo.png" width="170px" height="50px" alt="Trung"/></div>
                <ul class="navmenu">
                    <li class="active">
                        <a href="#">Problem</a>
                    </li>
                    <li>
                        <a href="#">Article</a>
                    </li>
                    <li>
                        <a href="#">Description</a>
                    </li>
                </ul>
               
                <c:if test="${sessionScope.ACCOUNT_SESSION == null}">
                    <div class="unlogin">
                        <form action="DispatcherServlet" method="POST">
                            <input type="text" name="txtUsername" value="" placeholder="User Name">
                            <input type="password" name="txtPassword" value="" placeholder="Your Password">
                            <input type="submit" value="Log in" name="action" />
                            <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                        </form>
                        <a href="DispatcherServlet?action=register.jsp" style="float: right; color: #DDDDDD">Register New Account</a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.ACCOUNT_SESSION != null}">
                    <div class="login">
                        <h3><font color="white">Welcome, ${sessionScope.ACCOUNT_SESSION.username}</font></h3>
                    </div>
                </c:if>
            </div>
        </header>
        <!--<a href="DispatcherServlet?action=detailquestion&questionid=1">${sessionScope.ACCOUNT_SESSION.username}</a>-->
        <!--        <a href="DispatcherServlet?action=TagList">Tag List</a>
                <a href="DispatcherServlet?action=Mainpage">Main Page</a>-->
        <!--<a href="DispatcherServlet?action=AccountList">Account Management</a>-->
    </body>
</html>

--%>


<%-- 
    Document   : login
    Created on : Jan 10, 2015, 6:26:51 PM
    Author     : VyTKSE60964
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Login</title>
        <style>
            html, body
            {
                height: 100%;
            }

            body
            {
                font: 12px 'Lucida Sans Unicode', 'Trebuchet MS', Arial, Helvetica;    
                margin: 0;
                background-color: #a73e2d;
            }

            /*--------------------*/

            #login
            {
                background-color: #fff;
                background-image: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#eee));
                background-image: -webkit-linear-gradient(top, #fff, #eee);
                background-image: -moz-linear-gradient(top, #fff, #eee);
                background-image: -ms-linear-gradient(top, #fff, #eee);
                background-image: -o-linear-gradient(top, #fff, #eee);
                background-image: linear-gradient(top, #fff, #eee);  
                height: 240px;
                width: 400px;
                margin: -150px 0 0 -230px;
                padding: 30px;
                position: absolute;
                top: 50%;
                left: 50%;
                z-index: 0;
                -moz-border-radius: 3px;
                -webkit-border-radius: 3px;
                border-radius: 3px;  
                -webkit-box-shadow:
                    0 0 2px rgba(0, 0, 0, 0.2),
                    0 1px 1px rgba(0, 0, 0, .2),
                    0 3px 0 #fff,
                    0 4px 0 rgba(0, 0, 0, .2),
                    0 6px 0 #fff,  
                    0 7px 0 rgba(0, 0, 0, .2);
                -moz-box-shadow:
                    0 0 2px rgba(0, 0, 0, 0.2),  
                    1px 1px   0 rgba(0,   0,   0,   .1),
                    3px 3px   0 rgba(255, 255, 255, 1),
                    4px 4px   0 rgba(0,   0,   0,   .1),
                    6px 6px   0 rgba(255, 255, 255, 1),  
                    7px 7px   0 rgba(0,   0,   0,   .1);
                box-shadow:
                    0 0 2px rgba(0, 0, 0, 0.2),  
                    0 1px 1px rgba(0, 0, 0, .2),
                    0 3px 0 #fff,
                    0 4px 0 rgba(0, 0, 0, .2),
                    0 6px 0 #fff,  
                    0 7px 0 rgba(0, 0, 0, .2);
            }

            #login:before
            {
                content: '';
                position: absolute;
                z-index: -1;
                border: 1px dashed #ccc;
                top: 5px;
                bottom: 5px;
                left: 5px;
                right: 5px;
                -moz-box-shadow: 0 0 0 1px #fff;
                -webkit-box-shadow: 0 0 0 1px #fff;
                box-shadow: 0 0 0 1px #fff;
            }

            /*--------------------*/

            h1
            {
                text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
                text-transform: uppercase;
                text-align: center;
                color: #666;
                margin: 0 0 30px 0;
                letter-spacing: 4px;
                font: normal 26px/1 Verdana, Helvetica;
                position: relative;
            }

            h1:after, h1:before
            {
                background-color: #777;
                content: "";
                height: 1px;
                position: absolute;
                top: 15px;
                width: 120px;   
            }

            h1:after
            { 
                background-image: -webkit-gradient(linear, left top, right top, from(#777), to(#fff));
                background-image: -webkit-linear-gradient(left, #777, #fff);
                background-image: -moz-linear-gradient(left, #777, #fff);
                background-image: -ms-linear-gradient(left, #777, #fff);
                background-image: -o-linear-gradient(left, #777, #fff);
                background-image: linear-gradient(left, #777, #fff);      
                right: 0;
            }

            h1:before
            {
                background-image: -webkit-gradient(linear, right top, left top, from(#777), to(#fff));
                background-image: -webkit-linear-gradient(right, #777, #fff);
                background-image: -moz-linear-gradient(right, #777, #fff);
                background-image: -ms-linear-gradient(right, #777, #fff);
                background-image: -o-linear-gradient(right, #777, #fff);
                background-image: linear-gradient(right, #777, #fff);
                left: 0;
            }

            /*--------------------*/

            fieldset
            {
                border: 0;
                padding: 0;
                margin: 0;
            }

            /*--------------------*/

            #inputs input
            {
                background: #f1f1f1 url('images/login_icon.png') no-repeat;
                padding: 15px 15px 15px 30px;
                margin: 0 0 10px 0;
                width: 353px; /* 353 + 2 + 45 = 400 */
                border: 1px solid #ccc;
                -moz-border-radius: 5px;
                -webkit-border-radius: 5px;
                border-radius: 5px;
                -moz-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
                -webkit-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
                box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
            }

            #username
            {
                background-position: 5px -2px !important;
            }

            #password
            {
                background-position: 5px -52px !important;
            }

            #inputs input:focus
            {
                background-color: #fff;
                border-color: #e8c291;
                outline: none;
                -moz-box-shadow: 0 0 0 1px #e8c291 inset;
                -webkit-box-shadow: 0 0 0 1px #e8c291 inset;
                box-shadow: 0 0 0 1px #e8c291 inset;
            }

            /*--------------------*/
            #actions
            {
                margin: 25px 0 0 0;
            }

            #submit
            {		
                background-color: #ffb94b;
                background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f), to(#ffb94b));
                background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
                background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
                background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
                background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
                background-image: linear-gradient(top, #fddb6f, #ffb94b);

                -moz-border-radius: 3px;
                -webkit-border-radius: 3px;
                border-radius: 3px;

                text-shadow: 0 1px 0 rgba(255,255,255,0.5);

                -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;
                -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;
                box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;    

                border-width: 1px;
                border-style: solid;
                border-color: #d69e31 #e3a037 #d5982d #e3a037;

                float: left;
                height: 35px;
                padding: 0;
                width: 120px;
                cursor: pointer;
                font: bold 15px Arial, Helvetica;
                color: #8f5a0a;
            }

            #submit:hover,#submit:focus
            {		
                background-color: #fddb6f;
                background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b), to(#fddb6f));
                background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
                background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
                background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
                background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
                background-image: linear-gradient(top, #ffb94b, #fddb6f);
            }	

            #submit:active
            {		
                outline: none;

                -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
                -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
                box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;		
            }

            #submit::-moz-focus-inner
            {
                border: none;
            }

            #actions a
            {
                color: #3151A2;    
                float: right;
                line-height: 35px;
                margin-left: 10px;
            }

            /*--------------------*/

            #back
            {
                display: block;
                text-align: center;
                position: relative;
                top: 60px;
                color: #999;
            }


        </style>
    </head>

    <body>

        <form id="login" action="DispatcherServlet" method="post">
            <h1>Log In</h1>
            <p style="color : red">${requestScope.message}</p>
            <fieldset id="inputs">
                <input id="username" type="text" name="txtUsername" placeholder="Username" autofocus required>   
                <input id="password" type="password" name="txtPassword" placeholder="Password" required>
            </fieldset>
            <fieldset id="actions">
                <input type="submit" id="submit" name="action" value="Log in">
            </fieldset>
        </form>
    </body>
</html>

