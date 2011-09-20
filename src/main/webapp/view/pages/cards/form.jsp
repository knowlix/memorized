<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form id="form_card_add" method="POST" action='/memorized/cards/add' commandName="card">
	<label for="title">Название карты</label>
	<form:input path="title"/>
	<label for="description">Описание карты</label>
	<form:input path="description"/>
    <label for="type">Тип карты</label>
    <form:select path="type">
        <c:forEach items="${cardTypes}" var="type">
            <form:option value="${type.name}">${type.description}</form:option>
        </c:forEach>
    </form:select>
	<input type="submit" value="Создать"/>	
</form:form>