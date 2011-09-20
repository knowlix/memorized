<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Сервис удобного обучения и накопления знаний.</h2>

<ul id="last-news">
  <c:forEach items="${news}" var="newsItem">
      <li>
        <h4><span class="date"><fmt:formatDate value="${ newsItem.created }" pattern="dd.MM.yyyy"/></span> - ${ newsItem.title }</h4>
        <p>${ newsItem.description }</p>
      </li>  
  </c:forEach>
</ul>

<div id="projectDescription">
    <p>Используя наш сервис, можно повысить эффективность изучения этого мира. Неважно, какая область знаний удостоилась вашего изучения. Создавайте новый раздел для каждой области знаний. В разделе фиксируйте полученные знания, а в комментариях пишите отправные точки, по которым сможете легко вспомнить данную тему изучения. Проверяйте себя в конце дня, просматривая и прокручивая в уме изученные тезисы. Для более детального восстановления знаний из памяти читайте комментарии. Полезно будет контролировать себя каждую неделю, вспоминая темы из случайной выборки.</p>
</div>