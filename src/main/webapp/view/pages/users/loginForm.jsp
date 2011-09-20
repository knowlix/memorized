<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <h2>Вход для зарегистрированных пользователей.</h2>

	<form name="loginForm" class="userForm" method="post" action='<c:url value="/j_spring_security_check" />'>
		<label for="j_username">Логин</label>
		<input type="text" id="j_username" name="j_username" value=""/><br />
		
		<label for="j_password">Пароль</label>
		<input type="password" id="j_password" name="j_password" value=""/><br />
		
		<input type="checkbox" id="remember_me" name="remember_me" checked="checked"/>
		<label for="remember_me">Оставаться в системе</label>
		
		<input type="submit" value="Войти"/>
	</form>