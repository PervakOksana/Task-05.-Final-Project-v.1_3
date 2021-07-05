package by.htp.jwd.service;

import by.htp.jwd.bean.ApplicationInfo;

public interface ApplicationInfoService {

	public boolean updateApplicationInfo(ApplicationInfo applicationInfo) throws ServiceException;
	
	public ApplicationInfo getApplicationInfo() throws ServiceException;
}
