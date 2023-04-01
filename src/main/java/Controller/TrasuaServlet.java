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
import Model.BO.TrasuaBO;
import Model.Bean.Admin;
import Model.Bean.Donhang;
import Model.Bean.Trasua;

@WebServlet("/Trasua")
public class TrasuaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TrasuaServlet() {
        super();
    }

    private void checkKH(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user = ((Admin)request.getSession().getAttribute("account")).getUser();
    	Admin ad = KhachhangBO.getKhachhangByUser(user); 
		if (!ad.getRoles().equals("KH")) {
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
		if (request.getParameter("buy")!=null) {
			checkKH(request, response);
			// them ts vao don hang
			String idts = request.getParameter("buy"); 
			String userkh = ((Admin)request.getSession().getAttribute("account")).getUser();
			Donhang dh = new Donhang();
			dh.setIdts(idts);
			dh.setUsername(userkh);
			dh.setIsdeliver("0");
			Boolean kq = TrasuaBO.TrasuaAdd(dh); 
			if (kq==true) {
				response.sendRedirect("DathangThanhcong.jsp");
			} else {
				response.sendRedirect("Error.jsp");
			}
		}
		else if (request.getParameter("buyall")!=null) {
			checkKH(request, response); 
			List<String> listidts = new ArrayList<String>(); 
			for (String idts : TrasuaBO.getAllIdts()) {
				if (request.getParameter(idts)!=null) {
					listidts.add(idts); 
				}
			}
			
			String userkh = ((Admin)request.getSession().getAttribute("account")).getUser();
			Boolean kq = TrasuaBO.TrasuaAddAll(listidts, userkh); 
			if (kq==true) {
				response.sendRedirect("DathangThanhcong.jsp");
			} else {
				System.out.println("Dat hang khong thanh cong.");
			}
		}
		else if (request.getParameter("update")!=null) {
			checkAD(request, response);  
			String idts = request.getParameter("update"); 
			Trasua ts = TrasuaBO.getTrasuaById(idts); 
			request.setAttribute("tsupdate", ts); 
			request.getRequestDispatcher("TrasuaUpdate.jsp").forward(request, response);
		}
		else if (request.getParameter("updateexecute")!=null) {
			checkAD(request, response);
			Trasua ts = new Trasua();
			ts.setId(request.getParameter("oldIDTS"));
			ts.setLoai(request.getParameter("loai"));
			ts.setKichthuoc(request.getParameter("kichthuoc"));
			ts.setGia(request.getParameter("gia"));
			
			Boolean kq = TrasuaBO.TrasuaUpdate(ts);
			if (kq==true) {
				System.out.println("update thanh cong");
				response.sendRedirect("Trasua");
			}
			else {
				System.out.println("update ko thanh cong");
			}
		}
		else {
			// lay du lieu ben DB
			List<Trasua> list = TrasuaBO.getAllTrasua();
			// chuyen ve cho form
			request.setAttribute("listTS", list);
			request.getRequestDispatcher("TrasuaList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
