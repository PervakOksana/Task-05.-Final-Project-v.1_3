<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../css/header.jsp" />
<jsp:include page="../css/form_body.jsp" />
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
    <fmt:message bundle="${loc}" key="local.cabinet.page.my.profile" var="profile" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.my.bill" var="bill" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.my.order" var="order" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.profile" var="profile" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.car" var="car" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.order" var="order_admin" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.repair" var="repair" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.user" var="user" />
    <fmt:message bundle="${loc}" key="local.cabinet.page.parking" var="parking" />


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
			href="Controller?command=localization&local=en&page=tocabinetpage"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=localization&local=ru&page=tocabinetpage"><img
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

<c:out value="${requestScope.mes}" />
    <!-- admin cabinet -->
         <c:if test="${sessionScope.roleByLogin eq \"ADMIN\" }">
        
				<div style="text-align: center;">
         		<table style="display: inline-block;">				
				 <tr>
					    <td>
					  <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="reportallcar" />         
                        <button type="submit">${car}</button>  
                        </form>
                        </td>
					    <td>
					    <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="addrepairbillview" />         
                        <button type="submit">${order_admin}</button>  
                        </form>
                        </td>
					    <td> 
					    <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="reportrepairbill" />         
                        <button type="submit">${repair}</button>  
                        </form>
                        </td>
                        					    
					  </tr>
					  
					  <tr>
					    <td>
					   <form  action="Controller" method="post">   
                       <input type="hidden" name="command" value="toinfopage" />         
                       <button type="submit">${profile} </button>  
                       </form>
                        </td>
					    <td>
					    <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="registration" />
                         <input type="hidden" name="take" value="is" />         
                        <button type="submit">${user}</button>  
                        </form>
                        </td>
					    <td> 
					    <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="toaddadresspage" />         
                        <button type="submit">${parking}</button>  
                        </form>
                        </td>
                        					    
					  </tr>
					  </table>
					  </div>
				
				
				
				
						
		</c:if> 
		
		<c:if test="${sessionScope.roleByLogin eq \"USER\" }">
         		<div style="text-align: center;">
         		<table style="display: inline-block;">				
				 <tr>
					    <td>
					   <form  action="Controller" method="post">   
                       <input type="hidden" name="command" value="toupdateuserpage" />         
                       <button type="submit">${profile}</button>  
                       </form>
                        </td>
					    <td>
					    <form  action="Controller" method="post">   
                        <input type="hidden" name="command" value="reportrepairbyuser" />         
                        <button type="submit">${bill}</button>  
                        </form>
                        </td>
					    <td> <form  action="Controller" method="post">   
                             <input type="hidden" name="command" value="reportoderbyuser" />         
                             <button type="submit">${order}</button>  
                             </form>
                        </td>					    
					  </tr>
					  </table>
					  </div>
		</c:if> 
	<!-- end of admin cabinet -->
	
	
	
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