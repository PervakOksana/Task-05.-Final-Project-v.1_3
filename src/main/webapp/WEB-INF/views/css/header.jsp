<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<style>
html,body {
    margin:0;
    padding:0;
    text-align:center;
    height:100%
}
#page {
    text-align:left;
    margin:0 auto;
    min-height:100%;
    
}
#page h3 {text-align:center; margin:0}
#header {
    background-image: url(images/header.jpg);
    height:150px;
    margin:0 0 3px
	position: fixed;
	background-size: cover;
	background-position: 0px 0px;
	width: 100%;
	object-fit: cover;
}
#left {
    background-color:#C93;
    float:left;
    height:100px;
    width:180px;
}
#right {
    background-color:#C93;
    float:right;
    height:100px;
    width:180px;
}
#content {
    margin:0 180px;
    padding:10px
}
.clear {
    clear:both;
    height:0px;
}
#substrate-footer {
    height:100px;
    margin:20px 0 0
}
#footer {
    background: #333;
	color: #fff; /* Цвет текста футера */
	clear: left;
    height:20px;
    margin:-20px auto 0;
   
	font-family: Segoe UI Light;
}
#menu, #menu li {
	margin: 0;
	padding: 0;
}

#menu {
	text-align: right; /* пункты и текст горизонтально по центру */
	background: #323232;
}

#menu li {
	display: inline-block;
}

#menu a {
	display: block;
	padding: 5px 8px;
	color: #fff;
	font-size: 120%;
	text-decoration: none;
	font-family: Segoe UI Light;
}

#menu a:hover {
	background: #8AB8CC;
}

h1 {
	font-size: 55px; /* Размер шрифта заголовка шапки */
	margin: 0; /* Убираем отступы */
	color: white; /* Цвет текста заголовка шапки */
	font-family: Segoe UI Black;
}

h2 {
	margin-top: 0; /* Убираем отступ сверху */
}

h4 {
	font-family: Cambria;
	color: white;
}
.column {
	float: left;
	padding: 10px;
	height: 600px;
}

.left {
	width: 73%;
}

.right {
	width: 27%;
}

/* Поля ввода полной ширины */
input[type=text], input[type=password] {
  width: 20%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

.container{
  padding: 16px;
}

button {
  background-color: #8AB8CC;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 20%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f99790;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="page"><!-- Основной блок PAGE -->
<div id="header"><h1>Car for you</h1>
		</br>
		<h4>"The world is a book and those who do not travel read only
			one page." - St. Augustine</h4></div>




</body>
</html>