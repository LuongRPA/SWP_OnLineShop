<%-- 
    Document   : contact
    Created on : Aug 18, 2019, 8:46:59 PM
    Author     : sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <title>LapTopGaming</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            body {
                margin-top: 20px;padding: 0;
                background: #FFFFFF;
                text-align: justify;
                font-family: "Helvetica Neue", "Lucida Grande", "Segoe UI", Arial, Helvetica, Verdana, sans-serif;
                font-size:  0.875em;
                color: #616161;	/*xám*/
            }

            h1 {
                padding-top: 10px;
                padding-bottom: 50px;
                padding-left: 50px;
                color: #000000;
                font-weight: normal;
                font-size: 2em;
            }

            p {
                font-size: 14px; 
                font-weight: bold;
            }

            /* Menu */

            #menuContaniner {
                width: 500px;
                float: right;
                margin: -60px 0 0 0;
                background-color:#FFFFFF;/* trắng */
            }
            ul.main_nav {
                margin: 0 auto;
                margin-left: -150px;	
                padding: 0;
                list-style: none;
                height: 50px;
            }

            ul.main_nav li {
                border-right: 1px solid #BCBCBC;
                float: left;
                position: relative;
                text-align: center;
                margin: 0;padding: 0;
                display: inline;
                height: 45px;
                padding-top: 5px;
            }
            ul.main_nav li a {
                color: #000000;
                width: 100px;
                padding: 10px;
                text-decoration: none;
                display: block;
            }

            /* Content */

            #mainContainer {
                width: 1200px;
                height: 100%;
                margin: 40px auto;
                padding: 20px 5px;
                background: #FFFACD;
                boder:10px solid black;
            }
            /* about */
            div.about {
                boder: 10px solid pink;
                text-align: center;
            }

            /* CONTACT FORM*/
            input[type=text], select, textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                margin-top: 6px;
                margin-bottom: 16px;
                resize: vertical;
            }

            input[type=submit] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            .container {
                border-radius: 0px;
                background-color: #f2f2f2;
                padding: 20px;
                width:500px;
                margin-left:350px;
                margin-top: 50px;
                text-align:left;
                /*                position: absolute;*/
            }

        </style>

        <%
            HttpSession s = request.getSession(true);
            String us = (String) s.getAttribute("username");
            String admin = (String) s.getAttribute("admin");
        %>
    </head>

    <body>
        <!-- START HEADER -->
        <div id="header" style="width: 1000px; height: 100px; margin: 0 auto; background-color:#FFFFFF; border: none solid #8C0209;">
            <div>
                <h1 style="float:left; margin-left:15px; font-size:40px;">
                    <span style=""><a href="home" style="text-decoration: none;color:#DAA520">LapTop</a> </span><span style=""><a href="home" style="text-decoration: none;color:#000000">Gaming</a></span>
                </h1>
                <p style="float:left;margin-top: 70px;font-weight: 900px;margin-left: 5px;word-spacing: 2px;color:black;font-size:15px;">
                   LapTopGaming Store
                </p>
            </div>
        </div>

        <!-- END HEADER -->

        <!-- START MENU -->
        <section id="menuContaniner">
            <ul class="main_nav">

                <% if (us != null) {%>
                <li ><a  href="about" >About</a></li>
                <li ><a  href="shop" style="color:green;">Shop</a></li>

                <% if (admin != null) {%>
                <li ><a  href="manager">Manager account</a></li>
                <li ><a  href="manageritem">Manager Product</a></li>
                    <%} else {%>
                <li ><a  href="cart">My Order</a></li><%}%>
                <li ><a  href="logout">Logout</a></li>
                    <%} else {%>
                <li ><a  href="about">About </a></li>
                <li ><a  href="shop" style="color:green;">Shop</a></li>
                <li ><a  href="login">Login</a></li>
                <li ><a  href="register">Register</a></li>
                <li ><a  href="contact">Contact</a></li>
                    <%}%>
            </ul>
        </section>	
        <!-- END MENU -->

        <!-- START CONTENT PAGE -->

        <section id="mainContainer">
            <div class="about" >
                <h2 style="font-size:45px;font-family:Time New Roman">CONTACT US</h2>
                <div style="margin-top:-20px;font-size:30px;color:#556B2F;">+84 34 230 462   |    Longchhe140535@fpt.edu.vn  </div>
                <div style="margin-top:20px;font-family:Candara;padding-left:210px;width:800px;font-size:20px;color:#556B2F;line-height:2em;">
                    Pick up is available at Hoa Noi, FPT University Hoa Lac<br> 
                    Ha Noi, Viet Nam
                </div>  


                <div class="container">
                    <form action="contact" method="post">
                        <label>Your Name</label>
                        <input type="text"name="name" required placeholder="Your name..">

                        <label>Email</label>
                        <input type="text" name="email" required placeholder="Your email..">

                        <label>Phone</label>
                        <input type="text" name="phone" required placeholder="Your phone..">

                        <label>Content</label>
                        <textarea  name="contents" required placeholder="Write something.." style="height:200px"></textarea>

                        <input type="submit" value="Send">
                    </form>

                    <%
                        if (request.getAttribute("msg") != null) {
                    %>
                    <h2 style="color: red"><%= request.getAttribute("msg")%></h2>
                    <%
                        }
                    %>
                </div>

        </section>
        <!-- end page -->

        <!-- start Footer-->
        <div id="footer" style="height: 250px; margin: 0 auto;padding: 0 20px;
             background-color: #D3D3D3; border: none solid #8C0209;">
       <p style="float:left; margin:70px; font-size:60px;"> <span style="color:#DAA520">LapTop</span><span style="color:#000000">Gaming</span>
                </p>
                  <p style="float:left;margin-top:170px;margin-left:-350px;">
                <a href="https://www.instagram.com/clongcena11/"><img src="src/ins.png" style="width:65px; height:65px;"></img>
                    <a href="https://www.facebook.com/long.chu.71653"><img src="src/fb.png" style="width:65px; height:65px;"></img>

                        </p>


                        <p style="float:right;margin:40px;text-align:left;font-size:15px;margin-right:190px;">
                            <b>Our Story</b><br /><br />
                            <span style="color:#556B2F;">LapTopGaming not only brings customers the latest and highest quality genuine products, but <br/>
                                also a place for customers to experience products comfortably under the advice of trained staff and technicians. repertoires. . The difference of LapTopGaming is also separate after-sales policies such as Gold Warranty: 
                                <br />  Warranty for both drops, water damage, 1-for-1 policy within 30 days <br/> 
                             
                            </span>
                            </div>
                            <!-- end page -->
                            </body>
                            </html>



