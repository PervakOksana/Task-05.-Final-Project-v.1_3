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
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.number" var="number" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.content" var="content" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.cost" var="cost" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.number.oder" var="number_oder" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.car.brand" var="car_brand" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.oder.dateStart" var="oder_dateStart" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.oder.dateEnd" var="oder_dateEnd" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.oder.costOder" var="oder_costOder" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.oder.title" var="title" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.pay" var="pay" />
    <fmt:message bundle="${loc}" key="local.report.repair.page.user.date" var="date" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.cancel" var="my_cancel" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.archive" var="archive" />
     <fmt:message bundle="${loc}" key="local.report.repair.page.user.status" var="status" />

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
			href="Controller?command=localization&local=en&page=reportrepairbyuser&take=archive"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=reportrepairbyuser&take=archive"><img
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
		<b><c:out value="${title}" /></b>
	</p>
	
	<div >

		<div >

<div style="text-align: center;">
         		<table style="display: inline-block;">
				<tr>
			
				    <th>${number}</th>
				    <th>${date}</th>
					<th>${content}</th>
					<th>${number_oder}</th>					
					<th>${car_brand}</th>
					<th>${oder_dateStart}</th>
					<th>${oder_dateEnd}</th>
					<th>${cost}</th>					
					<th>${status}</th>
					
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="repairBill" items="${requestScope.repairBill}">

					

					

					<tr>
					    <td>${repairBill.id}</td>
					    <td>${repairBill.dateRepair}</td>
					    <td>${repairBill.content}</td>					    
					    <td>${repairBill.oder.id}</td>
					    <td>${repairBill.oder.car.brand}</td>
					    <td>${repairBill.oder.dateStart}</td>
					    <td>${repairBill.oder.dateEnd}</td>
				        <td>${repairBill.costRepair}</td>	
						<c:if test="${repairBill.statusRepairBill eq \"1\" }"><td>paid</td></c:if>
						

					</tr>

				</c:forEach>

			</table>
</div>
		</div>

	</div>

	<!-- page -->

	<div class="pagination">
		<c:if test="${currentPage != 1}">
			<a href="Controller?command=reportrepairbyuser&take=archive&viewrecordsPerPage=${recordsPerPage}&tPage=${currentPage-1}">&laquo;</a>
		</c:if>

		<c:forEach begin="1" end="${noOfPages}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<a> ${i}</a>
				</c:when>
				<c:otherwise>
					<a href="Controller?command=reportrepairbyuser&take=archive&recordsPerPage=${recordsPerPage}&tPage=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${currentPage lt noOfPages}">
			<a href="Controller?command=reportrepairbyuser&take=archive&recordsPerPage=${recordsPerPage}&tPage=${currentPage+1}">&raquo;</a>
		</c:if>
	</div>
	
	<!-- end of page -->
<form  action="Controller" method="post"> 
    <div class="container" style="background-color:#f1f1f1">
      <input type="hidden" name="command" value="reportrepairbyuser" />
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