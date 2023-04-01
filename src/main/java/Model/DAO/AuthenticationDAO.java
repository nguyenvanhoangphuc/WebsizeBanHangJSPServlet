package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Bean.Admin;

public class AuthenticationDAO {

	public static Admin getAccount(String user, String pass) {
		// truy cap co so du lieu
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from admin");

			while (rs.next()) {
				String username = rs.getObject(1).toString();
				String password = rs.getObject(2).toString();
				String firstname = rs.getObject(3).toString();
				String lastname = rs.getObject(4).toString();
				String address = rs.getObject(5).toString();
				String sdt = rs.getObject(6).toString();
				String roles = rs.getObject(7).toString();
				if (username.equals(user) && password.equals(pass)) {
					Admin a = new Admin();
					a.setUser(username);
					a.setPass(password);
					a.setFirstname(firstname);
					a.setLastname(lastname);
					a.setAddress(address);
					a.setSdt(sdt);
					a.setRoles(roles);
					return a;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean AccountAdd(Admin a) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO admin VALUES ('"+a.getUser()+"','"+a.getPass()+"','"
			+a.getFirstname()+"','"+a.getLastname()+"','"+a.getAddress()+"','"+a.getSdt()+"', '"+a.getRoles()+"')");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
