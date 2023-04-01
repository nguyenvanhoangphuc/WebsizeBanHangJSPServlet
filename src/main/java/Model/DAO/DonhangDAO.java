package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Donhang;

public class DonhangDAO {

	public static List<Donhang> getAllDonhang() {
		List<Donhang> list = new ArrayList<Donhang>();
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from donhang");
			
			while (rs.next()) {
				String iddh = rs.getObject(1).toString();
				String idts = rs.getObject(2).toString();
				String username = rs.getObject(3).toString();
				String isdeliver = rs.getObject(4).toString();
				Donhang dh = new Donhang();
				dh.setIddh(iddh);
				dh.setIdts(idts);
				dh.setUsername(username);
				dh.setIsdeliver(isdeliver);
				list.add(dh); 
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Donhang getDonhangById(String iddh) {
		for (Donhang dh :  getAllDonhang()) {
			if(dh.getIddh().equals(iddh)) {
				return dh;
			}
		}
		return null;
	}

	public static Boolean DonhangUpdate(Donhang dh) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE donhang "
					+ "SET idts = '"+dh.getIdts()+"', username = '"+dh.getUsername()+"', isdeliver = '"+dh.getIsdeliver()+"' "
					+ "WHERE iddh='"+ dh.getIddh()+"';");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean DonhangDelete(String iddh) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM donhang WHERE iddh='"+iddh+"';");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
