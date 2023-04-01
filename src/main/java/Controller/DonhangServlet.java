package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.DonhangBO;
import Model.BO.KhachhangBO;
import Model.Bean.Admin;
import Model.Bean.Donhang;

@WebServlet("/Donhang")
public class DonhangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonhangServlet() {
        super();
    }
    
    private void checkAD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user = ((Admin)request.getSession().getAttribute("account")).getUser();
    	Admin ad = KhachhangBO.getKhachhangByUser(user); 
		if (!ad.getRoles().equals("AD")) {
			System.out.println("Phien lam viec loi");
			request.getRequestDispatcher("Authentication?logout=1").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("chuyentt")!=null) {
			checkAD(request, response);
			String iddh = request.getParameter("chuyentt");
			Boolean kq = DonhangBO.DonhangChuyenTT(iddh);
			if (kq==true) {
				response.sendRedirect("Donhang");
			} else {
				System.out.println("Loi chuyen trang thai");
			}
		} 
		else if (request.getParameter("delete")!=null) {
			checkAD(request, response);
			String iddh = request.getParameter("delete"); 
			Boolean kq = DonhangBO.DonhangDelete(iddh); 
			if (kq==true) {
				response.sendRedirect("Donhang");
			} else {
				System.out.println("Loi xoa don hang");
			}
		}
		else if (request.getParameter("deleteall")!=null) {
			checkAD(request, response);
			List<String> listiddh = new ArrayList<String>(); 
			for (String iddh : DonhangBO.getAllIddh()) {
				if (request.getParameter(iddh)!=null) {
					listiddh.add(iddh); 
				}
			}
			
			Boolean kq = DonhangBO.DonhangDeleteAll(listiddh); 
			if (kq==true) {
				response.sendRedirect("Donhang");
			} else {
				System.out.println("Xoa tat ca khong thanh cong");
			}
		}
		else {
			// lay du lieu ben DB
			List<Donhang> list = DonhangBO.getAllDonhang();
			// chuyen ve cho form
			request.setAttribute("listDH", list);
			request.getRequestDispatcher("DonhangList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
