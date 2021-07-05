<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.ArrayList,java.util.List,by.htp.jwd.bean.Car"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="css/header.jsp" />
<html>


<body>
	<!-- menu -->
	<ul id="menu">
		<li><a href="#">HOMEPAGE</a></li>
		<li><a href='<c:url value="/Controller?command=choosecarview" />'>SERVICES</a></li>
		<li><a href='<c:url value="/Controller?command=allcarsview" />'>OUR CARS</a></li>
		<li><a href="#">PAYMENT TERMS</a></li>
		<li><a href="#">CONTACT US</a></li>
		<li><a href='<c:url value="/Controller?command=addnewcarview" />'>UPLOAD</a></li>
		<li><a href='<c:url value="/Controller?command=tologinationpage" />'>LOG
				IN</a></li>
		<li><a
			href="Controller?command=Localization&lang=en&page=gotoaddnewspage"><img
				src="images/eng.png" /></a></li>
		<li><a
			href="Controller?command=Localization&lang=en&page=gotoaddnewspage"><img
				src="images/ru.png" /></a></li>
	</ul>
	<!-- end of menu -->
	
<!-- column left -->	
<div class="column left" style="background-color: #fafafa">
	
<c:forEach var="n" items="${requestScope.cars}">

		<table width="100%">
			<tr>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="10%"><img src="${n.car.photoPath}" width="130" height="130" /></td>
				<td><br /></td>
				<td><br /></td>
				<td><br /></td>
				<td width="75%"><b><c:out value="${n.car.brand}" /></b><br /> <c:out
						value="${n.car.describe_of_brand}" /> <br />
					</td>
				<td width="15%">
				<h2><b>${n.cost_hour} &#36</b></h2>
				
				</td>
				<td><br /></td>
				<td><br /></td>
			</tr>

		</table>

	</c:forEach>
</div>
<!-- end of column left -->


<br/>
 <form  action="Controller" method="post">
    
      <input type="hidden" name="command" value="logination" />
      <label for="login"><b>Имя пользователя</b></label>
      <input type="text" placeholder="Введите имя пользователя" name="login" required>
      <br />
      <label for="password"><b>Пароль</b></label><br />
      <input type="password" placeholder=" Ведите пароль" name="password" required>
        
      <button type="submit">Пользователь</button>
      
    

    
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Отменить</button>
      <span class="psw">Забыли <a href="#">пароль?</a></span></br>
      <a href='<c:url value="/Controller?command=registration" />'>Registration</a></span>
    
    
   
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