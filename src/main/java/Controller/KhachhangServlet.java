package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.KhachhangBO;
import Model.Bean.Admin;

@WebServlet("/Khachhang")
public class KhachhangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KhachhangServlet() {
        super();
    }
    
    private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("account")==null) {
			System.out.println("Phien lam viec loi");
			request.getRequestDispatcher("Authentication?logout=1").forward(request, response);
		}
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
		if (request.getParameter("delete")!=null) {
			checkAD(request, response);
			String userkh = request.getParameter("delete");
			Boolean kq = KhachhangBO.KhachhangDelete(userkh); 
			if (kq==true) {
				System.out.println("Xoa thanh cong");
				response.sendRedirect("Khachhang");
			}
			else {
				System.err.println("Xoa khong thanh cong");
			}
		}
		else if (request.getParameter("deleteall")!=null) {
			checkAD(request, response);
			List<String> listuser = new ArrayList<String>(); 
			for (String user : KhachhangBO.getAllUser()) {
				if (request.getParameter(user)!=null) {
					listuser.add(user); 
				}
			}
			
			Boolean kq = KhachhangBO.KhachhangDeleteAll(listuser); 
			if (kq==true) {
				System.out.println("Xoa tat ca thanh cong");
				response.sendRedirect("Khachhang");
			} else {
				System.out.println("Xoa tat ca khong thanh cong");
			}
		}
		else if (request.getParameter("update")!=null) {
			check(request, response);
			request.getRequestDispatcher("KhachhangUpdate.jsp").forward(request, response);
		}
		else if (request.getParameter("updateexecute")!=null) {
			check(request, response);
			Admin kh = new Admin();
			kh.setUser(request.getParameter("oldUserKH"));
			kh.setPass(request.getParameter("pass"));
			kh.setFirstname(request.getParameter("firstname"));
			kh.setLastname(request.getParameter("lastname"));
			kh.setAddress(request.getParameter("address"));
			kh.setSdt(request.getParameter("sdt"));
			kh.setRoles("KH");
			
			Boolean kq = KhachhangBO.KhachhangUpdate(kh);
			if (kq==true) {
				System.out.println("update thanh cong");
				request.getSession().setAttribute("account", kh);
				request.getRequestDispatcher("Khachhang?update=1").forward(request, response);
			}
			else {
				System.out.println("update ko thanh cong");
			}
		}
		else {
			checkAD(request, response);
			// lay du lieu ben DB
			List<Admin> list = KhachhangBO.getAllKhachhang();
			// chuyen ve cho form
			request.setAttribute("listKH", list);
			request.getRequestDispatcher("KhachhangList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
