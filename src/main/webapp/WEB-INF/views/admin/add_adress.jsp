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
    <fmt:message bundle="${loc}" key="local.registration.page.country" var="country" />
    <fmt:message bundle="${loc}" key="local.registration.page.city" var="city" />
    <fmt:message bundle="${loc}" key="local.registration.page.street" var="street" />
    <fmt:message bundle="${loc}" key="local.registration.page.house" var="house" />
    <fmt:message bundle="${loc}" key="local.registration.page.submit" var="submit" />
    <fmt:message bundle="${loc}" key="local.choose.page.cancel" var="cancel" />
    
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
			href="Controller?command=localization&local=en&page=torepairbillpage&id=${sessionScope.id}"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=torepairbillpage&id=${sessionScope.id}"><img
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
		<b><c:out value="${create_bill}" /></b>
	</p>

<form  action="Controller" method="post">
  
    <div class="container">
    <input type="hidden" name="command" value="addadress" /> 
    <c:if test="${sessionScope.notice_number eq \"error_number\" }">
			<label style="color: #f99790" >${wrong_date}</label></br>
	</c:if>
    
      <label for="brand"><b>${country}</b></label>
      <input type="text" name="country" value=""  required/>
      
      
      <label for="content"><b>${city}</b></label>
      <input type="text" name="city" value="" maxlength="1000" required/>
      
      <label for="costRepair"><b>${street}</b></label>
      <input type="text" name="street" value="" required/>
      
      <label for="content"><b>${house}</b></label>
      <input type="text" name="house" value="" maxlength="1000" required/>
    

      <button type="submit">${submit}</button>
      
    </div>

    </form>

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