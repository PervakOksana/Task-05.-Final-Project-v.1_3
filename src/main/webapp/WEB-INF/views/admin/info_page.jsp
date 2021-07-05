<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" 	var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en"	var="en_button" />
	<fmt:message bundle="${loc}" key="local.choose.page.rent" var="rent" />
	<fmt:message bundle="${loc}" key="local.header.menu.homepage" var="home" />
    <fmt:message bundle="${loc}" key="local.header.menu.ourcar" var="ourcar" />
    <fmt:message bundle="${loc}" key="local.header.menu.payment" var="payment" />
    <fmt:message bundle="${loc}" key="local.header.menu.contact" var="contact" />
    <fmt:message bundle="${loc}" key="local.header.menu.log" var="login" />
    <fmt:message bundle="${loc}" key="local.header.menu.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.header.menu.cabinet" var="cabinet" />
    <fmt:message bundle="${loc}" key="local.info.page.contact" var="contact" />
    <fmt:message bundle="${loc}" key="local.info.page.payTerm" var="payTerm" />
    <fmt:message bundle="${loc}" key="local.info.page.adress.email" var="adress_email" />
    <fmt:message bundle="${loc}" key="local.info.page.password.email" var="password_email" />
    <fmt:message bundle="${loc}" key="local.info.page.letter.bill" var="letter_bill" />
    <fmt:message bundle="${loc}" key="local.info.page.letter.reminder" var="letter_reminder" />
    <fmt:message bundle="${loc}" key="local.info.page.update" var="update" />
    <fmt:message bundle="${loc}" key="local.info.page.title" var="title" />
    <fmt:message bundle="${loc}" key="local.info.page.cancel" var="cancel" />


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
			href="Controller?command=localization&local=en&page=toinfopage"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=toinfopage"><img
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
	<form  action="Controller" method="post">
    
    <div class="container">
    <input type="hidden" name="command" value="updateinfopage" />
    <input type="hidden" name="applicationInfoId" value="${requestScope.applicationInfo.id}" /> 
     
    
      <label for="brand"><b>${contact}</b></label>
      <input type="text" name="contact" value="${requestScope.applicationInfo.contact}" required/>
      
      <label for="describe_of_brand"><b>${payTerm}</b></label>
      <input type="text" name="payTerm" value="${requestScope.applicationInfo.payTerm}"  required/> 
      
      <label for="bodywork"><b>${adress_email}</b></label>
      <input type="text" name="email" value="${requestScope.applicationInfo.adressEmail}" required/>

      <label for="power"><b>${password_email}</b></label>
      <input type="text" name="password" value="${requestScope.applicationInfo.passwordEmail}" required/>
      
      <label for="transmission"><b>${letter_reminder}</b></label>
      <input type="text" name="letterReminder" value="${requestScope.applicationInfo.letterReminder}" required/>
      
      <label for="number_of_doors"><b>${letter_bill}</b></label>
      <input type="text" name="letterBill" value="${requestScope.applicationInfo.letterBill}" required/>
      
      
        
      <button type="submit">${update}</button>
      
    </div>

    </form>

   <form  action="Controller" method="post">
    <input type="hidden" name="command" value="tocabinetpage" /> 
    <div class="container" style="background-color:#f1f1f1">
      <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>
      
    </div>
    
   
   </form>
    
	
	
	
	<!-- footer -->
<div></div><!-- очищающий блок -->
<div id="substrate-footer"></div><!-- блок подложка подвала сайта -->
</div><!-- конец блока PAGE -->

<div id="footer"><!-- подвал сайта -->
&copy; Pervak Oksana
<!-- end of footer -->


</body>
</html>