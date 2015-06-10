<%-- 
    Document   : register
    Created on : Jan 17, 2015, 4:15:17 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
                height: 300px;
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
            
            #email
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
        <script language="javascript">
            function checkvalidate(form) {
                var email = document.getElementById("email");
                var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                var username = document.getElementById("username").value.length;
                var password = document.getElementById("password").value.length;
                if (username > 16 || username < 6) {
                    alert("Username must be less than 16 character and more than 6 character!");
                    document.getElementById("username").focus();
                    return false;
                }
                if (password > 16 || password < 6) {
                    alert("Password must be less than 16 character and more than 6 character!");
                    document.getElementById("password").focus();
                    return false;
                }
                if (!filter.test(email.value)) {
                    alert("Please provide a valid email address");
                    email.focus();
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <form id="login" action="DispatcherServlet" method="POST" >
            <h1>Register</h1>
            <fieldset id="inputs">
                Username: <input type="text" id="username" name="txtUsername" value="" />
                <h3><font color="red">${requestScope.ERROR_MESS}</font></h3><br/>
                Password: <input type="password" id="password" name="txtPassword" value=""/><br/>
                Email: <input type="text" id="email" name="txtEmail" value="" placeholder="abc@example.com"/><br/>
            </fieldset>
            <fieldset id="actions">
                <input id="submit" type="submit" value="Register" name="action" onclick='return checkvalidate(this.form)' />
            </fieldset>
        </form>
    </body>
</html>
