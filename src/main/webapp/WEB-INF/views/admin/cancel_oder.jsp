<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../css/header.jsp" />
<jsp:include page="../css/form_body.jsp" />
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
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.title" var="title" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.number" var="number" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.adress.start" var="adress_start" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.adress.end" var="adress_end" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.date.first" var="date_first" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.date.secend" var="date_secend" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.cost" var="cost" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.user.name" var="user_name" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.user.surname" var="user_surname" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.user.email" var="user_email" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.reason.cancel" var="reason_cancel" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.cancel.oder.page.submit" var="submit" />

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
			href="Controller?command=localization&local=en&page=tocanceloderpage&id=${requestScope.oder.id}"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=tocanceloderpage&id=${requestScope.oder.id}"><img
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
  <form  action="Controller" method="post">
    <p align="center">
		<b><c:out value="${title}" /></b>
	</p>
   
       
      <form  action="Controller" method="post">
    
    <div class="container">
    <input type="hidden" name="command" value="canceloder" /> 
    <input type="hidden" name="id_oder" value="${requestScope.oder.id}" /> 
    
      <label for="cost_hour"><b>${number}:</b></label>
      <input type="text" name="number" value="${requestScope.oder.id}" readonly/>
    	
      <label for="cost_hour"><b>${adress_start}:</b></label>
      <input type="text" name="adressStart" value="${requestScope.oder.adressStart.adressAll}" readonly/>
      
      <label for="cost_dayr"><b>${adress_end}:</b></label>
      <input type="text" name="adressEnd" value="${requestScope.oder.adressEnd.adressAll}"  readonly/>
      
      <label for="sale"><b>${date_first}:</b></label>
      <input type="text" name="dateFirst" value="${requestScope.oder.dateStart}" readonly/>
      
      <label for="cost_hour"><b>${date_secend}:</b></label>
      <input type="text" name="dateSecend" value="${requestScope.oder.dateEnd}" readonly/>
      
      <label for="cost_dayr"><b>${cost}:</b></label>
      <input type="text" name="cost" value="${requestScope.oder.costOder}"  readonly/>
      
      <label for="sale"><b>${user_name}:</b></label>
      <input type="text" name="userName" value="${requestScope.oder.user.name}" readonly/>
        
      <label for="cost_dayr"><b>${user_surname}:</b></label>
      <input type="text" name="userSurname" value="${requestScope.oder.user.surname}"  readonly/>
      
      <label for="sale"><b>${user_email}:</b></label>
      <input type="text" name="userEmail" value="${requestScope.oder.user.email}" readonly/>
      
      <label for="sale"><b>${reason_cancel}:</b></label>
      <input type="text" name="reasonCancel" value="" required/>
        
      <button type="submit">${submit}</button>
      
    </div>
</form>

  <form  action="Controller" method="post">
    <input type="hidden" name="command" value="addrepairbillview" /> 
    <div class="container" style="background-color:#f1f1f1">
      <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>      
    </div>  
   </form>
      
      
     
   
  
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