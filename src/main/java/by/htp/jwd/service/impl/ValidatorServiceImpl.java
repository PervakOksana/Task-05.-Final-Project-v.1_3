package by.htp.jwd.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public boolean loginationValidator(HttpServletRequest request, HttpServletResponse response) {
		boolean result = true;
		HttpSession session = request.getSession();
		if (session == null) {
			result = false;

		}
		Boolean iaAuth = (Boolean) session.getAttribute(AUTH);
		if (iaAuth == null || !iaAuth) {
			result = false;

		}
		return result;
	}

	@Override
	public boolean registrationValidator(HttpServletRequest request) {
		String password;

		password = request.getParameter(PASSWORD);
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		matcher.lookingAt();
		return matcher.lookingAt();
	}

	@Override
	public boolean dateValidator(HttpServletRequest request) {

		String dateStart;
		String dateEnd;

		boolean result = false;

		dateStart = request.getParameter(DATE_START);
		dateEnd = request.getParameter(DATE_END);

		Pattern pattern = Pattern.compile(DATE_PATTERN);
		Matcher matcherDateStart = pattern.matcher(dateStart);
		Matcher matcherDateEnd = pattern.matcher(dateEnd);

		boolean dateStartResult = matcherDateStart.lookingAt();
		boolean dateEndResult = matcherDateEnd.lookingAt();

		if (dateStartResult == true && dateEndResult == true) {
			result = true;
		}

		return result;
	}

	@Override
	public boolean timeValidator(HttpServletRequest request) {

		String timeStart;
		String timeEnd;

		boolean result = false;

		timeStart = request.getParameter(TIME_START);
		timeEnd = request.getParameter(TIME_END);
		Pattern pattern = Pattern.compile(TIME_PATTERN);
		Matcher matcherTimeStart = pattern.matcher(timeStart);
		Matcher matcherTimeEnd = pattern.matcher(timeEnd);

		boolean timeStartResult = matcherTimeStart.lookingAt();
		boolean timeEndResult = matcherTimeEnd.lookingAt();

		if (timeStartResult == true && timeEndResult == true) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean dateUserValidator(HttpServletRequest request) {

		boolean result = false;

		String passwordDate = request.getParameter(PASSWORD_DATE);
		String drivingLicanseDate = request.getParameter(DRIVING_LICANSE_DATE);

		Pattern pattern = Pattern.compile(DATE_PATTERN);
		Matcher matcherPasswordDate = pattern.matcher(passwordDate);
		Matcher matcherDrivingLicanseDate = pattern.matcher(drivingLicanseDate);

		boolean datePassword = matcherPasswordDate.lookingAt();
		boolean dateDrivingLicanse = matcherDrivingLicanseDate.lookingAt();

		if (datePassword == true && dateDrivingLicanse == true) {
			result = true;
		}

		return result;
	}

	@Override
	public boolean emailValidator(HttpServletRequest request) {

		boolean result = false;

		String email = request.getParameter(EMAIL);

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matchEremail = pattern.matcher(email);

		matchEremail.lookingAt();

		if (matchEremail.lookingAt() == true) {
			result = true;
		}

		return result;
	}

	@Override
	public boolean compareDateValidator(HttpServletRequest request) throws ParseException {

		String timeStart;
		String timeEnd;

		boolean result = false;

		timeStart = request.getParameter(TIME_START);
		timeEnd = request.getParameter(TIME_END);

		String dateStart;
		String dateEnd;

		dateStart = request.getParameter(DATE_START);
		dateEnd = request.getParameter(DATE_END);

		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateS = dateParser.parse(dateStart + " " + timeStart);
		Date dateE = dateParser.parse(dateEnd + " " + timeEnd);

		if (dateE.after(dateS)) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean loginationAdminValidator(HttpServletRequest request, HttpServletResponse response) {
		boolean result = true;
		HttpSession session = request.getSession();
		if (session == null) {
			result = false;

		}
		Boolean iaAuth = (Boolean) session.getAttribute(AUTH);
		if (iaAuth == null || !iaAuth) {
			result = false;

		}
		if (!ADMIN.equals((String) session.getAttribute(ROLE_LOGIN))) {

			result = false;
		}
		return result;
	}

	@Override
	public boolean dateForOderValidator(HttpServletRequest request) {

		boolean result = true;
		String dateStart = request.getParameter(DATE_START);
		Date currentDay = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDay);
		calendar.add(Calendar.DATE, 1);
		Date nextDay = calendar.getTime();
		String currentDayAsString = new SimpleDateFormat(PATTERN_DATE).format(currentDay);
		String nextDayAsString = new SimpleDateFormat(PATTERN_DATE).format(nextDay);

		if (!currentDayAsString.equals(dateStart) && !nextDayAsString.equals(dateStart)) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean numberValidator(HttpServletRequest request) {
		boolean result = false;

		String costHour = request.getParameter(COST_HOUR);
		String costDayr = request.getParameter(COST_DAY);
		String sale = request.getParameter(SALE);

		Pattern pattern = Pattern.compile(PATTERN_NUMBER);
		Matcher matcherCostHour = pattern.matcher(costHour);
		Matcher matcherCostDayr = pattern.matcher(costDayr);
		Matcher matcherSale = pattern.matcher(sale);

		if (matcherCostHour.lookingAt() == true && matcherCostDayr.lookingAt() == true
				&& matcherSale.lookingAt() == true) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean repairValidator(HttpServletRequest request) {
		boolean result = false;

		String dateRepair = request.getParameter(DATE_REPAIR);
		String costRepair = request.getParameter(COST_REPAIR);
		
		Pattern patternNumber = Pattern.compile(PATTERN_NUMBER);
		Pattern patternDate = Pattern.compile(DATE_PATTERN);
		Matcher matcherNumber = patternNumber.matcher(costRepair);
		Matcher matcherDate = patternDate.matcher(dateRepair);
		
		if (matcherNumber.lookingAt() == true && matcherDate.lookingAt() == true) {
			result = true;
		}
		return result;
	}

}
