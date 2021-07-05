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

</head>


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
			href="Controller?command=localization&local=en&page=toupdateuserpage"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=toupdateuserpage"><img
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

<form  action="Controller" method="post">
    
    <div class="container">
    <input type="hidden" name="command" value="updateuser" /> 
     <input type="hidden" name="adressId" value="${requestScope.userDetail.user.adress.id}" /> 
     <input type="hidden" name="userId" value="${requestScope.userDetail.user.id}" /> 
     <input type="hidden" name="userDetailId" value="${requestScope.userDetail.id}" /> 
    <input type="hidden" name="role_user" value="${requestScope.userDetail.user.role}" /> 
     <c:if test="${sessionScope.notice_empty eq \"error_empty\" }">
						<label style="color: #f99790" >${empty_wrong}</label></br>
					</c:if>
					
      <label for="login"><b>${login_reg}</b></label>
      <input placeholder="${login_reg}" type="text" name="login" value="${requestScope.userDetail.user.login}" required/>
      
      <label for="name"><b>${name}</b></label>
      <input  placeholder="${name}" type="text" name="name" value="${requestScope.userDetail.user.name}" required/>
      
       <label for="surname"><b>${surname}</b></label>
      <input  placeholder="${surname}" type="text" name="surname"value="${requestScope.userDetail.user.surname}" required/>

      <label for="password"><b>${password}</b></label>
      <input  placeholder=" ${password}" type="password" name="password"value="${requestScope.userDetail.user.password}" required/>
      <c:if test="${sessionScope.notice_password eq \"error_password\" }">
						<label style="color: #f99790" >${rules}</br>
						${rules_one}</br>
						${rules_two}</br>
						${rules_three}</br>
						${rules_four}</br>
						${rules_five}</label></br>
					</c:if>
					
       <label for="surname"><b>${phone}</b></label>
      <input  placeholder="${phone}" type="text" name="phone"value="${requestScope.userDetail.user.phone}" required/>

      <label for="password"><b>${email}</b></label>
      <input  placeholder=" ${email}" type="text" name="email"value="${requestScope.userDetail.user.email}" required/>
      <c:if test="${sessionScope.notice_email eq \"error_email\" }">
						<label style="color: #f99790" >${email_wrong}</label></br>
					</c:if>
					
       <label for="login"><b>${country}</b></label>
      <input placeholder="${country}" type="text" name="country" value="${requestScope.userDetail.user.adress.country}" required/>
      
      <label for="name"><b>${city}</b></label>
      <input  placeholder="${city}" type="text" name="city" value="${requestScope.userDetail.user.adress.city}" required/>
      
       <label for="surname"><b>${street}</b></label>
      <input  placeholder="${street}" type="text" name="street" value="${requestScope.userDetail.user.adress.street}" required/>

      <label for="password"><b>${house}</b></label>
      <input  placeholder=" ${house}" type="text" name="house" value="${requestScope.userDetail.user.adress.house}" required/>
      
      <label for="login"><b>${passwordNumber}</b></label>
      <input placeholder="${passwordNumber}" type="text" name="passwordNumber" value="${requestScope.userDetail.passwordNumber}" required/>
      
      <label for="name"><b>${passwordDate}</b></label>
      <input  placeholder="${date_pattern}" type="text" name="passwordDate" value="${requestScope.userDetail.passwordDate}" required/>
      <c:if test="${sessionScope.notice_date eq \"error_date\" }">
						<label style="color: #f99790" >${date_wrong}</label></br>
					</c:if>
      
       <label for="surname"><b>${drivingLicanseNumber}</b></label>
      <input  placeholder="${drivingLicanseNumber}" type="text" name="drivingLicanseNumber" value="${requestScope.userDetail.drivingLicanseNumber}" required/>

      <label for="password"><b>${drivingLicanseDate}</b></label>
      <input  placeholder=" ${date_pattern}" type="text" name="drivingLicanseDate" value="${requestScope.userDetail.drivingLicanseDate}" required/>
      <c:if test="${sessionScope.notice_date eq \"error_date\" }">
						<label style="color: #f99790" >${date_wrong}</label></br>
					</c:if>
        
      <button type="submit">${next}</button>
      
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
</div>
</body>
</html>