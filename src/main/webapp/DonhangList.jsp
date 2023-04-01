<%@page import="Model.Bean.Donhang"%>
<%@page import="Model.Bean.Trasua"%>
<%@page import="Model.Bean.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Menu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        #main{
            display: flex;padding:20px;margin-top:180px;
        }
        #sidebar{
            width: 22%;
            height: 100%;
        }
        #content {
            width: 78%;
            height: 530px;
            border: 2px solid #007bff;
            padding: 10px 20px;
            margin-left:30px ;
            overflow-y: scroll;
        }
        #navbar{
            position: fixed;top: 0;width: 100%;height: 180px;z-index: 10;
        }
        <% if (request.getSession().getAttribute("account")==null) { %>
        .session {
        	display: none !important;
        }	
        <% } %>
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
    </style>
</head>
<body>
	<div id="navbar">
        <jsp:include page="Nav.jsp" ></jsp:include>
    </div>
    <div id="main">
        <div id="sidebar">
            <jsp:include page="Sidebar.jsp" ></jsp:include>
        </div>
        <div id="content" style="background-image: url('image/BG.png');">
            <h4 style="width:100%;text-align:center;color:#007bff;margin-top:20px;">Danh sách đơn hàng</h4>
            <form action="Donhang?deleteall=1" method="post">
	            <table width='100%' class="table table-striped">
	                <thead>
	                    <tr>
	                        <th scope="col">ID</th>
	                        <th scope="col">ID Trà sữa</th>
	                        <th scope="col">User khach hang</th>
	                        <th scope="col">Tình trạng</th>
	                        <th scope="col" class="session sessionAD">Action</th>
	                        <th scope="col" class="session sessionAD">Choose</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <%
	                    ArrayList<Donhang> listDH = (ArrayList<Donhang>)request.getAttribute("listDH");
	                    for (int i=0; i<listDH.size(); i++) {
	                    %>
	                    <tr>
	                    	<td><%= listDH.get(i).getIddh() %></td>
	                    	<td><%= listDH.get(i).getIdts() %></td>
	                    	<td><%= listDH.get(i).getUsername() %></td>
	                    	<%
	                    	if (listDH.get(i).getIsdeliver().equals("1")) {
	                    	%>
	                    	<td><%= "da giao" %></td>
	                    	<% }
	                    	else {%>
	                    	<td><%= "chua giao" %></td>
	                    	<% } %>
	                    	<td  class="session sessionAD"><a href="Donhang?chuyentt=<%=listDH.get(i).getIddh() %>"><button type="button" class="btn btn-primary" onclick="return confirm('Are you sure you want to chuyen?')">Chuyen TT</button></a>
	                    	<a class="session sessionAD" href="Donhang?delete=<%=listDH.get(i).getIddh() %>"><button type="button" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete?')">Delete</button></a></td>
	                   		<td class="session sessionAD"><input type="checkbox" name="<%= listDH.get(i).getIddh() %>" /></td>
	                   	</tr>
	                   	<% 
	                   	}
	                   	%>
	                </tbody>        
	            </table>
	            <button type="submit" class="session sessionAD btn btn-primary" style="margin-left:auto;display:block;" 
	            onclick="return confirm('Are you sure you want to delete all?')">Delete All</button>
            </form>
        </div>
    </div>
	<script lang="javascript">
         function TongGia(gia) {
         	alert("ban da vao day");
         	let tong = parseInt( document.getElementById("tonggia").value); 
         	let gia = parseInt( gia); 
         	tong = tong + gia;
         	document.getElementById("tonggia").value = tong; 
         }
     </script>
</body>
</html>