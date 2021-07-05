<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="css/header.jsp" />
<jsp:include page="css/form_body.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
    <fmt:message bundle="${loc}" key="local.pay.page.number" var="number" />
    <fmt:message bundle="${loc}" key="local.pay.page.name" var="name" />
    <fmt:message bundle="${loc}" key="local.pay.page.validity" var="validity" />
    <fmt:message bundle="${loc}" key="local.pay.page.cvv" var="cvv" />
    <fmt:message bundle="${loc}" key="local.pay.page.detail" var="detail" />
    <fmt:message bundle="${loc}" key="local.pay.page.pattern.date" var="date" />
    <fmt:message bundle="${loc}" key="local.pay.page.place.first" var="place_first" />
    <fmt:message bundle="${loc}" key="local.pay.page.place.secend" var="place_secend" />
    <fmt:message bundle="${loc}" key="local.pay.page.date.first" var="date_first" />
    <fmt:message bundle="${loc}" key="local.pay.page.date.secend" var="date_secend" />
    <fmt:message bundle="${loc}" key="local.pay.page.amount" var="amount" />
    <fmt:message bundle="${loc}" key="local.pay.page.pay.card" var="pay_card" />
    <fmt:message bundle="${loc}" key="local.pay.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.pay.page.pay" var="pay" />
    <fmt:message bundle="${loc}" key="local.pay.page.pay.done" var="pay_done" />

</head>
<body>


<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="Controller?command=topaymaentcontactpage&take=is">${payment}</a></li>		
		<li><a href="Controller?command=topaymaentcontactpage&take=not">${contact}</a></li>
		<li><a href="#">${sessionScope.role}</a></li>
		
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
			href="Controller?command=localization&local=en&page=addoder&take=is"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=addoder&take=is"><img
				src="images/ru.png" /></a></li>
				
		<c:if
						test="${sessionScope.roleByLogin eq \"USER\" }">
						<li><a><c:out value="${sessionScope.message}" /></a></li>
						<li><a href="Controller?command=logout"><c:out value="${logout}" /></a></li>
		</c:if> 
		<c:if
						test="${sessionScope.roleByLogin eq \"ADMIN\" }">
						<li><a><c:out value="${sessionScope.message}" /></a></li>
						<li><a href="Controller?command=logout"><c:out value="${logout}" /></a></li>
		</c:if> 		
	</ul>
	<!-- end of menu -->
	
<div id="content">
 <div class="container">
        <b><c:out value="${pay_done}" /></b></br>
		<b><c:out value="${detail}" /></b></br>
    
      <label ><b>${place_first}: </b></label>
      <label >${sessionScope.oderInfo.adressStart}</label></br>
      
      <label ><b>${place_secend}: </b></label>
      <label >${sessionScope.oderInfo.adressEnd}</label></br>
      
      <label ><b>${date_first}:</b></label>
      <label >${sessionScope.oderInfo.dateStart} </label></br>
      
      <label ><b>${date_secend}: </b></label>
      <label >${sessionScope.oderInfo.dateEnd} </label></br></br>
      
      <label ><b>${amount}:</b></label>
      <label >${sessionScope.oderInfo.costOder}</label></br></br>
      
      
     
   
  
</div>
</div>
	
	
	
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