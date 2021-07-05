<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>
<jsp:include page="css/header.jsp" />
<jsp:include page="css/form_body.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
	<fmt:message bundle="${loc}" key="local.choose.page.rent" var="rent" />
	<fmt:message bundle="${loc}" key="local.header.menu.homepage" var="home" />
    <fmt:message bundle="${loc}" key="local.header.menu.ourcar" var="ourcar" />
    <fmt:message bundle="${loc}" key="local.header.menu.payment" var="payment" />
    <fmt:message bundle="${loc}" key="local.header.menu.contact" var="contact" />
    <fmt:message bundle="${loc}" key="local.header.menu.log" var="login" />
    <fmt:message bundle="${loc}" key="local.header.menu.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.header.menu.cabinet" var="cabinet" />
    <fmt:message bundle="${loc}" key="local.choose.page.place.first" var="place_first" />
    <fmt:message bundle="${loc}" key="local.choose.page.place.secend" var="place_second" />
    <fmt:message bundle="${loc}" key="local.choose.page.date.first" var="date_first" />
    <fmt:message bundle="${loc}" key="local.choose.page.date.secend" var="date_second" />
    <fmt:message bundle="${loc}" key="local.choose.page.time.first" var="time_first" />
    <fmt:message bundle="${loc}" key="local.choose.page.time.secend" var="time_second" />
    <fmt:message bundle="${loc}" key="local.choose.page.look" var="look" />
    <fmt:message bundle="${loc}" key="local.choose.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.choose.page.pattern.date" var="date" />
    <fmt:message bundle="${loc}" key="local.choose.page.pattern.time" var="time" />
    <fmt:message bundle="${loc}" key="local.choose.page.pattern.date.wrong" var="date_wrong" />
    <fmt:message bundle="${loc}" key="local.choose.page.pattern.time.wrong" var="time_wrong" />
    <fmt:message bundle="${loc}" key="local.choose.page.date.oder.wrong" var="date_oder_wrong" />


</head>
<body>

