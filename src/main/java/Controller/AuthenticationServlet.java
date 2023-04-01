package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.AuthenticationBO;
import Model.Bean.Admin;

@WebServlet("/Authentication")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthenticationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("check_login")!=null) {
			String user = request.getParameter("username");
			String pass = request.getParameter("password");
			Admin a = AuthenticationBO.getAccount(user, pass);
			if (a!=null) {
				if (a.getRoles().equals("KH")) {
					request.getSession().setAttribute("account", a);
					request.getRequestDispatcher("Trasua").forward(request, response);
				}
				if (a.getRoles().equals("AD")) {
					request.getSession().setAttribute("account", a);
					request.getRequestDispatcher("Donhang").forward(request, response);
				}
			}
			else {
				request.getRequestDispatcher("LoginFail.jsp").forward(request, response);
			}
		}
		else if (request.getParameter("signin")!=null) {
			response.sendRedirect("Register.jsp");
		}
		else if (request.getParameter("register")!=null) {
			String user = request.getParameter("username");
			String pass = request.getParameter("password");
			String firstname = request.getParameter("fisrtname"); 
			String lastname = request.getParameter("lastname");
			String address = request.getParameter("address");
			String sdt = request.getParameter("sdt");
			Admin a = new Admin();
			a.setUser(user);
			a.setPass(pass);
			a.setFirstname(firstname);
			a.setLastname(lastname);
			a.setAddress(address);
			a.setSdt(sdt);
			a.setRoles("KH");
			Boolean kq = AuthenticationBO.AccountAdd(a);
			if (kq) {
				request.getSession().setAttribute("account", a);
				request.getRequestDispatcher("Trasua").forward(request, response);
			}
			else {
				request.getRequestDispatcher("LoginFail.jsp").forward(request, response);
			}
		}
		else if (request.getParameter("logout")!=null) {
			if (((Admin)request.getSession().getAttribute("account"))!=null) {
				request.getSession().setAttribute("account", null);	
			}
			response.sendRedirect("Login.jsp ");
		}
		else {
			response.sendRedirect("Login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
