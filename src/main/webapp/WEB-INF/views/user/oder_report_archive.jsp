<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../css/header.jsp" />
<jsp:include page="../css/form_body.jsp" />
<jsp:include page="../css/table.jsp" />
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
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.new.oders" var="my_oders" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.number" var="my_number" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.adress.start" var="my_adress_start" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.adress.end" var="my_adress_end" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.date.first" var="my_date_first" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.date.secend" var="my_date_secend" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.time.secend" var="my_time_secend" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.time.first" var="my_time_first" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.brand" var="my_brand" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.cost" var="my_cost" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.cancel" var="my_cancel" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.archive" var="my_archive" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.status" var="my_status" />

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
			href="Controller?command=localization&local=en&page=reportoderbyuser&take=archive"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=reportoderbyuser&take=archive"><img
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
		<b><c:out value="${my_oders}" /></b></br>
		<c:out value="${my_archive}" />
	</p>
	 
     
    
	<div >

		<div >

<div style="text-align: center;">
         		<table style="display: inline-block;">
				<tr>

				    <th>${my_number}</th>
					<th>${my_adress_start}</th>
					<th>${my_adress_start}</th>
					<th>${my_date_first}</th>					
					<th>${my_date_secend}</th>
					<th>${my_brand}</th>					
					<th>${my_cost}</th>
					<th>${my_status}</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="oder" items="${requestScope.oders}">

					

					

					<tr>
					    <td>${oder.id}</td>
					    <td>${oder.adressStart.adressAll}</td>
					    <td>${oder.adressEnd.adressAll}</td>
					    <td>${oder.dateStart}</td>
					    <td>${oder.dateEnd}</td>				   
					    <td>${oder.car.brand}</td>
					    <td>${oder.costOder}</td>					
						<c:if test="${oder.statusOder eq \"0\" }"><td>don't pay</td></c:if>
						<c:if test="${oder.statusOder eq \"1\" }"><td>paid</td></c:if> 
						<c:if test="${oder.statusOder eq \"2\" }"><td>canceled</td></c:if>
						<c:if test="${oder.statusOder eq \"3\" }"><td>money returned</td></c:if>  
                        <c:if test="${oder.statusOder eq \"4\" }"><td>cancel by admin</td></c:if> 
                        <c:if test="${oder.statusOder eq \"5\" }"><td>completed</td></c:if>
					</tr>

				</c:forEach>

			</table>
</div>
		</div>

	</div>

	<!-- page -->

	<div class="pagination">
		<c:if test="${currentPage != 1}">
			<a href="Controller?command=reportoderbyuser&take=archive&viewrecordsPerPage=${recordsPerPage}&tPage=${currentPage-1}">&laquo;</a>
		</c:if>

		<c:forEach begin="1" end="${noOfPages}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<a> ${i}</a>
				</c:when>
				<c:otherwise>
					<a href="Controller?command=reportoderbyuser&take=archive&recordsPerPage=${recordsPerPage}&tPage=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${currentPage lt noOfPages}">
			<a href="Controller?command=reportoderbyuser&take=archive&recordsPerPage=${recordsPerPage}&tPage=${currentPage+1}">&raquo;</a>
		</c:if>
	</div>
	
	<!-- end of page -->
<form  action="Controller" method="post">
 <div class="container" style="background-color:#f1f1f1">
      <input type="hidden" name="command" value="reportoderbyuser" />
      <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${my_cancel}</button>
      
    </div>
</form>

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