<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="Controller?command=topaymaentcontactpage&take=is">${payment}</a></li>		
		<li><a href="Controller?command=topaymaentcontactpage&take=not">${contact}</a></li>
		
		<c:if
						test="${sessionScope.roleByLogin == null }">
						<li><a href='<c:url value="/Controller?command=tologinationpage" />'>${login}</a></li>
		</c:if> 
		
		<c:if
						test="${sessionScope.roleByLogin eq \"USER\" }">
						<li><a href='<c:url value="/Controller?command=tocabinetpage" />'>${cabinet}</a></li>
		</c:if>
		
		<c:if
						test="${sessionScope.roleByLogin eq \"ADMIN\" }">
						<li><a href='<c:url value="/Controller?command=tocabinetpage" />'>${cabinet}</a></li>
		</c:if>
		
		<li><a
			href="Controller?command=localization&local=en&page=choosecarview&error=is"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=choosecarview&error=is"><img
				src="images/ru.png" /></a></li>
				
		<c:if
						test="${sessionScope.roleByLogin eq \"USER\" }">
						<li><a><c:out value="${sessionScope.message}" /></a></li>
						<li><a href="Controller?command=logout"><c:out value="${logout}" /></a></li>
		</c:if> 
		<c:if
						test="${sessionScope.roleByLogin eq \"ADMIN\" }">
						<li><a><c:out value="${sessionScope.message}" /></a></li>
						<li><a><c:out value="ADMIN" /></a></li>
						<li><a href="Controller?command=logout"><c:out value="${logout}" /></a></li>
		</c:if> 		
	</ul>
	<!-- end of menu -->


	<p align="center">
		<b><c:out value="${rent}" /></b>
	</p>
	<form action="Controller" method="post">

		<div class="container">
		
		<input type="hidden" name="command" value="lookingcar" />
		<table width="100%">

		<tr >
		    <td width="5%"></td>
			<td width="40%">
                <label><b>${place_first}</b></label> 
				<select name="adressStart">
                   <c:forEach var="item" items="${adr}">
                   <option  value="${item.id}">${item.adressAll}</option>
                   </c:forEach>
                </select>
            </td >
            <td width="5%"></td>
			<td width="40%">
                <label ><b>${place_second}</b></label> 
				<select name="adressEnd">
                   <c:forEach var="item" items="${adr}">
                   <option value="${item.id}">${item.adressAll}</option>
                   </c:forEach>
                </select>
            </td>
			<td width="5%"></td>
		</tr>
		<tr>
		<td width="5%"></td>
			<td width="40%">
                <label for="bodywork"><b>${date_first}</b></label> 
				<input type="text" name="dateStart" value="" placeholder="${date}" /> 
				<c:if test="${sessionScope.notice_date eq \"error_date\" }">
						<label style="color: #f99790" >${date_wrong}</label></br>
					</c:if>
					<c:if test="${sessionScope.notice_date_wait eq \"error_date_wait\" }">
						<label style="color: #f99790" >${date_oder_wrong}</label>
					</c:if>
            </td>
            <td width="5%"></td>
			<td width="40%">
            <label for="transmission"><b>${date_second}</b></label> <input type="text" name="dateEnd" value=""
					placeholder="${date}" /> 
					<c:if test="${sessionScope.notice_date eq \"error_date\" }">
						<label style="color: #f99790" >${date_wrong}</label>
					</c:if>
					
            </td>
			<td width="5%"></td>
		</tr>
		<tr>
		    <td width="5%"></td>
			<td width="40%">
               <label for="power"><b>${time_first}</b></label> 
			   <select name="timeStart">
                   <option  >00:00</option>
                   <option  >01:00</option>
                   <option  >02:00</option>
                   <option  >03:00</option>
                   <option  >04:00</option>
                   <option  >05:00</option>
                   <option  >06:00</option>
                   <option  >07:00</option>
                   <option  >08:00</option>
                   <option  >09:00</option>
                   <option  >10:00</option>
                   <option  >11:00</option>
                   <option  >12:00</option>
                   <option  >13:00</option>
                   <option  >14:00</option>
                   <option  >15:00</option>
                   <option  >16:00</option>
                   <option  >17:00</option>
                   <option  >18:00</option>
                   <option  >19:00</option>
                   <option  >20:00</option>
                   <option  >21:00</option>
                   <option  >22:00</option>
                   <option  >23:00</option>
                </select>
					
            </td>
            <td width="5%"></td>
			<td width="40%">
               <label for="number_of_doors"><b>${time_second}</b></label> 
			   <select name="timeEnd">               
                   <option  >00:00</option>
                   <option  >01:00</option>
                   <option  >02:00</option>
                   <option  >03:00</option>
                   <option  >04:00</option>
                   <option  >05:00</option>
                   <option  >06:00</option>
                   <option  >07:00</option>
                   <option  >08:00</option>
                   <option  >09:00</option>
                   <option  >10:00</option>
                   <option  >11:00</option>
                   <option  >12:00</option>
                   <option  >13:00</option>
                   <option  >14:00</option>
                   <option  >15:00</option>
                   <option  >16:00</option>
                   <option  >17:00</option>
                   <option  >18:00</option>
                   <option  >19:00</option>
                   <option  >20:00</option>
                   <option  >21:00</option>
                   <option  >22:00</option>
                   <option  >23:00</option>
                </select>
            </td>
			<td width="5%"></td>
		</tr>
		<tr>
		    <td width="5%"></td>
			<td colspan="2" style="text-align: left">
            <button type="submit">${look}</button>
            </td>
		</form>	
		</tr>
		<tr>
		    <td width="5%"></td>
			<td colspan="3" style="text-align: left">
            <div class="container" style="background-color: #f1f1f1">
			 <form  action="Controller" method="post">
             <input type="hidden" name="command" value="choosecarview" /> 
             <div class="container" style="background-color:#f1f1f1">
            <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>      
              </div> 
            </form>


		</div>
            </td>
			
		</tr>
	</table>
		
		</div>
		
	<div class="container">
<h5> <ct:today format="yyyy-MM-dd"/></h5></div>

	<!-- footer -->
	<div></div>
	<!-- очищающий блок -->
	<div id="substrate-footer"></div>
	<!-- блок подложка подвала сайта -->
	</div>
	<!-- конец блока PAGE -->

	<div id="footer">
		<!-- подвал сайта -->
		&copy; Pervak Oksana
	</div>
	<!-- end of footer -->
	
	
</body>
</html>