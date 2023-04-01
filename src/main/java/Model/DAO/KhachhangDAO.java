package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Admin;

public class KhachhangDAO {

	public static List<Admin> getAllAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from admin");
			
			while (rs.next()) {
				String user = rs.getObject(1).toString();
				String pass = rs.getObject(2).toString();
				String firstname = rs.getObject(3).toString();
				String lastname = rs.getObject(4).toString();
				String address = rs.getObject(5).toString();
				String sdt = rs.getObject(6).toString();
				String roles = rs.getObject(7).toString(); 
				Admin kh = new Admin();
				kh.setUser(user);
				kh.setPass(pass);
				kh.setFirstname(firstname);
				kh.setLastname(lastname);
				kh.setAddress(address);
				kh.setSdt(sdt);
				kh.setRoles(roles);
				list.add(kh); 
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Admin> getAllKhachhang() {
		List<Admin> list = new ArrayList<Admin>();
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from admin");
			
			while (rs.next()) {
				String user = rs.getObject(1).toString();
				String pass = rs.getObject(2).toString();
				String firstname = rs.getObject(3).toString();
				String lastname = rs.getObject(4).toString();
				String address = rs.getObject(5).toString();
				String sdt = rs.getObject(6).toString();
				String roles = rs.getObject(7).toString(); 
				if (!roles.equals("KH")) continue;
				Admin kh = new Admin();
				kh.setUser(user);
				kh.setPass(pass);
				kh.setFirstname(firstname);
				kh.setLastname(lastname);
				kh.setAddress(address);
				kh.setSdt(sdt);
				kh.setRoles(roles);
				list.add(kh); 
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Boolean KhachhangDelete(String userkh) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM admin WHERE username='"+userkh+"';");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean KhachhangUpdate(Admin kh) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE admin "
					+ "SET password = '"+kh.getPass()+"', firstname = N'"+kh.getFirstname()+"', lastname = N'"+kh.getLastname()+"', "
							+ "address = N'"+kh.getAddress()+"', sdt = '"+kh.getSdt()+"'  "
					+ "WHERE username='"+ kh.getUser()+"';");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Admin getKhachhangByUser(String user) {
		for (Admin ad :  getAllAdmin()) {
			if(ad.getUser().equals(user)) {
				return ad;
			}
		}
		return null;
	}

}
