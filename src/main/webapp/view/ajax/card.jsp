<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card" id="card${cardWrapper.card.cardId}" style="position: relative; left: ${cardWrapper.left}px; top: ${cardWrapper.top}px;">
	<div class="title bg-blue">
		<h3>${cardWrapper.card.title}</h3>
		<blockquote>${cardWrapper.card.description}</blockquote>
	</div>
	<div class="tools">
		<ul class="horizontal left">
			<li class="selected">за сегодня</li>
			<li>за неделю</li>
			<li>все</li>
		</ul>
		<ul class="horizontal right">
			<li class="action_add_mema">добавить</li>
			<li>перемешать</li>
		</ul>
	</div>
	
	<form class="form_add_mema hidden" name="form_add_mema" method="POST" action="#">
		<input type="text" title="" value="" name="mema_title"/>
		<input type="submit" value=""/>
	</form>
	
	<dl class="memas">
		<% int i=0; %>
		<c:forEach items="${cardWrapper.card.memas}" var="mema">
			<dt><span class="num"><%= ++i %>.</span>${mema.title}</dt>
			<dd>${mema.description}</dd>
		</c:forEach>
	</dl>
	
</div>