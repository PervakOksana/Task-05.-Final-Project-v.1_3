package by.htp.jwd.service;

import java.util.List;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.DAOException;

public interface OderService {
	
	List<Adress> adressCar() throws ServiceException;
	public Adress adressCarId (String adress) throws ServiceException;
	public OderInfo  costCar (PriceList priceList, OderInfo oderInfo)throws ServiceException;
	public void addOder(OderInfo oderInfo, String carId, String userId )throws ServiceException;
	public List<Oder> allOder(int numberPage) throws ServiceException;
	public Integer getNumberRows() throws ServiceException;
	public Oder findOderById(int id) throws ServiceException;
	public List<Oder> allOderByUser(int numberPage, int userId) throws ServiceException;
	public void cancelOder(int oderId,String status)  throws ServiceException;
	public List<Oder> allOderNewByUser( int userId) throws ServiceException;
	public List<Oder> allOderNew( ) throws ServiceException;
	public void oderСompleted(int oderId) throws ServiceException;

}
