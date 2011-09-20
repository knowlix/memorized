<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:useAttribute ignore="true" name="menuItem" id="currentItem"/>
<c:url value="/spaces/" var="spaceLink"/>
<c:url value="/cards/" var="cardsLink"/>

<!DOCTYPE html>
<html>
	<head>
	
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title><tiles:insertAttribute name="title"/> | Memorized</title>
		
		<tiles:useAttribute name="styles" classname="java.util.List" id="stylesList"/>
		<c:forEach items="${stylesList}" var="style">
			<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/${style}"/>"/>
		</c:forEach>
		
		<tiles:useAttribute name="scripts" classname="java.util.List" id="scriptsList"/>
		<c:forEach items="${scriptsList}" var="script">
			<script src="<c:url value="/static/js/${script}"/>"></script>
		</c:forEach>
		
	</head>
	<body>
		<div id="wrapper">
            <header>
                <a href="<c:url value="/"/>" class="logo" title="на главную"><img src="<c:url value="/static/img/logo.jpg"/>" alt="Memorized logotype" /></a>
                <ul class="main_menu horizontal">
                   <li <c:if test="${currentItem eq 'spaces'}">class="selected"</c:if>><a href="${spaceLink}" title="просмотреть пространство">Пространство</a></li>
                   <li <c:if test="${currentItem eq 'cards'}">class="selected"</c:if>><a href="${cardsLink}" title="детальное управление картами">Карты</a></li>
                   <li <c:if test="${currentItem eq 'blog'}">class="selected"</c:if>><a href="#" title="статьи по организации своего ума">Блог</a></li>
                </ul>
			    <tiles:insertAttribute name="userbar" />
            </header>
			<div id="content">
				<tiles:insertAttribute name="content"/>
			</div>
			<div id="push"></div>
		</div>
		<tiles:insertAttribute name="footer"/>
	</body>
</html>