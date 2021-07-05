<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../css/header.jsp" />
<jsp:include page="../css/form_body.jsp" />
<!DOCTYPE html>
<html>

<meta charset="UTF-8">
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
    <fmt:message bundle="${loc}" key="local.registration.page.login" var="login_reg" />
    <fmt:message bundle="${loc}" key="local.registration.page.name" var="name" />
    <fmt:message bundle="${loc}" key="local.registration.page.surname" var="surname" />
    <fmt:message bundle="${loc}" key="local.registration.page.password" var="password" />
    <fmt:message bundle="${loc}" key="local.registration.page.submit" var="next" />
    <fmt:message bundle="${loc}" key="local.registration.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.registration.page.phone" var="phone" />
    <fmt:message bundle="${loc}" key="local.registration.page.email" var="email" />
    <fmt:message bundle="${loc}" key="local.registration.page.country" var="country" />
    <fmt:message bundle="${loc}" key="local.registration.page.city" var="city" />
    <fmt:message bundle="${loc}" key="local.registration.page.street" var="street" />
    <fmt:message bundle="${loc}" key="local.registration.page.house" var="house" />
    <fmt:message bundle="${loc}" key="local.registration.page.passwordNumber" var="passwordNumber" />
    <fmt:message bundle="${loc}" key="local.registration.page.passwordDate" var="passwordDate" />
    <fmt:message bundle="${loc}" key="local.registration.page.drivingLicanseNumber" var="drivingLicanseNumber" />
    <fmt:message bundle="${loc}" key="local.registration.page.drivingLicanseDate" var="drivingLicanseDate" />
    <fmt:message bundle="${loc}" key="local.registration.page.pattern.date" var="date_pattern" />
    <fmt:message bundle="${loc}" key="local.registration.page.pattern.date.wrong" var="date_wrong" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules" var="rules" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules.one" var="rules_one" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules.two" var="rules_two" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules.three" var="rules_three" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules.four" var="rules_four" />
    <fmt:message bundle="${loc}" key="local.registration.page.rules.five" var="rules_five" />
    <fmt:message bundle="${loc}" key="local.registration.page.pattern.email.wrong" var="email_wrong" />
    <fmt:message bundle="${loc}" key="local.registration.page.pattern.empty.wrong" var="empty_wrong" />
    <fmt:message bundle="${loc}" key="local.pay.page.number" var="number" />
    <fmt:message bundle="${loc}" key="local.pay.page.name" var="name" />
    <fmt:message bundle="${loc}" key="local.pay.page.validity" var="validity" />
    <fmt:message bundle="${loc}" key="local.pay.page.cvv" var="cvv" />
    <fmt:message bundle="${loc}" key="local.pay.page.detail" var="detail" />
    <fmt:message bundle="${loc}" key="local.pay.page.pattern.date" var="date" />
    <fmt:message bundle="${loc}" key="local.pay.page.amount" var="amount" />
    <fmt:message bundle="${loc}" key="local.pay.page.pay.card" var="pay_card" />
    <fmt:message bundle="${loc}" key="local.pay.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.pay.page.pay" var="pay" />
</head>


</head>
<body>

<!-- menu -->
	<ul id="menu">
		<li><a href="Controller?command=choosecarview"><c:out value="${home}" /></a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>${ourcar}</a></li>
		<li><a href="Controller?command=topaymaentcontactpage&take=is">${payment}</a></li>		
		<li><a href="Controller?command=topaymaentcontactpage&take=not">${contact}</a></li>
		<li><a href='<c:url value="/Controller?command=tologinationpage" />'>${login}</a></li>
		<li><a
			href="Controller?command=localization&local=en&page=registration"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=registration"><img
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
		<b><c:out value="${detail}" /></b>
	</p>
      <input type="hidden" name="command" value="payforrepairbill" />
      <input type="hidden" name="id_repair" value="${requestScope.id_repair}" />
      <label ><b>${number}</b></label>
      <input type="text" placeholder="${number} " name="number" required>

      <label><b>${name}</b></label>
      <input type="text" placeholder="${name}" name="name" required>
      
      <label><b>${validity}</b></label>
      <input type="text" placeholder="${date}" name="validity" required>
      
      <label><b>${cvv}</b></label>
      <input type="password" placeholder="${cvv}" name="cvv" required>
        
      <button type="submit">${pay}</button>
      
    </form>

    
    <form  action="Controller" method="post">
    <input type="hidden" name="command" value="payforrepairbill" /> 
    <div class="container" style="background-color:#f1f1f1">
      <button type="submit" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">${cancel}</button>
      
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