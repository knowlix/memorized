<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/registration" var="registration_link"/>

<h2>Регистрация нового пользователя.</h2>

<form:form id="form_user_add" class="userForm" method="POST" commandName="userInfo">
	<label for="username">Логин</label>
	<input type="text" name="username"/>
	<label for="password">Пароль</label>
	<input type="password" name="password"/>
    <label for="email">Эл. почта</label>
    <form:input path="email"/>
	<input type="submit" value="Зарегистрировать"/>	
</form:form>