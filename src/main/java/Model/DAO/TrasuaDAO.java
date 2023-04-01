package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Admin;
import Model.Bean.Donhang;
import Model.Bean.Trasua;

public class TrasuaDAO {

	public static List<Trasua> getAllTrasua() {
		List<Trasua> list = new ArrayList<Trasua>();
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from trasua");
			
			while (rs.next()) {
				String id = rs.getObject(1).toString();
				String loaits = rs.getObject(2).toString();
				String kichthuoc = rs.getObject(3).toString();
				String gia = rs.getObject(4).toString();
				Trasua ts = new Trasua();
				ts.setId(id);
				ts.setLoai(loaits);
				ts.setKichthuoc(kichthuoc);
				ts.setGia(gia);
				list.add(ts); 
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Boolean TrasuaAdd(Donhang dh) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO donhang (idts, username, isdeliver) VALUES ('"+dh.getIdts()+"','"+dh.getUsername()+"','"
			+dh.getIsdeliver()+"');");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Trasua getTrasuaById(String idts) {
		for (Trasua ts :  getAllTrasua()) {
			if(ts.getId().equals(idts)) {
				return ts;
			}
		}
		return null;
	}

	public static Boolean TrasuaUpdate(Trasua ts) {
		try {
			Connection con = CreateConnection.create();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE trasua "
					+ "SET loai = '"+ts.getLoai()+"', kichthuoc = '"+ts.getKichthuoc()+"', gia = '"+ts.getGia()+"' "
					+ "WHERE id='"+ ts.getId()+"';");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
