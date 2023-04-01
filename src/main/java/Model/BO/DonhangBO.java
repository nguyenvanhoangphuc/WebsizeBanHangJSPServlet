package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.Bean.Donhang;
import Model.Bean.Trasua;
import Model.DAO.DonhangDAO;

public class DonhangBO {

	public static List<Donhang> getAllDonhang() {
		return DonhangDAO.getAllDonhang();
	}

	public static Boolean DonhangChuyenTT(String iddh) {
		Donhang dh = DonhangDAO.getDonhangById(iddh); 
		if (dh==null) return false;
		if (dh.getIsdeliver().equals("1")) dh.setIsdeliver("0");
		else dh.setIsdeliver("1");
		return DonhangDAO.DonhangUpdate(dh);
	}

	public static Boolean DonhangDelete(String iddh) {
		return DonhangDAO.DonhangDelete(iddh);
	}

	public static List<String> getAllIddh() {
		List<Donhang> list = getAllDonhang(); 
		List<String> listiddh = new ArrayList<String>(); 
		for (Donhang dh : list) {
			listiddh.add(dh.getIddh()); 
		}
		return listiddh;
	}

	public static Boolean DonhangDeleteAll(List<String> listiddh) {
		for (String iddh: listiddh) {
			if (!DonhangDelete(iddh)) {
				return false;
			}
		}
		return true;
	}

}
