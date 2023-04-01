package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.Bean.Admin;
import Model.DAO.KhachhangDAO;

public class KhachhangBO {

	public static List<Admin> getAllKhachhang() {
		return KhachhangDAO.getAllKhachhang();
	}

	public static Boolean KhachhangDelete(String userkh) {
		return KhachhangDAO.KhachhangDelete(userkh);
	}

	public static List<String> getAllUser() {
		List<Admin> list = getAllKhachhang(); 
		List<String> listuser = new ArrayList<String>(); 
		for (Admin kh : list) {
			listuser.add(kh.getUser()); 
		}
		return listuser;
	}

	public static Boolean KhachhangDeleteAll(List<String> listuser) {
		for (String user: listuser) {
			if (!KhachhangDelete(user)) {
				return false;
			}
		}
		return true;
	}

	public static Boolean KhachhangUpdate(Admin kh) {
		return KhachhangDAO.KhachhangUpdate(kh);
	}

	public static Admin getKhachhangByUser(String user) {
		return KhachhangDAO.getKhachhangByUser(user); 
	}

}
