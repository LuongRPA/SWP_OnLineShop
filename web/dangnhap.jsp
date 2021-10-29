<%-- 
    Document   : dangnhap
    Created on : Aug 16, 2019, 9:03:24 PM
    Author     : sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
         <title>LapTopGaming</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/5eb161e28e.js"></script>
    </head>
    <style>
        body {
            font-family: 'Mukta', sans-serif;
            background: url("src/dangnhap.png") no-repeat, center ;
            background-size: cover;
        }
 
        .text-center {
            text-align: center;
        }

        /*/////////////// FONT STYLES ////////////////////*/
        .content-w3ls {
            max-width: 500px;
            margin: 6em auto;
        }
        .icon span.fa{
            font-size: 60px;
            color: #eee;
        }
        .icon{
            margin-bottom:3em;
        }
        .content-bottom {
            padding: 0em 4em;
            margin: 0 1em;
            position: relative; 
            z-index: 1;
        }
        .logo h1 a {
            font-size: 25px;
            color: #fff;
            text-transform: capitalize;
            font-weight: 600;
            letter-spacing: 1px;
        }

        .field-group label {
            font-size: 15px;
        }

        /*/////////////// FORM STYLES ////////////////////*/

        form .field-group {
            background: rgba(255, 255, 255, 0.15);
            display: flex;
            display: flex;
            display: -webkit-box;
            /* OLD - iOS 6-, Safari 3.1-6 */
            display: -moz-box;
            /* OLD - Firefox 19- (buggy but mostly works) */
            display: -ms-flexbox;
            /* TWEENER - IE 10 */
            display: -webkit-flex;
            /* NEW - Chrome */
            margin-bottom: 20px;
            padding: 2px 10px 2px 0;
            border-radius: 35px;
            -webkit-border-radius: 35px;
            -moz-border-radius: 35px;
            -ms-border-radius: 35px;
            -o-border-radius: 35px;
        }

        form .field-group span {
            flex: 1;
            -webkit-box-flex: 1;      /* OLD - iOS 6-, Safari 3.1-6 */
            -moz-box-flex: 1;         /* OLD - Firefox 19- */
            width: 20%;               /* For old syntax, otherwise collapses. */
            -webkit-flex: 1;          /* Chrome */
            -ms-flex: 1;              /* IE 10 */
            color: #fff;
            background: transparent;
            line-height: 49px;
            text-align: right;
        }

        form .field-group .wthree-field {
            flex: 4 50%;
            -webkit-box-flex:4 50%;     /* OLD - iOS 6-, Safari 3.1-6 */
            -moz-box-flex: 4 50%;        /* OLD - Firefox 19- */
            -webkit-flex: 4 50%;          /* Chrome */
            -ms-flex: 4 50%;             /* IE 10 */
        }

        .wthree-field button.btn {
            background: #fc636b;
            border: none;
            color: #fff;
            padding: 11px 15px;
            text-transform: uppercase;
            font-family: 'Mukta', sans-serif;
            font-size: 16px;
            width:100%;
            margin-top:10px;
            letter-spacing: 2px;
            cursor: pointer;
            transition: 0.5s all;
            -webkit-transition: 0.5s all;
            -moz-transition: 0.5s all;
            -o-transition: 0.5s all;
            -ms-transition: 0.5s all;
            border-radius: 35px;
            -webkit-border-radius: 35px;
            -moz-border-radius: 35px;
            -ms-border-radius: 35px;
            -o-border-radius: 35px;
        }

        .wthree-field input {
            padding: 10px 0 10px 15px;
            font-size: 17px;
            font-weight: 300;
            color:#ddd;
            letter-spacing: 1px;
            border: none;
            background: transparent;
            box-sizing: border-box;
            font-family: 'Mukta', sans-serif;
            width: 100%;
            outline: none;
        }
        ::-webkit-input-placeholder { /* Chrome/Opera/Safari */
            color: #ccc;
        }
        ::-moz-placeholder { /* Firefox 19+ */
            color: #ccc;
        }
        ::-ms-input-placeholder { /* IE 10+ */
            color: #ccc;
        }
        ::-moz-placeholder { /* Firefox 18- */
            color: #ccc;
        }

        form .register a {
            font-size: 15px;
            letter-spacing: 2px;
            font-weight: normal;
            text-transform: uppercase;
            color: #fff !important;


        }

    </style>
    <body>
        <div class="content-w3ls">
            <div class="text-center icon">
                <span class="fa fa-fire"></span>
            </div>

            <div class="content-bottom">

                <form action="login" method="post">

                    <div class="field-group">
                        <span class="fa fa-user" aria-hidden="true"></span>
                        <div class="wthree-field">
                            <input name="username" id="text1" type="text" value="" placeholder="Username" required >
                        </div>
                    </div>
                    <div class="field-group">
                        <span class="fa fa-lock" aria-hidden="true"></span>
                        <div class="wthree-field">
                            <input name="password" id="myInput" type="Password" placeholder="Password" required >
                        </div>
                    </div>
                    <div class="wthree-field">
                        <input type="submit" class="btn" value="LOGIN">
                    </div>
                    <div class="register" style="margin-top: 15px; ">
                        <a href="register">CREATE ACCOUNT</a>
                    </div>
                </form>

                <%
                    if (request.getAttribute("errorMessage") != null) {
                %>
                <h2 style="color: red"><%= request.getAttribute("errorMessage")%></h2>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>

