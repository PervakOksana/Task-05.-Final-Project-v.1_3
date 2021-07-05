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
    <fmt:message bundle="${loc}" key="local.addcar.page.brand" var="brand" />
    <fmt:message bundle="${loc}" key="local.addcar.page.describe.brand" var="describe_brand" />
    <fmt:message bundle="${loc}" key="local.addcar.page.bodywork" var="bodywork" />
    <fmt:message bundle="${loc}" key="local.addcar.page.power" var="power" />
    <fmt:message bundle="${loc}" key="local.addcar.page.transmission" var="transmission" />
    <fmt:message bundle="${loc}" key="local.addcar.page.number.doors" var="number_doors" />
    <fmt:message bundle="${loc}" key="local.addcar.page.year" var="year" />
    <fmt:message bundle="${loc}" key="local.addcar.page.photo" var="photo" />
    <fmt:message bundle="${loc}" key="local.addcar.page.next" var="next" />
    <fmt:message bundle="${loc}" key="local.addcar.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.header.menu.cabinet" var="cabinet" />
    <fmt:message bundle="${loc}" key="local.addprice.page.cost.hour" var="cost_hour" />
    <fmt:message bundle="${loc}" key="local.addprice.page.cost.day" var="cost_day" />
    <fmt:message bundle="${loc}" key="local.addprice.page.sale" var="sale" />
    <fmt:message bundle="${loc}" key="local.addprice.page.save" var="save" />
    <fmt:message bundle="${loc}" key="local.addprice.page.cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="local.addcar.page.parking" var="parking" />
    <fmt:message bundle="${loc}" key="local.addprice.page.wrong" var="wrong" />
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
			href="Controller?command=localization&local=en&page=toupdatecarpage&id=${requestScope.priceList.car.id}"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=toupdatecarpage&id=${requestScope.priceList.car.id}"><img
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
    
    <div class="container">
    <input type="hidden" name="command" value="updatecar" />
    <input type="hidden" name="carId" value="${requestScope.priceList.car.id}" /> 
    <input type="hidden" name="priceListId" value="${requestScope.priceList.id}" />  
    
      <label for="brand"><b>${brand}</b></label>
      <input type="text" name="brand" value="${requestScope.priceList.car.brand}" required/>
      
      <label for="describe_of_brand"><b>${describe_brand}</b></label>
      <input type="text" name="describe_of_brand" value="${requestScope.priceList.car.describeBrand}"  required/> 
      
      <label for="bodywork"><b>${bodywork}</b></label>
      <input type="text" name="bodywork" value="${requestScope.priceList.car.bodywork}" required/>

      <label for="power"><b>${power}</b></label>
      <input type="text" name="power" value="${requestScope.priceList.car.power}" required/>
      
      <label for="transmission"><b>${transmission}</b></label>
      <input type="text" name="transmission" value="${requestScope.priceList.car.transmission}" required/>
      
      <label for="number_of_doors"><b>${number_doors}</b></label>
      <input type="text" name="number_of_doors" value="${requestScope.priceList.car.numberDoors}" required/>
      
      <label for="year"><b>${year}</b></label>
      <input type="text" name="year" value="${requestScope.priceList.car.year}" required/>
      
      <label for="photoPath"><b>${photo}</b></label>
      <input type="file" name="photoPath"value="${requestScope.priceList.car.photoPath}" required/></br>
      
       <label><b>${parking}</b></label> 
				<select name="adressCar">
                   <c:forEach var="item" items="${adr}">
                   <option  value="${item.id}">${item.adressAll}</option>
                   </c:forEach>
                </select>
                
                <c:if test="${sessionScope.notice_number eq \"error_number\" }">
			<label style="color: #f99790" >${wrong}</label></br>
	</c:if>
        
      <label for="cost_hour"><b>${cost_hour}</b></label>
      <input type="text" name="cost_hour" value="${requestScope.priceList.costHour}" required/> 
      
      <label for="cost_dayr"><b>${cost_day}</b></label>
      <input type="text" name="cost_dayr" value="${requestScope.priceList.costDayr}"  required/>
      
      <label for="sale"><b>${sale}</b></label>
      <input type="text" name="sale" value="${requestScope.priceList.sale}" required/> 
        
      <button type="submit">${save}</button>
      
    </div>

    </form>

   <form  action="Controller" method="post">
    <input type="hidden" name="command" value="reportallcar" /> 
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