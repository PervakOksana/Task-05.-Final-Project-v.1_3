package by.htp.jwd.dao;

import by.htp.jwd.dao.impl.AdressDAOImpl;
import by.htp.jwd.dao.impl.ApplicationInfoDAOImpl;
import by.htp.jwd.dao.impl.CarDAOimpl;
import by.htp.jwd.dao.impl.OderDAOimpl;
import by.htp.jwd.dao.impl.RepairBillDAOImpl;
import by.htp.jwd.dao.impl.UserDAOimpl;

public final class DAOProvider {
	private static final DAOProvider instanse = new DAOProvider();
	private final UserDAO userdao = new UserDAOimpl();
	private final CarDAO cardao = new CarDAOimpl();
	private final OderDAO oderdao = new OderDAOimpl();
	private final RepairBillDAO repairBilldao = new RepairBillDAOImpl();
	private final ApplicationInfoDAO applicationInfodao = new ApplicationInfoDAOImpl();
	private final AdressDAO adressDao = new AdressDAOImpl();
	
	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instanse;
	}

	public UserDAO getUserdao() {
		return userdao;
	}

	public CarDAO getCardao() {
		return cardao;
	}

	public OderDAO getOderdao() {
		return oderdao;
	}
	
	public RepairBillDAO getRepairBilldao() {
		return repairBilldao;
	}

	public ApplicationInfoDAO getApplicationInfodao() {
		return applicationInfodao;
	}

	public AdressDAO getAdressDao() {
		return adressDao;
	}
	
	

}
