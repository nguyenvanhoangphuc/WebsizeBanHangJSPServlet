package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.Bean.Admin;
import Model.Bean.Donhang;
import Model.Bean.Trasua;
import Model.DAO.TrasuaDAO;

public class TrasuaBO {

	public static List<Trasua> getAllTrasua() {
		return TrasuaDAO.getAllTrasua();
	}
	
	public static Trasua getTrasuaById(String idts) {
		return TrasuaDAO.getTrasuaById(idts);
	}

	public static Boolean TrasuaAdd(Donhang dh) {
		return TrasuaDAO.TrasuaAdd(dh);
	}

	public static List<String> getAllIdts() {
		List<Trasua> list = getAllTrasua(); 
		List<String> listidts = new ArrayList<String>(); 
		for (Trasua ts : list) {
			listidts.add(ts.getId()); 
		}
		return listidts;
	}

	public static Boolean TrasuaAddAll(List<String> listidts, String username) {
		for (String idts: listidts) {
			Donhang dh = new Donhang();
			dh.setIdts(idts);
			dh.setUsername(username);
			dh.setIsdeliver("0");
			if (!TrasuaAdd(dh)) {
				return false;
			}
		}
		return true;
	}

	public static Boolean TrasuaUpdate(Trasua ts) {
		return TrasuaDAO.TrasuaUpdate(ts);
	}

}
