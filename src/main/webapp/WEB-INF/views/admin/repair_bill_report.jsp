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
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.oder.number" var="oder_number" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.car" var="oder_car" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.start" var="date_start" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.end" var="date_end" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.cost" var="cost" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.surname" var="surname" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.name" var="name" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.email" var="email" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.bill" var="bill" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.create" var="create" />
    <fmt:message bundle="${loc}" key="local.to.repair.bill.page.date.phone" var="phone" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.number" var="bill_number" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.date" var="bill_date" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.content" var="bill_content" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.cost" var="bill_cost" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.cost.repair.bill" var="repair_bill" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.status" var="status" />
    <fmt:message bundle="${loc}" key="local.repair.bill.report.page.not.paid" var="not_paid" />
    <fmt:message bundle="${loc}" key="local.report.new.oder.page.user.cancel" var="cancel" />

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
			href="Controller?command=localization&local=en&page=reportrepairbill"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=reportrepairbill"><img
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

<form  action="Controller" method="post">   
      <input type="hidden" name="command" value="reportrepairbill" /> 
      <input type="hidden" name="take" value="onlynotpay" />          
      <button type="submit">${not_paid}</button>  
  </form>
  
<p align="center">
		<b><c:out value="${repair_bill}" /></b>
	</p>
	
	<div >

		<div >

<div style="text-align: center;">

         		<table style="display: inline-block;">
				<tr>
				    <th>${bill_number}</th>
					<th>${bill_date}</th>
					<th>${bill_content}</th>
					<th>${bill_cost}</th>					
					<th>${oder_number}</th>
					<th>${oder_car}</th>
					<th>${date_start}</th>
					<th>${date_end}</th>
					<th>${cost}</th>
					<th>${status}</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="repairBill" items="${requestScope.repairBills}">

					

					

					<tr>
					    <td>${repairBill.id}</td>
					    <td>${repairBill.dateRepair}</td>
					    <td>${repairBill.content}</td>
					    <td>${repairBill.costRepair}</td>
					    <td>${repairBill.oder.id}</td>
					    <td>${repairBill.oder.car.brand}</td>
						<td>${repairBill.oder.dateStart}</td>
						<td>${repairBill.oder.dateEnd}</td>
						<td>${repairBill.oder.costOder}</td>
                        <c:if test="${repairBill.statusRepairBill eq \"0\" }"><td>don't pay</td></c:if>
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
			<a href="Controller?command=reportrepairbill&viewrecordsPerPage=${recordsPerPage}&tPage=${currentPage-1}">&laquo;</a>
		</c:if>

		<c:forEach begin="1" end="${noOfPages}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<a> ${i}</a>
				</c:when>
				<c:otherwise>
					<a href="Controller?command=reportrepairbill&recordsPerPage=${recordsPerPage}&tPage=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${currentPage lt noOfPages}">
			<a href="Controller?command=reportrepairbill&recordsPerPage=${recordsPerPage}&tPage=${currentPage+1}">&raquo;</a>
		</c:if>
	</div>
	
	<!-- end of page -->

            <form  action="Controller" method="post">
             <input type="hidden" name="command" value="tocabinetpage" /> 
             <div class="container" style="background-color:#f1f1f1">
             <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>      
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