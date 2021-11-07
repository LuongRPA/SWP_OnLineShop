<%@page import="models.Cart"%>
<%@page import="java.util.List"%>
`x<%-- 
    Document   : shop
    Created on : November 15, 2020, 3:25:14 PM
    Author     : sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LapTopGaming</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">       
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">    
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js">
        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
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
                height:100%;
                margin: 60px auto;
                padding: 20px 5px;
                background: #f1f1f1;
                boder:10px solid black;
            }

            div.about {
                boder: 10px solid pink;
                text-align: center;
            }
            /* shop */
            .product {
                /*              position: absolute;*/
                top: 50%;
                left: 50%;
                transform: translate(0,0);
                width: 240px;
                height: 300px;
                background: #fff;
                box-shadow: 0 5px 15px rgba(0,0,0,.25);
                border-radius: 5px;
                overflow: hidden;
                margin: 35px;
            }
            .product .imgbox {
                height: 100%;
                box-sizing: border-box;
            }
            .product .imgbox img {
                display: block;
                width: 99%;
                margin: 20px auto 0;
            }
            .details {
                position: absolute;
                width: 100%;
                bottom: -50px;
                background: #fff; 
                padding: 10px;
                box-sizing: border-box;
                box-shadow: 0 0 0 rgba(0,0,0,0);
                transition: .5s;
            }/*
            */            .product:hover .details {
                bottom: 0;
            }
            .details h2 {
                margin: 0;
                padding: 0;
                font-size: 16px;
                width: 100%;
            }
            .details h2 span {
                font-size: 13px;
                color: #ccc;
                font-weight: normal;
            }
            .details .price {
                /*                position: absolute;*/
                top: 10px;
                right: 10px;
                font-weight: bold;
                color: #ff4faf;
                font-size: 20px;  
            }/*
            */          .details a{
                display: block;
                padding: 5px;
                color: #fff;
                margin: 15px 0 0;
                background: #ff4faf;
                text-align: center;
                text-decoration: none;
                transition: .3s;
                cursor: pointer;
            }
            .details a:hover {
                background: #ff209a; 
            }

            .products ul,.products ul li {
                list-style: none;
                display: inline-block;
            }
        </style>

        <%
            HttpSession s = request.getSession(true);
            String us = (String) s.getAttribute("username");
            String admin = (String) s.getAttribute("admin");

            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart_list");

            if (cart_list != null) {
                request.setAttribute("cart_list", cart_list);
            }
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
                <li ><a  href="userinfo">Account</a></li>

                <% if (admin != null) {%>
                <li ><a  href="ListPostServlet">Manager Post</a></li>
                <li ><a  href="OrderList">Manager Order</a></li>
                <li ><a  href="manager">Manager account</a></li>
                <li ><a  href="manageritem" style="color:green;">Manager Product</a></li>
                <li ><a  href="FeedbackListServlet">Manager Feedback</a></li>
                    <%} else {%>
                <li ><a  href="ListPostServlet">Blog</a></li>
                <li ><a  href="mycart">Cart<span class="badge badge-danger">${cart_list.size()}</span></a></li>
                <li ><a  href="shop">Shop</a></li>
                <li ><a  href="myorder">My Order</a></li><%}%>
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
                <h2 style="font-size:50px;font-family:Time New Roman">MANAGE PRODUCT</h2>
                <table style="width: 50%;height: 100%;" border="1px solid black" align="center">
                    <tr>
                        <td style="font-size:20px;"> <b>Product Id</b></td>
                        <td style="font-size:20px;"> <b>Product Name</b></td>
                        <td style="font-size:20px;"> <b>Product Price</b></td>
                        <td style="font-size:20px;"> <b>Product Image</b></td>  
                        <td style="font-size:20px;" colspan="2"><b>Manager Iteam</b></td>
                    </tr>
                    <%
                        List<Product> list = (List<Product>) request.getAttribute("list");
                        for (Product p : list) {
                    %>
                    <tr>
                        <td><%= p.getProductID()%> </td>
                        <td><%= p.getProductName()%> </td>
                        <td><%= p.getProductPrice()%> </td>
                        <td><%= p.getProductImage()%> </td>
                        <td><a href="manageritem?id_del=<%= p.getProductID()%>">Delete</a></td>
                        <td><a href="editproduct?id_edit=<%= p.getProductID()%>">Update</a></td>
                    </tr>
                    <%}%>
                </table>
                <a href="addproduct.jsp"> <button>Add Product</button></a>
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


