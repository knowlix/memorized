<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/cards/add" var="cardAddUrl" />
<c:url value="/cards/remove" var="cardRemoveUrl" />

<h2>Управление картами</h2>

<div class="cardList_block left">
    <table>
        <thead>
            <tr>
                <th>№</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Тип карты</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ cards }" var="cardItem">
                <tr>
                    <td>${cardItem.cardId}</td>
                    <td>${cardItem.title}</td>
                    <td>${cardItem.description}</td>
                    <td>
                        <c:forEach items="${cardTypes}" var="type">
                            <c:if test="${type.name eq cardItem.type}">${type.description}</c:if>
                        </c:forEach>
                    </td>
                </tr>                
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="right" id="add_card_block">
    <h3>Новая карта</h3>
    <form:form id="form_card_add" method="POST" action="${cardAddUrl}" commandName="card" class="right">
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
</div>

<script type="text/javascript">
	$(".cardList li").bind("mouseenter", function() {
	    $(this).append("<span class='cardLinks'><a href='${cardRemoveUrl}?cardId=" + $(this).attr("rel") + "'>удалить</a></span>");
	});
	$(".cardList li").bind("mouseleave", function() {
	    $(".cardLinks").remove();
	});
</script>