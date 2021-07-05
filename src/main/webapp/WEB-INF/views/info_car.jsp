<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="css/header.jsp" />
<jsp:include page="css/form_body.jsp" />
<html>

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
    <fmt:message bundle="${loc}" key="local.all.car.page.cost.day" var="cost_day" />
    <fmt:message bundle="${loc}" key="local.all.car.page.cost.hour" var="cost_hour" />
    <fmt:message bundle="${loc}" key="local.all.car.page.sale" var="sale" />
    <fmt:message bundle="${loc}" key="local.addcar.page.bodywork" var="bodywork" />
    <fmt:message bundle="${loc}" key="local.addcar.page.power" var="power" />
    <fmt:message bundle="${loc}" key="local.addcar.page.transmission" var="transmission" />
    <fmt:message bundle="${loc}" key="local.addcar.page.number.doors" var="number_doors" />
    <fmt:message bundle="${loc}" key="local.addcar.page.year" var="year" />
  
<body>

	<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="Controller?command=topaymaentcontactpage&take=is">${payment}</a></li>		
		<li><a href="Controller?command=topaymaentcontactpage&take=not">${contact}</a></li>
		<li><a href='<c:url value="/Controller?command=tologinationpage" />'>${login}</a></li>
		<li><a
			href="Controller?command=localization&local=en&page=infocar&id=${requestScope.carInfo.car.id}"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=infocar&id=${requestScope.carInfo.car.id}"><img
				src="images/ru.png" /></a></li>
	</ul>
	<!-- end of menu -->
	

		<table width="100%">
			<tr>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="10%"><img src="${requestScope.carInfo.car.photoByte}" width="130"
					height="130" /></td>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="80%"><b><c:out value="${requestScope.carInfo.car.brand}" /></b><br />
					 <c:out value="${requestScope.carInfo.car.describeBrand}" /> <br />				
					 <div>${bodywork}: ${requestScope.carInfo.car.bodywork}</div>
					  <div>${power}: ${requestScope.carInfo.car.power}</div>
					  <div>${transmission}: ${requestScope.carInfo.car.transmission}</div>
					   <div>${number_doors}: ${requestScope.carInfo.car.numberDoors}</div>
					   <div>${year}: ${requestScope.carInfo.car.year}</div>
					<div>${cost_day}: ${requestScope.carInfo.costDayr}</br>  ${cost_hour}: ${requestScope.carInfo.costHour}</div>				 
				<div style="color: #f99790">${sale}: ${requestScope.carInfo.sale}%</div>	 
					
					</td>
				
				<td><br /></td>
				<td><br /></td>
			</tr>

		</table>

	
	
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