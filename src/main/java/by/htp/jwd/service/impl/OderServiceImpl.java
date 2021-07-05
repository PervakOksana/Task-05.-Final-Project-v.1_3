package by.htp.jwd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.dao.OderDAO;
import by.htp.jwd.service.OderService;
import by.htp.jwd.service.ServiceException;

public class OderServiceImpl implements OderService {

	private final Logger LOGGER = LoggerFactory.getLogger(OderServiceImpl.class);

	@Override
	public List<Adress> adressCar() throws ServiceException {

		LOGGER.info("OderServiceimpl started adressCar.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		List<Adress> adresses = new ArrayList<Adress>();

		try {
			adresses = oderDAO.adressCar();

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return adresses;
	}

	@Override
	public Adress adressCarId(String adress) throws ServiceException {

		LOGGER.info("OderServiceimpl started adressCarId.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		Adress adresses = new Adress();

		try {
			adresses = oderDAO.adressCarId(adress);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return adresses;
	}

	@Override
	public OderInfo costCar(PriceList priceList, OderInfo oderInfo) throws ServiceException {

		LOGGER.info("OderServiceimpl started costCar.");

		String[] dateStart = oderInfo.getDateStart().split(" ")[0].split("-");
		String[] dateEnd = oderInfo.getDateEnd().split(" ")[0].split("-");

		String[] timeStart = oderInfo.getDateStart().split(" ")[1].split(":");
		String[] timeEnd = oderInfo.getDateEnd().split(" ")[1].split(":");
		double min = 0;
		double hour = 0;
		double day = 0;
		double price = 0;
		double sale = 0;
		if (Integer.parseInt(dateStart[0]) == Integer.parseInt(dateEnd[0])
				&& Integer.parseInt(dateStart[1]) == Integer.parseInt(dateEnd[1])
				&& Integer.parseInt(dateStart[2]) == Integer.parseInt(dateEnd[2])) {

			hour = Math.abs((Double.parseDouble(timeStart[0]) * 60 + Double.parseDouble(timeStart[1]))
					- (Double.parseDouble(timeEnd[0]) * 60 + Double.parseDouble(timeEnd[1]))) / 60;

			price = hour * priceList.getCostHour();
			sale=price*priceList.getSale()/100;
			price=price - sale;
			oderInfo.setCostOder(price);
			oderInfo.setSaleOder(sale);
			
			return oderInfo;
		}
		if (Integer.parseInt(dateStart[0]) == Integer.parseInt(dateEnd[0])
				&& Integer.parseInt(dateStart[1]) == Integer.parseInt(dateEnd[1])
				&& Integer.parseInt(dateStart[2]) != Integer.parseInt(dateEnd[2])) {

			if (Double.parseDouble(timeStart[0]) < Double.parseDouble(timeEnd[0])) {

				day = Double.parseDouble(dateEnd[2]) - Double.parseDouble(dateStart[2]);
				hour = Math.abs(Double.parseDouble(timeStart[0]) - Double.parseDouble(timeEnd[0]));
				min = Math.abs(Double.parseDouble(timeStart[1]) - Double.parseDouble(timeEnd[1]));

				day = day + hour / 24 + min / 60 / 24;
				price = day * priceList.getCostDayr();
				sale=price*priceList.getSale()/100;
				price=price - sale;
				oderInfo.setCostOder(price);
				oderInfo.setSaleOder(sale);
				
				return oderInfo;
			}
			if (Double.parseDouble(timeStart[0]) > Double.parseDouble(timeEnd[0])) {

				day = Double.parseDouble(dateEnd[2]) - Double.parseDouble(dateStart[2]) - 1;
				
				hour = 24 - Double.parseDouble(timeStart[0]) + Double.parseDouble(timeEnd[0]);

				day = day + hour / 24;
				price = day * priceList.getCostDayr();
				sale=price*priceList.getSale()/100;
				price=price - sale;
				oderInfo.setCostOder(price);
				oderInfo.setSaleOder(sale);
				
				return oderInfo;
			}
		}

		if (Integer.parseInt(dateStart[0]) == Integer.parseInt(dateEnd[0])
				&& Integer.parseInt(dateStart[1]) != Integer.parseInt(dateEnd[1])
				&& Integer.parseInt(dateStart[2]) != Integer.parseInt(dateEnd[2])) {

			int year = Integer.parseInt(dateStart[0]);
			if (Integer.parseInt(dateStart[1]) == 1 || Integer.parseInt(dateStart[1]) == 3
					|| Integer.parseInt(dateStart[1]) == 5 || Integer.parseInt(dateStart[1]) == 7
					|| Integer.parseInt(dateStart[1]) == 8 || Integer.parseInt(dateStart[1]) == 10
					|| Integer.parseInt(dateStart[1]) == 12) {

				day = 31 - Integer.parseInt(dateStart[2]) + Integer.parseInt(dateEnd[2]);
			}

			if (Integer.parseInt(dateStart[1]) == 4 || Integer.parseInt(dateStart[1]) == 6
					|| Integer.parseInt(dateStart[1]) == 9 || Integer.parseInt(dateStart[1]) == 11) {

				day = 30 - Integer.parseInt(dateStart[2]) + Integer.parseInt(dateEnd[2]);

			}

			if (Integer.parseInt(dateStart[1]) == 2) {

				day = 28 - Integer.parseInt(dateStart[2]) + Integer.parseInt(dateEnd[2]);

			}

			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {

				day = 29 - Integer.parseInt(dateStart[2]) + Integer.parseInt(dateEnd[2]);

			}

			if (Double.parseDouble(timeStart[0]) < Double.parseDouble(timeEnd[0])) {

				hour = Math.abs(Double.parseDouble(timeStart[0]) - Double.parseDouble(timeEnd[0]));
				min = Math.abs(Double.parseDouble(timeStart[1]) - Double.parseDouble(timeEnd[1]));

				day = day + hour / 24 + min / 60 / 24;
				price = day * priceList.getCostDayr();
				sale=price*priceList.getSale()/100;
				price=price - sale;
				oderInfo.setCostOder(price);
				oderInfo.setSaleOder(sale);
				
				return oderInfo;
			}
			if (Double.parseDouble(timeStart[0]) > Double.parseDouble(timeEnd[0])) {

				day = day - 1;
				
				hour = 24 - Double.parseDouble(timeStart[0]) + Double.parseDouble(timeEnd[0]);

				day = day + hour / 24;
				price = day * priceList.getCostDayr();
				sale=price*priceList.getSale()/100;
				price=price - sale;
				oderInfo.setCostOder(price);
				oderInfo.setSaleOder(sale);
				return oderInfo;
			}

			price = day * priceList.getCostDayr();
			sale=price*priceList.getSale()/100;
			price=price - sale;
			oderInfo.setCostOder(price);
			oderInfo.setSaleOder(sale);

		}

		return oderInfo;
	}

	@Override
	public void addOder(OderInfo oderInfo,  String carId, String userId) throws ServiceException {

		LOGGER.info("OderServiceimpl started addOder.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		try {
			oderDAO.addOder(oderInfo, carId, userId);
		} catch (DAOException e) {

			throw new ServiceException(e);

		}

	}

	@Override
	public List<Oder> allOder(int numberPage) throws ServiceException {

		LOGGER.info("OderServiceimpl started allOder.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		List<Oder> oders = new ArrayList<Oder>();

		try {

			oders = oderDAO.allOder(numberPage);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return oders;
	}

	@Override
	public Integer getNumberRows() throws ServiceException {

		LOGGER.info("OderServiceimpl started getNumberRows.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		Integer number = 0;
		try {
			number = oderDAO.getNumberRows();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return number;
	}

	@Override
	public Oder findOderById(int id) throws ServiceException {

		LOGGER.info("OderServiceimpl started findOderById.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		Oder oder = new Oder();
		try {
			oder = oderDAO.findOderById(id);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return oder;
	}

	@Override
	public List<Oder> allOderByUser(int numberPage, int userId) throws ServiceException {

		LOGGER.info("OderServiceimpl started allOder.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		List<Oder> oders = new ArrayList<Oder>();

		try {

			oders = oderDAO.allOderByUser(numberPage, userId);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return oders;
	}

	@Override
	public void cancelOder(int oderId, String status) throws ServiceException {

		LOGGER.info("OderServiceimpl started cancelOder.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		List<Oder> oders = new ArrayList<Oder>();

		try {

			oderDAO.cancelOder(oderId, status);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
	}

	@Override
	public List<Oder> allOderNewByUser(int userId) throws ServiceException {

		LOGGER.info("OderServiceimpl started allOderNewByUser.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		List<Oder> oders = new ArrayList<Oder>();

		try {

			oders = oderDAO.allOderNewByUser(userId);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return oders;
	}

	@Override
	public List<Oder> allOderNew() throws ServiceException {

		LOGGER.info("OderServiceimpl started addOder.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();
		List<Oder> oders = new ArrayList<Oder>();

		try {
			oders =oderDAO.allOderNew();
		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return oders;
	}

	@Override
	public void oderСompleted(int oderId) throws ServiceException {

		LOGGER.info("OderServiceimpl started oderСompleted.");

		DAOProvider proviger = DAOProvider.getInstance();
		OderDAO oderDAO = proviger.getOderdao();

		try {
			oderDAO.oderСompleted(oderId);
		} catch (DAOException e) {

			throw new ServiceException(e);

		}

		
	}

}
