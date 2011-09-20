<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url value="/logout" var="logoutLink"/>
<c:url value="/login" var="loginLink"/>
<c:url value="/registration" var="registerLink"/>


<div class="user_bar right">
	<sec:authorize access="fullyAuthenticated">
		<p>
			<span>вы авторизованы, как</span>
			<a href="#" title="управление профилем пользователя">${ pageContext.request.userPrincipal.name }</a>
		</p>
		<a class="logout" href="${ logoutLink }" title="завершить сеанс работы с системой"><img src="<c:url value='/static/img/logout.png'/>" alt="Выйти" title="Завершить сеанс"/></a>		
	</sec:authorize>
	<sec:authorize access="not fullyAuthenticated">
		<p>
			<span>вы не авторизованы</span>
			<a href="${ registerLink }" title="перейти на страницу регистрации нового пользователя">Зарегистрироваться</a>
		</p>			
		<a class="logout" href="${ loginLink }" title="перейти на страницу авторизации"><img src="<c:url value='/static/img/logout.png'/>" alt="Войти" title="Войти"/></a>		
	</sec:authorize>
</div>
