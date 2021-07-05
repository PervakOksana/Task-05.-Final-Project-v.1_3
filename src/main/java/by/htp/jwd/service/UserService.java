package by.htp.jwd.service;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;

public interface UserService {
	
	
	User authorization(String login, String password) throws ServiceException;

	public boolean registration(User user) throws ServiceException;
	
	boolean registrationAddDetail(UserDetail userDetail) throws ServiceException;
	
	boolean registrationAddAdress(Adress adress) throws ServiceException;
	
	boolean registrationAll(UserDetail userDetail) throws ServiceException;
	
	boolean updateUser(UserDetail userDetail) throws ServiceException;
	
	User findPassword (String email) throws ServiceException;
	 
	UserDetail findUserViaId (String id) throws ServiceException;
	
	User findLogin (String login) throws ServiceException;

}
