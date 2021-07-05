<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="css/header.jsp" />
<jsp:include page="css/form_body.jsp" />
<!DOCTYPE html>
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
    <fmt:message bundle="${loc}" key="local.logination.page.login" var="login_button" />
    <fmt:message bundle="${loc}" key="local.logination.page.password" var="password" />
    <fmt:message bundle="${loc}" key="local.logination.page.submit" var="submit" />
    <fmt:message bundle="${loc}" key="local.logination.page.forgot" var="forgot" />
    <fmt:message bundle="${loc}" key="local.logination.page.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.logination.page.cancel" var="cancel" />
    
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="Controller?command=topaymaentcontactpage&take=is">${payment}</a></li>		
		<li><a href="Controller?command=topaymaentcontactpage&take=not">${contact}</a></li>
		<li><a href='<c:url value="/Controller?command=tologinationpage" />'>${login}</a></li>
		<li><a href="Controller?command=localization&local=en&page=tologinationpage"><img	src="images/eng.png" /></a></li>
		<li><a href="Controller?command=localization&local=ru&page=tologinationpage"><img	src="images/ru.png" /></a></li>
	</ul>
	<!-- end of menu -->

<div id="content">
 <div class="container">
  <form  action="Controller" method="post">
    
      <input type="hidden" name="command" value="logination" />
      <label for="uname"><b>${login_button}</b></label>
      <input type="text" placeholder="${login_button} " name="login" required>

      <label for="psw"><b>${password}</b></label>
      <input type="password" placeholder="${password}" name="password" required>
        
      <button type="submit">${submit}</button>
      
    

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>
      <span class="psw"><a href="Controller?command=toforgotpasswordpage">${forgot}</a></span></br>
      <a href='<c:url value="/Controller?command=registration" />'>${registration}</a></span>
    </div>
    
   
  </form>
</div>
</div> 

<!-- footer -->
<div></div><!-- очищающий блок -->
<div id="substrate-footer"></div><!-- блок подложка подвала сайта -->
</div><!-- конец блока PAGE -->

<div id="footer"><!-- подвал сайта -->
&copy; Pervak Oksana
</div>

</body>
</html>