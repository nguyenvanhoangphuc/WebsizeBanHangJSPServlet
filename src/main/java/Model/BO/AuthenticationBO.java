package Model.BO;

import Model.Bean.Admin;
import Model.DAO.AuthenticationDAO;

public class AuthenticationBO {

	public static Admin getAccount(String user, String pass) {
		return AuthenticationDAO.getAccount(user, pass);
	}

	public static Boolean AccountAdd(Admin a) {
		return AuthenticationDAO.AccountAdd(a);
	}

}
