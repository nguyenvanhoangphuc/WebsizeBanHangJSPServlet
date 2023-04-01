<%@page import="Model.Bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sidebar</title>
    <style>
        .sidebar{
            width:20vw;height:50px;background-color:aqua;display:flex;align-items:center;justify-content:start;
            border-radius: 10px;
            margin: 10px auto;
            padding-left:20px;
        }
        .sidebar:hover{
            background-color: aquamarine;
        }
        .red {
            background-color: rgb(237, 247, 195);
        }
        <% if (request.getSession().getAttribute("account")==null) { %>
        .session {
        	display: none !important;
        }
        <% } %>
        <% if (request.getSession().getAttribute("account")!=null) { %>
        .unsession {
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
    <table>
        <tr>
            <td><div class='sidebar'><i class='fa-solid fa-house-user'></i><a href='Trasua'>Trang Chủ</a></div></td>
        </tr>
        <tr>
            <td><div class='sidebar'><i class="fa-solid fa-list-ul"></i><a href='Trasua'>Danh sách trà sữa</a></div></td>
        </tr>
        <tr>
            <td><div class='sidebar'><i class="fa-solid fa-truck-fast"></i><a href='Donhang'>Danh sách đơn hàng</a></div></td>
        </tr>
        <tr class="session sessionAD">
            <td><div class='sidebar'><i class="fa-solid fa-users"></i><a href='Khachhang'>Danh sách khách hàng</a></div></td>
        </tr>
        <tr class="session">
            <td><div class='sidebar'><i class="fa-solid fa-user"></i><a href='Khachhang?update=1'>Tài khoản</a></div></td>
        </tr>
        <tr>
            <td><div class='sidebar red unsession'><i class='fa-solid fa-right-from-bracket'></i><a href='Authentication'>Đăng nhập</a></div></td>
        </tr>
        <tr>
            <td><div class='sidebar red session'><i class='fa-solid fa-right-from-bracket'></i><a href='Authentication?logout=1'>Đăng xuất</a></div></td>
        </tr>
    </table>
</body>
</html>