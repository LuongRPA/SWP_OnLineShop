<%-- 
    Document   : PostComponent
    Created on : Oct 26, 2021, 8:27:05 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">       
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">    
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js">
        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        
        <style>
            .title {
                color: #45a049;
                margin-bottom: 30px;
            }
            
            .parent-content {
                position: relative;
            }
            
            .content {
                font-style: italic;
                font-size: 20px;
            }
            
            .publicAt {
                position: absolute;
                bottom: 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <h1 class="title">${post.title}</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm">
                    <img src="img/${post.image}" class="img-fluid" alt="Responsive image">
                </div>

                <div class="col-sm parent-content">
                    <p class="content">${post.content}</p>
                    <p class="publicAt">Ngày đăng: ${post.publicAt}</p>
                </div>
            </div>
        </div>
    </body>
</html>
