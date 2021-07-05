package by.htp.jwd.dao;

import by.htp.jwd.bean.ApplicationInfo;

public interface ApplicationInfoDAO {

	final String INFO_ID ="Id";
	final String INFO_PAY_TERM ="pay_term";
	final String INFO_CONTACT ="contact";
	final String INFO_ADRESS_EMAIL ="adress_email";
	final String INFO_REMINDER ="letter_reminder";
	final String INFO_BILL ="letter_bill";
	final String INFO_PASSWORD_EMAIL ="password_email";
	final String INFO_UPDATE = "UPDATE info_app SET pay_term=?, contact=?, adress_email=?, letter_reminder=?, letter_bill=?, password_email=?  WHERE Id=1";
	final String INFO_GET = "SELECT * FROM info_app WHERE Id=1";

	public boolean updateApplicationInfo(ApplicationInfo applicationInfo) throws DAOException;
	
	public ApplicationInfo getApplicationInfo() throws DAOException;
	
	
}
