<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <fmt:message bundle="${loc}" key="local.looking.page.more" var="more" />
    <fmt:message bundle="${loc}" key="local.looking.page.pay" var="pay" />
    <fmt:message bundle="${loc}" key="local.looking.page.notcar" var="notcar" />
    
</head>
<body>

<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="#">${payment}</a></li>
		<li><a href="#">${contact}</a></li>
		<li><a href="#">${sessionScope.userIdLogin}</a></li>
		
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
			href="Controller?command=localization&local=en&page=lookingcar"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=lookingcar"><img
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
	
	
				
			<c:if test="${sessionScope.carIs eq \"carNot\" }">
						<label style="color: #f99790" >${notcar}</label>
					</c:if>
<c:forEach  var="n" items="${requestScope.cars}">

		<table width="100%">
			<tr>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="10%"><img src="${n.car.photoByte}" width="130"
					height="130" /></td>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="80%"><b><c:out value="${n.car.brand}" /></b><br />
					 <c:out value="${n.car.describeBrand}" /> <br />
					<li><a href="Controller?command=infocar&id=${n.car.id}">${more}</a></li>
					
			        <c:choose>
                              <c:when test="${sessionScope.isUser eq \"user\"}">
                              <li><a href="Controller?command=topaypage&id=${n.car.id}">${pay}</a></li>
                              </c:when>    
                              <c:otherwise>
                              <li><a href="Controller?command=tologinationpage">${pay}</a></li>
                              </c:otherwise>
                    </c:choose>
					</td>
				<td width="10%">
					<h2>
						<b>${n.costHour} </b></br>
					</h2>
					<h2>
						<b style="color: #f99790">-${n.sale}% </b>
					</h2>
                   
				</td>
				<td><br /></td>
				<td><br /></td>
			</tr>

		</table>

	</c:forEach>
	
	
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