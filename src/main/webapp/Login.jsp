<%@page import="Model.Bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<title>Login</title>
    <style>
        #main{
            display: flex;padding:20px;margin-top:180px;
        }
        #sidebar{
            width: 22%;height: 100%;
        }
        #content {
            width: 78%;height: 530px;border: 2px solid #007bff;
            padding: 10px 20px;margin-left:30px ;overflow-y: scroll;
        }
        .wrapper {
            padding-bottom: 20px;
        }
        #navbar{
            position: absolute;top: 0;width: 100%;height: 180px;z-index: 10;
        }
        <% if (request.getSession().getAttribute("account")==null) { %>
        .session {
        	display: none !important;
        }	
        <% } %>
        <% 
        if (request.getSession().getAttribute("account")!=null) {
        	if (!((Admin) request.getSession().getAttribute("account")).getRoles().equals("KH")) {
        %>
		        .sessionKH {
		        	display: none !important;
		        }	
        <% 
        	}
        } 
        %>
        <% 
        if (request.getSession().getAttribute("account")!=null) {
        	if (!((Admin) request.getSession().getAttribute("account")).getRoles().equals("AD")) {
        %>
		        .sessionAD {
		        	display: none !important;
		        }	
        <% 
        	}
        } 
        %>
	</style>
</head>
<body>
    <div id="navbar">
        <jsp:include page="Nav.jsp"></jsp:include>
    </div>
    <div id="main">
        <div id="sidebar">
            <jsp:include page="Sidebar.jsp"></jsp:include>
        </div>
        <div id="content" style="background-image: url('image/BG.png');">
            <h4 style="width:100%;text-align:center;color:#007bff">Login</h4><hr>
            <p class="text-center"><span style="color:gray">(username = PhucNguyen,password= 23102002)</span></p>
            <div>
                <section class="container wrapper" >
                    <form action="Authentication?check_login=1" method="post" style="width:30%;margin:auto">
                        <label for="username">Username</label>
                        <input type="text" required name="username" id="username" class="form-control">
                        <br>
                        <label for="password">Password</label>
                        <input type="password" required name="password" id="password" class="form-control">
                        <br>
                        <input type="submit" class="btn btn-block btn-outline-primary" value="login">
                        <a href="Authentication?signin=1"><input type="button" class="btn btn-block btn-outline-primary" style="margin-top:20px;" value="Sign in"></a>
                    </form>
                </section>
            </div>
        </div>
    </div>
</body>
</html>